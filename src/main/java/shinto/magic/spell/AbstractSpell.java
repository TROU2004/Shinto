package shinto.magic.spell;

import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;

public abstract class AbstractSpell {
    public abstract void parse(MagicTarget target, Charm charm, Object source);
    public void cast(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget().getClass().isArray()) {
            for (Object o : (Object[]) target.getTarget()) {
                parse(new MagicTarget().setTarget(o), charm, source);
            }
        } else {
            parse(target, charm, source);
        }
    }
}
