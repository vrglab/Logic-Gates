package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.Customs.NotGateBlockEntity;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.LogicGates.World.WorldUtils;
import org.Vrglab.Utils.Utils;
import org.jetbrains.annotations.Nullable;

public class NotGateBlock extends BaseEntityBlock {

    protected NotGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NotGateBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return WorldUtils.checkType(blockEntityType, Utils.convertToMcSafeType(LogicGateBlockEntityTypes.NOT_GATE_ENTITY_TYPE), NotGateBlockEntity::tick);
    }
}
