package com.thamajorn.scullery.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class HotPlate extends Block {

    public HotPlate() {
        super(AbstractBlock.Properties.create(Material.IRON)
            .hardnessAndResistance(3.5f, 4.0f)
            .sound(SoundType.METAL)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE));
    }
}
