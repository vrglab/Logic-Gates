package org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes;

import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.LogicGateBlockEntityBlocks;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.Customs.NotGateBlockEntity;
import org.Vrglab.LogicGates.World.Blocks.LogicGateBlocks;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;
import org.Vrglab.Modloader.Registration.Registry;

public class LogicGateBlockEntityTypes {

    public static Object NOT_GATE_ENTITY_TYPE = Registry.RegisterBlockEntityType("not_gate_entity_type", LogicGatesMod.MOD_ID, NotGateBlockEntity::new, LogicGateBlockEntityBlocks.NOT_GATE);

    public static void init(){

    }
}
