package com.tontonsamael.recipes;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.tontonsamael.config.RecycleMaterialsConfig;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/** Registers the generated recipes in the game's recipe store, and swaps them live when the config changes. */
public final class RecipeManager {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private static final String PACK_KEY = "RecycleMaterials:Crafts";

    // Replacing recipes takes the global asset lock for writing. It must never be requested from the world thread
    // (a UI click), which would freeze the whole server: reloads run on this single background thread, in order.
    private static final ExecutorService WORKER = Executors.newSingleThreadExecutor(runnable -> {
        Thread thread = new Thread(runnable, "RecycleMaterials-Recipes");
        thread.setDaemon(true);
        return thread;
    });

    /** Newest config waiting for a reload. Several quick changes (a dragged slider) collapse into one reload. */
    private static final AtomicReference<RecycleMaterialsConfig> PENDING = new AtomicReference<>();

    private static boolean registered = false;

    private RecipeManager() {
    }

    /** Generates and registers the recipes on the calling thread. Used at startup, outside the world thread. */
    public static synchronized void reload(RecycleMaterialsConfig config) {
        long start = System.nanoTime();
        RecipeSet set = RecipeCatalog.build(config);
        set.reportMissing();
        LOGGER.atInfo().log("Recipe check: %s", set.existingSummary());
        List<CraftingRecipe> recipes = set.recipes();

        var store = CraftingRecipe.getAssetStore();
        if (registered) {
            store.removeAssetPack(PACK_KEY);
        }
        store.loadAssets(PACK_KEY, recipes);
        registered = true;

        LOGGER.atInfo().log("Recipes registered: %d (%d ms), craft time %.1f s, bench shows %s",
                recipes.size(), (System.nanoTime() - start) / 1_000_000, config.getCraftTimeSeconds(), benchSample(recipes));
    }

    /** Craft time of one of our recipes as the Builders bench itself would serve it, to check the swap reached the bench. */
    private static String benchSample(List<CraftingRecipe> recipes) {
        if (recipes.isEmpty()) return "no recipe";
        String id = recipes.get(0).getId();
        var bench = com.hypixel.hytale.builtin.crafting.CraftingPlugin.getBenchRecipes(
                com.hypixel.hytale.protocol.BenchType.StructuralCrafting, "Builders");
        for (CraftingRecipe recipe : bench) {
            if (recipe.getId().equals(id)) return id + "=" + recipe.getTimeSeconds() + " s";
        }
        return id + " missing from bench";
    }

    /** Same as {@link #reload} but on the background thread, with a snapshot of the config. Safe from any thread. */
    public static void reloadAsync(RecycleMaterialsConfig config) {
        if (PENDING.getAndSet(config.copy()) != null) {
            return; // a reload is already queued and will pick up this newer snapshot
        }
        WORKER.execute(() -> {
            try {
                Thread.sleep(300); // let a burst of changes settle, they collapse into this one reload
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            RecycleMaterialsConfig snapshot = PENDING.getAndSet(null);
            try {
                reload(snapshot);
            } catch (Throwable t) {
                LOGGER.atSevere().withCause(t).log("Failed to reload recipes");
            }
        });
    }
}
