package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.LogicGates.World.WorldUtils;
import org.Vrglab.Utils.Utils;
import org.Vrglab.Utils.VLModInfo;
import org.jetbrains.annotations.Nullable;

public class NotGateBlock extends Block {
    public static final MapCodec<NotGateBlock> CODEC = simpleCodec(NotGateBlock::new);
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public NotGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(((BlockState) getStateDefinition().any()).setValue(POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return direction != Direction.SOUTH ? state.getValue(POWER) : 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            updatePower(state, world, pos);
        }
    }

    private void updatePower(BlockState state, Level world, BlockPos pos) {
        boolean hasInputSignal = false;

        for (Direction direction : Direction.values()) {
            if (direction == Direction.SOUTH) {  // Ignore the south direction to prevent feedback loops
                int inputSignal = getInputSignal(world, pos, direction);
                if (inputSignal > 0) {
                    hasInputSignal = true;
                    break;
                }
            }
        }

        int outputSignal = hasInputSignal ? 0 : 15;

        if (state.getValue(POWER) != outputSignal) {
            world.setBlock(pos, state.setValue(POWER, outputSignal), 3);
        }
    }

    private int getInputSignal(Level world, BlockPos pos, Direction direction) {
        BlockPos inputPos = pos.relative(direction);
        return world.getSignal(inputPos, direction);
    }
}
