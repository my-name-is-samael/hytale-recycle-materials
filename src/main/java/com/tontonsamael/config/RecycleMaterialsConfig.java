package com.tontonsamael.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

import java.util.Objects;

public class RecycleMaterialsConfig implements Cloneable {
    public static final BuilderCodec<RecycleMaterialsConfig> CODEC = BuilderCodec.builder(RecycleMaterialsConfig.class, RecycleMaterialsConfig::new)
            .append(new KeyedCodec<>("SoilCrafts", Codec.BOOLEAN),
                    (config, value) -> config.soil = value,
                    (config) -> config.soil
            ).add()
            .append(new KeyedCodec<>("SlabsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.slabs = value,
                    (config) -> config.slabs
            ).add()
            .append(new KeyedCodec<>("StairsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.stairs = value,
                    (config) -> config.stairs
            ).add()
            .append(new KeyedCodec<>("PillarsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.pillars = value,
                    (config) -> config.pillars
            ).add()
            .append(new KeyedCodec<>("BeamsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.beams = value,
                    (config) -> config.beams
            ).add()
            .append(new KeyedCodec<>("RoofsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.roofs = value,
                    (config) -> config.roofs
            ).add()
            .append(new KeyedCodec<>("FencesToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.fences = value,
                    (config) -> config.fences
            ).add()
            .append(new KeyedCodec<>("WindowsToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.windows = value,
                    (config) -> config.windows
            ).add()
            .append(new KeyedCodec<>("SaltToBlocks", Codec.BOOLEAN),
                    (config, value) -> config.salt = value,
                    (config) -> config.salt
            ).add()
            .append(new KeyedCodec<>("LeatherToRoofs", Codec.BOOLEAN),
                    (config, value) -> config.leatherRoofs = value,
                    (config) -> config.leatherRoofs
            ).add()
            .build();

    private boolean soil = true;
    private boolean slabs = true;
    private boolean stairs = true;
    private boolean pillars = true;
    private boolean beams = true;
    private boolean roofs = true;
    private boolean fences = true;
    private boolean windows = true;
    private boolean salt = true;
    private boolean leatherRoofs = true;

    public RecycleMaterialsConfig() {
    }

    public boolean isSoil() {
        return soil;
    }

    public void setSoil(boolean soil) {
        this.soil = soil;
    }

    public boolean isSlabs() {
        return slabs;
    }

    public void setSlabs(boolean slabs) {
        this.slabs = slabs;
    }

    public boolean isStairs() {
        return stairs;
    }

    public void setStairs(boolean stairs) {
        this.stairs = stairs;
    }

    public boolean isPillars() {
        return pillars;
    }

    public void setPillars(boolean pillars) {
        this.pillars = pillars;
    }

    public boolean isBeams() {
        return beams;
    }

    public void setBeams(boolean beams) {
        this.beams = beams;
    }

    public boolean isRoofs() {
        return roofs;
    }

    public void setRoofs(boolean roofs) {
        this.roofs = roofs;
    }

    public boolean isFences() {
        return fences;
    }

    public void setFences(boolean fences) {
        this.fences = fences;
    }

    public boolean isWindows() {
        return windows;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public boolean isSalt() {
        return salt;
    }

    public void setSalt(boolean salt) {
        this.salt = salt;
    }

    public boolean isLeatherRoofs() {
        return leatherRoofs;
    }

    public void setLeatherRoofs(boolean leatherRoofs) {
        this.leatherRoofs = leatherRoofs;
    }

    public int hashCode() {
        return Objects.hash(soil, slabs, stairs, pillars, beams, roofs, fences, windows, salt, leatherRoofs);
    }

    public boolean equals(RecycleMaterialsConfig other) {
        return this.hashCode() == other.hashCode();
    }

    public RecycleMaterialsConfig clone() {
        try {
            return (RecycleMaterialsConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
