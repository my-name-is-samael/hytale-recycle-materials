package com.tontonsamael;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.tontonsamael.commands.RecycleMaterialsCommand;
import com.tontonsamael.config.RecycleMaterialsConfig;
import com.tontonsamael.recipes.RecipeManager;

import javax.annotation.Nonnull;

public class RecycleMaterials extends JavaPlugin {
    private static RecycleMaterials INSTANCE;

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<RecycleMaterialsConfig> config = this.withConfig("baseConfig", RecycleMaterialsConfig.CODEC);

    public RecycleMaterials(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    @Override
    protected void setup() {
        config.save(); // writes the defaults on first run
        this.getCommandRegistry().registerCommand(new RecycleMaterialsCommand());    }

    @Override
    protected void start() {
        // Started once every asset pack is loaded, so recipes can be checked against the real item list.
        RecipeManager.reload(config.get());
        LOGGER.atInfo().log("RecycleMaterials loaded !");
    }

    public static RecycleMaterials get() {
        return INSTANCE;
    }

    public RecycleMaterialsConfig getConfig() {
        return config.get();
    }

    /** Persists the config and swaps the registered recipes live, no restart needed. */
    public void applyConfigChange() {
        config.save();
        RecipeManager.reloadAsync(config.get());
    }
}
