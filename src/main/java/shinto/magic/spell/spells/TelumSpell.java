package shinto.magic.spell.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import shinto.magic.chant.statement.Charm;
import shinto.magic.chant.statement.MagicTarget;
import shinto.magic.spell.AbstractSpell;

public class TelumSpell extends AbstractSpell {
    @Override
    public void parse(MagicTarget target, Charm charm, Object source) {
        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target.getTarget();
            CompoundTag tag = new CompoundTag();
            player.readCustomDataFromTag(tag);
            tag.putInt("telum", (int) (4 * charm.praecantatio));
            player.writeCustomDataToTag(tag);
        }
    }
}
