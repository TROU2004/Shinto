package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;
import shinto.magic.spell.AbstractSpell;

public class IterSpell extends AbstractSpell {
    @Override
    public boolean parse(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget() instanceof LivingEntity && source instanceof PlayerEntity) {
            LivingEntity entity = (LivingEntity) target.getTarget();
            PlayerEntity player = (PlayerEntity) source;
            entity.yaw = player.yaw;
            entity.headYaw = player.headYaw;
            entity.bodyYaw = player.bodyYaw;
            entity.prevYaw = player.prevYaw;
            entity.prevBodyYaw = player.prevBodyYaw;
            entity.prevHeadYaw = player.prevHeadYaw;
            for (int i = 0; i < charm.praecantatio * 3; i++) {
                entity.travel(new Vec3d(0, 0, 1));
            }
            return true;
        }
        return false;
    }
}
