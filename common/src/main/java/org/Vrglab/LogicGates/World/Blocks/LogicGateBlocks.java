package org.Vrglab.LogicGates.World.Blocks;

import org.Vrglab.LogicGates.World.Blocks.BlockEntities.LogicGateBlockEntities;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;

public class LogicGateBlocks {

    public static void init(){
        LogicGateBasicBlocks.init();
        LogicGateBlockEntities.init();
        LogicGateBlockEntityTypes.init();
    }
}
