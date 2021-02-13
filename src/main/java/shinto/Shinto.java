package shinto;

import net.fabricmc.api.ModInitializer;
import shinto.network.ServerPacketHandler;

public class Shinto implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerPacketHandler.init();
    }
}
