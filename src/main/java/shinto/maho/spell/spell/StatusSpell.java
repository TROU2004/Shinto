package shinto.maho.spell.spell;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import shinto.maho.spell.ISpell;


public class StatusSpell implements ISpell {
    private final int baseMP;
    private final StatusEffect effect;
    private final int baseDuration;
    private final int baseAmplifier;

    public StatusSpell(int baseMP, int baseDuration, int baseAmplifier, StatusEffect effect) {
        this.baseMP = baseMP;
        this.effect = effect;
        this.baseAmplifier = baseAmplifier;
        this.baseDuration = baseDuration;
    }

    @Override
    public int getBaseMP() {
        return baseMP;
    }

    @Override
    public void surf(int speed, int intensity, Object source, Object target) {
        if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;
            player.addStatusEffect(new StatusEffectInstance(effect, 2 * baseDuration, 2 * baseAmplifier)); //TODO... SPEED INENSITY
        }
    }
}
