package com.thamajorn.scullery.util;

import com.thamajorn.scullery.recipes.IExampleRecipe;
import com.thamajorn.scullery.recipes.cuttingBoardRecipe;
import com.thamajorn.scullery.scullery;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

        public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, scullery.MOD_ID);


        public static final IRecipeSerializer<cuttingBoardRecipe> CBOARD_RECIPE_SERIALIZER = new cboardSerializer();
        public static final IRecipeType<IExampleRecipe> CBOARD_TYPE = registerType(IExampleRecipe.RECIPE_TYPE_ID);

        public static final RegistryObject<IRecipeSerializer<?>> CBOARD_SERIALIZER = RECIPE_SERIALIZERS.register("cboard",
                () -> CBOARD_RECIPE_SERIALIZER);

        private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
            @Override
            public String toString() {
                return Registry.RECIPE_TYPE.getKey(this).toString();
            }
        }

        private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
            return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
        }
}

