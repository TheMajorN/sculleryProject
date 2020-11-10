package com.thamajorn.scullery.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Coffee extends Item {

    public Coffee() {
        super(new Item.Properties()
        .group(ItemGroup.FOOD)
        .food(new Food.Builder()
                .hunger(0)
                .saturation(1.5f)
                .effect(new EffectInstance(Effects.SPEED, 1800, 0), 1f)
                .setAlwaysEdible()
                .build()));
    }
}
