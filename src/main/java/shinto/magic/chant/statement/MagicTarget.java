package shinto.magic.chant.statement;

public class MagicTarget {
    private Object target;

    public boolean fromString(String string, Object source) {
        switch (string.toLowerCase()) { //TODO...
            case "mlp":
                target = null;
                break;
            case "mle":
                target = null;
                break;
            case "m":
                target = source;
                break;
            case "mre":
                target = null;
                break;
            case "mrp":
                target = null;
                break;
            case "mib":
                target = null;
                break;
            case "mrb":
                target = null;
                break;
            default:
                return false;
        }
        return true;
    }

    public Object getTarget() {
        return target;
    }
}
