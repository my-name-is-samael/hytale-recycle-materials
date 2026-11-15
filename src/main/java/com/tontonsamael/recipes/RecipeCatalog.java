package com.tontonsamael.recipes;

import com.hypixel.hytale.protocol.BenchType;
import com.hypixel.hytale.server.core.inventory.MaterialQuantity;
import com.tontonsamael.config.RecycleCategory;
import com.tontonsamael.config.RecycleMaterialsConfig;

import java.util.List;

/**
 * Every recipe the mod can add, as data. The families are generated from the material lists below,
 * so supporting a new material means adding one name to the matching list.
 */
final class RecipeCatalog {
    private RecipeCatalog() {
    }

    // ---- Materials ----------------------------------------------------------------------------------------------

    /** Wood families that have planks (Wood_X_Planks). */
    private static final List<String> PLANKS = List.of(
            "Wood_Blackwood", "Wood_Darkwood", "Wood_Deadwood", "Wood_Drywood", "Wood_Goldenwood", "Wood_Greenwood",
            "Wood_Hardwood", "Wood_Lightwood", "Wood_Redwood", "Wood_TropicalWood", "Wood_Softwood");

    /** Trunk species that miss the Full/Half/Stairs conversions in the base game. */
    private static final List<String> TRUNKS = List.of(
            "Amber", "Apple", "Ash", "Aspen", "Azure", "Bamboo", "Banyan", "Beech", "Birch", "Blackwood", "Bottletree",
            "Burnt", "Camphor", "Cedar", "Crystal", "Dry", "Fig_Blue", "Fir", "Fire", "Gumboab", "Ice", "Jungle", "Maple",
            "Oak", "Palm", "Palo", "Petrified", "Poisoned", "Sallow", "Spiral", "Stormbark", "Windwillow", "Wisteria");

    /** Rocks with a plain + Cobble variant, per family below. */
    private static final List<String> ROCKS_STAIRS = List.of(
            "Rock_Aqua", "Rock_Basalt", "Rock_Calcite", "Rock_Chalk", "Rock_Lime", "Rock_Marble", "Rock_Magma_Cooled",
            "Rock_Peach", "Rock_Quartzite", "Rock_Sandstone", "Rock_Sandstone_Red", "Rock_Sandstone_White", "Rock_Shale",
            "Rock_Slate", "Rock_Stone", "Rock_Volcanic");
    private static final List<String> ROCKS_SLABS = List.of(
            "Rock_Aqua", "Rock_Basalt", "Rock_Calcite", "Rock_Chalk", "Rock_Ledge", "Rock_Lime", "Rock_Magma_Cooled",
            "Rock_Marble", "Rock_Peach", "Rock_Quartzite", "Rock_Sandstone", "Rock_Sandstone_Red", "Rock_Sandstone_White",
            "Rock_Shale", "Rock_Slate", "Rock_Stone", "Rock_Stone_Mossy", "Rock_Volcanic");
    private static final List<String> ROCKS_BEAMS = List.of(
            "Rock_Aqua", "Rock_Basalt", "Rock_Calcite", "Rock_Chalk", "Rock_Lime", "Rock_Magma_Cooled", "Rock_Marble",
            "Rock_Peach", "Rock_Quartzite", "Rock_Runic_Blue", "Rock_Runic_Dark", "Rock_Runic_Teal", "Rock_Sandstone_Red",
            "Rock_Sandstone_White", "Rock_Shale", "Rock_Slate", "Rock_Stone", "Rock_Volcanic");
    private static final List<String> ROCKS_ROOFS = List.of(
            "Rock_Aqua", "Rock_Basalt", "Rock_Calcite", "Rock_Chalk", "Rock_Limestone", "Rock_Magma_Cooled", "Rock_Marble",
            "Rock_Quartzite", "Rock_Sandstone", "Rock_Sandstone_Red", "Rock_Sandstone_White", "Rock_Shale", "Rock_Slate",
            "Rock_Stone", "Rock_Volcanic");
    private static final List<String> ROCKS_WALLS = List.of(
            "Rock_Aqua", "Rock_Basalt", "Rock_Calcite", "Rock_Chalk", "Rock_Ledge", "Rock_Lime", "Rock_Magma_Cooled",
            "Rock_Marble", "Rock_Peach", "Rock_Quartzite", "Rock_Sandstone", "Rock_Sandstone_Red", "Rock_Sandstone_White",
            "Rock_Shale", "Rock_Slate", "Rock_Stone", "Rock_Volcanic");
    private static final List<String> ROCKS_PILLARS = List.of("Rock_Ledge_Cobble", "Rock_Lime_Cobble", "Rock_Peach_Cobble", "Rock_Runic_Cobble");

