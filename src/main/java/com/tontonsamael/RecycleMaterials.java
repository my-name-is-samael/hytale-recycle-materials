package com.tontonsamael;

import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.tontonsamael.commands.RecycleMaterialsCommand;
import com.tontonsamael.config.RecycleMaterialsConfig;
import com.tontonsamael.event.RecycleMaterialsRecipesLoaded;

import javax.annotation.Nonnull;

public class RecycleMaterials extends JavaPlugin {
    private static RecycleMaterials INSTANCE;

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<RecycleMaterialsConfig> config = this.withConfig("baseConfig", RecycleMaterialsConfig.CODEC);

    public RecycleMaterials(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    private RecycleMaterialsConfig bootConfig;
    @Override
    protected void setup() {
        saveConfig();
        bootConfig = config.get().clone();

        this.getCommandRegistry().registerCommand(new RecycleMaterialsCommand());

        this.getEventRegistry().registerGlobal(LoadedAssetsEvent.class, RecycleMaterialsRecipesLoaded::onRecipeLoad);

        LOGGER.atInfo().log("RecycleMaterials loaded !");
    }

    public static RecycleMaterials get() {
        return INSTANCE;
    }

    public RecycleMaterialsConfig getConfig() {
        return config.get();
    }
    public void saveConfig() {
        config.save();
    }
    public RecycleMaterialsConfig getBootConfig() {
        return this.bootConfig;
    }
}