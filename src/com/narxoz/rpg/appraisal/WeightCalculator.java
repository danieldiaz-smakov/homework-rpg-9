package com.narxoz.rpg.appraisal;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Open/closed extension: total carry weight without modifying classes under {@code artifact/}.
 */
public class WeightCalculator implements ArtifactVisitor {

    private int runningTotal;

    public int getTotalWeight() {
        return runningTotal;
    }

    @Override
    public void visit(Weapon weapon) {
        int w = weapon.getWeight();
        runningTotal += w;
        System.out.println("[Weight] Weapon \"" + weapon.getName() + "\" — " + w + " (heavy swing mass)");
    }

    @Override
    public void visit(Potion potion) {
        int w = potion.getWeight();
        runningTotal += w;
        System.out.println("[Weight] Potion \"" + potion.getName() + "\" — " + w + " vials (light)");
    }

    @Override
    public void visit(Scroll scroll) {
        int w = scroll.getWeight();
        runningTotal += w;
        System.out.println("[Weight] Scroll \"" + scroll.getName() + "\" — " + w + " parchment");
    }

    @Override
    public void visit(Ring ring) {
        int w = ring.getWeight();
        runningTotal += w;
        System.out.println("[Weight] Ring \"" + ring.getName() + "\" — " + w + " (negligible)");
    }

    @Override
    public void visit(Armor armor) {
        int w = armor.getWeight();
        runningTotal += w;
        System.out.println("[Weight] Armor \"" + armor.getName() + "\" — " + w + " plates (bulk)");
    }
}
