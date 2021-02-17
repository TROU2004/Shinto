package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.spell.AbstractSpell;

public class StatusSpell extends AbstractSpell {
    private final StatusEffect effect;
    private final int baseDuration;
    private final int baseAmplifier;

    public StatusSpell(int baseDuration, int baseAmplifier, StatusEffect effect) {
        this.effect = effect;
        this.baseAmplifier = baseAmplifier;
        this.baseDuration = baseDuration;
    }

    @Override
    public boolean parse(Object target, PlayerEntity source, int memberSize, double speed, double strength) {
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) target;
            return entity.addStatusEffect(new StatusEffectInstance(effect, (int)(baseDuration * strength), (int)(baseAmplifier * speed)));
        }
        return false;
    }
}
