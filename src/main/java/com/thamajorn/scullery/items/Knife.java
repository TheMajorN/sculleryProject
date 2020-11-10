package com.thamajorn.scullery.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Knife extends Item {

    public Knife() {
        super(new Item.Properties()
            .group(ItemGroup.TOOLS)
            .maxStackSize(1)
            .maxDamage(5)
            .setNoRepair()

        );
    }
}
