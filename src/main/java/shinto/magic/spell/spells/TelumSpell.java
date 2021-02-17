package shinto.magic.spell.spells;

import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.spell.AbstractSpell;
import shinto.mixin.interfaces.IMixinPlayerEntity;

public class TelumSpell extends AbstractSpell {
    @Override
    public boolean parse(Object target, PlayerEntity source, int memberSize, double speed, double strength) {
        ((IMixinPlayerEntity) source).setExtraDamage(6 * speed * strength);
        return true;
    }
}
