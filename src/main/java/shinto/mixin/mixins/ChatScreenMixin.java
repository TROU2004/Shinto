package shinto.mixin.mixins;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.init.ItemInit;
import shinto.magic.chant.ChantConstant;
import shinto.magic.chant.statement.Charm;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Shadow
    private TextFieldWidget chatField;

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

    @Inject(method = "init",
            at = @At("TAIL")
    )
    private void init(CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (player.getMainHandStack().getItem() == ItemInit.ITEM_GLOVES) {
                chatField.setText(ChantConstant.PREFIX_GOOD + " Praecantatio");
                chatField.setCursor(ChantConstant.PREFIX_GOOD.length());
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeDouble(1);
                ClientPlayNetworking.send(Charm.COSTMP_IDENTIFIER, buf);
            }
        }
    }
}

