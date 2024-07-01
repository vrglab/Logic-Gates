package org.Vrglab.LogicGates.fabric;

import net.fabricmc.api.ModInitializer;

import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.LogicGates.fabriclike.LogicGatesModFabricLike;
import org.Vrglab.fabriclike.VLModFabricLike;

public final class LogicGatesModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VLModFabricLike.init(LogicGatesMod.MOD_ID, ()->LogicGatesModFabricLike.init());
    }
}
