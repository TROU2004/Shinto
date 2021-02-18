package shinto.magic;

import net.minecraft.entity.player.PlayerEntity;
import shinto.magic.chant.statement.MagicSign;
import shinto.mixin.interfaces.IMixinPlayerEntity;

import java.util.List;

public class Charm {
    private final PlayerEntity playerEntity;

    public static Charm fromPlayer(PlayerEntity playerEntity) {
        return new Charm(playerEntity);
    }

    public static double getBaseCharmFromList(List<MagicSign> signs) {
        double value = 0;
        if (signs.isEmpty()) return 0;
        for (int i = 0; i < signs.size(); i++) {
            value += signs.get(i).getBaseMP() * Math.pow(1.1, i);
        }
        return value;
    }

    private Charm(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public double getCharmValue() {
        return getShinshi().getCharm();
    }

    public void setCharmValue(double value) {
        getShinshi().setCharm(value);
    }

    public boolean costCharm(double cost) {
        cost *= (1 - getShinshi().getAffinity() / 100d);
        boolean success = cost <= getCharmValue();
        if (success) {
            System.out.println("本次施法消耗MP: " + cost);
            setMaxCharm(getMaxCharm() + cost / getMaxCharm() * 2 * Math.pow(1.02, cost) * getShinshi().getAffinity());
            setCharmValue(getCharmValue() - cost);
        }
        return success;
    }

    public void raiseCharm(double value) {
        setCharmValue(Math.min((value + getCharmValue()), getMaxCharm()));
    }

    public double getMaxCharm() {
        return getShinshi().getMaxCharm();
    }

    public void setMaxCharm(double value) {
        getShinshi().setMaxCharm(value);
    }

    public double getFirstMaxCharm() {
        return 20 * getShinshi().getAffinity();
    }

    public void applyRegain() {
        raiseCharm(getBaseRegain() * getShinshi().getAffinity());
    }

    private double getBaseRegain() {
        return 2;
    }

    private IMixinPlayerEntity getShinshi() {
        return (IMixinPlayerEntity) playerEntity;
    }
}
