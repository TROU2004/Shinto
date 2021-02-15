package shinto.magic.chant.statement;

public enum MagicSign {
    // grade 1 √
    PRAECANTATIO(1),
    AER(3),
    TERRA(3),
    IGNIS(3),
    AQUA(3),
    ORDO(3),
    PERDITIO(3),
    // grade 2
    GELUM(5),
    LUX(4),
    MOTUS(5),
    POTENTIA(6),
    VICTUS(6),
    VITREUS(5),
    BESTIA(5),
    FAMES(6),
    LIMUS(4),
    METALLUM(5),
    PERMUTATIO(7),
    SANO(6),
    TENEBRAE(7),
    VINCULUM(4),
    VITIUM(12),
    VOLATUS(7),
    // grade 3
    AURAM(5),
    CORPUS(5),
    EXANIMIS(8),
    HERBA(5),
    SPIRITUS(9),
    VENENUM(7),
    // grade 4
    ARBOR(5),
    COGNITIO(8),
    HUMANUS(10),
    SENSUS(6),
    INSTRUMENTUM(9),
    LUCRUM(6),
    MESSIS(6),
    // grade 5
    METO(6),
    PANNUS(7),
    TELUM(4),
    TUTAMEN(6),
    MACHINA(4),
    // grade 6
    IRA(10),
    INFERNUS(3),
    GULA(3),
    INVIDIA(6),
    DESIDIA(6),
    SUPERBIA(10),
    LUXURIA(12),
    // grade 7
    EXUBITOR(10),
    MAGNER(12),
    FLUCTUATIO(12),
    REVELATIO(20),
    // grade 8
    TERMINUS(0),
    // grade 9
    TEMPESTAS(10),
    VACUOS(10),
    PERFODIO(10),
    ALIENIS(7),
    ITER(8),
    FEBRICO(6),
    MORTUUS(20);

    private final int baseMP;

    MagicSign(int baseMP) {
        this.baseMP = baseMP;
    }

    public int getBaseMP() {
        return baseMP;
    }
}
