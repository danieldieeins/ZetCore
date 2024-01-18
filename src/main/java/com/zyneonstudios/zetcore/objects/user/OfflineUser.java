package com.zyneonstudios.zetcore.objects.user;

import com.zyneonstudios.zetcore.utils.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class OfflineUser {

    private UUID uuid;
    private String username;
    private OfflinePlayer offlinePlayer;
    private Storage userStorage;
    private Integer money;

    public OfflineUser(UUID uuid) {
        this.uuid = uuid;
        this.userStorage = new Storage("zyneon/users/"+uuid+"/storage.db");
        if(userStorage.get("user.start")==null) {
            userStorage.setInteger("user.start",0);
        }
        try {
            this.offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        } catch (Exception e) { this.offlinePlayer = null; }
        try {
            this.username = offlinePlayer.getName();
        } catch (Exception e) { this.username = null; }
        if(!userStorage.exist("stats.money")) {
            userStorage.setInteger("stats.money",0);
        }
        money = getUserStorage().getInteger("stats.money");
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(int money) {
        userStorage.setInteger("stats.money",money);
        this.money = money;
    }

    public void addMoney(int money) {
        setMoney(this.money+money);
    }

    public void removeMoney(int money) {
        setMoney(this.money-money);
    }

    public Storage getUserStorage() {
        return userStorage;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return username;
    }

    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public void destroy() {
        uuid = null;
        offlinePlayer = null;
        username = null;
        userStorage = null;
        money = null;
        System.gc();
    }
}