package com.thamajorn.scullery.util;

import com.google.gson.JsonObject;
import com.mojang.realmsclient.util.JsonUtils;
import com.thamajorn.scullery.recipes.cuttingBoardRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class cboardSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<cuttingBoardRecipe> {
    @Override
    public cuttingBoardRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

        return new cuttingBoardRecipe(recipeId, input, output);
    }

    @Override
    public cuttingBoardRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        Ingredient input = Ingredient.read(buffer);

        return new cuttingBoardRecipe(recipeId, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, cuttingBoardRecipe recipe) {
        Ingredient input = recipe.getIngredients().get(0);
        input.write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }
}