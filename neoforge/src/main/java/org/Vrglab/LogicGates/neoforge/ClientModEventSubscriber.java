package org.Vrglab.LogicGates.neoforge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.LogicGates.World.Blocks.Simple.LogicGateBasicBlocks;
import org.Vrglab.Utils.Utils;

@Mod.EventBusSubscriber(modid = LogicGatesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Replace TRANSPARENT_BLOCK with your block instance
            ItemBlockRenderTypes.setRenderLayer((Block)Utils.convertToMcSafeType(LogicGateBasicBlocks.NOT_GATE_BLOCK), RenderType.cutout());
        });
    }
}
