package com.thamajorn.scullery.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.thamajorn.scullery.recipes.CuttingBoardRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuttingBoardSerializer<C> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CuttingBoardRecipe> {

    @Override
    public CuttingBoardRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

        return new CuttingBoardRecipe(recipeId, input, output);
    }

    @Override
    public CuttingBoardRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        Ingredient input = Ingredient.read(buffer);

        return new CuttingBoardRecipe(recipeId, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, CuttingBoardRecipe recipe) {
        Ingredient input = recipe.getIngredients().get(0);
        input.write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }

    static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}