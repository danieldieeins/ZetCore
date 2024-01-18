package com.zyneonstudios.zetcore.objects.user;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.listeners.PlayerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User extends OfflineUser {

    private Player player;
    private String interactMode;
    private Boolean roleplay;
    private Boolean teamMode;

    public User(UUID uuid) {
        super(uuid);
        interactMode = "null";
        teamMode = false;
        Bukkit.broadcastMessage("§8» §a"+getName());
        player = Bukkit.getPlayer(uuid);
        roleplay = true;
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean isTeamMode() {
        return teamMode;
    }

    public void setTeamMode(Boolean teamMode) {
        this.teamMode = teamMode;
    }

    public void setInteractMode(String interactMode) {
        this.interactMode = interactMode;
    }

    public String getInteractMode() {
        return interactMode;
    }

    public boolean isRoleplay() {
        return roleplay;
    }

    public void setRoleplay(boolean roleplay) {
        this.roleplay = roleplay;
    }

    public void sendRawMessage(String message) {
        player.sendMessage(message);
    }

    public void sendPrefixMessage(String message) {
        sendRawMessage(API.getCommunicator().prefix+message);
    }

    public void sendMessage(String message) {
        sendRawMessage(API.getCommunicator().prefix+message);
        player.playSound(player.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
    }

    public void sendWarnMessage(String message) {
        sendRawMessage(API.getCommunicator().warnPrefix+message);
        player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_PLING,100,100);
    }

    public void sendErrorMessage(String message) {
        sendRawMessage(API.getCommunicator().errorPrefix+message);
        player.playSound(player.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
    }

    public void chat(String message) {
        PlayerChatListener.chat(this,message);
    }

    @Override
    public void destroy() {
        Bukkit.broadcastMessage("§8« §c"+getName());
        player = null;
        teamMode = null;
        interactMode = null;
        roleplay = null;
        super.destroy();
    }
}