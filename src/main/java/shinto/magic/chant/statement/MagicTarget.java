package shinto.magic.chant.statement;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.List;

public class MagicTarget {
    private Object target;

    public boolean isInGroup = false;

    public boolean fromString(String string, Object source) {
        string = string.toLowerCase();
        PlayerEntity playerEntity;
        if (source instanceof PlayerEntity) {
            playerEntity = (PlayerEntity) source;
            switch (string) {
                case "mlp":
                    target = getRayEntity(PlayerEntity.class, playerEntity);
                    break;
                case "mle":
                    target = getRayEntity(MobEntity.class, playerEntity);
                    break;
                case "m":
                    target = playerEntity;
                    break;
                case "mre":
                    target = playerEntity.world.getEntitiesByClass(MobEntity.class, new Box(playerEntity.getBlockPos()).expand(6), EntityPredicates.EXCEPT_SPECTATOR).toArray();
                    isInGroup = true;
                    break;
                case "mrp":
                    target = playerEntity.world.getEntitiesByClass(PlayerEntity.class, new Box(playerEntity.getBlockPos()).expand(6), EntityPredicates.EXCEPT_SPECTATOR).toArray();
                    isInGroup = true;
                    break;
                case "mlb":
                    target = getRayBoss(playerEntity);
                    break;
                case "mrb":
                    target = getNearBosses(playerEntity);
                    isInGroup = true;
                    break;
            }
        }
        return target != null;
    }

    public Object getTarget() {
        return target;
    }

    public MagicTarget setTarget(Object target) {
        this.target = target;
        return this;
    }

    private Entity getRayEntity(Class<? extends Entity> clazz, PlayerEntity player) {
        BlockHitResult result = rayTrace(player);
        List<Entity> entities = player.world.getEntitiesByClass(clazz, new Box(new BlockPos(result.getPos())).expand(3), EntityPredicates.EXCEPT_SPECTATOR);
        if (!entities.isEmpty()) {
            return entities.get(0);
        }
        return null;
    }

    private Entity getRayBoss(PlayerEntity player) {
        BlockHitResult result = rayTrace(player);
        List<Entity> entities = player.world.getEntitiesByClass(MobEntity.class, new Box(new BlockPos(result.getPos())).expand(3), entity -> entity instanceof EnderDragonEntity || entity instanceof WitherEntity);
        if (!entities.isEmpty()) {
            return entities.get(0);
        }
        return null;
    }

    private Object getNearBosses(PlayerEntity player) {
        return player.world.getEntitiesByClass(MobEntity.class, new Box(player.getBlockPos()).expand(50), entity -> entity instanceof EnderDragonEntity || entity instanceof WitherEntity).toArray();
    }

    private BlockHitResult rayTrace(PlayerEntity player) {
        Vec3d vec1 = new Vec3d(player.getX(), player.getY() + player.getEyeY(), player.getZ());
        Vec3d vec2 = player.getRotationVector();
        Vec3d vec3 = vec1.add(vec2.x * 1000, vec2.y * 1000, vec2.z * 1000);
        return player.world.raycast(new RaycastContext(vec1, vec3, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
    }
}
