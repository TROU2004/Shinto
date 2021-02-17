package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.chant.statement.MagicTarget;
import shinto.magic.spell.AbstractSpell;

public class TerminusSpell extends AbstractSpell {
    @Override
    public boolean parse(Object target, PlayerEntity source, int memberSize, double speed, double strength) {
        if (target instanceof LivingEntity && memberSize != 1) {
            LivingEntity livingEntity = (LivingEntity) target;
            double ratio = target instanceof PlayerEntity ? 0.4 * Math.pow(1.2, speed) * Math.pow(1.1, strength) : 0.6 * Math.pow(1.1, speed) * Math.pow(1.2, strength);
            double damage = (livingEntity.getMaxHealth() - livingEntity.getHealth()) * ratio / memberSize;
            return livingEntity.damage(DamageSource.MAGIC, (float) damage);
        }
        return false;
    }
}
