package com.zyneonstudios.zetcore.listeners;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        chat(API.getUserHandler().getUser(e.getPlayer()),e.getMessage());
    }

    public static void chat(User u, String message) {
        Player p = u.getPlayer();
        Bukkit.broadcastMessage(u.getName()+"§8: §7"+message);
    }
}