    /** Brick blocks, by family. */
    private static final List<String> BRICKS_ALL = List.of(
            "Rock_Aqua_Brick", "Rock_Basalt_Brick", "Rock_Calcite_Brick", "Rock_Chalk_Brick", "Rock_Gold_Brick",
            "Rock_Ledge_Brick", "Rock_Lime_Brick", "Rock_Magma_Cooled_Brick", "Rock_Marble_Brick", "Rock_Peach_Brick",
            "Rock_Quartzite_Brick", "Rock_Runic_Brick", "Rock_Runic_Blue_Brick", "Rock_Runic_Dark_Brick",
            "Rock_Runic_Teal_Brick", "Rock_Sandstone_Brick", "Rock_Sandstone_Red_Brick", "Rock_Sandstone_White_Brick",
            "Rock_Shale_Brick", "Rock_Slate_Brick", "Rock_Stone_Brick", "Rock_Volcanic_Brick");
    /** Bricks that also have a Smooth variant (slabs). */
    private static final List<String> BRICKS_WITH_SMOOTH = List.of(
            "Rock_Aqua_Brick", "Rock_Basalt_Brick", "Rock_Calcite_Brick", "Rock_Chalk_Brick", "Rock_Gold_Brick",
            "Rock_Ledge_Brick", "Rock_Lime_Brick", "Rock_Magma_Cooled_Brick", "Rock_Marble_Brick", "Rock_Quartzite_Brick",
            "Rock_Sandstone_Brick", "Rock_Sandstone_Red_Brick", "Rock_Sandstone_White_Brick", "Rock_Shale_Brick",
            "Rock_Slate_Brick", "Rock_Stone_Brick", "Rock_Volcanic_Brick");
    private static final List<String> BRICKS_PILLARS = List.of(
            "Rock_Aqua_Brick", "Rock_Basalt_Brick", "Rock_Calcite_Brick", "Rock_Chalk_Brick", "Rock_Gold_Brick",
            "Rock_Ledge_Brick", "Rock_Lime_Brick", "Rock_Magma_Cooled_Brick", "Rock_Marble_Brick", "Rock_Peach_Brick",
            "Rock_Quartzite_Brick", "Rock_Runic_Blue_Brick", "Rock_Runic_Dark_Brick", "Rock_Runic_Teal_Brick",
            "Rock_Sandstone_Brick", "Rock_Sandstone_Red_Brick", "Rock_Sandstone_White_Brick", "Rock_Shale_Brick",
            "Rock_Slate_Brick", "Rock_Stone_Brick", "Rock_Volcanic_Brick", "Soil_Clay_Ocean_Brick");
    private static final List<String> BRICKS_ROOFS = List.of(
            "Rock_Aqua_Brick", "Rock_Basalt_Brick", "Rock_Calcite_Brick", "Rock_Chalk_Brick", "Rock_Limestone_Brick",
            "Rock_Gold_Brick", "Rock_Magma_Cooled_Brick", "Rock_Marble_Brick", "Rock_Quartzite_Brick", "Rock_Sandstone_Brick",
            "Rock_Sandstone_Red_Brick", "Rock_Sandstone_White_Brick", "Rock_Shale_Brick", "Rock_Slate_Brick",
            "Rock_Stone_Brick", "Rock_Volcanic_Brick", "Soil_Clay_Ocean_Brick");
    private static final List<String> BRICKS_WALLS = List.of(
            "Rock_Aqua_Brick", "Rock_Basalt_Brick", "Rock_Calcite_Brick", "Rock_Chalk_Brick", "Rock_Gold_Brick",
            "Rock_Ledge_Brick", "Rock_Lime_Brick", "Rock_Magma_Cooled_Brick", "Rock_Marble_Brick", "Rock_Peach_Brick",
            "Rock_Quartzite_Brick", "Rock_Runic_Blue_Brick", "Rock_Runic_Dark_Brick", "Rock_Runic_Teal_Brick",
            "Rock_Sandstone_Brick", "Rock_Sandstone_Red_Brick", "Rock_Sandstone_White_Brick", "Rock_Shale_Brick",
            "Rock_Slate_Brick", "Rock_Stone_Brick", "Rock_Volcanic_Brick");
    /** Snow and clay bricks, shared by stairs/slabs/beams/walls. */
    private static final List<String> SOIL_BRICKS = List.of("Soil_Snow_Brick", "Soil_Clay_Brick", "Soil_Clay_Ocean_Brick");

