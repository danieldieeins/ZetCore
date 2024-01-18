package com.zyneonstudios.zetcore.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryManager {

    public static Inventory selectScenario = Bukkit.createInventory(null, InventoryType.CHEST,"Vorteilsszenario wählen");
    public static Inventory createScenario = Bukkit.createInventory(null, InventoryType.CHEST,"Szenario selbst erstellen");

    public static void fillInventory(Inventory inventory) {
        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i,ItemManager.placeholder());
        }
    }

    public static void initInventories() {
        fillInventory(selectScenario);
        fillInventory(createScenario);
        selectScenario.setItem(10,ItemManager.keineStarthilfe());
        selectScenario.setItem(11,ItemManager.geringverdiener());
        selectScenario.setItem(12,ItemManager.normalverdiener());
        selectScenario.setItem(13,ItemManager.ausgestattet());
        selectScenario.setItem(14,ItemManager.großverdiener());
        selectScenario.setItem(16,ItemManager.scenario());
    }
}