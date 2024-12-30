package org.Vrglab.LogicGates.World.Blocks.Simple.Gates;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.ticks.TickPriority;
import org.Vrglab.LogicGates.World.Blocks.Simple.BasicDirectionalBlock;

public class NotGateBlock extends BasicDirectionalBlock {
    public static final MapCodec<NotGateBlock> CODEC = simpleCodec(NotGateBlock::new);
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    @Override
    protected MapCodec<NotGateBlock> codec() {
        return CODEC;
    }

    public NotGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any().setValue(POWER, 15));
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
    protected void checkTickOnNeighbor(Level world, BlockPos pos, BlockState state) {
        if (!world.getBlockTicks().willTickThisTick(pos, this)) {
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
                world.scheduleTick(pos, this, this.getDelay(state), TickPriority.HIGH);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource randomSource) {
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
