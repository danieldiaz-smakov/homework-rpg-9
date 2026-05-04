package com.narxoz.rpg.appraisal;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Estimates resale value in gold — rules differ by artifact family.
 */
public class GoldAppraiser implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        int estimate = weapon.getValue() + weapon.getAttackBonus() * 12;
        System.out.println("[Gold] Weapon \"" + weapon.getName() + "\" → ~" + estimate + " gp (blade premium)");
    }

    @Override
    public void visit(Potion potion) {
        int estimate = potion.getValue() + potion.getHealing() * 3;
        System.out.println("[Gold] Potion \"" + potion.getName() + "\" → ~" + estimate + " gp (consumable)");
    }

    @Override
    public void visit(Scroll scroll) {
        int estimate = scroll.getValue() * 2 + scroll.getSpellName().length();
        System.out.println("[Gold] Scroll \"" + scroll.getName() + "\" → ~" + estimate + " gp (arcane rarity)");
    }

    @Override
    public void visit(Ring ring) {
        int estimate = ring.getValue() + ring.getMagicBonus() * 7;
        System.out.println("[Gold] Ring \"" + ring.getName() + "\" → ~" + estimate + " gp (focus crystal)");
    }

    @Override
    public void visit(Armor armor) {
        int estimate = armor.getValue() + armor.getDefenseBonus() * 4;
        System.out.println("[Gold] Armor \"" + armor.getName() + "\" → ~" + estimate + " gp (smith weight)");
    }
}
