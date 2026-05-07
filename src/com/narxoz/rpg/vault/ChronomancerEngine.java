package com.narxoz.rpg.vault;

import com.narxoz.rpg.appraisal.CurseDetector;
import com.narxoz.rpg.appraisal.EnchantmentScanner;
import com.narxoz.rpg.appraisal.GoldAppraiser;
import com.narxoz.rpg.appraisal.WeightCalculator;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return summary counts for the rubric
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            return new VaultRunResult(0, 0, 0);
        }

        Hero lead = party.get(0);
        Inventory vaultLoot = lead.getInventory();
        int n = vaultLoot.size();

        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        System.out.println();
        System.out.println("--- Party entering the vault ---");
        for (Hero h : party) {
            System.out.println("  " + h);
        }

        System.out.println();
        System.out.println("=== APPRAISAL (Visitor): resale estimates ===");
        vaultLoot.accept(new GoldAppraiser());
        artifactsAppraised += n;

        System.out.println();
        System.out.println("=== APPRAISAL (Visitor): enchantment scan ===");
        vaultLoot.accept(new EnchantmentScanner());
        artifactsAppraised += n;

        System.out.println();
        System.out.println("=== APPRAISAL (Visitor): curse sweep ===");
        vaultLoot.accept(new CurseDetector());
        artifactsAppraised += n;

        Caretaker crystals = new Caretaker();

        System.out.println();
        System.out.println("=== SNAPSHOT (Memento): sealing hero state before the ward reacts ===");
        System.out.println("Lead hero BEFORE trap: " + lead);
        System.out.println("Inventory size: " + lead.getInventory().size());

        crystals.save(lead.createMemento());
        mementosCreated++;
        System.out.println("Time crystal stored. Caretaker depth: " + crystals.size());

        System.out.println();
        System.out.println("=== VAULT EVENT: chronal backlash ===");
        lead.takeDamage(45);
        lead.spendGold(75);
        System.out.println("Lead hero AFTER trap: " + lead);

        System.out.println();
        System.out.println("=== REWIND (Memento): restoring from the sealed crystal ===");
        HeroMemento rewind = crystals.undo();
        lead.restoreFromMemento(rewind);
        restoredCount++;
        System.out.println("Lead hero AFTER rewind: " + lead);
        System.out.println("Inventory size: " + lead.getInventory().size());

        System.out.println();
        System.out.println("=== OPEN/CLOSED (Visitor #4, no artifact/ edits): carry weight ===");
        WeightCalculator bulk = new WeightCalculator();
        vaultLoot.accept(bulk);
        artifactsAppraised += n;
        System.out.println("[Weight] Combined carry estimate: " + bulk.getTotalWeight() + " units");

        if (party.size() > 1) {
            System.out.println();
            System.out.println("--- Ally status (unchanged by this rewind) ---");
            System.out.println("  " + party.get(1));
        }

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}
