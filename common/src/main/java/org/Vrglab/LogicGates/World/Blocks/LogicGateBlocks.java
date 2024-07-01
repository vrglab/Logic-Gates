package org.Vrglab.LogicGates.World.Blocks;

import org.Vrglab.LogicGates.World.Blocks.BlockEntityTypes.LogicGateBlockEntityTypes;
import org.Vrglab.LogicGates.World.Blocks.BlockEntityBlocks.LogicGateBlockEntityBlocks;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;

public class LogicGateBlocks {

    public static void init(){
        LogicGateBasicBlocks.init();
        LogicGateBlockEntityTypes.init();
        LogicGateBlockEntityBlocks.init();
    }
}