    private static final List<String> ROOF_SHAPES = List.of("", "_Flat", "_Shallow", "_Steep");
    private static final List<String> CLOTH_ROOF_SHAPES = List.of("", "_Flat", "_Flap", "_Shallow", "_Steep", "_Vertical");
    private static final List<String> MODERN_ROOF_SHAPES = List.of("", "_Flat", "_Vertical_Flap", "_Shallow", "_Steep", "_Vertical");

    // ---- Entry point --------------------------------------------------------------------------------------------

    static RecipeSet build(RecycleMaterialsConfig config) {
        RecipeSet r = new RecipeSet(config.getCraftTimeSeconds());
        r.skipExistingRecipes(true);
        missingRecipes(config, r);
        r.skipExistingRecipes(false);
        if (config.isEnabled(RecycleCategory.SOIL)) soil(config, r);
        if (config.isEnabled(RecycleCategory.STAIRS)) stairs(r);
        if (config.isEnabled(RecycleCategory.SLABS)) slabs(r);
        if (config.isEnabled(RecycleCategory.BEAMS)) beams(r);
        if (config.isEnabled(RecycleCategory.PILLARS)) pillars(r);
        if (config.isEnabled(RecycleCategory.ROOFS)) roofs(config, r);
        if (config.isEnabled(RecycleCategory.FENCES)) fencesAndWalls(r);
        if (config.isEnabled(RecycleCategory.WINDOWS)) windows(r);
        if (config.isEnabled(RecycleCategory.SALT)) {
            r.add("Ingredient_Salt", null, 5, "Rock_Salt", 1, "Ingredients", BenchType.Crafting, "Cookingbench", 1f);
        }
        return r;
    }

    // ---- Recipes missing from the base game (always on) ---------------------------------------------------------

