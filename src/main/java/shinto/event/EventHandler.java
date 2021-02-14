package shinto.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class EventHandler {
    public static void registerEvent() {
        AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
            if (hand != Hand.OFF_HAND) {
                CompoundTag tag = new CompoundTag();
                player.readCustomDataFromTag(tag);
                if (tag.contains("telum")) entity.damage(DamageSource.player(player), tag.getInt("telum"));
            }
            return ActionResult.PASS;
        }));
    }
}
