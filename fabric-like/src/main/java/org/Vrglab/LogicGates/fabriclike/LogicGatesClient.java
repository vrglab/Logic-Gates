package org.Vrglab.LogicGates.fabriclike;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;

public class LogicGatesClient  {

    public static void SetBlockLayerMaps() {
        BlockRenderLayerMap.INSTANCE.putBlock((Block) LogicGateBasicBlocks.NOT_GATE_BLOCK, RenderType.cutout());
    }
}
