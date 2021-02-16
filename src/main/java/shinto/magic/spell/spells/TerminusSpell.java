package shinto.magic.spell.spells;

import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;
import shinto.magic.spell.AbstractSpell;

public class TerminusSpell extends AbstractSpell {

    @Override
    public boolean parse(MagicTarget target, Charm charm, Object source) {
        return false;
    }
}
