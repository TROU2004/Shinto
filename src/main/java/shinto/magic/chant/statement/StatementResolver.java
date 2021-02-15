package shinto.magic.chant.statement;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import shinto.magic.spell.SpellRegistry;
import shinto.magic.chant.ChantConstant;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StatementResolver {
    MagicTarget magicTarget = new MagicTarget();
    Charm charm = new Charm();

    public boolean resolve(String sentence, Object source) {
        sentence = sentence.toLowerCase().substring(ChantConstant.PREFIX.length());
        List<String> strings = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        if (magicTarget.fromString(strings.get(0), source)) {
            strings.remove(0);
            if (charm.fromString(strings.get(strings.size() - 1))) {
                strings.remove(strings.size() - 1);
                List<MagicSign> signs = new ArrayList<>();
                for (String string : strings) {
                    Optional<MagicSign> optional = Enums.getIfPresent(MagicSign.class, string.toUpperCase());
                    if (optional.isPresent()) {
                        signs.add(optional.get());
                    } else {
                        charm.repellency *= 1.2;
                    }
                    charm.calcMP(signs);
                }
                if (signs.isEmpty()) return false;
                for (SpellRegistry value : SpellRegistry.values()) {
                    if (value.signs.equals(new HashSet<>(signs))) {
                        PlayerEntity player = (PlayerEntity) source;
                        if (Charm.costMP(player, charm.getFinalMP())) {
                            player.sendMessage(new LiteralText("剩余MP: " + Charm.getPlayerMP(player)), true);
                            return value.spell.cast(magicTarget, charm, source);
                        } else {
                            player.sendMessage(new LiteralText("MP不足"), true);
                        }
                    }
                }
            }
        }
        return false;
    }
}
