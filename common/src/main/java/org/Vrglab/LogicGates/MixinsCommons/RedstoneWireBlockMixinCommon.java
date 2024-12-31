package org.Vrglab.LogicGates.MixinsCommons;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.*;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;
import org.Vrglab.Utils.Utils;
import org.jetbrains.annotations.Nullable;

public class RedstoneWireBlockMixinCommon {

    public static boolean shouldConnectToReplaced(BlockState blockState, @Nullable Direction direction) {
        if (blockState.is(Blocks.REDSTONE_WIRE)) {
            return true;
        } else if (blockState.is(Blocks.REPEATER)) {
            Direction direction2 = blockState.getValue(RepeaterBlock.FACING);
            return direction2 == direction || direction2.getOpposite() == direction;
        } else if (blockState.is(Blocks.OBSERVER)) {
            return direction == blockState.getValue(ObserverBlock.FACING);
        } else if(blockState.is((NandGateBlock)Utils.convertToMcSafeType(LogicGateBasicBlocks.NAND_GATE_BLOCK))) {
            return HorizontalInput(blockState, NandGateBlock.FACING, direction);
        } else if(blockState.is((NotGateBlock)Utils.convertToMcSafeType(LogicGateBasicBlocks.NOT_GATE_BLOCK))) {
            Direction BlockFacing = blockState.getValue(NandGateBlock.FACING);
            return direction == BlockFacing || direction == BlockFacing.getOpposite();
        } else if(blockState.is((NorGateBlock)Utils.convertToMcSafeType(LogicGateBasicBlocks.NOR_GATE_BLOCK))) {
            return HorizontalInput(blockState, NorGateBlock.FACING, direction);
        } else if(blockState.is((AndGateBlock)Utils.convertToMcSafeType(LogicGateBasicBlocks.AND_GATE_BLOCK))) {
            return HorizontalInput(blockState, AndGateBlock.FACING, direction);
        }
        return blockState.isSignalSource() && direction != null;
    }

    private static boolean HorizontalInput(BlockState blockState, DirectionProperty facing, Direction direction) {
        Direction BlockFacing = blockState.getValue(facing);
        Direction right = Direction.EAST, left = Direction.WEST;
        if (BlockFacing.getAxis().isHorizontal()) {
            right = BlockFacing.getClockWise();
            left = BlockFacing.getCounterClockWise();
        }
        return direction == BlockFacing || direction == right || direction == left;
    }
}
