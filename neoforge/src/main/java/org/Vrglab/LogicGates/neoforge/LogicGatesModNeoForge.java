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
        DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LogicGatesMod.MOD_ID);
        BLOCK_ENTITY_TYPE.register(modbus);
        ICallBack BlockEntityTypeRegistryCallBack = new ICallBack() {
            @Override
            public Object accept(Object... args) {
                return BLOCK_ENTITY_TYPE.register(args[0].toString(), ()->BlockEntityType.Builder.of((blockPos,blockState)->((IBlockEntityLoaderFunction)args[1]).create(blockPos, blockState), ((Block)((DeferredBlock)args[2]).get())).build(null));
            }
        };
        Registry.initRegistry(BlockEntityTypeRegistryCallBack, RegistryTypes.BLOCK_ENTITY_TYPE, LogicGatesMod.MOD_ID);


        NeoForgeRegistryCreator.Create(modbus, LogicGatesMod.MOD_ID);
        LogicGatesMod.init();
    }
}
