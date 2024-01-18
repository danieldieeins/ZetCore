package com.zyneonstudios.zetcore.listeners;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
        Player p = e.getPlayer();
        User u = API.getUserHandler().getUser(p);
        API.getUserHandler().users.remove(p.getUniqueId());
        u.destroy();
    }
}