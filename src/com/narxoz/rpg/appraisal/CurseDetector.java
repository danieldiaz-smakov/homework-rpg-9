package com.narxoz.rpg.appraisal;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Flags risky finds — heuristics differ per artifact family (no instanceof).
 */
public class CurseDetector implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        boolean risky = weapon.getAttackBonus() >= 14;
        System.out.println("[Curse] Weapon \"" + weapon.getName() + "\": "
                + (risky ? "⚠ blood-thirst echo (handle with gloves)" : "clean steel"));
    }

    @Override
    public void visit(Potion potion) {
        boolean risky = potion.getName().toLowerCase().contains("shadow")
                || potion.getName().toLowerCase().contains("wither");
        System.out.println("[Curse] Potion \"" + potion.getName() + "\": "
                + (risky ? "⚠ unstable ichor" : "stable philter"));
    }

    @Override
    public void visit(Scroll scroll) {
        String spell = scroll.getSpellName().toLowerCase();
        boolean risky = spell.contains("necro") || spell.contains("void");
        System.out.println("[Curse] Scroll \"" + scroll.getName() + "\": "
                + (risky ? "⚠ forbidden clause detected" : "permitted arcana"));
    }

    @Override
    public void visit(Ring ring) {
        boolean risky = ring.getMagicBonus() >= 9;
        System.out.println("[Curse] Ring \"" + ring.getName() + "\": "
                + (risky ? "⚠ binding hum (soul strain)" : "whisper-quiet band"));
    }

    @Override
    public void visit(Armor armor) {
        boolean risky = armor.getDefenseBonus() >= 12 && armor.getWeight() > 20;
        System.out.println("[Curse] Armor \"" + armor.getName() + "\": "
                + (risky ? "⚠ grave plate weight curse risk" : "honorable ward"));
    }
}
