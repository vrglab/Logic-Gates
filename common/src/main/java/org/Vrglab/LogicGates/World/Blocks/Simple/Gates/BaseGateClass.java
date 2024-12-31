package org.Vrglab.LogicGates.World.Blocks.Simple.Gates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseGateClass extends DiodeBlock {

    public BaseGateClass(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.SOUTH));
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected int getDelay(BlockState blockState) {
        return 1;
    }

    protected int getInputSignal(Level world, BlockPos pos, Direction direction) {
        BlockPos inputPos = pos.relative(direction);
        return world.getSignal(inputPos, direction);
    }
}
