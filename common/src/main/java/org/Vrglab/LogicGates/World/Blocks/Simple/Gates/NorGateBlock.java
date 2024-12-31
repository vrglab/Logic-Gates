package org.Vrglab.LogicGates.World.Blocks.Simple.Gates;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.ticks.TickPriority;
import org.Vrglab.LogicGates.World.Blocks.Simple.BasicDirectionalBlock;
import org.Vrglab.LogicGates.World.Blocks.Simple.BlockStateProperties;

public class NorGateBlock extends BasicDirectionalBlock {
    public static final MapCodec<NorGateBlock> CODEC = simpleCodec(NorGateBlock::new);

    public static final BooleanProperty LEFT_INPUT = BlockStateProperties.LEFT_INPUT;
    public static final BooleanProperty RIGHT_INPUT = BlockStateProperties.RIGHT_INPUT;

    public NorGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(POWERED, true)
                .setValue(LEFT_INPUT, false)
                .setValue(RIGHT_INPUT, false));
    }

    @Override
    protected MapCodec<NorGateBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{POWERED, FACING, LEFT_INPUT, RIGHT_INPUT});
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected void checkTickOnNeighbor(Level world, BlockPos pos, BlockState state) {
        if (!world.getBlockTicks().willTickThisTick(pos, this)) {
            BlockState state_new = RunInputDealings(state, world, pos);
            if (state_new != state) {
                world.scheduleTick(pos, this, this.getDelay(state), TickPriority.HIGH);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource randomSource) {
        BlockState state_new = RunInputDealings(state, world, pos);
        if (state_new != state) {
            world.setBlock(pos, state_new, 3);
        }
    }

    private BlockState RunInputDealings(BlockState state_new, Level world, BlockPos pos) {
        Direction facing = state_new.getValue(FACING);
        Direction right, left;
        if (facing.getAxis().isHorizontal()) {
            right = facing.getClockWise();
            left = facing.getCounterClockWise();
        } else {
            right = Direction.EAST;
            left = Direction.WEST;
        }

        // Deal with inputs from the right of the block
        int inputSignal = getInputSignal(world, pos, right);
        if (inputSignal > 0 && !state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, true);
        }
        if (inputSignal <= 0 && state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, false);
        }

        inputSignal = getInputSignal(world, pos, left);
        if (inputSignal > 0 && !state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, true);
        }
        if (inputSignal <= 0 && state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, false);
        }

        boolean newPoweredValue = !state_new.getValue(LEFT_INPUT) && !state_new.getValue(RIGHT_INPUT);
        if (state_new.getValue(POWERED) != newPoweredValue) {
            state_new = state_new.setValue(POWERED, newPoweredValue);
        }

        return state_new;
    }
}
