package shinto;

import net.fabricmc.api.ModInitializer;
import shinto.event.EventHandler;
import shinto.init.ItemInit;
import shinto.network.ServerPacketHandler;

public class Shinto implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerPacketHandler.init();
        EventHandler.registerEvent();
        ItemInit.registerItems();
    }
}
