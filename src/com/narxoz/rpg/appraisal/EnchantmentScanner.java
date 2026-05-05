package com.narxoz.rpg.appraisal;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Prints magical readouts — wording differs strongly by artifact type.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        System.out.println("[Scan] WEAPON sigil: strike +" + weapon.getAttackBonus()
                + " | edge resonance: " + (weapon.getAttackBonus() > 10 ? "high" : "moderate"));
    }

    @Override
    public void visit(Potion potion) {
        System.out.println("[Scan] POTION brew: restorative +" + potion.getHealing()
                + " HP | vapor trail: " + (potion.getHealing() >= 30 ? "dense" : "light"));
    }

    @Override
    public void visit(Scroll scroll) {
        System.out.println("[Scan] SCROLL ink: \"" + scroll.getSpellName()
                + "\" | weave stability: glyph-bound");
    }

    @Override
    public void visit(Ring ring) {
        System.out.println("[Scan] RING channel: arcane +" + ring.getMagicBonus()
                + " | band warmth: " + (ring.getMagicBonus() > 5 ? "hot" : "tepid"));
    }

    @Override
    public void visit(Armor armor) {
        System.out.println("[Scan] ARMOR plates: ward +" + armor.getDefenseBonus()
                + " | coverage map: full silhouette");
    }
}