    private static void missingRecipes(RecycleMaterialsConfig config, RecipeSet r) {
        for (String trunk : TRUNKS) {
            String base = "Wood_" + trunk + "_Trunk";
            r.item(base, 1, base + "_Full", 1, "Wood");
            r.item(base + "_Full", 1, base, 1, "Wood");
            r.item(base + "_Full", 1, base + "_Stairs", 1, "Stairs");
            r.item(base + "_Full", 1, base + "_Half", 2, "HalfSlabs");
        }

        r.resource("Rock_Stone", 1, "Rock_Stone_Half", 2, "HalfSlabs");
        r.item("Rock_Calcite", 1, "Rock_Calcite_Cobble_Half", 2, "HalfSlabs");
        r.item("Rock_Calcite_Cobble", 1, "Rock_Calcite_Cobble_Half", 2, "HalfSlabs");
        r.resource("Rock_Volcanic", 1, "Rock_Volcanic_Half", 2, "HalfSlabs");

        // Chalk
        r.item("Rock_Chalk", 1, "Rock_Chalk_Cobble", 1, "Rocks");
        r.item("Rock_Chalk_Cobble", 1, "Rock_Chalk", 1, "Rocks");
        r.resource("Rock_Chalk", 1, "Rock_Chalk_Brick", 1, "Bricks");
        r.resource("Rock_Chalk", 1, "Rock_Chalk_Brick_Decorative", 1, "Bricks");
        brickVariants(r, "Rock_Chalk_Brick");
        r.resource("Rock_Chalk_Brick", 1, "Rock_Chalk_Brick_Stairs", 1, "Stairs");
        r.resource("Rock_Chalk_Brick", 1, "Rock_Chalk_Brick_Beam", 2, "Beam");

        // Cooled magma
        String magma = "Rock_Magma_Cooled";
        r.item(magma, 1, magma + "_Cobble", 1, "Rocks");
        r.item(magma + "_Cobble", 1, magma, 1, "Rocks");
        for (String v : List.of("", "_Decorative", "_Ornate", "_Smooth")) {
            r.item(magma, 1, magma + "_Brick" + v, 1, "Bricks");
        }
        r.item(magma, 1, magma + "_Half", 2, "HalfSlabs");
        r.item(magma, 1, magma + "_Cobble_Half", 2, "HalfSlabs");
        r.item(magma, 1, magma + "_Stairs", 1, "Stairs");
        r.item(magma, 1, magma + "_Cobble_Stairs", 1, "Stairs");
        r.item(magma, 1, magma + "_Beam", 2, "Beam");
        r.item(magma, 1, magma + "_Cobble_Beam", 2, "Beam");
        r.item(magma, 1, magma + "_Cobble_Wall", 2, "Wall");
        for (String shape : ROOF_SHAPES) {
            r.item(magma, 1, magma + "_Cobble_Roof" + shape, 1, "Roof");
        }
        r.item(magma + "_Cobble", 1, magma + "_Half", 1, "HalfSlabs");
        brickVariants(r, magma + "_Brick");

        // Runic
        String runic = "Rock_Runic";
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Stairs", 1, "Stairs");
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Half", 2, "HalfSlabs");
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Beam", 2, "Beam");
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Pillar_Base", 1, "Pillar");
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Pillar_Middle", 1, "Pillar");
        r.item(runic + "_Cobble", 1, runic + "_Cobble_Wall", 2, "Wall");
        r.item(runic + "_Cobble", 1, runic + "_Brick", 1, "Bricks");
        r.item(runic + "_Cobble", 1, runic + "_Brick_Ornate", 1, "Bricks");
        r.item(runic + "_Brick", 1, runic + "_Brick_Stairs", 1, "Stairs");
        r.item(runic + "_Brick", 1, runic + "_Brick_Half", 2, "HalfSlabs");
        r.item(runic + "_Brick", 1, runic + "_Brick_Ornate", 1, "Bricks");
        r.item(runic + "_Brick_Ornate", 1, runic + "_Brick", 1, "Bricks");
        for (String color : List.of("Blue", "Dark", "Teal")) {
            String brick = runic + "_" + color + "_Brick";
            r.item(runic + "_Cobble", 1, brick, 1, "Bricks");
            r.item(brick, 1, brick + "_Stairs", 1, "Stairs");
            r.item(brick, 1, brick + "_Half", 2, "HalfSlabs");
            r.item(brick, 1, brick + "_Beam", 2, "Beam");
            r.item(brick, 1, brick + "_Pillar_Base", 1, "Pillar");
            r.item(brick, 1, brick + "_Pillar_Middle", 1, "Pillar");
            r.item(brick, 1, brick + "_Wall", 2, "Wall");
        }

        // Snow
        r.item("Soil_Snow", 1, "Soil_Snow_Half", 2, "HalfSlabs");
        r.item("Soil_Snow", 1, "Soil_Snow_Brick", 1, "Bricks");
        for (String source : List.of("Soil_Snow", "Soil_Snow_Brick")) {
            r.item(source, 1, "Soil_Snow_Brick_Stairs", 1, "Stairs");
            r.item(source, 1, "Soil_Snow_Brick_Half", 2, "HalfSlabs");
            r.item(source, 1, "Soil_Snow_Brick_Beam", 2, "Beam");
            r.item(source, 1, "Soil_Snow_Brick_Wall", 2, "Wall");
        }

        // Leather -> roofs
        if (config.isEnabled(RecycleCategory.LEATHER_ROOFS)) {
            for (String shape : CLOTH_ROOF_SHAPES) {
                r.item("Ingredient_Leather_Light", 2, "Cloth_Roof_Hide" + shape, 1, "Roof");
                r.item("Ingredient_Leather_Medium", 1, "Cloth_Roof_Leather" + shape, 1, "Roof");
            }
        }

        // Wool -> modern cloth roofs
        modernClothRoofsFromWool(r, "Cloth_Block_Wool_Blue", "Cloth_Modern_Blue_Roof");
        modernClothRoofsFromWool(r, "Cloth_Block_Wool_Green", "Cloth_Modern_DarkGreen_Roof");
        modernClothRoofsFromWool(r, "Cloth_Block_Wool_Orange", "Cloth_Modern_Orange_Roof");
        modernClothRoofsFromWool(r, "Cloth_Block_Wool_Red", "Cloth_Modern_Red_Roof");
        modernClothRoofsFromWool(r, "Cloth_Block_Wool_Yellow", "Cloth_Modern_Yellow_Roof");
    }

