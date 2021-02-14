package shinto.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import shinto.magic.chant.ChantConstant;
import shinto.magic.chant.statement.StatementResolver;

public class ServerPacketHandler {
    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ChantConstant.CHANT_IDENTIFIER, (server, player, handler, buf, responseSender) -> {
            String str = buf.readString(32767);
            server.execute(() -> {
                if (new StatementResolver().resolve(str, player)) {
                    player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.BLOCKS, 1f, 1f);
                } else {
                    player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.BLOCKS, 1f, 1f);
                }
            });
        });
    }
}
