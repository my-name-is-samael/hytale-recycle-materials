package com.tontonsamael.recipes;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.BenchRequirement;
import com.hypixel.hytale.protocol.BenchType;
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.asset.type.item.config.ResourceType;
import com.hypixel.hytale.server.core.inventory.MaterialQuantity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Collects generated recipes. A recipe that references an item or resource type unknown to the running game
 * is skipped (and reported once) instead of being registered with a dangling id.
 */
final class RecipeSet {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private static final String ID_PREFIX = "RecycleMaterials";
    private static final Field RECIPE_ID = findIdField();

    private final Map<String, CraftingRecipe> recipes = new LinkedHashMap<>();
    private final Set<String> missing = new TreeSet<>();
    private final float defaultCraftTime;

    /** Recipes already provided by the game or other mods, keyed by what they take, give and where. Built lazily. */
    private Set<String> existing;
    private boolean skipExisting = false;
    private final Set<String> duplicates = new TreeSet<>();

    /**
     * While enabled, a recipe the game (or another mod) already provides on the same bench with the same inputs and
     * output is not added again. Used for the gap-filling recipes, which the game may have added since.
     */
    void skipExistingRecipes(boolean enabled) {
        this.skipExisting = enabled;
    }

    private static String signature(MaterialQuantity[] inputs, MaterialQuantity[] outputs, String benchType, String benchId) {
        List<String> in = new ArrayList<>();
        for (MaterialQuantity m : inputs) {
            in.add((m.getItemId() != null ? "i:" + m.getItemId() : "r:" + m.getResourceTypeId()) + "*" + m.getQuantity());
        }
        java.util.Collections.sort(in);
        List<String> out = new ArrayList<>();
        for (MaterialQuantity m : outputs) out.add(m.getItemId() + "*" + m.getQuantity());
        return String.join(",", in) + ">" + String.join(",", out) + "@" + benchType + ":" + benchId;
    }

    private Set<String> existingRecipes() {
        if (existing == null) {
            existing = new java.util.HashSet<>();
            for (CraftingRecipe recipe : CraftingRecipe.getAssetMap().getAssetMap().values()) {
                if (recipe.getId().startsWith(ID_PREFIX + "-") || recipe.getInput() == null || recipe.getOutputs() == null
                        || recipe.getBenchRequirement() == null) {
                    continue;
                }
                for (BenchRequirement bench : recipe.getBenchRequirement()) {
                    existing.add(signature(recipe.getInput(), recipe.getOutputs(), String.valueOf(bench.type), bench.id));
                }
            }
        }
        return existing;
    }

    /** @param defaultCraftTime craft time of every recipe that does not ask for its own (those declared with 0 s) */
    RecipeSet(float defaultCraftTime) {
        this.defaultCraftTime = defaultCraftTime;
    }

    private static Field findIdField() {
        try {
            Field field = CraftingRecipe.class.getDeclaredField("id");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("CraftingRecipe.id not found: the game API changed", e);
        }
    }

    /** Item (or resource type) to item, on the Builders bench, instant. */
    void add(String inputId, String inputResourceId, int inputQty, String outputId, int outputQty, String category) {
        add(inputId, inputResourceId, inputQty, outputId, outputQty, category, BenchType.StructuralCrafting, "Builders", 0f);
    }

    void add(String inputId, String inputResourceId, int inputQty, String outputId, int outputQty, String category,
             BenchType benchType, String benchId, float timeSeconds) {
        MaterialQuantity input = new MaterialQuantity(inputId, inputResourceId, null, inputQty, null);
        MaterialQuantity output = new MaterialQuantity(outputId, null, null, outputQty, null);
        add(new MaterialQuantity[]{input}, new MaterialQuantity[]{output}, category, benchType, benchId, timeSeconds);
    }

    /** Shorthand for item to item on the Builders bench. */
    void item(String inputId, int inputQty, String outputId, int outputQty, String category) {
        add(inputId, null, inputQty, outputId, outputQty, category);
    }

    /** Shorthand for resource type to item on the Builders bench. */
    void resource(String resourceId, int inputQty, String outputId, int outputQty, String category) {
        add(null, resourceId, inputQty, outputId, outputQty, category);
    }

    void add(MaterialQuantity[] inputs, MaterialQuantity[] outputs, String category, BenchType benchType, String benchId, float timeSeconds) {
        boolean valid = true;
        for (MaterialQuantity m : inputs) valid &= check(m);
        for (MaterialQuantity m : outputs) valid &= check(m);
        if (!valid) return;

        String signature = signature(inputs, outputs, String.valueOf(benchType), benchId);
        if (existingRecipes().contains(signature)) {
            if (skipExisting) {
                duplicates.add(signature);
                return;
            }
            overlapping++;
        }

        BenchRequirement bench = new BenchRequirement(benchType, benchId, new String[]{category}, 0, null);
        CraftingRecipe recipe = new CraftingRecipe(inputs, outputs[0], outputs, outputs[0].getQuantity(), new BenchRequirement[]{bench}, timeSeconds == 0f ? defaultCraftTime : timeSeconds, false, 0);

        List<String> in = new ArrayList<>();
        for (MaterialQuantity m : inputs) in.add(m.getItemId() != null ? m.getItemId() : "res_" + m.getResourceTypeId());
        List<String> out = new ArrayList<>();
        for (MaterialQuantity m : outputs) out.add(m.getItemId());
        String id = ID_PREFIX + "-" + String.join("+", in) + "-" + String.join("+", out);
        try {
            RECIPE_ID.set(recipe, id);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        recipes.put(id, recipe);
    }

    private boolean check(MaterialQuantity m) {
        if (m.getItemId() != null && Item.getAssetMap().getAsset(m.getItemId()) == null) {
            missing.add("item:" + m.getItemId());
            return false;
        }
        if (m.getResourceTypeId() != null && ResourceType.getAssetMap().getAsset(m.getResourceTypeId()) == null) {
            missing.add("resource:" + m.getResourceTypeId());
            return false;
        }
        return true;
    }

    List<CraftingRecipe> recipes() {
        return new ArrayList<>(recipes.values());
    }

    int size() {
        return recipes.size();
    }

    /** Recycling recipes (kept on purpose) that the game already offers as well. */
    private int overlapping = 0;

    int duplicateCount() {
        return duplicates.size();
    }

    String existingSummary() {
        return existingRecipes().size() + " existing recipes indexed, " + duplicates.size()
                + " gap-filling left out, " + overlapping + " recycling recipes overlap the game's";
    }

    void reportMissing() {
        if (!duplicates.isEmpty()) {
            LOGGER.atInfo().log("Left out %d gap-filling recipes the game already provides: %s", duplicates.size(), String.join(" | ", duplicates));
        }
        if (!missing.isEmpty()) {
            LOGGER.atWarning().log("Skipped recipes referencing unknown items (%d): %s", missing.size(), String.join(", ", missing));
        }
    }
}
