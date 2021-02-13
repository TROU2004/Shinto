package shinto.maho.spell;

public enum MagicTarget {
    ME, IT, YOU;

    public static MagicTarget getTarget(String symbol) {
        for (MagicTarget value : MagicTarget.values()) {
            if (value.name().toLowerCase().substring(0,1).equals(symbol)) return value;
        }
        return null;
    }
}
