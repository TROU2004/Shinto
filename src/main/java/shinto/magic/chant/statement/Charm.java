package shinto.magic.chant.statement;

import java.util.List;

public class Charm {
    public float repellency = 1;
    public float praecantatio;
    private float baseMP;
    private float relief; //TODO...


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
        for (int i = 0; i < signs.size(); i++) {
            baseMP += signs.get(i).getBaseMP() * Math.pow(1.1, i);
        }
    }

    public double getFinalMP() {
        return baseMP * praecantatio * repellency * (1 - relief);
    }
}
