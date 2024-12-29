package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

public class NotGateBlock extends DirectionalBlock {
    public static final MapCodec<NotGateBlock> CODEC = simpleCodec(NotGateBlock::new);
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    @Override
    protected MapCodec<NotGateBlock> codec() {
        return CODEC;
    }

    public NotGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(((BlockState) getStateDefinition().any()).setValue(POWER, 15));
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState)blockState.setValue(FACING, rotation.rotate((Direction)blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction)blockState.getValue(FACING)));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return (BlockState)this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{POWER, FACING});
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return (direction == state.getValue(FACING)) ? state.getValue(POWER) : 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            updatePower(state, world, pos);
        }
    }

    private void updatePower(BlockState state, Level world, BlockPos pos) {
        boolean hasInputSignal = false;
        int inputSignal = getInputSignal(world, pos, state.getValue(FACING));
        if (inputSignal > 0) {
            BlockPos inputPos = pos.relative(state.getValue(FACING));
            if (inputPos.equals(pos)) {
                return;
            }
            hasInputSignal = true;
        }
        int outputSignal = hasInputSignal ? 0 : 15;
        if (state.getValue(POWER) != outputSignal) {
            BlockState newState = state.setValue(POWER, outputSignal);
            world.setBlock(pos, newState, 3);
        }
    }

    private int getInputSignal(Level world, BlockPos pos, Direction direction) {
        BlockPos inputPos = pos.relative(direction);
        return world.getSignal(inputPos, direction);
    }
}
