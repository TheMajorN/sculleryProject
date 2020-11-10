package com.thamajorn.scullery.procedures;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import com.thamajorn.scullery.procedures.CutboardModElements;

import java.util.function.Supplier;
import java.util.Map;

@CutboardModElements.ModElement.Tag
public class DoboardProcedure extends CutboardModElements.ModElement {
    public DoboardProcedure(CutboardModElements instance) {
        super(instance, 2);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                System.err.println("Failed to load dependency entity for procedure Doboard!");
            return;
        }
        Entity entity = (Entity) dependencies.get("entity");
        if ((((new Object() {
            public ItemStack getItemStack(int sltid) {
                Entity _ent = entity;
                if (_ent instanceof ServerPlayerEntity) {
                    Container _current = ((ServerPlayerEntity) _ent).openContainer;
                    if (_current instanceof Supplier) {
                        Object invobj = ((Supplier) _current).get();
                        if (invobj instanceof Map) {
                            return ((Slot) ((Map) invobj).get(sltid)).getStack();
                        }
                    }
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack((int) (0))).getItem() == new ItemStack(Items.GLOWSTONE_DUST, (int) (1)).getItem()) && (((new Object() {
            public int getAmount(int sltid) {
                if (entity instanceof ServerPlayerEntity) {
                    Container _current = ((ServerPlayerEntity) entity).openContainer;
                    if (_current instanceof Supplier) {
                        Object invobj = ((Supplier) _current).get();
                        if (invobj instanceof Map) {
                            ItemStack stack = ((Slot) ((Map) invobj).get(sltid)).getStack();;
                            if (stack != null)
                                return stack.getCount();
                        }
                    }
                }
                return 0;
            }
        }.getAmount((int) (1))) == 0) || ((new Object() {
            public ItemStack getItemStack(int sltid) {
                Entity _ent = entity;
                if (_ent instanceof ServerPlayerEntity) {
                    Container _current = ((ServerPlayerEntity) _ent).openContainer;
                    if (_current instanceof Supplier) {
                        Object invobj = ((Supplier) _current).get();
                        if (invobj instanceof Map) {
                            return ((Slot) ((Map) invobj).get(sltid)).getStack();
                        }
                    }
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack((int) (1))).getItem() == new ItemStack(Items.REDSTONE, (int) (1)).getItem())))) {
            {
                Entity _ent = entity;
                if (_ent instanceof ServerPlayerEntity) {
                    Container _current = ((ServerPlayerEntity) _ent).openContainer;
                    if (_current instanceof Supplier) {
                        Object invobj = ((Supplier) _current).get();
                        if (invobj instanceof Map) {
                            ((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
                            _current.detectAndSendChanges();
                        }
                    }
                }
            }
            if (entity instanceof PlayerEntity) {
                Container _current = ((PlayerEntity) entity).openContainer;
                if (_current instanceof Supplier) {
                    Object invobj = ((Supplier) _current).get();
                    if (invobj instanceof Map) {
                        ItemStack _setstack = new ItemStack(Items.REDSTONE, (int) (1));
                        _setstack.setCount((int) ((new Object() {
                            public int getAmount(int sltid) {
                                if (entity instanceof ServerPlayerEntity) {
                                    Container _current = ((ServerPlayerEntity) entity).openContainer;
                                    if (_current instanceof Supplier) {
                                        Object invobj = ((Supplier) _current).get();
                                        if (invobj instanceof Map) {
                                            ItemStack stack = ((Slot) ((Map) invobj).get(sltid)).getStack();;
                                            if (stack != null)
                                                return stack.getCount();
                                        }
                                    }
                                }
                                return 0;
                            }
                        }.getAmount((int) (1))) + 1));
                        ((Slot) ((Map) invobj).get((int) (1))).putStack(_setstack);
                        _current.detectAndSendChanges();
                    }
                }
            }
        }
    }
}

