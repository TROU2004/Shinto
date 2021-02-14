package shinto.magic.chant.statement;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
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
                        charm.repellency *= 0.8;
                    }
                    charm.calcMP(signs);
                }
                for (SpellRegistry value : SpellRegistry.values()) {
                    if (value.signs.equals(new HashSet<>(signs))) {
                        //TODO.. Check if mp is enough.
                        //TODO.. Cost mp
                        value.spell.parse(magicTarget, charm, source);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
