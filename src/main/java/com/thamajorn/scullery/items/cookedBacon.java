package com.thamajorn.scullery.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class cookedBacon extends Item {
    public cookedBacon() {
        super(new Item.Properties()
                .group(ItemGroup.FOOD)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(3f)
                        .meat()
                        .build()));
    }
}
