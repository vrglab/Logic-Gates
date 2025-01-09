package org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes;

import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.LogicGateBlockEntityBlocks;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.PulseGenBlock;
import org.Vrglab.Modloader.Registration.Registry;

public class LogicGateBlockEntityTypes {

    public static Object PULSE_GEN_ENTITY_TYPE = Registry.RegisterBlockEntityType("pulse_gen_entity_type", LogicGatesMod.MOD_ID, PulseGenBlock::new, LogicGateBlockEntityBlocks.PULSE_GEN_BLOCK);


    public static void init(){

    }
}
