package org.Vrglab.LogicGates.Mixins;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.NandGateBlock;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.NotGateBlock;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RedStoneWireBlock.class)
public class RedstoneWireBlockMixin {

    @Redirect(method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z"))
    private boolean shouldConnectTo_getConnectingSide(BlockState blockState, @Nullable Direction direction) {
        return RedstoneWireBlockMixin.shouldConnectToReplaced(blockState, direction);
    }

    @Redirect(method = "shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z"))
    private static boolean shouldConnectTo_shouldConnectTo(BlockState blockState, @Nullable Direction direction) {
        return RedstoneWireBlockMixin.shouldConnectToReplaced(blockState, direction);
    }


    private static boolean shouldConnectToReplaced(BlockState blockState, @Nullable Direction direction) {
        if (blockState.is(Blocks.REDSTONE_WIRE)) {
            return true;
        } else if (blockState.is(Blocks.REPEATER)) {
            Direction direction2 = (Direction)blockState.getValue(RepeaterBlock.FACING);
            return direction2 == direction || direction2.getOpposite() == direction;
        } else if (blockState.is(Blocks.OBSERVER)) {
            return direction == blockState.getValue(ObserverBlock.FACING);
        } else if(blockState.is((NandGateBlock)LogicGateBasicBlocks.NAND_GATE_BLOCK)) {
            Direction BlockFacing = blockState.getValue(NandGateBlock.FACING);
            Direction right, left;
            if (BlockFacing.getAxis().isHorizontal()) {
                right = BlockFacing.getClockWise();
                left = BlockFacing.getCounterClockWise();
            } else {
                right = Direction.EAST;
                left = Direction.WEST;
            }
            return direction == BlockFacing || direction == right || direction == left;
        }else if(blockState.is((NotGateBlock)LogicGateBasicBlocks.NOT_GATE_BLOCK)) {
            Direction BlockFacing = blockState.getValue(NandGateBlock.FACING);
            return direction == BlockFacing || direction == BlockFacing.getOpposite();
        }
        return blockState.isSignalSource() && direction != null;
    }
}
