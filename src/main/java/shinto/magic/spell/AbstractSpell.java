package shinto.magic.spell;

import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.Charm;
import shinto.magic.chant.statement.MagicTarget;

public abstract class AbstractSpell {
    protected abstract boolean parse(Object target, PlayerEntity source, int memberSize, double speed, double strength);

    public boolean cast(MagicTarget target, PlayerEntity source, double charmValue) {
        return cast(target, source, charmValue, 1, 1);
    }

    public boolean cast(MagicTarget target, PlayerEntity source, double charmValue, double speed, double strength) {
        Object[] objects = target.getTarget().getClass().isArray() ? (Object[]) target.getTarget() : new Object[]{ target.getTarget() };
        if (costCharm(source, charmValue, objects.length)) {
            for (Object o : objects) {
                if (!parse(o, source, objects.length, speed, strength)) return false;
            }
            return true;
        }
        return false;
    }

    private boolean costCharm(PlayerEntity source, double charmValue, int memberSize) {
        return Charm.fromPlayer(source).cost(charmValue * Math.pow(1.5, memberSize - 1));
    }
}
