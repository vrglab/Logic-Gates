package org.Vrglab.LogicGates.quilt;

import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.fabriclike.VLModFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.Vrglab.LogicGates.fabriclike.LogicGatesModFabricLike;

public final class LogicGatesModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        VLModFabricLike.init(LogicGatesMod.MOD_ID, ()->LogicGatesModFabricLike.init());
    }
}
