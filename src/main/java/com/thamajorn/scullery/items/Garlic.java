package com.thamajorn.scullery.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Garlic extends Item {

    public Garlic() {
        super(new Item.Properties()
            .group(ItemGroup.FOOD)
            .food(new Food.Builder()
                .hunger(2)
                .saturation(1.0f)
                .fastToEat()
                .build()));
    }
}