    /** Brick, Decorative, Ornate and Smooth bricks can all be converted into each other. */
    private static void brickVariants(RecipeSet r, String brick) {
        List<String> variants = List.of("", "_Decorative", "_Ornate", "_Smooth");
        for (String from : variants) {
            for (String to : variants) {
                if (!from.equals(to)) r.item(brick + from, 1, brick + to, 1, "Bricks");
            }
        }
    }

    private static void modernClothRoofsFromWool(RecipeSet r, String wool, String roof) {
        for (String shape : MODERN_ROOF_SHAPES) {
            r.item(wool, 1, roof + shape, 1, "Roof");
        }
    }

    // ---- Soil ---------------------------------------------------------------------------------------------------

    /** {rubble, gravel} */
    private static final String[][] GRAVELS = {
            {"Rubble_Aqua", "Soil_Aqua_Gravel"}, {"Rubble_Basalt", "Soil_Basalt_Gravel"},
            {"Rubble_Calcite", "Soil_Calcite_Gravel"}, {"Rubble_Chalk", "Soil_Chalk_Gravel"},
            {"Rubble_Lime", "Soil_Gravel_Lime"}, {"Rubble_Magma_Cooled", "Soil_Magma_Cooled_Gravel"},
            {"Rubble_Marble", "Soil_Pebbles"}, {"Rubble_Quartzite", "Soil_Quartzite_Gravel"},
            {"Rubble_Shale", "Soil_Pebbles_Frozen"}, {"Rubble_Slate", "Soil_Slate_Gravel"},
            {"Rubble_Stone_Mossy", "Soil_Gravel_Mossy"}, {"Rubble_Volcanic", "Soil_Volcanic_Gravel"},
            {"Rubble_Sandstone", "Soil_Gravel_Sand"}, {"Rubble_Sandstone_Red", "Soil_Gravel_Sand_Red"},
            {"Rubble_Sandstone_White", "Soil_Gravel_Sand_White"}};

    /** {full block, half slab} */
    private static final String[][] SOIL_SLABS = {
            {"Soil_Aqua_Gravel", "Soil_Aqua_Gravel_Half"}, {"Soil_Basalt_Gravel", "Soil_Basalt_Gravel_Half"},
            {"Soil_Calcite_Gravel", "Soil_Calcite_Gravel_Half"}, {"Soil_Chalk_Gravel", "Soil_Chalk_Gravel_Half"},
            {"Soil_Gravel_Lime", "Soil_Lime_Gravel_Half"}, {"Soil_Magma_Cooled_Gravel", "Soil_Magma_Cooled_Gravel_Half"},
            {"Soil_Pebbles", "Soil_Marble_Gravel_Half"}, {"Soil_Quartzite_Gravel", "Soil_Quartzite_Gravel_Half"},
            {"Soil_Pebbles_Frozen", "Soil_Shale_Gravel_Half"}, {"Soil_Slate_Gravel", "Soil_Slate_Gravel_Half"},
            {"Soil_Gravel", "Soil_Gravel_Half"}, {"Soil_Gravel_Mossy", "Soil_Gravel_Mossy_Half"},
            {"Soil_Volcanic_Gravel", "Soil_Volcanic_Gravel_Half"}, {"Soil_Gravel_Sand", "Soil_Gravel_Sand_Half"},
            {"Soil_Gravel_Sand_Red", "Soil_Gravel_Sand_Red_Half"}, {"Soil_Gravel_Sand_White", "Soil_Gravel_Sand_White_Half"},
            {"Soil_Sand_White", "Soil_Sand_White_Path_Half"}};

