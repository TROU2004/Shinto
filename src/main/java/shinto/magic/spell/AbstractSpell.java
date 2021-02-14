package shinto.magic.spell;

import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;

public abstract class AbstractSpell {
    public abstract void parse(MagicTarget target, Charm charm, Object source);
}
