package shinto.maho.surf.chant;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import net.minecraft.entity.player.PlayerEntity;
import shinto.maho.sign.Sign;
import shinto.maho.spell.MagicTarget;
import shinto.maho.spell.SpellRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChantSpell {
    private int repellency = 1;
    private ArrayList<Sign> signs = new ArrayList<>();
    private final PlayerEntity player;

    public ChantSpell(PlayerEntity player) {
        this.player = player;
    }

    public void parseSpell(String nativeString) {
        String[] symbols = nativeString.substring(nativeString.indexOf(ChantConstant.PREFIX)).split(" ");
        MagicTarget target = MagicTarget.getTarget(symbols[0]);
        resolveSigns(Arrays.asList(nativeString.substring(nativeString.indexOf(ChantConstant.PREFIX) + 2).split(" ")));
        trySurf();
    }

    private void resolveSigns(List<String> arrayList) {
        for (String string : arrayList) {
            Optional<Sign> optional = Enums.getIfPresent(Sign.class, string.toUpperCase());
            if (optional.isPresent()) {
                signs.add(optional.get());
            } else {
                repellency *= 0.8;
            }
        }
    }

    private void trySurf() {
        for (SpellRegistry value : SpellRegistry.values()) {
            if (value.signs.equals(signs)) {
                //TODO... MP Cost Check
                value.spell.surf(1, 1, player, player);
            }
        }
        //TODO...Surf failed
    }
}
