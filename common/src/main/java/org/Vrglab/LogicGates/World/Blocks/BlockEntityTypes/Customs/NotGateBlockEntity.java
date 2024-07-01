package org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.Customs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.Utils.Utils;
import org.Vrglab.Utils.VLModInfo;

public class NotGateBlockEntity extends BlockEntity {
    public NotGateBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Utils.convertToMcSafeType(LogicGateBlockEntityTypes.NOT_GATE_ENTITY_TYPE), blockPos, blockState);
    }


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        VLModInfo.LOGGER.info("entity tick");
    }
}
