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
    public boolean resolve(String sentence, Object source) {
        sentence = sentence.toLowerCase().substring(ChantConstant.PREFIX.length());
        List<String> strings = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        MagicTarget magicTarget = new MagicTarget();
        if (magicTarget.fromString(strings.get(0), source)) {
            strings.remove(0);
            Charm charm = new Charm();
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
                PlayerEntity player = (PlayerEntity) source;
                for (SpellRegistry value : SpellRegistry.values()) {
                    if (value.signs.equals(new HashSet<>(signs))) {
                        charm.fromPlayer(player);
                        if (charm.costMP(charm.getFinalMP())) {
                            player.sendMessage(new LiteralText("剩余MP: " + charm.getMP()), true);
                            player.sendMessage(new LiteralText("本次施法: " + value.name() + "; 消耗MP: " + charm.getFinalMP()), false);
                            return value.spell.cast(magicTarget, charm, source);
                        } else {
                            player.sendMessage(new LiteralText("MP不足"), true);
                        }
                    }
                }
                player.sendMessage(new LiteralText("法术不存在"), false);
            }
        }
        return false;
    }
}