    private static void soil(RecycleMaterialsConfig config, RecipeSet r) {
        // Pathway
        r.item("Soil_Pathway", 1, "Soil_Pathway_Half", 2, "Structural-Rock");
        r.item("Soil_Pathway", 1, "Soil_Pathway_Quarter", 4, "Structural-Rock");
        r.item("Soil_Pathway", 3, "Soil_Pathway_ThreeQuarter", 4, "Structural-Rock");

        // Rubble -> gravel
        for (String[] g : GRAVELS) {
            r.add(g[0], null, 3, g[1], 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f);
        }
        // Gravel -> slab
        for (String[] s : SOIL_SLABS) {
            r.item(s[0], 1, s[1], 2, "Structural-Rock");
        }

        if (config.isEnabled(RecycleCategory.SLABS)) {
            r.item("Soil_Pathway_Half", 2, "Soil_Pathway", 1, "Decorative");
            r.item("Soil_Pathway_Quarter", 4, "Soil_Pathway", 1, "Decorative");
            r.item("Soil_Pathway_ThreeQuarter", 4, "Soil_Pathway", 3, "Decorative");
            for (String[] s : SOIL_SLABS) {
                r.item(s[1], 2, s[0], 1, "Decorative");
            }
            r.item("Rock_Stone_Cobble_Mossy_Half", 2, "Rock_Stone_Mossy", 1, "Rocks");
            r.item("Rock_Stone_Cobble_Mossy_Half", 2, "Rock_Stone_Cobble_Mossy", 1, "Rocks");
        }

        // Mossy stone
        r.resource("Rock_Stone_Mossy", 1, "Rock_Stone", 1, "Rocks");
        r.resource("Rock_Stone_Mossy", 1, "Rock_Stone_Cobble", 1, "Rocks");
        for (String mossy : List.of("Rock_Stone_Mossy", "Rock_Stone_Cobble_Mossy")) {
            r.add(new MaterialQuantity[]{
                            new MaterialQuantity(null, "Rock_Stone", null, 1, null),
                            new MaterialQuantity(null, "Moss", null, 1, null)},
                    new MaterialQuantity[]{new MaterialQuantity(mossy, null, null, 1, null)},
                    "Decorative", BenchType.Crafting, "Farmingbench", 0f);
        }
    }

    // ---- Stairs -------------------------------------------------------------------------------------------------

    private static void stairs(RecipeSet r) {
        r.item("Wood_Oak_Trunk_Stairs", 1, "Wood_Oak_Trunk", 1, "Woods");
        for (String wood : PLANKS) {
            r.item(wood + "_Stairs", 1, wood + "_Planks", 1, "WoodPlanks");
        }
        for (String rock : ROCKS_STAIRS) {
            r.item(rock + "_Stairs", 1, rock, 1, "Rocks");
            r.item(rock + "_Stairs", 1, rock + "_Cobble", 1, "Cobbles");
            r.item(rock + "_Cobble_Stairs", 1, rock, 1, "Rocks");
            r.item(rock + "_Cobble_Stairs", 1, rock + "_Cobble", 1, "Cobbles");
        }
        r.item("Rock_Runic_Cobble_Stairs", 1, "Rock_Runic_Cobble", 1, "Cobbles");
        for (String brick : BRICKS_ALL) stair(r, brick);
        for (String brick : SOIL_BRICKS) stair(r, brick);
        // Gold and Ledge have no cobble; Clay bricks are already in SOIL_BRICKS.
    }

    private static void stair(RecipeSet r, String brick) {
        r.item(brick + "_Stairs", 1, brick, 1, "Bricks");
    }

    // ---- Slabs --------------------------------------------------------------------------------------------------

    private static void slab(RecipeSet r, String id, String category, boolean cobble, boolean smooth) {
        r.item(id + "_Half", 2, id, 1, category);
        if (cobble) {
            r.item(id + "_Half", 2, id + "_Cobble", 1, category);
            r.item(id + "_Cobble_Half", 2, id, 1, category);
            r.item(id + "_Cobble_Half", 2, id + "_Cobble", 1, category);
        }
        if (smooth) {
            r.item(id + "_Smooth_Half", 2, id + "_Smooth", 1, category);
            r.item(id + "_Smooth_Half", 2, id, 1, category);
        }
    }

