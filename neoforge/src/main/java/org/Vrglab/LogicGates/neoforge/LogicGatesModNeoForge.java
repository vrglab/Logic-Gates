package org.Vrglab.LogicGates.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.neoforge.Utils.NeoForgeRegistryCreator;

@Mod(LogicGatesMod.MOD_ID)
public final class LogicGatesModNeoForge {
    public LogicGatesModNeoForge(IEventBus modbus) {
        NeoForgeRegistryCreator.Create(modbus, LogicGatesMod.MOD_ID);
        LogicGatesMod.init();
    }
}
