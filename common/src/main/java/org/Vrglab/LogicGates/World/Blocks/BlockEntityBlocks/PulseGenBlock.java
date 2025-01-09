package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.Utils.Utils;

public class PulseGenBlock extends EntityBlock {

    public PulseGenBlock(BlockPos blockPos, BlockState blockState) {
        super(Utils.convertToMcSafeType(LogicGateBlockEntityTypes.PULSE_GEN_ENTITY_TYPE), blockPos, blockState);
    }
}
