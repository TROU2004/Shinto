package shinto.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.maho.surf.chant.ChantConstant;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(method = "onChatFieldUpdate",
            at = @At("RETURN"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void onChatFieldUpdate(String arg0, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (arg0.toLowerCase().startsWith(ChantConstant.PREFIX_BEGIN + " ") && player != null) {
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.1f, 0.8f);
        }
    }
}

