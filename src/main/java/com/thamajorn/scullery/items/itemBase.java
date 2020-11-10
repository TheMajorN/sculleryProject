package com.thamajorn.scullery.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class itemBase extends Item {
    public itemBase() {
        super(new Item.Properties().group(ItemGroup.FOOD));
    }
}
