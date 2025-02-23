package org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.LogicGateBlockEntityBlocks;
import org.Vrglab.Utils.Utils;

import java.util.Set;

public class PulseGenEntityType extends BlockEntity {

    public PulseGenEntityType(BlockPos blockPos, BlockState blockState) {
        super(Utils.convertToMcSafeType(LogicGateBlockEntityTypes.PULSE_GEN_ENTITY_TYPE), blockPos, blockState);
    }
}
