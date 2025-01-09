package org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks;


import net.minecraft.world.level.block.state.BlockBehaviour;
import org.Vrglab.LogicGates.World.WorldUtils;

public class LogicGateBlockEntityBlocks {

    public static Object PULSE_GEN_BLOCK = WorldUtils.createBlock("pulse_gen_block", ()->WorldUtils.getBaseSettings(), BlockBehaviour.Properties.of(), PulseGenBlock.class);

    public static void init(){

    }
}
