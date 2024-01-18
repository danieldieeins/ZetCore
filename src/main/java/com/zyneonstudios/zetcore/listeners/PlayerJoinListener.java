package com.zyneonstudios.zetcore.listeners;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!API.getUserHandler().users.containsKey(uuid)) {
            API.getUserHandler().users.put(uuid,new User(uuid));
        }
        User u = API.getUserHandler().getUser(uuid);
        p.setPlayerListHeaderFooter("\n§9Zyneon Testserver\n","\n§9Project'Z\n");
    }
}
