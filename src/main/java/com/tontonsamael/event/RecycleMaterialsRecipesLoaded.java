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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class RecycleMaterialsRecipesLoaded {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private static final String RECIPE_PREFIX_ID = "RecycleMaterials";

    private static Field _recipeId;

    private static CraftingRecipe generateRecipe(String inputId, String inputResourceId, int inputQuantity, String outputId, int outputQuantity, String category, BenchType benchType, String benchId, float timeSeconds) {
        BenchRequirement bench = new BenchRequirement(benchType, benchId, new String[]{category}, 0);
        MaterialQuantity input = new MaterialQuantity(inputId, inputResourceId, null, inputQuantity, null);
        MaterialQuantity output = new MaterialQuantity(outputId, null, null, outputQuantity, null);
        CraftingRecipe recipe = new CraftingRecipe(new MaterialQuantity[]{input}, output, new MaterialQuantity[]{output}, output.getQuantity(), new BenchRequirement[]{bench}, timeSeconds, false, 0);
        try {
            _recipeId.set(recipe, String.format("%s-%s-%s-%s", RECIPE_PREFIX_ID, inputId != null ? "id" : "res", inputId != null ? inputId : inputResourceId, outputId));
        } catch (IllegalAccessException e) {
            LOGGER.atSevere().log("Failed to write recipe ID: %s", e.getMessage());
            assert false;
        }
        return recipe;
    }

    private static CraftingRecipe generateRecipe(String inputId, String inputResourceId, int inputQuantity, String outputId, int outputQuantity, String category) {
        return generateRecipe(inputId, inputResourceId, inputQuantity, outputId, outputQuantity, category, BenchType.StructuralCrafting, "Builders", 0f);
    }

    private static CraftingRecipe generateRecipe(MaterialQuantity[] inputs, MaterialQuantity[] outputs, String category, BenchType benchType, String benchId, float timeSeconds) {
        BenchRequirement bench = new BenchRequirement(benchType, benchId, new String[]{category}, 0);
        CraftingRecipe recipe = new CraftingRecipe(inputs, outputs[0], outputs, outputs[0].getQuantity(), new BenchRequirement[]{bench}, timeSeconds, false, 0);
        try {
            String name = Arrays.stream(inputs).map(MaterialQuantity::getItemId).collect(Collectors.joining("+")) + "-" + Arrays.stream(outputs).map(MaterialQuantity::getItemId).collect(Collectors.joining("+"));
            _recipeId.set(recipe, String.format("%s-%s", RECIPE_PREFIX_ID, name));
        } catch (IllegalAccessException e) {
            LOGGER.atSevere().log("Failed to write recipe ID: %s", e.getMessage());
            assert false;
        }
        return recipe;
    }

    private static void loadMissingRecipesWoodTrunk(List<CraftingRecipe> recipes, String trunk) {
        recipes.add(generateRecipe("Wood_" + trunk + "_Trunk", null, 1, "Wood_" + trunk + "_Trunk_Full", 1, "Wood"));
        recipes.add(generateRecipe("Wood_" + trunk + "_Trunk_Full", null, 1, "Wood_" + trunk + "_Trunk", 1, "Wood"));
        recipes.add(generateRecipe("Wood_" + trunk + "_Trunk_Full", null, 1, "Wood_" + trunk + "_Trunk_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Wood_" + trunk + "_Trunk_Full", null, 1, "Wood_" + trunk + "_Trunk_Half", 2, "HalfSlabs"));
    }

    private static void loadMissingRecipesChalk(List<CraftingRecipe> recipes) {
        recipes.add(generateRecipe("Rock_Chalk", null, 1, "Rock_Chalk_Cobble", 1, "Rocks"));
        recipes.add(generateRecipe("Rock_Chalk_Cobble", null, 1, "Rock_Chalk", 1, "Rocks"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1, "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe(null, "Rock_Chalk", 1, "Rock_Chalk_Brick_Decorative", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Chalk_Brick", null, 1, "Rock_Chalk_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick", null, 1, "Rock_Chalk_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick", null, 1, "Rock_Chalk_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Chalk_Brick_Decorative", null, 1, "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Decorative", null, 1, "Rock_Chalk_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Decorative", null, 1, "Rock_Chalk_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Chalk_Brick_Ornate", null, 1, "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Ornate", null, 1, "Rock_Chalk_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Ornate", null, 1, "Rock_Chalk_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Chalk_Brick_Smooth", null, 1, "Rock_Chalk_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Smooth", null, 1, "Rock_Chalk_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Chalk_Brick_Smooth", null, 1, "Rock_Chalk_Brick_Ornate", 1, "Bricks"));

        recipes.add(generateRecipe(null, "Rock_Chalk_Brick", 1, "Rock_Chalk_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe(null, "Rock_Chalk_Brick", 1, "Rock_Chalk_Brick_Beam", 2, "Beam"));
    }

    private static void loadMissingRecipesMagma(List<CraftingRecipe> recipes) {
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble", 1, "Rocks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Cobble", null, 1, "Rock_Magma_Cooled", 1, "Rocks"));

        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Wall", 2, "Wall"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Roof", 1, "Roof"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Roof_Flat", 1, "Roof"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Roof_Shallow", 1, "Roof"));
        recipes.add(generateRecipe("Rock_Magma_Cooled", null, 1, "Rock_Magma_Cooled_Cobble_Roof_Steep", 1, "Roof"));

        recipes.add(generateRecipe("Rock_Magma_Cooled_Cobble", null, 1, "Rock_Magma_Cooled_Half", 1, "HalfSlabs"));

        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick", null, 1, "Rock_Magma_Cooled_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick", null, 1, "Rock_Magma_Cooled_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick", null, 1, "Rock_Magma_Cooled_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Decorative", null, 1, "Rock_Magma_Cooled_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Decorative", null, 1, "Rock_Magma_Cooled_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Decorative", null, 1, "Rock_Magma_Cooled_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Ornate", null, 1, "Rock_Magma_Cooled_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Ornate", null, 1, "Rock_Magma_Cooled_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Ornate", null, 1, "Rock_Magma_Cooled_Brick_Smooth", 1, "Bricks"));

        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Smooth", null, 1, "Rock_Magma_Cooled_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Smooth", null, 1, "Rock_Magma_Cooled_Brick_Decorative", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Magma_Cooled_Brick_Smooth", null, 1, "Rock_Magma_Cooled_Brick_Ornate", 1, "Bricks"));
    }

    private static void loadMissingRecipesRunic(List<CraftingRecipe> recipes) {
        // Rock_Runic_Cobble
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Cobble_Wall", 2, "Wall"));

        // Rock_Runic_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Brick", null, 1, "Rock_Runic_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Brick", null, 1, "Rock_Runic_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Brick", null, 1, "Rock_Runic_Brick_Ornate", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Brick_Ornate", null, 1, "Rock_Runic_Brick", 1, "Bricks"));


        // Rock_Runic_Blue_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Blue_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Blue_Brick", null, 1, "Rock_Runic_Blue_Brick_Wall", 2, "Wall"));

        // Rock_Runic_Dark_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Dark_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Dark_Brick", null, 1, "Rock_Runic_Dark_Brick_Wall", 2, "Wall"));

        // Rock_Runic_Teal_Brick
        recipes.add(generateRecipe("Rock_Runic_Cobble", null, 1, "Rock_Runic_Teal_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Pillar_Base", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Pillar_Middle", 1, "Pillar"));
        recipes.add(generateRecipe("Rock_Runic_Teal_Brick", null, 1, "Rock_Runic_Teal_Brick_Wall", 2, "Wall"));
    }

    private static void loadMissingRecipesSnow(List<CraftingRecipe> recipes) {
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Brick", 1, "Bricks"));
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Soil_Snow", null, 1, "Soil_Snow_Brick_Wall", 2, "Wall"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1, "Soil_Snow_Brick_Stairs", 1, "Stairs"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1, "Soil_Snow_Brick_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1, "Soil_Snow_Brick_Beam", 2, "Beam"));
        recipes.add(generateRecipe("Soil_Snow_Brick", null, 1, "Soil_Snow_Brick_Wall", 2, "Wall"));
    }

    private static void loadMissingRecipesLeatherRoofs(List<CraftingRecipe> recipes) {
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide_Flap", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide_Flat", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide_Shallow", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide_Steep", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Light", null, 2, "Cloth_Roof_Hide_Vertical", 1, "Roof"));

        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather_Flap", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather_Flat", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather_Shallow", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather_Steep", 1, "Roof"));
        recipes.add(generateRecipe("Ingredient_Leather_Medium", null, 1, "Cloth_Roof_Leather_Vertical", 1, "Roof"));
    }

    private static void loadMissingRecipesClothRoof(List<CraftingRecipe> recipes, String inputId, String roofId) {
        recipes.add(generateRecipe(inputId, null, 1, roofId, 1, "Roof"));
        recipes.add(generateRecipe(inputId, null, 1, roofId + "_Vertical_Flap", 1, "Roof"));
        recipes.add(generateRecipe(inputId, null, 1, roofId + "_Flat", 1, "Roof"));
        recipes.add(generateRecipe(inputId, null, 1, roofId + "_Shallow", 1, "Roof"));
        recipes.add(generateRecipe(inputId, null, 1, roofId + "_Steep", 1, "Roof"));
        recipes.add(generateRecipe(inputId, null, 1, roofId + "_Vertical", 1, "Roof"));
    }

    private static void loadMissingRecipes(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Trunks
        loadMissingRecipesWoodTrunk(recipes, "Amber");
        loadMissingRecipesWoodTrunk(recipes, "Apple");
        loadMissingRecipesWoodTrunk(recipes, "Ash");
        loadMissingRecipesWoodTrunk(recipes, "Aspen");
        loadMissingRecipesWoodTrunk(recipes, "Azure");
        loadMissingRecipesWoodTrunk(recipes, "Bamboo");
        loadMissingRecipesWoodTrunk(recipes, "Banyan");
        loadMissingRecipesWoodTrunk(recipes, "Beech");
        loadMissingRecipesWoodTrunk(recipes, "Birch");
        loadMissingRecipesWoodTrunk(recipes, "Blackwood");
        loadMissingRecipesWoodTrunk(recipes, "Bottletree");
        loadMissingRecipesWoodTrunk(recipes, "Burnt");
        loadMissingRecipesWoodTrunk(recipes, "Camphor");
        loadMissingRecipesWoodTrunk(recipes, "Cedar");
        loadMissingRecipesWoodTrunk(recipes, "Crystal");
        loadMissingRecipesWoodTrunk(recipes, "Dry");
        loadMissingRecipesWoodTrunk(recipes, "Fig_Blue");
        loadMissingRecipesWoodTrunk(recipes, "Fir");
        loadMissingRecipesWoodTrunk(recipes, "Fire");
        loadMissingRecipesWoodTrunk(recipes, "Gumboab");
        loadMissingRecipesWoodTrunk(recipes, "Ice");
        loadMissingRecipesWoodTrunk(recipes, "Jungle");
        loadMissingRecipesWoodTrunk(recipes, "Maple");
        loadMissingRecipesWoodTrunk(recipes, "Oak");
        loadMissingRecipesWoodTrunk(recipes, "Palm");
        loadMissingRecipesWoodTrunk(recipes, "Palo");
        loadMissingRecipesWoodTrunk(recipes, "Petrified");
        loadMissingRecipesWoodTrunk(recipes, "Poisoned");
        loadMissingRecipesWoodTrunk(recipes, "Sallow");
        loadMissingRecipesWoodTrunk(recipes, "Spiral");
        loadMissingRecipesWoodTrunk(recipes, "Stormbark");
        loadMissingRecipesWoodTrunk(recipes, "Windwillow");
        loadMissingRecipesWoodTrunk(recipes, "Wisteria");

        // Stone
        recipes.add(generateRecipe(null, "Rock_Stone", 1, "Rock_Stone_Half", 2, "HalfSlabs"));

        // Rock_Calcite
        recipes.add(generateRecipe("Rock_Calcite", null, 1, "Rock_Calcite_Cobble_Half", 2, "HalfSlabs"));
        recipes.add(generateRecipe("Rock_Calcite_Cobble", null, 1, "Rock_Calcite_Cobble_Half", 2, "HalfSlabs"));

        loadMissingRecipesChalk(recipes);

        loadMissingRecipesMagma(recipes);

        loadMissingRecipesRunic(recipes);

        // Rock_Volcanic
        recipes.add(generateRecipe(null, "Rock_Volcanic", 1, "Rock_Volcanic_Half", 2, "HalfSlabs"));

        loadMissingRecipesSnow(recipes);

        if (conf.isLeatherRoofs()) {
            loadMissingRecipesLeatherRoofs(recipes);
        }

        loadMissingRecipesClothRoof(recipes, "Cloth_Block_Wool_Blue", "Cloth_Modern_Blue_Roof");
        loadMissingRecipesClothRoof(recipes, "Cloth_Block_Wool_Green", "Cloth_Modern_DarkGreen_Roof");
        loadMissingRecipesClothRoof(recipes, "Cloth_Block_Wool_Orange", "Cloth_Modern_Orange_Roof");
        loadMissingRecipesClothRoof(recipes, "Cloth_Block_Wool_Red", "Cloth_Modern_Red_Roof");
        loadMissingRecipesClothRoof(recipes, "Cloth_Block_Wool_Yellow", "Cloth_Modern_Yellow_Roof");
    }

    private static void loadSoil(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Pathway
        recipes.add(generateRecipe("Soil_Pathway", null, 1, "Soil_Pathway_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pathway", null, 1, "Soil_Pathway_Quarter", 4, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pathway", null, 3, "Soil_Pathway_ThreeQuarter", 4, "Structural-Rock"));

        // Gravel variants
        recipes.add(generateRecipe("Rubble_Aqua", null, 3, "Soil_Aqua_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Basalt", null, 3, "Soil_Basalt_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Calcite", null, 3, "Soil_Calcite_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Chalk", null, 3, "Soil_Chalk_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Lime", null, 3, "Soil_Gravel_Lime", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Magma_Cooled", null, 3, "Soil_Magma_Cooled_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Marble", null, 3, "Soil_Pebbles", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Quartzite", null, 3, "Soil_Quartzite_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Shale", null, 3, "Soil_Pebbles_Frozen", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Slate", null, 3, "Soil_Slate_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Stone_Mossy", null, 3, "Soil_Gravel_Mossy", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Volcanic", null, 3, "Soil_Volcanic_Gravel", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));

        // Sand soils variants
        recipes.add(generateRecipe("Rubble_Sandstone", null, 3, "Soil_Gravel_Sand", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Sandstone_Red", null, 3, "Soil_Gravel_Sand_Red", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe("Rubble_Sandstone_White", null, 3, "Soil_Gravel_Sand_White", 1, "Decorative", BenchType.Crafting, "Farmingbench", 0f));

        // Gravel slabs
        recipes.add(generateRecipe("Soil_Aqua_Gravel", null, 1, "Soil_Aqua_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Basalt_Gravel", null, 1, "Soil_Basalt_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Calcite_Gravel", null, 1, "Soil_Calcite_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Chalk_Gravel", null, 1, "Soil_Chalk_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Lime", null, 1, "Soil_Lime_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Magma_Cooled_Gravel", null, 1, "Soil_Magma_Cooled_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pebbles", null, 1, "Soil_Marble_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Quartzite_Gravel", null, 1, "Soil_Quartzite_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Pebbles_Frozen", null, 1, "Soil_Shale_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Slate_Gravel", null, 1, "Soil_Slate_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel", null, 1, "Soil_Gravel_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Mossy", null, 1, "Soil_Gravel_Mossy_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Volcanic_Gravel", null, 1, "Soil_Volcanic_Gravel_Half", 2, "Structural-Rock"));

        // Sand soils slabs
        recipes.add(generateRecipe("Soil_Gravel_Sand", null, 1, "Soil_Gravel_Sand_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Sand_Red", null, 1, "Soil_Gravel_Sand_Red_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Gravel_Sand_White", null, 1, "Soil_Gravel_Sand_White_Half", 2, "Structural-Rock"));
        recipes.add(generateRecipe("Soil_Sand_White", null, 1, "Soil_Sand_White_Path_Half", 2, "Structural-Rock"));

        if (conf.isSlabs()) {
            // Pathway
            recipes.add(generateRecipe("Soil_Pathway_Half", null, 2, "Soil_Pathway", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Pathway_Quarter", null, 4, "Soil_Pathway", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Pathway_ThreeQuarter", null, 4, "Soil_Pathway", 3, "Decorative"));

            // Gravel
            recipes.add(generateRecipe("Soil_Aqua_Gravel_Half", null, 2, "Soil_Aqua_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Basalt_Gravel_Half", null, 2, "Soil_Basalt_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Calcite_Gravel_Half", null, 2, "Soil_Calcite_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Chalk_Gravel_Half", null, 2, "Soil_Chalk_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Lime_Gravel_Half", null, 2, "Soil_Gravel_Lime", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Magma_Cooled_Gravel_Half", null, 2, "Soil_Magma_Cooled_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Marble_Gravel_Half", null, 2, "Soil_Pebbles", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Quartzite_Gravel_Half", null, 2, "Soil_Quartzite_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Shale_Gravel_Half", null, 2, "Soil_Pebbles_Frozen", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Slate_Gravel_Half", null, 2, "Soil_Slate_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Gravel_Half", null, 2, "Soil_Gravel", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Gravel_Mossy_Half", null, 2, "Soil_Gravel_Mossy", 1, "Decorative"));
            recipes.add(generateRecipe("Soil_Volcanic_Gravel_Half", null, 2, "Soil_Volcanic_Gravel", 1, "Decorative"));

            // Sand soils
            loadSlab(recipes, "Soil_Gravel_Sand", "Decorative", null, null);
            loadSlab(recipes, "Soil_Gravel_Sand_Red", "Decorative", null, null);
            loadSlab(recipes, "Soil_Gravel_Sand_White", "Decorative", null, null);
            recipes.add(generateRecipe("Soil_Sand_White_Path_Half", null, 2, "Soil_Sand_White", 1, "Decorative"));

            // Mossy cobble
            recipes.add(generateRecipe("Rock_Stone_Cobble_Mossy_Half", null, 2, "Rock_Stone_Mossy", 1, "Rocks"));
            recipes.add(generateRecipe("Rock_Stone_Cobble_Mossy_Half", null, 2, "Rock_Stone_Cobble_Mossy", 1, "Rocks"));
        }

        // Mossy cobble
        recipes.add(generateRecipe(null, "Rock_Stone_Mossy", 1, "Rock_Stone", 1, "Rocks"));
        recipes.add(generateRecipe(null, "Rock_Stone_Mossy", 1, "Rock_Stone_Cobble", 1, "Rocks"));
        recipes.add(generateRecipe(new MaterialQuantity[]{
                        new MaterialQuantity(null, "Rock_Stone", null, 1, null),
                        new MaterialQuantity(null, "Moss", null, 1, null),
                },
                new MaterialQuantity[]{ // to mossy stone
                        new MaterialQuantity("Rock_Stone_Mossy", null, null, 1, null)
                },
                "Decorative", BenchType.Crafting, "Farmingbench", 0f));
        recipes.add(generateRecipe(new MaterialQuantity[]{
                        new MaterialQuantity(null, "Rock_Stone", null, 1, null),
                        new MaterialQuantity(null, "Moss", null, 1, null),
                },
                new MaterialQuantity[]{ // to mossy cobblestone
                        new MaterialQuantity("Rock_Stone_Cobble_Mossy", null, null, 1, null)
                },
                "Decorative", BenchType.Crafting, "Farmingbench", 0f));
    }

    private static void loadWoodStair(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadCobbleStair(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1, materialId + "_Cobble", 1, "Cobbles"));
        recipes.add(generateRecipe(materialId + "_Cobble_Stairs", null, 1, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Stairs", null, 1, materialId + "_Cobble", 1, "Cobbles"));
    }

    private static void loadStair(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Stairs", null, 1, materialId, 1, category));
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
        loadCobbleStair(recipes, "Rock_Aqua");
        loadCobbleStair(recipes, "Rock_Basalt");
        loadCobbleStair(recipes, "Rock_Calcite");
        loadCobbleStair(recipes, "Rock_Chalk");
        loadCobbleStair(recipes, "Rock_Lime");
        loadCobbleStair(recipes, "Rock_Marble");
        loadCobbleStair(recipes, "Rock_Magma_Cooled");
        loadCobbleStair(recipes, "Rock_Peach");
        loadCobbleStair(recipes, "Rock_Quartzite");
        loadCobbleStair(recipes, "Rock_Sandstone");
        loadCobbleStair(recipes, "Rock_Sandstone_Red");
        loadCobbleStair(recipes, "Rock_Sandstone_White");
        loadCobbleStair(recipes, "Rock_Shale");
        loadCobbleStair(recipes, "Rock_Slate");
        loadCobbleStair(recipes, "Rock_Stone");
        loadCobbleStair(recipes, "Rock_Volcanic");
        loadStair(recipes, "Rock_Runic_Cobble", "Cobbles");

        // Bricks
        loadStair(recipes, "Rock_Aqua_Brick", "Bricks");
        loadStair(recipes, "Rock_Basalt_Brick", "Bricks");
        loadStair(recipes, "Rock_Calcite_Brick", "Bricks");
        loadStair(recipes, "Rock_Chalk_Brick", "Bricks");
        loadStair(recipes, "Rock_Gold_Brick", "Bricks");
        loadStair(recipes, "Rock_Ledge_Brick", "Bricks");
        loadStair(recipes, "Rock_Lime_Brick", "Bricks");
        loadStair(recipes, "Rock_Marble_Brick", "Bricks");
        loadStair(recipes, "Rock_Magma_Cooled_Brick", "Bricks");
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

    private static void loadSlab(List<CraftingRecipe> recipes, String materialId, String category, Boolean cobble, Boolean smooth) {
        recipes.add(generateRecipe(materialId + "_Half", null, 2, materialId, 1, category));
        if (cobble != null && cobble) {
            recipes.add(generateRecipe(materialId + "_Half", null, 2, materialId + "_Cobble", 1, category));
            recipes.add(generateRecipe(materialId + "_Cobble_Half", null, 2, materialId, 1, category));
            recipes.add(generateRecipe(materialId + "_Cobble_Half", null, 2, materialId + "_Cobble", 1, category));
        }
        if (smooth != null && smooth) {
            recipes.add(generateRecipe(materialId + "_Smooth_Half", null, 2, materialId + "_Smooth", 1, category));
            recipes.add(generateRecipe(materialId + "_Smooth_Half", null, 2, materialId, 1, category));
        }
    }

    private static void loadSlabs(RecycleMaterialsConfig conf, List<CraftingRecipe> recipes) {
        // Woods
        loadSlab(recipes, "Wood_Oak_Trunk", "Woods", null, null);

        // Planks
        loadSlab(recipes, "Wood_Blackwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Darkwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Deadwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Drywood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Goldenwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Greenwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Hardwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Lightwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Redwood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_TropicalWood_Planks", "WoodPlanks", null, null);
        loadSlab(recipes, "Wood_Softwood_Planks", "WoodPlanks", null, null);

        // Rocks
        loadSlab(recipes, "Rock_Aqua", "Rocks", true, null);
        loadSlab(recipes, "Rock_Basalt", "Rocks", true, null);
        loadSlab(recipes, "Rock_Calcite", "Rocks", true, null);
        loadSlab(recipes, "Rock_Chalk", "Rocks", true, null);
        loadSlab(recipes, "Rock_Ledge", "Rocks", true, null);
        loadSlab(recipes, "Rock_Lime", "Rocks", true, null);
        loadSlab(recipes, "Rock_Magma_Cooled", "Rocks", true, null);
        loadSlab(recipes, "Rock_Marble", "Rocks", true, null);
        loadSlab(recipes, "Rock_Peach", "Rocks", true, null);
        loadSlab(recipes, "Rock_Quartzite", "Rocks", true, null);
        loadSlab(recipes, "Rock_Sandstone", "Rocks", true, null);
        loadSlab(recipes, "Rock_Sandstone_Red", "Rocks", true, null);
        loadSlab(recipes, "Rock_Sandstone_White", "Rocks", true, null);
        loadSlab(recipes, "Rock_Shale", "Rocks", true, null);
        loadSlab(recipes, "Rock_Slate", "Rocks", true, null);
        loadSlab(recipes, "Rock_Stone", "Rocks", true, null);
        loadSlab(recipes, "Rock_Stone_Mossy", "Rocks", true, null);
        loadSlab(recipes, "Rock_Volcanic", "Rocks", true, null);
        loadSlab(recipes, "Rock_Runic_Cobble", "Rocks", false, null);

        // Bricks
        loadSlab(recipes, "Rock_Aqua_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Basalt_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Calcite_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Chalk_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Gold_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Ledge_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Lime_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Magma_Cooled_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Marble_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Peach_Brick", "Bricks", null, null);
        loadSlab(recipes, "Rock_Quartzite_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Runic_Brick", "Bricks", null, null);
        loadSlab(recipes, "Rock_Runic_Blue_Brick", "Bricks", null, null);
        loadSlab(recipes, "Rock_Runic_Dark_Brick", "Bricks", null, null);
        loadSlab(recipes, "Rock_Runic_Teal_Brick", "Bricks", null, null);
        loadSlab(recipes, "Rock_Sandstone_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Sandstone_Red_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Sandstone_White_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Shale_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Stone_Brick", "Bricks", null, true);
        loadSlab(recipes, "Rock_Volcanic_Brick", "Bricks", null, true);

        // Snow
        loadSlab(recipes, "Soil_Snow", "Bricks", null, null);
        loadSlab(recipes, "Soil_Snow_Brick", "Bricks", null, null);

        // Clays
        loadSlab(recipes, "Soil_Clay_Brick", "Bricks", null, null);
        loadSlab(recipes, "Soil_Clay_Ocean_Brick", "Bricks", null, null);
    }

    private static void loadWoodBeam(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Beam", null, 2, materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadRockBeam(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Beam", null, 2, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Beam", null, 2, materialId + "_Cobble", 1, "Cobbles"));
        recipes.add(generateRecipe(materialId + "_Cobble_Beam", null, 2, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Beam", null, 2, materialId + "_Cobble", 1, "Cobbles"));
    }

    private static void loadBeam(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Beam", null, 2, materialId, 1, category));
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
        loadRockBeam(recipes, "Rock_Aqua");
        loadRockBeam(recipes, "Rock_Basalt");
        loadRockBeam(recipes, "Rock_Calcite");
        loadRockBeam(recipes, "Rock_Chalk");
        loadRockBeam(recipes, "Rock_Lime");
        loadRockBeam(recipes, "Rock_Magma_Cooled");
        loadRockBeam(recipes, "Rock_Marble");
        loadRockBeam(recipes, "Rock_Peach");
        loadRockBeam(recipes, "Rock_Quartzite");
        loadRockBeam(recipes, "Rock_Runic_Blue");
        loadRockBeam(recipes, "Rock_Runic_Dark");
        loadRockBeam(recipes, "Rock_Runic_Teal");
        loadRockBeam(recipes, "Rock_Sandstone_Red");
        loadRockBeam(recipes, "Rock_Sandstone_White");
        loadRockBeam(recipes, "Rock_Shale");
        loadRockBeam(recipes, "Rock_Slate");
        loadRockBeam(recipes, "Rock_Stone");
        loadRockBeam(recipes, "Rock_Volcanic");
        loadBeam(recipes, "Rock_Runic_Cobble", "Cobbles");

        // Brick
        loadBeam(recipes, "Rock_Aqua_Brick", "Bricks");
        loadBeam(recipes, "Rock_Basalt_Brick", "Bricks");
        loadBeam(recipes, "Rock_Calcite_Brick", "Bricks");
        loadBeam(recipes, "Rock_Chalk_Brick", "Bricks");
        loadBeam(recipes, "Rock_Gold_Brick", "Bricks");
        loadBeam(recipes, "Rock_Ledge_Brick", "Bricks");
        loadBeam(recipes, "Rock_Lime_Brick", "Bricks");
        loadBeam(recipes, "Rock_Magma_Cooled_Brick", "Bricks");
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
        recipes.add(generateRecipe(materialId + "_Pillar_Base", null, 1, materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Pillar_Middle", null, 1, materialId, 1, category));
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
        loadPillar(recipes, "Rock_Magma_Cooled_Brick", "Bricks");
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
        recipes.add(generateRecipe(materialId + "_Roof", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Flat", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Shallow", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Roof_Steep", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadRoof(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Roof", null, 1, materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Flat", null, 1, materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Shallow", null, 1, materialId, 1, category));
        recipes.add(generateRecipe(materialId + "_Roof_Steep", null, 1, materialId, 1, category));
    }

    private static void loadCobbleRoof(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Cobble_Roof", null, 1, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Roof_Flat", null, 1, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Roof_Shallow", null, 1, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Roof_Steep", null, 1, materialId, 1, "Rocks"));
        loadRoof(recipes, materialId + "_Cobble", "Cobbles");
    }

    private static void loadClothRoof(List<CraftingRecipe> recipes, String materialId, String outputId, int outputQuantity, String category) {
        recipes.add(generateRecipe(materialId, null, 1, outputId, outputQuantity, category));
        recipes.add(generateRecipe(materialId + "_Flat", null, 1, outputId, outputQuantity, category));
        recipes.add(generateRecipe(materialId + "_Flap", null, 1, outputId, outputQuantity, category));
        recipes.add(generateRecipe(materialId + "_Shallow", null, 1, outputId, outputQuantity, category));
        recipes.add(generateRecipe(materialId + "_Steep", null, 1, outputId, outputQuantity, category));
        recipes.add(generateRecipe(materialId + "_Vertical", null, 1, outputId, outputQuantity, category));
    }

    private static void loadModernClothRoof(List<CraftingRecipe> recipes, String materialId, String outputId) {
        recipes.add(generateRecipe(materialId, null, 1, outputId, 1, "Furniture_Textiles"));
        recipes.add(generateRecipe(materialId + "_Flat", null, 1, outputId, 1, "Furniture_Textiles"));
        recipes.add(generateRecipe(materialId + "_Vertical_Flap", null, 1, outputId, 1, "Furniture_Textiles"));
        recipes.add(generateRecipe(materialId + "_Shallow", null, 1, outputId, 1, "Furniture_Textiles"));
        recipes.add(generateRecipe(materialId + "_Steep", null, 1, outputId, 1, "Furniture_Textiles"));
        recipes.add(generateRecipe(materialId + "_Vertical", null, 1, outputId, 1, "Furniture_Textiles"));
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
        loadCobbleRoof(recipes, "Rock_Aqua");
        loadCobbleRoof(recipes, "Rock_Basalt");
        loadCobbleRoof(recipes, "Rock_Calcite");
        loadCobbleRoof(recipes, "Rock_Chalk");
        loadCobbleRoof(recipes, "Rock_Limestone");
        loadCobbleRoof(recipes, "Rock_Magma_Cooled");
        loadCobbleRoof(recipes, "Rock_Marble");
        loadCobbleRoof(recipes, "Rock_Quartzite");
        loadCobbleRoof(recipes, "Rock_Sandstone");
        loadCobbleRoof(recipes, "Rock_Sandstone_Red");
        loadCobbleRoof(recipes, "Rock_Sandstone_White");
        loadCobbleRoof(recipes, "Rock_Shale");
        loadCobbleRoof(recipes, "Rock_Slate");
        loadCobbleRoof(recipes, "Rock_Stone");
        loadCobbleRoof(recipes, "Rock_Volcanic");

        // Bricks
        loadRoof(recipes, "Rock_Aqua_Brick", "Bricks");
        loadRoof(recipes, "Rock_Basalt_Brick", "Bricks");
        loadRoof(recipes, "Rock_Calcite_Brick", "Bricks");
        loadRoof(recipes, "Rock_Chalk_Brick", "Bricks");
        loadRoof(recipes, "Rock_Limestone_Brick", "Bricks");
        loadRoof(recipes, "Rock_Gold_Brick", "Bricks");
        loadRoof(recipes, "Rock_Magma_Cooled_Brick", "Rocks");
        loadRoof(recipes, "Rock_Marble_Brick", "Bricks");
        loadRoof(recipes, "Rock_Quartzite_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_Red_Brick", "Bricks");
        loadRoof(recipes, "Rock_Sandstone_White_Brick", "Bricks");
        loadRoof(recipes, "Rock_Shale_Brick", "Bricks");
        loadRoof(recipes, "Rock_Stone_Brick", "Bricks");
        loadRoof(recipes, "Rock_Volcanic_Brick", "Bricks");
        loadRoof(recipes, "Soil_Clay_Ocean_Brick", "Bricks");

        // Leather
        if (conf.isLeatherRoofs()) {
            loadClothRoof(recipes, "Cloth_Roof_Hide", "Ingredient_Leather_Light", 2, "Ingredients");
            loadClothRoof(recipes, "Cloth_Roof_Leather", "Ingredient_Leather_Medium", 1, "Ingredients");
        }

        // Cloth
        loadClothRoof(recipes, "Cloth_Roof_Blue", "Cloth_Block_Wool_Blue", 1, "Furniture_Textiles");
        loadClothRoof(recipes, "Cloth_Roof_Green", "Cloth_Block_Wool_Green", 1, "Furniture_Textiles");
        loadClothRoof(recipes, "Cloth_Roof_Orange", "Cloth_Block_Wool_Orange", 1, "Furniture_Textiles");
        loadClothRoof(recipes, "Cloth_Roof_Red", "Cloth_Block_Wool_Red", 1, "Furniture_Textiles");
        loadClothRoof(recipes, "Cloth_Roof_White", "Cloth_Block_Wool_White", 1, "Furniture_Textiles");
        loadClothRoof(recipes, "Cloth_Roof_Yellow", "Cloth_Block_Wool_Yellow", 1, "Furniture_Textiles");

        // Modern Cloth
        loadModernClothRoof(recipes, "Cloth_Modern_Blue_Roof", "Cloth_Block_Wool_Blue");
        loadModernClothRoof(recipes, "Cloth_Modern_DarkGreen_Roof", "Cloth_Block_Wool_Green");
        loadModernClothRoof(recipes, "Cloth_Modern_Orange_Roof", "Cloth_Block_Wool_Orange");
        loadModernClothRoof(recipes, "Cloth_Modern_Red_Roof", "Cloth_Block_Wool_Red");
        loadModernClothRoof(recipes, "Cloth_Modern_Yellow_Roof", "Cloth_Block_Wool_Yellow");
    }

    private static void loadFence(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Fence", null, 2, materialId + "_Planks", 1, "WoodPlanks"));
        recipes.add(generateRecipe(materialId + "_Fence_Gate", null, 1, materialId + "_Planks", 1, "WoodPlanks"));
    }

    private static void loadCobbleWall(List<CraftingRecipe> recipes, String materialId) {
        recipes.add(generateRecipe(materialId + "_Cobble_Wall", null, 2, materialId, 1, "Rocks"));
        recipes.add(generateRecipe(materialId + "_Cobble_Wall", null, 2, materialId + "_Cobble", 1, "Cobbles"));
    }

    private static void loadWall(List<CraftingRecipe> recipes, String materialId, String category) {
        recipes.add(generateRecipe(materialId + "_Wall", null, 2, materialId, 1, category));
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
        loadCobbleWall(recipes, "Rock_Aqua");
        loadCobbleWall(recipes, "Rock_Basalt");
        loadCobbleWall(recipes, "Rock_Calcite");
        loadCobbleWall(recipes, "Rock_Chalk");
        loadCobbleWall(recipes, "Rock_Ledge");
        loadCobbleWall(recipes, "Rock_Lime");
        loadCobbleWall(recipes, "Rock_Magma_Cooled");
        loadCobbleWall(recipes, "Rock_Marble");
        loadCobbleWall(recipes, "Rock_Peach");
        loadCobbleWall(recipes, "Rock_Quartzite");
        loadCobbleWall(recipes, "Rock_Sandstone");
        loadCobbleWall(recipes, "Rock_Sandstone_Red");
        loadCobbleWall(recipes, "Rock_Sandstone_White");
        loadCobbleWall(recipes, "Rock_Shale");
        loadCobbleWall(recipes, "Rock_Slate");
        loadCobbleWall(recipes, "Rock_Stone");
        loadCobbleWall(recipes, "Rock_Volcanic");
        loadWall(recipes, "Rock_Runic_Cobble", "Cobbles");

        // Bricks
        loadWall(recipes, "Rock_Aqua_Brick", "Bricks");
        loadWall(recipes, "Rock_Basalt_Brick", "Bricks");
        loadWall(recipes, "Rock_Calcite_Brick", "Bricks");
        loadWall(recipes, "Rock_Chalk_Brick", "Bricks");
        loadWall(recipes, "Rock_Gold_Brick", "Bricks");
        loadWall(recipes, "Rock_Ledge_Brick", "Bricks");
        loadWall(recipes, "Rock_Lime_Brick", "Bricks");
        loadWall(recipes, "Rock_Magma_Cooled_Brick", "Rocks");
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
