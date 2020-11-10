package com.thamajorn.scullery.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class rawBacon extends Item {
    public rawBacon() {
        super(new Item.Properties()
                .group(ItemGroup.FOOD)
                .food(new Food.Builder()
                        .hunger(1)
                        .saturation(1f)
                        .meat()
                        .build()));
    }
}
