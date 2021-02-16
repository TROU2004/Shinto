package shinto.mixin.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.magic.chant.statement.Charm;
import shinto.mixin.interfaces.IMixinPlayerEntity;

import java.util.Random;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IMixinPlayerEntity {
    private double mpValue;
    private double extraDamage;
    private double affinity;
    private int mpRegainTimer;
    @Shadow
    @Final
    private GameProfile gameProfile;

    @Inject(method = "writeCustomDataToTag",
            at = @At("TAIL"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putDouble("mpValue", mpValue);
        tag.putDouble("extraDamage", extraDamage);
        tag.putDouble("affinity", affinity);
    }

    @Inject(method = "readCustomDataFromTag",
            at = @At("TAIL"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        affinity = tag.contains("affinity") ? tag.getDouble("affinity") : (new Random().nextInt(151) + 50) / 100d;
        mpValue = tag.contains("mpValue") ? tag.getDouble("mpValue") : affinity * 20;
        extraDamage = tag.contains("extraDamage") ? tag.getDouble("extraDamage") : 0;
    }

    @Inject(method = "tick",
            at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        mpRegainTimer++;
        if (mpRegainTimer > 100) {
            new Charm().fromPlayer((PlayerEntity) (Object) this).raiseMP(2 * affinity);
            mpRegainTimer = 0;
        }
    }

    @Override
    public double getMP() {
        return mpValue;
    }

    @Override
    public void setMP(double value) {
        mpValue = value;
    }

    @Override
    public double getExtraDamage() {
        return extraDamage;
    }

    @Override
    public void setExtraDamage(double value) {
        extraDamage = value;
    }

    @Override
    public double getAffinity() {
        return gameProfile.getName().equals("yaoyueDream") ? 0 : affinity;
    }
}
