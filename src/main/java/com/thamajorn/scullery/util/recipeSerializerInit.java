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

public class recipeSerializerInit {

    public class RecipeSerializerInit {

        public DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, scullery.MOD_ID);


        public final IRecipeSerializer<cuttingBoardRecipe> CBOARD_RECIPE_SERIALIZER = new cboardSerializer();
        public final IRecipeType<cuttingBoardRecipe> CBOARD_TYPE = registerType(IExampleRecipe.RECIPE_TYPE_ID);

        public final RegistryObject<IRecipeSerializer<?>> EXAMPLE_SERIALIZER = RECIPE_SERIALIZERS.register("example",
                () -> CBOARD_RECIPE_SERIALIZER);

        private class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
            @Override
            public String toString() {
                return Registry.RECIPE_TYPE.getKey(this).toString();
            }
        }

        private <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
            return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
        }
    }
}
