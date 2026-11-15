package com.tontonsamael.config;

/**
 * One toggle per recipe family. {@link #key} is the JSON key in baseConfig.json (kept stable for existing servers),
 * {@link #lang} is the suffix of the translation keys (recyclematerials.ui.<lang> and recyclematerials.ui.<lang>.desc).
 */
public enum RecycleCategory {
    SLABS("SlabsToBlocks", "slabs", Group.STRUCTURE),
    STAIRS("StairsToBlocks", "stairs", Group.STRUCTURE),
    PILLARS("PillarsToBlocks", "pillars", Group.STRUCTURE),
    BEAMS("BeamsToBlocks", "beams", Group.STRUCTURE),
    ROOFS("RoofsToBlocks", "roofs", Group.STRUCTURE),
    FENCES("FencesToBlocks", "fences", Group.STRUCTURE),
    WINDOWS("WindowsToBlocks", "windows", Group.STRUCTURE),
    SOIL("SoilCrafts", "soil", Group.MATERIALS),
    SALT("SaltToBlocks", "salt", Group.MATERIALS),
    LEATHER_ROOFS("LeatherToRoofs", "leatherRoofs", Group.MATERIALS);

    public enum Group {
        STRUCTURE("structure"), MATERIALS("materials");

        public final String lang;

        Group(String lang) {
            this.lang = lang;
        }
    }

    public final String key;
    public final String lang;
    public final Group group;

    RecycleCategory(String key, String lang, Group group) {
        this.key = key;
        this.lang = lang;
        this.group = group;
    }
}