    private static void slabs(RecipeSet r) {
        slab(r, "Wood_Oak_Trunk", "Woods", false, false);
        for (String wood : PLANKS) slab(r, wood + "_Planks", "WoodPlanks", false, false);
        for (String rock : ROCKS_SLABS) slab(r, rock, "Rocks", true, false);
        slab(r, "Rock_Runic_Cobble", "Rocks", false, false);
        for (String brick : BRICKS_ALL) slab(r, brick, "Bricks", false, BRICKS_WITH_SMOOTH.contains(brick));
        slab(r, "Soil_Snow", "Bricks", false, false);
        for (String brick : SOIL_BRICKS) slab(r, brick, "Bricks", false, false);
    }

    // ---- Beams --------------------------------------------------------------------------------------------------

    private static void beams(RecipeSet r) {
        for (String wood : PLANKS) {
            r.item(wood + "_Beam", 2, wood + "_Planks", 1, "WoodPlanks");
        }
        for (String rock : ROCKS_BEAMS) {
            r.item(rock + "_Beam", 2, rock, 1, "Rocks");
            r.item(rock + "_Beam", 2, rock + "_Cobble", 1, "Cobbles");
            r.item(rock + "_Cobble_Beam", 2, rock, 1, "Rocks");
            r.item(rock + "_Cobble_Beam", 2, rock + "_Cobble", 1, "Cobbles");
        }
        r.item("Rock_Runic_Cobble_Beam", 2, "Rock_Runic_Cobble", 1, "Cobbles");
        for (String brick : BRICKS_ALL) r.item(brick + "_Beam", 2, brick, 1, "Bricks");
        for (String brick : SOIL_BRICKS) r.item(brick + "_Beam", 2, brick, 1, "Bricks");
    }

    // ---- Pillars ------------------------------------------------------------------------------------------------

    private static void pillars(RecipeSet r) {
        for (String rock : ROCKS_PILLARS) pillar(r, rock, "Rocks");
        for (String brick : BRICKS_PILLARS) pillar(r, brick, "Bricks");
    }

    private static void pillar(RecipeSet r, String id, String category) {
        r.item(id + "_Pillar_Base", 1, id, 1, category);
        r.item(id + "_Pillar_Middle", 1, id, 1, category);
    }

    // ---- Roofs --------------------------------------------------------------------------------------------------

    private static void roofs(RecycleMaterialsConfig config, RecipeSet r) {
        for (String wood : List.of("Wood_Blackwood", "Wood_Darkwood", "Wood_Deadwood", "Wood_Drywood", "Wood_Goldenwood",
                "Wood_Greenwood", "Wood_Hardwood", "Wood_Lightwood", "Wood_Redwood", "Wood_Softwood", "Wood_TropicalWood")) {
            for (String shape : ROOF_SHAPES) {
                r.item(wood + "_Roof" + shape, 1, wood + "_Planks", 1, "WoodPlanks");
            }
        }
        for (String rock : ROCKS_ROOFS) {
            for (String shape : ROOF_SHAPES) {
                r.item(rock + "_Cobble_Roof" + shape, 1, rock, 1, "Rocks");
                r.item(rock + "_Cobble_Roof" + shape, 1, rock + "_Cobble", 1, "Cobbles");
            }
        }
        for (String brick : BRICKS_ROOFS) {
            for (String shape : ROOF_SHAPES) {
                r.item(brick + "_Roof" + shape, 1, brick, 1, "Bricks");
            }
        }

        if (config.isEnabled(RecycleCategory.LEATHER_ROOFS)) {
            clothRoof(r, "Cloth_Roof_Hide", "Ingredient_Leather_Light", 2, "Ingredients");
            clothRoof(r, "Cloth_Roof_Leather", "Ingredient_Leather_Medium", 1, "Ingredients");
        }
        for (String color : List.of("Blue", "Green", "Orange", "Red", "White", "Yellow")) {
            clothRoof(r, "Cloth_Roof_" + color, "Cloth_Block_Wool_" + color, 1, "Furniture_Textiles");
        }
        for (String[] modern : new String[][]{
                {"Cloth_Modern_Blue_Roof", "Cloth_Block_Wool_Blue"}, {"Cloth_Modern_DarkGreen_Roof", "Cloth_Block_Wool_Green"},
                {"Cloth_Modern_Orange_Roof", "Cloth_Block_Wool_Orange"}, {"Cloth_Modern_Red_Roof", "Cloth_Block_Wool_Red"},
                {"Cloth_Modern_Yellow_Roof", "Cloth_Block_Wool_Yellow"}}) {
            for (String shape : MODERN_ROOF_SHAPES) {
                r.item(modern[0] + shape, 1, modern[1], 1, "Furniture_Textiles");
            }
        }
    }

