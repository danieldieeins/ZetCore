package com.zyneonstudios.zetcore.api;

import com.zyneonstudios.zetcore.managers.InventoryManager;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UserAPI {

    public HashMap<UUID,User> users;

    public UserAPI() {
        users = new HashMap<>();
    }

    @Deprecated
    public User getUser(String username) {
        try { return getUser(Bukkit.getOfflinePlayer(username).getUniqueId()); } catch (Exception ignore) {}
        return null;
    }


    public User getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    public User getUser(UUID uuid) {
        if(users.containsKey(uuid)) {
            return users.get(uuid);
        }
        return null;
    }

    public void openStartProcedure(User u, int i) {
        if(i==0) {
            openScenarioSelector(u);
        } else if(i==1) {
            openCharacterEditor(u);
        } else if(i==2) {
            openPointsManager(u);
        }
    }

    public void openScenarioSelector(User u) {
        u.getPlayer().openInventory(InventoryManager.selectScenario);
    }

    public void openPointsManager(User u) {
        u.sendMessage("1");
    }

    public void openCharacterEditor(User u) {
        u.sendMessage("2");
    }
}