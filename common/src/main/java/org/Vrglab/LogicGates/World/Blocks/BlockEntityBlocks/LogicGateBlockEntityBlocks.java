package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.Customs.NotGateBlock;
import org.Vrglab.LogicGates.World.WorldUtils;

public class LogicGateBlockEntityBlocks {

    public static Object NOT_GATE = WorldUtils.createBlock("not_gate", ()->WorldUtils.getBaseSettings(), BlockBehaviour.Properties.of(), NotGateBlock.class);

    public static void init(){

    }
}
