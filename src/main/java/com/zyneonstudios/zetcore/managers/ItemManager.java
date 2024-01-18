package com.zyneonstudios.zetcore.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

    public static ItemStack placeholder() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_PLACEHOLDER"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§0");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack keineStarthilfe() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_X"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§eKeine Starthilfe");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack geringverdiener() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_SAD"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cGeringverdiener");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack normalverdiener() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_HAPPY"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§bNormalverdiener");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack ausgestattet() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_WHEEL"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§aAusgestattet");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack großverdiener() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_RICH"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§dGroßverdiener");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack scenario() {
        ItemStack item = new ItemStack(Material.valueOf("ZETCORE_PENCIL"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§3Vorteile selbst wählen");
        item.setItemMeta(itemMeta);
        return item;
    }
}