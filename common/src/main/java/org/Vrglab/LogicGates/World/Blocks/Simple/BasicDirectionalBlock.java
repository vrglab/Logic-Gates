package org.Vrglab.LogicGates.World.Blocks.Simple;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class BasicDirectionalBlock extends DiodeBlock {

    public BasicDirectionalBlock(Properties properties) {
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
}
