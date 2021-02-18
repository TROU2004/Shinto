package shinto.magic.chant.statement;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import shinto.magic.Charm;
import shinto.magic.spell.SpellRegistry;
import shinto.magic.chant.ChantConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StatementResolver {
    public boolean resolve(String sentence, PlayerEntity source) {
        sentence = sentence.toLowerCase().substring(ChantConstant.PREFIX.length());
        List<String> strings = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        MagicTarget magicTarget = new MagicTarget();
        if (magicTarget.fromString(strings.get(0), source)) {
            strings.remove(0);
            List<MagicSign> signs = new ArrayList<>();
            for (String string : strings) {
                Optional<MagicSign> optional = Enums.getIfPresent(MagicSign.class, string.toUpperCase());
                if (optional.isPresent()) signs.add(optional.get());
            }
            if (signs.isEmpty()) return false;
            double baseCharm = Charm.getBaseCharmFromList(signs);
            for (SpellRegistry value : SpellRegistry.values()) {
                if (value.signs.equals(new HashSet<>(signs))) {
                    return value.spell.cast(magicTarget, source, baseCharm);
                }
            }
            source.sendMessage(new LiteralText("法术不存在"), false);
        }
        return false;
    }
}
