package com.zyneonstudios.zetcore.listeners;

import com.zyneonstudios.zetcore.managers.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory()!=null) {
            if(e.getCurrentItem()!=null) {
                if(e.getCurrentItem().getItemMeta()!=null){
                    ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                    if(itemMeta.equals(ItemManager.placeholder().getItemMeta())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}