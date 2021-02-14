package shinto.mixin;

import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.magic.chant.ChantConstant;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Inject(method = "addToMessageHistory",
            at = @At("HEAD"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void addToMessageHistory(String arg0, CallbackInfo ci) {
        if (arg0.toLowerCase().startsWith(ChantConstant.PREFIX_BEGIN)) {
            ci.cancel();
        }
    }
}