    private static void clothRoof(RecipeSet r, String roof, String output, int outputQty, String category) {
        for (String shape : CLOTH_ROOF_SHAPES) {
            r.item(roof + shape, 1, output, outputQty, category);
        }
    }

    // ---- Fences and walls ---------------------------------------------------------------------------------------

    private static void fencesAndWalls(RecipeSet r) {
        for (String wood : List.of("Wood_Blackwood", "Wood_Darkwood", "Wood_Deadwood", "Wood_Drywood", "Wood_Goldenwood",
                "Wood_Greenwood", "Wood_Hardwood", "Wood_Lightwood", "Wood_Redwood", "Wood_Softwood", "Wood_TropicalWood")) {
            r.item(wood + "_Fence", 2, wood + "_Planks", 1, "WoodPlanks");
            r.item(wood + "_Fence_Gate", 1, wood + "_Planks", 1, "WoodPlanks");
        }
        for (String rock : ROCKS_WALLS) {
            r.item(rock + "_Cobble_Wall", 2, rock, 1, "Rocks");
            r.item(rock + "_Cobble_Wall", 2, rock + "_Cobble", 1, "Cobbles");
        }
        r.item("Rock_Runic_Cobble_Wall", 2, "Rock_Runic_Cobble", 1, "Cobbles");
        for (String brick : BRICKS_WALLS) r.item(brick + "_Wall", 2, brick, 1, "Bricks");
        for (String brick : SOIL_BRICKS) r.item(brick + "_Wall", 2, brick, 1, "Bricks");
    }

    // ---- Windows ------------------------------------------------------------------------------------------------

    /** {window, material, category} */
    private static final String[][] WINDOWS = {
            {"Furniture_Ancient_Window", "Wood_Blackwood_Planks", "WoodPlanks"},
            {"Furniture_Ancient_Window", "Wood_Deadwood_Planks", "WoodPlanks"},
            {"Furniture_Crude_Window", "Wood_Lightwood_Planks", "WoodPlanks"},
            {"Furniture_Crude_Window", "Wood_Softwood_Planks", "WoodPlanks"},
            {"Furniture_Desert_Window", "Wood_Drywood_Planks", "WoodPlanks"},
            {"Furniture_Desert_Window", "Wood_Goldenwood_Planks", "WoodPlanks"},
            {"Furniture_Frozen_Castle_Window", "Wood_Ice_Trunk", "Woods"},
            {"Furniture_Jungle_Window", "Wood_TropicalWood_Planks", "WoodPlanks"},
            {"Furniture_Lumberjack_Window", "Wood_Redwood_Planks", "WoodPlanks"},
            {"Furniture_Tavern_Window", "Wood_Darkwood_Planks", "WoodPlanks"},
            {"Furniture_Village_Window", "Wood_Hardwood_Planks", "WoodPlanks"},
            {"Furniture_Temple_Dark_Window", "Rock_Shale_Cobble", "Rocks"},
            {"Furniture_Temple_Emerald_Window", "Rock_Quartzite_Cobble", "Rocks"},
            {"Furniture_Temple_Light_Window", "Rock_Marble_Cobble", "Rocks"},
            {"Furniture_Temple_Wind_Window", "Rock_Sandstone_Cobble", "Rocks"}};

    private static void windows(RecipeSet r) {
        for (String[] w : WINDOWS) {
            r.item(w[0], 2, w[1], 1, w[2]);
        }
    }
}
