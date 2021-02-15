package shinto.mixin.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.mixin.interfaces.IMixinPlayerEntity;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IMixinPlayerEntity {
    private double mpValue;
    private double extraDamage;

    @Inject(method = "writeCustomDataToTag",
            at = @At("TAIL"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putDouble("mpValue", mpValue);
        tag.putDouble("extraDamage", extraDamage);
    }

    @Inject(method = "readCustomDataFromTag",
            at = @At("TAIL"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        mpValue = tag.contains("mpValue") ? tag.getDouble("mpValue") : 50;
        extraDamage = tag.contains("extraDamage") ? tag.getDouble("extraDamage") : 0;
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
}
