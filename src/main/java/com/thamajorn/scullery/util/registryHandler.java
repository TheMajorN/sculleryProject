package com.thamajorn.scullery.util;

import com.thamajorn.scullery.blocks.Cuttingboard;
import com.thamajorn.scullery.blocks.Grinder;
import com.thamajorn.scullery.blocks.SaltEvaporator;
import com.thamajorn.scullery.container.CuttingBoardContainer;
import com.thamajorn.scullery.items.*;
import com.thamajorn.scullery.recipes.IExampleRecipe;
import com.thamajorn.scullery.recipes.cuttingBoardRecipe;
import com.thamajorn.scullery.scullery;
import com.thamajorn.scullery.tileentities.cuttingBoardTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class registryHandler {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, scullery.MOD_ID);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, scullery.MOD_ID);
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, scullery.MOD_ID);
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, scullery.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //---------------------------------
    //---------BLOCKS & ITEMS----------
    //---------------------------------

    // Utensil List
    //----------
    public static final RegistryObject<Knife> KNIFE = ITEMS.register("knife", Knife::new);
    public static final RegistryObject<Item> JAR = ITEMS.register("jar", itemBase::new);

    // Non-Meat List
    //----------
    public static final RegistryObject<Item> SCRAMBLEDEGGS = ITEMS.register("scrambled_eggs", itemBase::new);
    public static final RegistryObject<Item> MOLASSES = ITEMS.register("molasses", itemBase::new);
    public static final RegistryObject<Item> BROWNSUGAR = ITEMS.register("brownsugar", itemBase::new);

    // Meat List
    //----------
    public static final RegistryObject<Item> RAW_BACON = ITEMS.register("raw_bacon", rawBacon::new);
    public static final RegistryObject<Item> COOKED_BACON = ITEMS.register("cooked_bacon", cookedBacon::new);
    public static final RegistryObject<Item> SLICED_BEEF = ITEMS.register("sliced_beef", itemBase::new);

    // Beverage List
    //----------
    public static final RegistryObject<Coffee> COFFEE = ITEMS.register("coffee", Coffee::new);

    // Crop List
    //----------
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic_item", Garlic::new);
    public static final RegistryObject<Item> ONION = ITEMS.register("onion_item", Onion::new);

    // Crop Block List
    //----------
    public static final RegistryObject<Block> GARLIC_CROP = BLOCKS.register("garlic", () -> new GarlicCrop(Block.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> ONION_CROP = BLOCKS.register("onion", () -> new OnionCrop(Block.Properties.from(Blocks.WHEAT)));

    // Crop Seed List
    //----------
    public static final RegistryObject<Item> ONION_SEED = ITEMS.register("onionseed",
            () -> new BlockItem(ONION_CROP.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> GARLIC_SEED = ITEMS.register("garlicseed",
            () -> new BlockItem(GARLIC_CROP.get(), new Item.Properties().group(ItemGroup.MISC)));

    // Block List
    //----------
    public static final RegistryObject<Block> GRINDER = BLOCKS.register("grinder", Grinder::new);
    public static final RegistryObject<Block> CUTTINGBOARD = BLOCKS.register("cuttingboard", Cuttingboard::new);
    public static final RegistryObject<Block> SALTEVAPORATOR = BLOCKS.register("saltevaporator", () -> new SaltEvaporator(Block.Properties.from(Blocks.WHEAT)));

    // Block Item List
    //----------
    public static final RegistryObject<Item> GRINDER_ITEM = ITEMS.register("grinder", () -> new BlockItem(GRINDER.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> CUTTINGBOARD_ITEMS = ITEMS.register("cuttingboarditem", () -> new BlockItem(CUTTINGBOARD.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> SALTEVAPORATOR_ITEM = ITEMS.register("saltevaporator", () -> new BlockItem(SALTEVAPORATOR.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    // Food Effect List
    //----------

    //---------------------------------
    //----------TILE ENTITIES----------
    //---------------------------------

    public static final RegistryObject<TileEntityType<cuttingBoardTileEntity>> CUTTINGBOARD_TILE = TILE_ENTITIES.register("cuttingboard",
            () -> TileEntityType.Builder.create(cuttingBoardTileEntity::new, registryHandler.CUTTINGBOARD.get()).build(null));

    //---------------------------------
    //-------------RECIPES-------------
    //---------------------------------

    public static final RegistryObject<ContainerType<CuttingBoardContainer>> CUTTINGBOARD_CONTAINER = CONTAINERS.register("cuttingboard",
            () -> IForgeContainerType.create(CuttingBoardContainer::new));
}