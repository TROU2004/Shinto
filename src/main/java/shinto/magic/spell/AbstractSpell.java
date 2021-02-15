package shinto.magic.spell;

import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;

public abstract class AbstractSpell {
    public abstract boolean parse(MagicTarget target, Charm charm, Object source);
    public boolean cast(MagicTarget target, Charm charm, Object source) {
        if (target.getTarget().getClass().isArray()) {
            for (Object o : (Object[]) target.getTarget()) {
                parse(new MagicTarget().setTarget(o), charm, source);
            }
            return true;
        } else {
            return parse(target, charm, source);
        }
    }
}
