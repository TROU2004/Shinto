package shinto.magic.chant.statement;

public enum MagicSign {
    AER(1),
    AQUA(1),
    TERRA(1),
    IGNIS(1),
    VICTUS(1),
    FAMES(1),
    LIMUS(1),
    SANO(1),
    VOLATUS(1),
    SENSUS(1),
    DESIDIA(1); //TODO...

    private final int baseMP;

    MagicSign(int baseMP) {
        this.baseMP = baseMP;
    }

    public int getBaseMP() {
        return baseMP;
    }
}
