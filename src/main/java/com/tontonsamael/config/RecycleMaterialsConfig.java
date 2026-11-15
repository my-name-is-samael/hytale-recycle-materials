package com.tontonsamael.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

import java.util.EnumSet;
import java.util.Set;

public class RecycleMaterialsConfig {
    public static final BuilderCodec<RecycleMaterialsConfig> CODEC = buildCodec();

    private static BuilderCodec<RecycleMaterialsConfig> buildCodec() {
        var builder = BuilderCodec.builder(RecycleMaterialsConfig.class, RecycleMaterialsConfig::new);
        for (RecycleCategory category : RecycleCategory.values()) {
            builder.append(new KeyedCodec<>(category.key, Codec.BOOLEAN),
                    (config, value) -> config.setEnabled(category, value),
                    config -> config.isEnabled(category)).add();
        }
        builder.append(new KeyedCodec<>("CraftTimeTenths", Codec.INTEGER),
                (config, value) -> config.setCraftTimeTenths(value),
                config -> config.craftTimeTenths).add();
        return builder.build();
    }

    public static final int MAX_CRAFT_TIME_TENTHS = 10;

    // Every category is enabled by default, so keys missing from an older baseConfig.json stay on.
    private final Set<RecycleCategory> enabled = EnumSet.allOf(RecycleCategory.class);

    // Craft time of every recipe, in tenths of a second (0 to 10), kept as an integer to avoid float noise.
    private int craftTimeTenths = 1;

    public int getCraftTimeTenths() {
        return craftTimeTenths;
    }

    public void setCraftTimeTenths(int tenths) {
        this.craftTimeTenths = Math.max(0, Math.min(MAX_CRAFT_TIME_TENTHS, tenths));
    }

    public float getCraftTimeSeconds() {
        return craftTimeTenths / 10f;
    }

    public boolean isEnabled(RecycleCategory category) {
        return enabled.contains(category);
    }

    public void setEnabled(RecycleCategory category, boolean value) {
        if (value) enabled.add(category);
        else enabled.remove(category);
    }

    public void toggle(RecycleCategory category) {
        setEnabled(category, !isEnabled(category));
    }

    /** Independent snapshot, safe to hand to another thread. */
    public RecycleMaterialsConfig copy() {
        RecycleMaterialsConfig copy = new RecycleMaterialsConfig();
        copy.enabled.clear();
        copy.enabled.addAll(enabled);
        copy.craftTimeTenths = craftTimeTenths;
        return copy;
    }

    public void apply(RecyclePreset preset) {
        for (RecycleCategory category : RecycleCategory.values()) {
            setEnabled(category, preset.includes(category));
        }
    }
}
