package com.thamajorn.scullery.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class Grinder extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6, 9, 0, 10, 13, 2),
            Block.makeCuboidShape(7, 9, 12, 9, 14, 13),
            Block.makeCuboidShape(0, 0, 2, 2, 4, 4),
            Block.makeCuboidShape(14, 0, 2, 16, 4, 4),
            Block.makeCuboidShape(14, 0, 14, 16, 4, 16),
            Block.makeCuboidShape(0, 0, 14, 2, 4, 16),
            Block.makeCuboidShape(1, 0, 3, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 4, 14, 9, 14),
            Block.makeCuboidShape(7, 10, 2, 9, 12, 5),
            Block.makeCuboidShape(6, 9, 5, 10, 14, 8),
            Block.makeCuboidShape(4, 14, 4, 12, 15, 15),
            Block.makeCuboidShape(4, 15, 4, 5, 16, 15),
            Block.makeCuboidShape(11, 15, 4, 12, 16, 15),
            Block.makeCuboidShape(5, 15, 4, 11, 16, 5),
            Block.makeCuboidShape(5, 15, 14, 11, 16, 15)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(14, 9, 6, 16, 13, 10),
            Block.makeCuboidShape(3, 9, 7, 4, 14, 9),
            Block.makeCuboidShape(12, 0, 0, 14, 4, 2),
            Block.makeCuboidShape(12, 0, 14, 14, 4, 16),
            Block.makeCuboidShape(0, 0, 14, 2, 4, 16),
            Block.makeCuboidShape(0, 0, 0, 2, 4, 2),
            Block.makeCuboidShape(1, 0, 1, 13, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 12, 9, 14),
            Block.makeCuboidShape(11, 10, 7, 14, 12, 9),
            Block.makeCuboidShape(8, 9, 6, 11, 14, 10),
            Block.makeCuboidShape(1, 14, 4, 12, 15, 12),
            Block.makeCuboidShape(1, 15, 4, 12, 16, 5),
            Block.makeCuboidShape(1, 15, 11, 12, 16, 12),
            Block.makeCuboidShape(11, 15, 5, 12, 16, 11),
            Block.makeCuboidShape(1, 15, 5, 2, 16, 11)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6, 9, 14, 10, 13, 16),
            Block.makeCuboidShape(7, 9, 3, 9, 14, 4),
            Block.makeCuboidShape(14, 0, 12, 16, 4, 14),
            Block.makeCuboidShape(0, 0, 12, 2, 4, 14),
            Block.makeCuboidShape(0, 0, 0, 2, 4, 2),
            Block.makeCuboidShape(14, 0, 0, 16, 4, 2),
            Block.makeCuboidShape(1, 0, 1, 15, 8, 13),
            Block.makeCuboidShape(2, 8, 2, 14, 9, 12),
            Block.makeCuboidShape(7, 10, 11, 9, 12, 14),
            Block.makeCuboidShape(6, 9, 8, 10, 14, 11),
            Block.makeCuboidShape(4, 14, 1, 12, 15, 12),
            Block.makeCuboidShape(11, 15, 1, 12, 16, 12),
            Block.makeCuboidShape(4, 15, 1, 5, 16, 12),
            Block.makeCuboidShape(5, 15, 11, 11, 16, 12),
            Block.makeCuboidShape(5, 15, 1, 11, 16, 2)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 9, 6, 2, 13, 10),
            Block.makeCuboidShape(12, 9, 7, 13, 14, 9),
            Block.makeCuboidShape(2, 0, 14, 4, 4, 16),
            Block.makeCuboidShape(2, 0, 0, 4, 4, 2),
            Block.makeCuboidShape(14, 0, 0, 16, 4, 2),
            Block.makeCuboidShape(14, 0, 14, 16, 4, 16),
            Block.makeCuboidShape(3, 0, 1, 15, 8, 15),
            Block.makeCuboidShape(4, 8, 2, 14, 9, 14),
            Block.makeCuboidShape(2, 10, 7, 5, 12, 9),
            Block.makeCuboidShape(5, 9, 6, 8, 14, 10),
            Block.makeCuboidShape(4, 14, 4, 15, 15, 12),
            Block.makeCuboidShape(4, 15, 11, 15, 16, 12),
            Block.makeCuboidShape(4, 15, 4, 15, 16, 5),
            Block.makeCuboidShape(4, 15, 5, 5, 16, 11),
            Block.makeCuboidShape(14, 15, 5, 15, 16, 11)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public Grinder() {
        super(AbstractBlock.Properties.create(Material.IRON)
                .hardnessAndResistance(3.5f, 4.0f)
                .sound(SoundType.METAL)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE));
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
}
