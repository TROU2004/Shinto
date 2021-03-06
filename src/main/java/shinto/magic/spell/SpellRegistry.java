package shinto.magic.spell;

import net.minecraft.entity.effect.StatusEffects;
import shinto.magic.chant.statement.MagicSign;
import shinto.magic.spell.spells.IterSpell;
import shinto.magic.spell.spells.StatusSpell;
import shinto.magic.spell.spells.TelumSpell;
import shinto.magic.spell.spells.TerminusSpell;

import java.util.Collections;
import java.util.HashSet;

public enum SpellRegistry {
    EFFECT_SPEED(new StatusSpell(1000, 1, StatusEffects.SPEED), new MagicSign[]{MagicSign.AER}),
    EFFECT_RESISTANCE(new StatusSpell(1500, 1, StatusEffects.RESISTANCE), new MagicSign[]{MagicSign.TERRA}),
    EFFECT_STRENGTH(new StatusSpell(500, 1, StatusEffects.STRENGTH), new MagicSign[]{MagicSign.IGNIS}),
    EFFECT_JUMP_BOOST(new StatusSpell(1000, 1, StatusEffects.JUMP_BOOST), new MagicSign[]{MagicSign.AQUA}),
    EFFECT_HEALTH_BOOST(new StatusSpell(500, 1, StatusEffects.HEALTH_BOOST), new MagicSign[]{MagicSign.VICTUS}),
    EFFECT_HUNGER(new StatusSpell(1500, 1, StatusEffects.HUNGER), new MagicSign[]{MagicSign.FAMES}),
    EFFECT_SLOWNESS(new StatusSpell(500, 1, StatusEffects.SLOWNESS), new MagicSign[]{MagicSign.LIMUS}),
    EFFECT_INSTANT_HEALTH(new StatusSpell(10, 1, StatusEffects.INSTANT_HEALTH), new MagicSign[]{MagicSign.SANO}),
    EFFECT_LEVITATION(new StatusSpell(150, 1, StatusEffects.LEVITATION), new MagicSign[]{MagicSign.VOLATUS}),
    EFFECT_NIGHT_VISION(new StatusSpell(1000, 1, StatusEffects.NIGHT_VISION), new MagicSign[]{MagicSign.SENSUS}),
    EFFECT_SLOWNESSS(new StatusSpell(1000, 1, StatusEffects.SLOWNESS), new MagicSign[]{MagicSign.DESIDIA}),
    ATTACK_TELUM(new TelumSpell(), new MagicSign[]{MagicSign.TELUM}),
    MOVE_TOWARDS(new IterSpell(), new MagicSign[]{MagicSign.ITER}),
    SHUT_DOWN(new TerminusSpell(), new MagicSign[]{MagicSign.LUCRUM, MagicSign.SUPERBIA, MagicSign.VACUOS, MagicSign.ALIENIS, MagicSign.TERMINUS});

    public final AbstractSpell spell;
    public final HashSet<MagicSign> signs = new HashSet<>();

    SpellRegistry(AbstractSpell spell, MagicSign[] signs) {
        this.spell = spell;
        Collections.addAll(this.signs, signs);
    }
}
