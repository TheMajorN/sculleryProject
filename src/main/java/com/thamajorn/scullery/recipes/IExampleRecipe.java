package com.thamajorn.scullery.recipes;

import com.thamajorn.scullery.scullery;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import java.util.Objects;

public interface IExampleRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(scullery.MOD_ID, "cuttingboardrecipes");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Objects.requireNonNull(Registry.RECIPE_TYPE.getOrDefault(RECIPE_TYPE_ID));
    }

    @Override
    default boolean canFit(int width, int height) {
       return false;
    }

    Ingredient getInput();
}
