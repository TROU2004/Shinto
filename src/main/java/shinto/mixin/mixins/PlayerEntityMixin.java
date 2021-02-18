package shinto.mixin.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shinto.magic.Charm;
import shinto.mixin.interfaces.IMixinPlayerEntity;

import java.util.Random;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IMixinPlayerEntity {
    private double affinity = (new Random().nextInt(151) + 50) / 100d;
    private double charmValue = getCharmInstance().getFirstMaxCharm();
    private double maxCharm = getCharmInstance().getFirstMaxCharm();
    private double extraDamage;
    private int mpRegainTimer;

    @Inject(method = "writeCustomDataToTag",
            at = @At("HEAD"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putDouble("charmValue", charmValue);
        tag.putDouble("maxCharm", maxCharm);
        tag.putDouble("affinity", affinity);
        tag.putDouble("extraDamage", extraDamage);
    }

    @Inject(method = "readCustomDataFromTag",
            at = @At("HEAD"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        affinity = tag.getDouble("affinity");
        charmValue = tag.getDouble("charmValue");
        maxCharm = tag.getDouble("maxCharm");
        extraDamage = tag.getDouble("extraDamage");
    }

    @Inject(method = "tick",
            at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        mpRegainTimer++;
        int level = getInstance().getHungerManager().getFoodLevel();
        if (mpRegainTimer > 200 && level >= 6 && getCharmInstance().getCharmValue() < getCharmInstance().getMaxCharm() * 0.8) {
            getCharmInstance().applyRegain();
            getInstance().getHungerManager().setFoodLevel(level - 2);
            mpRegainTimer = 0;
        }
    }

    private Charm getCharmInstance() {
        return Charm.fromPlayer(getInstance());
    }

    @Override
    public double getCharm() {
        return charmValue;
    }

    @Override
    public void setCharm(double value) {
        charmValue = value;
    }

    @Override
    public double getMaxCharm() {
        return maxCharm;
    }

    @Override
    public void setMaxCharm(double value) {
        maxCharm = value;
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
        return affinity;
    }


    private PlayerEntity getInstance() {
        return (PlayerEntity) (Object) this;
    }
}
