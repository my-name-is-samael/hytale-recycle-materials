package com.tontonsamael.event;

import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent;
import com.hypixel.hytale.assetstore.map.DefaultAssetMap;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.BenchRequirement;
import com.hypixel.hytale.protocol.BenchType;
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.hypixel.hytale.server.core.inventory.MaterialQuantity;
import com.tontonsamael.RecycleMaterials;
import com.tontonsamael.config.RecycleMaterialsConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class RecycleMaterialsRecipesLoaded {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private static final String RECIPE_PREFIX_ID = "RecycleMaterials";

    private static Field _recipeId;

    private static CraftingRecipe generateRecipe(String inputId, String inputResourceId, int inputQuantity, String outputId, int outputQuantity, String category, BenchType benchType, String benchId, float timeSeconds) {
        BenchRequirement bench = new BenchRequirement(benchType, benchId, new String[]{category}, 0);
        MaterialQuantity input = new MaterialQuantity(inputId, inputResourceId, null, inputQuantity, null);
        MaterialQuantity output = new MaterialQuantity(outputId, null, null, outputQuantity, null);
        CraftingRecipe recipe = new CraftingRecipe(
                new MaterialQuantity[]{input},
                output, new MaterialQuantity[]{output},
                output.getQuantity(), new BenchRequirement[]{bench},
                timeSeconds, false, 0);
        try {
            _recipeId.set(recipe, String.format("%s-%s-%s-%s",
                    RECIPE_PREFIX_ID, inputId != null ? "id" : "res",
                    inputId != null ? inputId : inputResourceId, outputId));
        } catch (IllegalAccessException e) {
            LOGGER.atSevere().log("Failed to write recipe ID: %s", e.getMessage());
            assert false;
        }
        return recipe;
    }

    private static CraftingRecipe generateRecipe(String inputId, String inputResourceId, int inputQuantity, String outputId, int outputQuantity, String category) {
        return generateRecipe(inputId, inputResourceId, inputQuantity, outputId, outputQuantity, category, BenchType.StructuralCrafting, "Builders", 0f);
    }

    private static void loadMissingRecipes(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Wood_Oak_Trunk
        recipes.add(generateRecipe("Wood_Oak_Trunk", null, 1,
                "Wood_Oak_Trunk_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Wood_Oak_Trunk_Full", null, 1,
                "Wood_Oak_Trunk_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Wood_Oak_Trunk", null, 1,
                "Wood_Oak_Trunk_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Wood_Oak_Trunk_Full", null, 1,
                "Wood_Oak_Trunk_Half", 2, "HalfSlabs"));

        // Rock_Calcite
        recipes.add(generateRecipe("Rock_Calcite", null, 1,
                "Rock_Calcite_Cobble_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Calcite_Cobble", null, 1,
                "Rock_Calcite_Cobble_Half", 2, "HalfSlabs"));

        // Rock_Chalk_Brick
        recipes.add(generateRecipe("Rock_Chalk", null, 1,
                "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Decorative", null, 1,
                "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk", null, 1,
                "Rock_Chalk_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick", null, 1,
                "Rock_Chalk_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe(null, "Rock_Chalk_Brick", 1,
                "Rock_Chalk_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe(null, "Rock_Chalk_Brick_Decorative", 1,
                "Rock_Chalk_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe(null, "Rock_Chalk_Brick", 1,
                "Rock_Chalk_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe(null, "Rock_Chalk_Brick_Decorative", 1,
                "Rock_Chalk_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1,
                "Rock_Chalk_Brick_Wall", 2, "Wall"));

        // Rock_Runic_Cobble
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Cobble_Wall", 1, "Wall"));

        // Rock_Runic_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Brick", null, 1,
                "Rock_Runic_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Brick", null, 1,
                "Rock_Runic_Brick_Half", 2, "HalfSlabs"));


        // Rock_Runic_Blue_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Blue_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1,
                "Rock_Runic_Blue_Brick_Wall", 1, "Wall"));

        // Rock_Runic_Dark_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Dark_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1,
                "Rock_Runic_Dark_Brick_Wall", 1, "Wall"));

        // Rock_Runic_Teal_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1,
                "Rock_Runic_Teal_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1,
                "Rock_Runic_Teal_Brick_Wall", 1, "Wall"));

        // Rock_Volcanic
        recipes.add(generateRecipe(null, "Rock_Volcanic", 1,
                "Rock_Volcanic_Half", 2, "HalfSlabs"));

        // Soil_Snow
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Soil_Snow", null, 1,
                "Soil_Snow_Brick_Wall", 2, "Wall"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1,
                "Soil_Snow_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1,
                "Soil_Snow_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1,
                "Soil_Snow_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1,
                "Soil_Snow_Brick_Wall", 2, "Wall"));
    }

    private static void loadSoil(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Pathway
        recipes.add(generateRecipe("Soil_Pathway", null, 1,
                "Soil_Pathway_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pathway", null, 1,
                "Soil_Pathway_Quarter", 4, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pathway", null, 3,
                "Soil_Pathway_ThreeQuarter", 4, "Structural-Rock"));

        // Gravel
        recipes.add(generateRecipe("Soil_Gravel", null, 1,
                "Soil_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Mossy", null, 1,
                "Soil_Gravel_Mossy_Half", 2, "Structural-Rock"));

        // Sand
        recipes.add(generateRecipe("Soil_Gravel_Sand", null, 1,
                "Soil_Gravel_Sand_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Sand_Red", null, 1,
                "Soil_Gravel_Sand_Red_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Sand_White", null, 1,
                "Soil_Gravel_Sand_White_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Sand_White", null, 1,
                "Soil_Sand_White_Path_Half", 2, "Structural-Rock"));

        if (conf.isSlabs()) {
            // Pathway
            recipes.add(generateRecipe("Soil_Pathway_Half", null, 2,
                    "Soil_Pathway", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Pathway_Quarter", null, 4,
                    "Soil_Pathway", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Pathway_ThreeQuarter", null, 4,
                    "Soil_Pathway", 3, "Decorative"));

            // Gravel
            recipes.add(generateRecipe("Soil_Gravel_Half", null, 2,
                    "Soil_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Gravel_Mossy_Half", null, 2,
                    "Soil_Gravel_Mossy", 1, "Decorative"));

            // Sand
            recipes.add(generateRecipe("Soil_Gravel_Sand_Half", null, 2,
                    "Soil_Gravel_Sand", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Gravel_Sand_Red_Half", null, 2,
                    "Soil_Gravel_Sand_Red", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Gravel_Sand_White_Half", null, 2,
                    "Soil_Gravel_Sand_White", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Sand_White_Path_Half", null, 2,
                    "Soil_Sand_White", 1, "Decorative"));
        }
    }

    private static void loadWoodStair(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadStair(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1,
                materialId, 1, category));
    }

    private static void loadStairs(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Woods
        loadStair(recipes, "Wood_Oak_Trunk", "Woods");

        // Planks
        loadWoodStair(recipes, "Wood_Blackwood");
        loadWoodStair(recipes, "Wood_Darkwood");
        loadWoodStair(recipes, "Wood_Deadwood");
        loadWoodStair(recipes, "Wood_Drywood");
        loadWoodStair(recipes, "Wood_Goldenwood");
        loadWoodStair(recipes, "Wood_Greenwood");
        loadWoodStair(recipes, "Wood_Hardwood");
        loadWoodStair(recipes, "Wood_Lightwood");
        loadWoodStair(recipes, "Wood_Redwood");
        loadWoodStair(recipes, "Wood_TropicalWood");
        loadWoodStair(recipes, "Wood_Softwood");

        // Rocks
        loadStair(recipes, "Rock_Aqua_Cobble", "Rocks");
        loadStair(recipes, "Rock_Basalt_Cobble", "Rocks");
        loadStair(recipes, "Rock_Calcite_Cobble", "Rocks");
        loadStair(recipes, "Rock_Ledge_Cobble", "Rocks");
        loadStair(recipes, "Rock_Lime_Cobble", "Rocks");
        loadStair(recipes, "Rock_Marble_Cobble", "Rocks");
        loadStair(recipes, "Rock_Peach_Cobble", "Rocks");
        loadStair(recipes, "Rock_Quartzite_Cobble", "Rocks");
        loadStair(recipes, "Rock_Runic_Cobble", "Rocks");
        loadStair(recipes, "Rock_Sandstone_Cobble", "Rocks");
        loadStair(recipes, "Rock_Sandstone_Red_Cobble", "Rocks");
        loadStair(recipes, "Rock_Sandstone_White_Cobble", "Rocks");
        loadStair(recipes, "Rock_Shale_Cobble", "Rocks");
        loadStair(recipes, "Rock_Slate_Cobble", "Rocks");
        loadStair(recipes, "Rock_Stone_Cobble", "Rocks");
        loadStair(recipes, "Rock_Volcanic_Cobble", "Rocks");

        // Bricks
        loadStair(recipes, "Rock_Aqua_Brick", "Bricks");
        loadStair(recipes, "Rock_Basalt_Brick", "Bricks");
        loadStair(recipes, "Rock_Calcite_Brick", "Bricks");
        loadStair(recipes, "Rock_Chalk_Brick", "Bricks");
        loadStair(recipes, "Rock_Gold_Brick", "Bricks");
        loadStair(recipes, "Rock_Ledge_Brick", "Bricks");
        loadStair(recipes, "Rock_Lime_Brick", "Bricks");
        loadStair(recipes, "Rock_Marble_Brick", "Bricks");
        loadStair(recipes, "Rock_Peach_Brick", "Bricks");
        loadStair(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadStair(recipes, "Rock_Runic_Brick", "Bricks");
        loadStair(recipes, "Rock_Runic_Blue_Brick", "Bricks");
        loadStair(recipes, "Rock_Runic_Dark_Brick", "Bricks");
        loadStair(recipes, "Rock_Runic_Teal_Brick", "Bricks");
        loadStair(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadStair(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadStair(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadStair(recipes, "Rock_Shale_Brick", "Bricks");
        loadStair(recipes, "Rock_Stone_Brick", "Bricks");
        loadStair(recipes, "Rock_Volcanic_Brick", "Bricks");
        loadStair(recipes, "Soil_Snow_Brick", "Bricks");

        // Clays
        loadStair(recipes, "Soil_Clay_Brick", "Bricks");
        loadStair(recipes, "Soil_Clay_Ocean_Brick", "Bricks");
    }

    private static void loadSlab(List<CraftingRecipe> recipes, String materialId, String category, Boolean smooth) {
        recipes.add(generateRecipe(materialId + "_Half", null, 2,
                materialId, 1, category));
        if(smooth != null && smooth) {
            recipes.add(generateRecipe(materialId + "_Smooth_Half", null, 2,
                    materialId, 1, category));
        }
    }

    private static void loadSlabs(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Woods
        loadSlab(recipes, "Wood_Oak_Trunk", "Woods", null);

        // Planks
        loadSlab(recipes, "Wood_Blackwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Darkwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Deadwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Drywood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Goldenwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Greenwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Hardwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Lightwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Redwood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_TropicalWood_Planks", "WoodPlanks", null);
        loadSlab(recipes, "Wood_Softwood_Planks", "WoodPlanks", null);

        // Rocks
        loadSlab(recipes, "Rock_Aqua_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Basalt_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Calcite_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Ledge_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Lime_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Marble_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Peach_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Quartzite_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Runic_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Sandstone_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Sandstone_Red_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Sandstone_White_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Shale_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Slate_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Stone_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Stone_Mossy_Cobble", "Rocks", null);
        loadSlab(recipes, "Rock_Volcanic_Cobble", "Rocks", null);

        // Bricks
        loadSlab(recipes, "Rock_Aqua_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Basalt_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Calcite_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Ledge_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Lime_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Marble_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Peach_Brick", "Bricks", null);
        loadSlab(recipes, "Rock_Quartzite_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Runic_Brick", "Bricks", null);
        loadSlab(recipes, "Rock_Runic_Blue_Brick", "Bricks", null);
        loadSlab(recipes, "Rock_Runic_Dark_Brick", "Bricks", null);
        loadSlab(recipes, "Rock_Runic_Teal_Brick", "Bricks", null);
        loadSlab(recipes, "Rock_Sandstone_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Sandstone_Red_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Sandstone_White_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Shale_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Stone_Brick", "Bricks", true);
        loadSlab(recipes, "Rock_Volcanic_Brick", "Bricks", true);

        // Snow
        loadSlab(recipes, "Soil_Snow", "Bricks", null);
        loadSlab(recipes, "Soil_Snow_Brick", "Bricks", null);

        // Clays
        loadSlab(recipes, "Soil_Clay_Brick", "Bricks", null);
        loadSlab(recipes, "Soil_Clay_Ocean_Brick", "Bricks", null);
    }

    private static void loadWoodBeam(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Beam", null, 2,
                materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadBeam(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Beam", null, 2,
                materialId, 1, category));
    }

    private static void loadBeams(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Planks
        loadWoodBeam(recipes, "Wood_Blackwood");
        loadWoodBeam(recipes, "Wood_Darkwood");
        loadWoodBeam(recipes, "Wood_Deadwood");
        loadWoodBeam(recipes, "Wood_Drywood");
        loadWoodBeam(recipes, "Wood_Goldenwood");
        loadWoodBeam(recipes, "Wood_Greenwood");
        loadWoodBeam(recipes, "Wood_Hardwood");
        loadWoodBeam(recipes, "Wood_Lightwood");
        loadWoodBeam(recipes, "Wood_Redwood");
        loadWoodBeam(recipes, "Wood_TropicalWood");
        loadWoodBeam(recipes, "Wood_Softwood");

        // Rocks
        loadBeam(recipes, "Rock_Aqua_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Basalt_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Calcite_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Chalk_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Lime_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Marble_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Peach_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Quartzite_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Runic_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Runic_Blue_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Runic_Dark_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Runic_Teal_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Sandstone_Red_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Sandstone_White_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Shale_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Stone_Cobble", "Cobbles");
        loadBeam(recipes, "Rock_Volcanic_Cobble", "Cobbles");

        // Brick
        loadBeam(recipes, "Rock_Aqua_Brick", "Bricks");
        loadBeam(recipes, "Rock_Basalt_Brick", "Bricks");
        loadBeam(recipes, "Rock_Calcite_Brick", "Bricks");
        loadBeam(recipes, "Rock_Chalk_Brick", "Bricks");
        loadBeam(recipes, "Rock_Gold_Brick", "Bricks");
        loadBeam(recipes, "Rock_Ledge_Brick", "Bricks");
        loadBeam(recipes, "Rock_Lime_Brick", "Bricks");
        loadBeam(recipes, "Rock_Marble_Brick", "Bricks");
        loadBeam(recipes, "Rock_Peach_Brick", "Bricks");
        loadBeam(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadBeam(recipes, "Rock_Runic_Brick", "Bricks");
        loadBeam(recipes, "Rock_Runic_Blue_Brick", "Bricks");
        loadBeam(recipes, "Rock_Runic_Dark_Brick", "Bricks");
        loadBeam(recipes, "Rock_Runic_Teal_Brick", "Bricks");
        loadBeam(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadBeam(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadBeam(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadBeam(recipes, "Rock_Shale_Brick", "Bricks");
        loadBeam(recipes, "Rock_Stone_Brick", "Bricks");
        loadBeam(recipes, "Rock_Volcanic_Brick", "Bricks");

        // Snow
        loadBeam(recipes, "Soil_Snow_Brick", "Bricks");

        // Clays
        loadBeam(recipes, "Soil_Clay_Brick", "Bricks");
        loadBeam(recipes, "Soil_Clay_Ocean_Brick", "Bricks");
    }

    private static void loadPillar(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Pillar_Base", null, 1,
                materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Pillar_Middle", null, 1,
                materialId, 1, category));
    }

    private static void loadPillars(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Rocks
        loadPillar(recipes, "Rock_Ledge_Cobble", "Rocks");
        loadPillar(recipes, "Rock_Lime_Cobble", "Rocks");
        loadPillar(recipes, "Rock_Peach_Cobble", "Rocks");
        loadPillar(recipes, "Rock_Runic_Cobble", "Rocks");

        // Bricks
        loadPillar(recipes, "Rock_Aqua_Brick", "Bricks");
        loadPillar(recipes, "Rock_Basalt_Brick", "Bricks");
        loadPillar(recipes, "Rock_Calcite_Brick", "Bricks");
        loadPillar(recipes, "Rock_Chalk_Brick", "Bricks");
        loadPillar(recipes, "Rock_Gold_Brick", "Bricks");
        loadPillar(recipes, "Rock_Ledge_Brick", "Bricks");
        loadPillar(recipes, "Rock_Lime_Brick", "Bricks");
        loadPillar(recipes, "Rock_Marble_Brick", "Bricks");
        loadPillar(recipes, "Rock_Peach_Brick", "Bricks");
        loadPillar(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadPillar(recipes, "Rock_Runic_Blue_Brick", "Bricks");
        loadPillar(recipes, "Rock_Runic_Dark_Brick", "Bricks");
        loadPillar(recipes, "Rock_Runic_Teal_Brick", "Bricks");
        loadPillar(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadPillar(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadPillar(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadPillar(recipes, "Rock_Shale_Brick", "Bricks");
        loadPillar(recipes, "Rock_Stone_Brick", "Bricks");
        loadPillar(recipes, "Rock_Volcanic_Brick", "Bricks");

        // Clays
        loadPillar(recipes, "Soil_Clay_Ocean_Brick", "Bricks");
    }

    private static void loadPlanksRoof(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Roof", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Flat", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Shallow", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Steep", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadRoof(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Roof", null, 1,
                materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Flat", null, 1,
                materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Shallow", null, 1,
                materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Steep", null, 1,
                materialId, 1, category));
    }

    private static void loadRoofs(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Planks
        loadPlanksRoof(recipes, "Wood_Blackwood");
        loadPlanksRoof(recipes, "Wood_Darkwood");
        loadPlanksRoof(recipes, "Wood_Deadwood");
        loadPlanksRoof(recipes, "Wood_Drywood");
        loadPlanksRoof(recipes, "Wood_Goldenwood");
        loadPlanksRoof(recipes, "Wood_Greenwood");
        loadPlanksRoof(recipes, "Wood_Hardwood");
        loadPlanksRoof(recipes, "Wood_Lightwood");
        loadPlanksRoof(recipes, "Wood_Redwood");
        loadPlanksRoof(recipes, "Wood_Softwood");
        loadPlanksRoof(recipes, "Wood_Tropicalwood");

        // Rocks
        loadRoof(recipes, "Rock_Aqua_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Basalt_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Calcite_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Marble_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Quartzite_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Sandstone_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Sandstone_Red_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Sandstone_White_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Shale_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Slate_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Stone_Cobble", "Rocks");
        loadRoof(recipes, "Rock_Volcanic_Cobble", "Rocks");

        // Bricks
        loadRoof(recipes, "Rock_Aqua_Brick", "Bricks");
        loadRoof(recipes, "Rock_Basalt_Brick", "Bricks");
        loadRoof(recipes, "Rock_Calcite_Brick", "Bricks");
        loadRoof(recipes, "Rock_Gold_Brick", "Bricks");
        loadRoof(recipes, "Rock_Marble_Brick", "Bricks");
        loadRoof(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadRoof(recipes, "Rock_Shale_Brick", "Bricks");
        loadRoof(recipes, "Rock_Stone_Brick", "Bricks");
        loadRoof(recipes, "Rock_Volcanic_Brick", "Bricks");
    }

    private static void loadFence(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Fence", null, 2,
                materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Fence_Gate", null, 1,
                materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadWall(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Wall", null, 2,
                materialId, 1, category));
    }

    private static void loadFencesAndWalls(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Planks
        loadFence(recipes, "Wood_Blackwood");
        loadFence(recipes, "Wood_Darkwood");
        loadFence(recipes, "Wood_Deadwood");
        loadFence(recipes, "Wood_Drywood");
        loadFence(recipes, "Wood_Goldenwood");
        loadFence(recipes, "Wood_Greenwood");
        loadFence(recipes, "Wood_Hardwood");
        loadFence(recipes, "Wood_Lightwood");
        loadFence(recipes, "Wood_Redwood");
        loadFence(recipes, "Wood_Softwood");
        loadFence(recipes, "Wood_Tropicalwood");

        // Rocks
        loadWall(recipes, "Rock_Aqua_Cobble", "Rocks");
        loadWall(recipes, "Rock_Basalt_Cobble", "Rocks");
        loadWall(recipes, "Rock_Calcite_Cobble", "Rocks");
        loadWall(recipes, "Rock_Ledge_Cobble", "Rocks");
        loadWall(recipes, "Rock_Lime_Cobble", "Rocks");
        loadWall(recipes, "Rock_Marble_Cobble", "Rocks");
        loadWall(recipes, "Rock_Peach_Cobble", "Rocks");
        loadWall(recipes, "Rock_Quartzite_Cobble", "Rocks");
        loadWall(recipes, "Rock_Runic_Cobble", "Rocks");
        loadWall(recipes, "Rock_Sandstone_Cobble", "Rocks");
        loadWall(recipes, "Rock_Sandstone_Red_Cobble", "Rocks");
        loadWall(recipes, "Rock_Sandstone_White_Cobble", "Rocks");
        loadWall(recipes, "Rock_Shale_Cobble", "Rocks");
        loadWall(recipes, "Rock_Slate_Cobble", "Rocks");
        loadWall(recipes, "Rock_Stone_Cobble", "Rocks");
        loadWall(recipes, "Rock_Volcanic_Cobble", "Rocks");

        // Bricks
        loadWall(recipes, "Rock_Aqua_Brick", "Bricks");
        loadWall(recipes, "Rock_Basalt_Brick", "Bricks");
        loadWall(recipes, "Rock_Calcite_Brick", "Bricks");
        loadWall(recipes, "Rock_Chalk_Brick", "Bricks");
        loadWall(recipes, "Rock_Gold_Brick", "Bricks");
        loadWall(recipes, "Rock_Ledge_Brick", "Bricks");
        loadWall(recipes, "Rock_Lime_Brick", "Bricks");
        loadWall(recipes, "Rock_Marble_Brick", "Bricks");
        loadWall(recipes, "Rock_Peach_Brick", "Bricks");
        loadWall(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadWall(recipes, "Rock_Runic_Blue_Brick", "Bricks");
        loadWall(recipes, "Rock_Runic_Dark_Brick", "Bricks");
        loadWall(recipes, "Rock_Runic_Teal_Ball_Brick", "Bricks");
        loadWall(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadWall(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadWall(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadWall(recipes, "Rock_Shale_Brick", "Bricks");
        loadWall(recipes, "Rock_Stone_Brick", "Bricks");
        loadWall(recipes, "Rock_Volcanic_Brick", "Bricks");

        // Snow
        loadWall(recipes, "Soil_Snow_Brick", "Bricks");

        // Clay
        loadWall(recipes, "Soil_Clay_Brick", "Bricks");
        loadWall(recipes, "Soil_Clay_Ocean_Brick", "Bricks");
    }

    private static void loadWindow(List<CraftingRecipe> recipes, String windowId, String materialId, String category) {
        recipes.add(generateRecipe(windowId, null, 2, materialId, 1, category));
    }

    private static void loadWindows(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Planks
        loadWindow(recipes, "Furniture_Ancient_Window", "Wood_Blackwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Ancient_Window", "Wood_Deadwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Crude_Window", "Wood_Lightwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Crude_Window", "Wood_Softwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Desert_Window", "Wood_Drywood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Desert_Window", "Wood_Goldenwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Frozen_Castle_Window", "Wood_Ice_Trunk", "Woods");
        loadWindow(recipes, "Furniture_Jungle_Window", "Wood_Tropicalwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Lumberjack_Window", "Wood_Redwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Tavern_Window", "Wood_Darkwood_Planks", "WoodPlanks");
        loadWindow(recipes, "Furniture_Village_Window", "Wood_Hardwood_Planks", "WoodPlanks");

        //Rocks
        loadWindow(recipes, "Furniture_Temple_Dark_Window", "Rock_Shale_Cobble", "Rocks");
        loadWindow(recipes, "Furniture_Temple_Emerald_Window", "Rock_Quartzite_Cobble", "Rocks");
        loadWindow(recipes, "Furniture_Temple_Light_Window", "Rock_Marble_Cobble", "Rocks");
        loadWindow(recipes, "Furniture_Temple_Wind_Window", "Rock_Sandstone_Cobble", "Rocks");
    }

    private static void loadRecipes() {
        RecycleMaterialsConfig conf = RecycleMaterials.get().getConfig();
        assert conf != null;
        List<CraftingRecipe> recipes = new ArrayList<>();

        loadMissingRecipes(conf, recipes);

        if (conf.isSoil()) {
            loadSoil(conf, recipes);
        }

        if (conf.isStairs()) {
            loadStairs(conf, recipes);
        }

        if (conf.isSlabs()) {
            loadSlabs(conf, recipes);
        }

        if (conf.isBeams()) {
            loadBeams(conf, recipes);
        }

        if (conf.isPillars()) {
            loadPillars(conf, recipes);
        }

        if (conf.isRoofs()) {
            loadRoofs(conf, recipes);
        }

        if (conf.isFences()) {
            loadFencesAndWalls(conf, recipes);
        }

        if (conf.isWindows()) {
            loadWindows(conf, recipes);
        }

        if (conf.isSalt()) {
            recipes.add(generateRecipe("Ingredient_Salt", null, 5,
                    "Rock_Salt", 1, "Ingredients", BenchType.Crafting, "Cookingbench", 1f));
        }

        CraftingRecipe.getAssetStore().loadAssets("RecycleMaterials:Crafts", recipes);
        LOGGER.atInfo().log("Recipes registered: %d", recipes.size());
    }

    private static void initRecipes() {
        try {
            _recipeId = CraftingRecipe.class.getDeclaredField("id");
            _recipeId.setAccessible(true);

            loadRecipes();
        } catch (NoSuchFieldException e) {
            LOGGER.atSevere().log("Failed to override recipes ID: %s", e.getMessage());
            assert false;
        }
    }

    private static boolean init = false;

    public static void onRecipeLoad(LoadedAssetsEvent<String, CraftingRecipe, DefaultAssetMap<String, CraftingRecipe>> event) {
        if (!init) {
            init = true;
            initRecipes();
        }
    }
}
