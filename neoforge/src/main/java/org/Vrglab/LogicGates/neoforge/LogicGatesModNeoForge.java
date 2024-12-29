package org.Vrglab.LogicGates.neoforge;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.Modloader.Registration.Registry;
import org.Vrglab.Modloader.Types.IBlockEntityLoaderFunction;
import org.Vrglab.Modloader.Types.ICallBack;
import org.Vrglab.Modloader.enumTypes.RegistryTypes;
import org.Vrglab.neoforge.Utils.NeoForgeRegistryCreator;

@Mod(LogicGatesMod.MOD_ID)
public final class LogicGatesModNeoForge {
    public LogicGatesModNeoForge(IEventBus modbus) {
        NeoForgeRegistryCreator.Create(modbus, LogicGatesMod.MOD_ID);
        LogicGatesMod.init();
    }
}
