package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;
import shinto.magic.spell.AbstractSpell;

public class TerminusSpell extends AbstractSpell {
    @Override
    public boolean parse(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget() instanceof LivingEntity && target.isInGroup) {
            LivingEntity livingEntity = (LivingEntity) target.getTarget();
            double ratio = target.getTarget() instanceof PlayerEntity ? 0.4 : 0.6;
            double damage = (livingEntity.getMaxHealth() - livingEntity.getHealth()) * ratio * charm.praecantatio;
            return livingEntity.damage(DamageSource.MAGIC, (float) damage);
        }
        return false;
    }
}
