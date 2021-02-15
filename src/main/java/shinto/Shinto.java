package shinto;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import shinto.event.EventHandler;
import shinto.network.ServerPacketHandler;

public class Shinto implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerPacketHandler.init();
        EventHandler.registerEvent();
    }
}
