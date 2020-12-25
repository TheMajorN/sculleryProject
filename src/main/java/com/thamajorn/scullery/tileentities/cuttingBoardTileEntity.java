package com.thamajorn.scullery.tileentities;

import com.thamajorn.scullery.scullery;
import com.thamajorn.scullery.util.registryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class cuttingBoardTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private ITextComponent customName;
    public cuttingBoardTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public cuttingBoardTileEntity() {
        super(registryHandler.CUTTINGBOARD_TILE.get());
    }


    @Override
    public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
        return new cuttingBoardContainer(windowID, playerInv, this);
    }

    @Override
    public void tick() {
    boolean dirty = false;
    if (this.world != null && !this.world.isRemote) {
        if (world.isBlockPowered(this.getPos())) {

        }
    }

    }

    public void setCustomName (ITextComponent name) {
        this.customName = name;
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    private ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + scullery.MOD_ID + ".cuttongboard");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    public ITextComponent getCustomName() {
        return this.customName;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName");
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(ITextComponent.getTextComponentOrEmpty(compound.getString("CustomName"))));
        }
        return compound;
    }
}
