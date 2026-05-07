package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

/**
 * Entry point for Homework 9 — Chronomancer's Vault: Visitor + Memento.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory vaultMix = new Inventory();
        vaultMix.addArtifact(new Weapon("Moonfang Blade", 120, 18, 16));
        vaultMix.addArtifact(new Potion("Shadow Draught", 40, 2, 25));
        vaultMix.addArtifact(new Scroll("Crimson Ledger", 90, 1, "Spark Bolt"));
        vaultMix.addArtifact(new Ring("Serpent Coil", 200, 1, 10));
        vaultMix.addArtifact(new Armor("Graveward Plate", 300, 35, 14));

        Hero kael = new Hero("Kael", 100, 45, 18, 12, 150, vaultMix);
        Hero mira = new Hero("Mira", 88, 30, 14, 10, 40, new Inventory());

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(kael, mira));

        System.out.println();
        System.out.println("=== VaultRunResult (summary) ===");
        System.out.println(result);
    }
}
