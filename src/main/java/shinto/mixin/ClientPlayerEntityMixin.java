package shinto.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.magic.chant.ChantConstant;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "sendChatMessage",
            at = @At("HEAD"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void sendChatMessage(String arg0, CallbackInfo ci) {
        if (arg0.toLowerCase().startsWith(ChantConstant.PREFIX)) {
            ClientPlayNetworking.send(ChantConstant.CHANT_IDENTIFIER, PacketByteBufs.create().writeString(arg0));
            ci.cancel();
        }
    }
}
