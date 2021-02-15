package shinto.magic.chant.statement;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class MagicTarget {
    private Object target;

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
                    break;
                case "mrp":
                    target = playerEntity.world.getEntitiesByClass(PlayerEntity.class, new Box(playerEntity.getBlockPos()).expand(6), EntityPredicates.EXCEPT_SPECTATOR).toArray();
                    break;
                case "mlb":
                    target = getRayBoss(playerEntity);
                    break;
                case "mrb":
                    target = getNearBosses(playerEntity);
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
        HitResult hitResult = player.raycast(20, 1.0f, false);
        System.out.println(hitResult);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            System.out.println("GetEntity");
            List<Entity> entities = player.world.getEntitiesByClass(clazz, new Box(new BlockPos(hitResult.getPos())).expand(0.1), EntityPredicates.EXCEPT_SPECTATOR);
            if (!entities.isEmpty()) {
                return entities.get(0);
            }
        }
        return null;
    }

    private Entity getRayBoss(PlayerEntity player) {
        HitResult hitResult = player.raycast(50, 1.0f, false);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            List<Entity> entities = player.world.getEntitiesByClass(MobEntity.class, new Box(new BlockPos(hitResult.getPos())).expand(2), entity -> entity instanceof EnderDragonEntity || entity instanceof WitherEntity);
            if (!entities.isEmpty()) {
                return entities.get(0);
            }
        }
        return null;
    }

    private Object getNearBosses(PlayerEntity player) {
        return player.world.getEntitiesByClass(MobEntity.class, new Box(player.getBlockPos()).expand(50), entity -> entity instanceof EnderDragonEntity || entity instanceof WitherEntity).toArray();
    }

}
