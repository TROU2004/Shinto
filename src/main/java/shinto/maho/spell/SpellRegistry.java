package shinto.maho.spell;

import net.minecraft.entity.effect.StatusEffects;
import shinto.maho.sign.Sign;
import shinto.maho.spell.spell.StatusSpell;

import java.util.ArrayList;
import java.util.Arrays;

public enum SpellRegistry {
    EFFECT_SPEED(new StatusSpell(80, 500, 1, StatusEffects.SPEED), new Sign[]{Sign.AER}),
    EFFECT_RESISTANCE(new StatusSpell(80, 500, 1, StatusEffects.RESISTANCE), new Sign[]{Sign.TERRA}),
    EFFECT_STRENGTH(new StatusSpell(80, 500, 1, StatusEffects.STRENGTH), new Sign[]{Sign.IGNIS}),
    EFFECT_JUMP_BOOST(new StatusSpell(80, 500, 1, StatusEffects.JUMP_BOOST), new Sign[]{Sign.AQUA}),
    EFFECT_HEALTH_BOOST(new StatusSpell(80, 500, 1, StatusEffects.HEALTH_BOOST), new Sign[]{Sign.VICTUS}),
    EFFECT_HUNGER(new StatusSpell(80, 500, 1, StatusEffects.HUNGER), new Sign[]{Sign.FAMES}),
    EFFECT_SLOWNESS(new StatusSpell(80, 500, 1, StatusEffects.SLOWNESS), new Sign[]{Sign.LIMUS}),
    EFFECT_INSTANT_HEALTH(new StatusSpell(80, 500, 1, StatusEffects.INSTANT_HEALTH), new Sign[]{Sign.SANO}),
    EFFECT_LEVITATION(new StatusSpell(80, 500, 1, StatusEffects.LEVITATION), new Sign[]{Sign.VOLATUS}),
    EFFECT_NIGHT_VISION(new StatusSpell(80, 500, 1, StatusEffects.NIGHT_VISION), new Sign[]{Sign.SENSUS}),
    EFFECT_SLOWNESSS(new StatusSpell(80, 500, 1, StatusEffects.SLOWNESS), new Sign[]{Sign.DESIDIA});


    public ISpell spell;
    public ArrayList<Sign> signs;

    SpellRegistry(ISpell spell, Sign[] signs) {
        this.spell = spell;
        this.signs = new ArrayList<Sign>(Arrays.asList(signs));
    }
}
