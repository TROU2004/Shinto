package shinto.magic.chant.statement;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import shinto.mixin.interfaces.IMixinPlayerEntity;

import java.util.List;

public class Charm {
    public static final Identifier COSTMP_IDENTIFIER = new Identifier("shinto", "costmp");
    public double repellency = 1;
    public double praecantatio;
    private double baseMP;
    public double relief;
    private PlayerEntity playerEntity;

    public boolean fromString(String string) {
        switch (string.toLowerCase()) {
            case "n-praecantatio":
                praecantatio = 0.1f;
                break;
            case "s-praecantatio":
                praecantatio = 0.5f;
                break;
            case "praecantatio":
                praecantatio = 1.0f;
                break;
            case "b-praecantatio":
                praecantatio = 1.5f;
                break;
            case "m-praecantatio":
                praecantatio = 2.0f;
                break;
            default:
                String str = string.toLowerCase();
                if (str.contains("c-praecantatio~")) {
                    try {
                        praecantatio = Integer.parseInt(str.substring("c-praecantatio~".length()));
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    return false;
                }
        }
        return true;
    }

    public Charm fromPlayer(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
        return this;
    }

    public void calcMP(List<MagicSign> signs) {
        if (signs.isEmpty()) return;
        MagicSign lastSign = signs.get(signs.size() - 1);
        if (lastSign.equals(MagicSign.TERMINUS)) {
            signs.remove(MagicSign.TERMINUS);
            baseMP = getMPWithPriority(signs) * 2.1;
        } else {
            baseMP = getMPWithPriority(signs);
        }
    }

    private double getMPWithPriority(List<MagicSign> signs) {
        double value = 0;
        if (signs.isEmpty()) return 0;
        for (int i = 0; i < signs.size(); i++) {
            value += signs.get(i).getBaseMP() * Math.pow(1.1, i);
        }
        return value;
    }

    public double getFinalMP() {
        return baseMP * praecantatio * repellency * (1 - relief) * (1 - ((IMixinPlayerEntity) playerEntity).getAffinity() / 100d);
    }

    public double getMP() {
        return ((IMixinPlayerEntity) playerEntity).getMP();
    }

    public void setMP(double value) {
        ((IMixinPlayerEntity) playerEntity).setMP(value);
    }

    public boolean costMP(double cost) {
        if (cost > getMP()) {
            return false;
        } else {
            setMP(getMP() - cost);
            return true;
        }
    }

    public void raiseMP(double value) {
        setMP(Math.min((value + getMP()), getMaxMP()));
    }

    public double getMaxMP() {
        return ((IMixinPlayerEntity) playerEntity).getAffinity() * 20;
    }
}
