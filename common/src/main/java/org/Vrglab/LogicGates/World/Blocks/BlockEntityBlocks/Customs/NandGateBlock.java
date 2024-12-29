package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NandGateBlock extends Block {
    public static final MapCodec<NandGateBlock> CODEC = simpleCodec(NandGateBlock::new);


    public NandGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<NandGateBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return super.getSignal(blockState, blockGetter, blockPos, direction);
    }
}
