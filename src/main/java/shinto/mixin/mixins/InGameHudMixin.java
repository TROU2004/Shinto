package shinto.mixin.mixins;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderCrosshair",
            at = @At("HEAD"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void renderCrosshair(MatrixStack arg0, CallbackInfo ci) {

    }
}
