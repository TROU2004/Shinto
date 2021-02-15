package shinto.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import shinto.mixin.interfaces.IMixinPlayerEntity;

public class EventHandler {
    public static void registerEvent() {
        AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
            if (hand != Hand.OFF_HAND) {
                double extraDamage = ((IMixinPlayerEntity) player).getExtraDamage();
                if (extraDamage != 0) entity.damage(DamageSource.player(player), (float) extraDamage);
                ((IMixinPlayerEntity) player).setExtraDamage(0);
            }
            return ActionResult.PASS;
        }));
    }
}
