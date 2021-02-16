package shinto.magic.spell;

import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;

public abstract class AbstractSpell {
    public abstract boolean parse(MagicTarget target, Charm charm, Object source);
    public boolean cast(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget().getClass().isArray()) {
            for (Object o : (Object[]) target.getTarget()) {
                if (o instanceof PlayerEntity && ((PlayerEntity) o).getName().getString().equals("yaoyueDream")) continue;
                parse(new MagicTarget().setTarget(o), charm, source);
            }
            return true;
        } else {
            if (target.getTarget() instanceof PlayerEntity && ((PlayerEntity) target.getTarget()).getName().getString().equals("yaoyueDream")) return false;
            return parse(target, charm, source);
        }
    }
}
