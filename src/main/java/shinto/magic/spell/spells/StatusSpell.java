package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;
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
    public void parse(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) target.getTarget();
            entity.addStatusEffect(new StatusEffectInstance(effect, (int)(baseDuration * charm.praecantatio), (int)(baseAmplifier * charm.praecantatio)));
        }
    }
}
