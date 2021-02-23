package com.thamajorn.scullery.blocks;


import com.thamajorn.scullery.tileentities.CuttingBoardTileEntity;
import com.thamajorn.scullery.util.ExampleItemHandler;
import com.thamajorn.scullery.util.registryHandler;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraftforge.fml.network.NetworkHooks;

public class Cuttingboard extends Block {


    // Directional Block Stuff
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Block.makeCuboidShape(1, 0, 2, 15, 1, 12);

    private static final VoxelShape SHAPE_E = Block.makeCuboidShape(4, 0, 1, 14, 1, 15);

    private static final VoxelShape SHAPE_S = Block.makeCuboidShape(1, 0, 4, 15, 1, 14);

    private static final VoxelShape SHAPE_W = Block.makeCuboidShape(2, 0, 1, 12, 1, 15);





    public Cuttingboard() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(1.5f, 1.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return registryHandler.CUTTINGBOARD_TILE.get().create();
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) { return true; }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (worldIn != null && !worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof CuttingBoardTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof CuttingBoardTileEntity && state.getBlock() != newState.getBlock()) {
            CuttingBoardTileEntity furnace = (CuttingBoardTileEntity) tile;
            ((ExampleItemHandler) furnace.getInventory()).toNonNullList().forEach(item -> {
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
                worldIn.addEntity(itemEntity);
            });
        }

        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }
}