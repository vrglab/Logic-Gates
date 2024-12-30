package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class NandGateBlock extends BasicDirectionalBlock {
    public static final MapCodec<NandGateBlock> CODEC = simpleCodec(NandGateBlock::new);

    public static final BooleanProperty LEFT_INPUT = BlockStateProperties.LEFT_INPUT;
    public static final BooleanProperty RIGHT_INPUT = BlockStateProperties.RIGHT_INPUT;
    public static final BooleanProperty OUTPUT = BlockStateProperties.OUTPUT;

    public NandGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(LEFT_INPUT, false)
                .setValue(RIGHT_INPUT, false)
                .setValue(OUTPUT, true)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LEFT_INPUT, RIGHT_INPUT, OUTPUT, FACING});
    }

    @Override
    protected MapCodec<NandGateBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockState.getSignal(blockGetter, blockPos, direction);
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        if(direction == state.getValue(FACING)) {
            if(state.getValue(OUTPUT)) {
                return 15;
            }
        }
        return 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            updatePower(state, world, pos);
        }
    }

    private void updatePower(BlockState state, Level world, BlockPos pos) {
        BlockState state_new = state;

        //Deal with inputs from the right of the block
        int inputSignal = getInputSignal(world, pos, state_new.getValue(FACING).getClockWise());
        if (inputSignal > 0 && !state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, true);
        }
        if(inputSignal <= 0 && state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, false);
        }


        //Deal with inputs from the left of the block
        inputSignal = getInputSignal(world, pos, state_new.getValue(FACING).getCounterClockWise());
        if (inputSignal > 0 && !state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, true);
        }
        if(inputSignal <= 0 && state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, false);
        }


        //Deal with the output state
        if (state_new.getValue(OUTPUT) && state_new.getValue(LEFT_INPUT) && state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(OUTPUT, false);
        }
        if ((!state_new.getValue(OUTPUT) && !state_new.getValue(LEFT_INPUT)) || (!state_new.getValue(OUTPUT) && !state_new.getValue(RIGHT_INPUT))) {
            state_new = state_new.setValue(OUTPUT, true);
        }

        //Update block state when changed
        if(state_new != state) {
            world.setBlock(pos, state_new, 3);
        }
    }

    private int getInputSignal(Level world, BlockPos pos, Direction direction) {
        BlockPos inputPos = pos.relative(direction);
        return world.getSignal(inputPos, direction);
    }
}
