package shinto.magic.spell.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import shinto.magic.spell.AbstractSpell;

public class IterSpell extends AbstractSpell {
    @Override
    public boolean parse(Object target, PlayerEntity source, int memberSize, double speed, double strength) {
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) target;
            entity.yaw = source.yaw;
            entity.headYaw = source.headYaw;
            entity.bodyYaw = source.bodyYaw;
            entity.prevYaw = source.prevYaw;
            entity.prevBodyYaw = source.prevBodyYaw;
            entity.prevHeadYaw = source.prevHeadYaw;
            for (int i = 0; i < 3 * strength; i++) {
                entity.travel(new Vec3d(0, 0, speed));
            }
            return true;
        }
        return false;
    }
}
