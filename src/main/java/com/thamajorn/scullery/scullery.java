package com.thamajorn.scullery;

import com.thamajorn.scullery.gui.CuttingBoardScreen;
import com.thamajorn.scullery.util.registryHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// The value here should match an entry in the META-INF/mods.toml file
@Mod("scullery")
public class scullery
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "scullery";


    public scullery() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        registryHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        registryHandler.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        registryHandler.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        registryHandler.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(registryHandler.ONION_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(registryHandler.GARLIC_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(registryHandler.SALTEVAPORATOR.get(), RenderType.getCutout());
        ScreenManager.registerFactory(registryHandler.CUTTINGBOARD_CONTAINER.get(), CuttingBoardScreen::new);
    }
}
