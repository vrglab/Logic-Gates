package org.Vrglab.LogicGates.quilt;

import net.fabricmc.api.ClientModInitializer;
import org.Vrglab.LogicGates.fabriclike.LogicGatesClient;

public class LogicGatesModQuiltClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        LogicGatesClient.SetBlockLayerMaps();
    }
}
