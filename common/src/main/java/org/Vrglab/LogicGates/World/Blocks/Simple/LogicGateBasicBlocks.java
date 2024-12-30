package org.Vrglab.LogicGates.World.Blocks.Simple;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.NandGateBlock;
import org.Vrglab.LogicGates.World.Blocks.Simple.Gates.NotGateBlock;
import org.Vrglab.LogicGates.World.WorldUtils;

public class LogicGateBasicBlocks {

    public static Object NOT_GATE_BLOCK = WorldUtils.createBlock("not_gate_block", ()->WorldUtils.getBaseSettings(), BlockBehaviour.Properties.of(), NotGateBlock.class);

    public static Object NAND_GATE_BLOCK = WorldUtils.createBlock("nand_gate_block", ()->WorldUtils.getBaseSettings(), BlockBehaviour.Properties.of(), NandGateBlock.class);

    public static void init(){

    }
}
