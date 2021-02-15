package shinto.magic.chant.statement;

import net.minecraft.entity.player.PlayerEntity;
import shinto.mixin.interfaces.IMixinPlayerEntity;

import java.util.List;

public class Charm {
    public double repellency = 1;
    public double praecantatio;
    private double baseMP;
    public double relief;

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

    public void calcMP(List<MagicSign> signs) {
        MagicSign lastSign = signs.get(signs.size() - 1);
        if (lastSign.equals(MagicSign.TERMINUS)) {
            for (int i = 0; i < signs.size() - 1; i++) {
                float otherMP = (float) (signs.get(i).getBaseMP() * Math.pow(1.1, i));
                baseMP += otherMP + otherMP * 1.1;
            }
        } else {
            for (int i = 0; i < signs.size(); i++) {
                baseMP += signs.get(i).getBaseMP() * Math.pow(1.1, i);
            }
        }
    }

    public double getFinalMP() {
        return baseMP * praecantatio * repellency * (1 - relief);
    }

    public static double getPlayerMP(PlayerEntity playerEntity) {
        return ((IMixinPlayerEntity) playerEntity).getMP();
    }

    public static void setPlayerMP(PlayerEntity playerEntity, double value) {
        ((IMixinPlayerEntity) playerEntity).setMP(value);
    }

    public static boolean costMP(PlayerEntity playerEntity, double cost) {
        if (cost > getPlayerMP(playerEntity)) {
            return false;
        } else {
            ((IMixinPlayerEntity) playerEntity).setMP(getPlayerMP(playerEntity) - cost);
            return true;
        }
    }
}
