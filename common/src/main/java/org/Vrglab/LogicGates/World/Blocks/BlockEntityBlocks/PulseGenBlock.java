package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.PulseGenEntityType;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.BaseGateClass;
import org.Vrglab.Utils.Utils;
import org.jetbrains.annotations.Nullable;

public class PulseGenBlock extends Block implements EntityBlock {

    public PulseGenBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PulseGenEntityType(blockPos, blockState);
    }
}
