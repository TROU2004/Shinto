package shinto.maho.spell;

public interface ISpell {
    int getBaseMP();
    void surf(int speed, int intensity, Object source, Object target);
}
