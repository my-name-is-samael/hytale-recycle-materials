package com.tontonsamael.config;

import java.util.EnumSet;
import java.util.Set;

import static com.tontonsamael.config.RecycleCategory.*;

/** Ready-made sets of enabled categories, applied in one click from the settings window. */
public enum RecyclePreset {
    FULL("full", EnumSet.allOf(RecycleCategory.class)),
    BUILDING("building", EnumSet.of(SLABS, STAIRS, PILLARS, BEAMS, ROOFS, FENCES, WINDOWS)),
    MINIMAL("minimal", EnumSet.of(SLABS, STAIRS)),
    NONE("none", EnumSet.noneOf(RecycleCategory.class));

    public final String lang;
    private final Set<RecycleCategory> enabled;

    RecyclePreset(String lang, Set<RecycleCategory> enabled) {
        this.lang = lang;
        this.enabled = enabled;
    }

    public boolean includes(RecycleCategory category) {
        return enabled.contains(category);
    }

    /** True when the given config has exactly this preset's categories enabled. */
    public boolean matches(RecycleMaterialsConfig config) {
        for (RecycleCategory c : RecycleCategory.values()) {
            if (config.isEnabled(c) != includes(c)) return false;
        }
        return true;
    }
}
