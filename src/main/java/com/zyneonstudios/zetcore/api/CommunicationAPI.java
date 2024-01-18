package com.zyneonstudios.zetcore.api;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommunicationAPI {

    public String prefix;
    public String warnPrefix;
    public String errorPrefix;

    public CommunicationAPI() {
        prefix = "§8[§9Zet§8] §7";
        warnPrefix = "§8[§eZet§8] §7";
        errorPrefix = "§8[§4Zet§8] §7";
    }

    public void sendRawMessage(CommandSender receiver, String message) {
        receiver.sendMessage(message);
    }

    public void sendPrefixMessage(CommandSender receiver, String message) {
        sendRawMessage(receiver,prefix+message);
    }

    public void sendMessage(CommandSender receiver, String message) {
        sendPrefixMessage(receiver,message);
        if(receiver instanceof Player) {
            Player player = (Player)receiver;
            player.playSound(player.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
        }
    }

    public void sendWarnMessage(CommandSender receiver, String message) {
        sendRawMessage(receiver,warnPrefix+message);
        if(receiver instanceof Player) {
            Player player = (Player)receiver;
            player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_PLING,100,100);
        }
    }

    public void sendErrorMessage(CommandSender receiver, String message) {
        sendRawMessage(receiver,errorPrefix+message);
        if(receiver instanceof Player) {
            Player player = (Player)receiver;
            player.playSound(player.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
        }
    }

    private void sendCard(CommandSender receiver) {
        sendPrefixMessage(receiver," ______     __  __     __   __     ______     ______     __   __    ");
        sendPrefixMessage(receiver,"/\\___  \\   /\\ \\_\\ \\   /\\ \"-.\\ \\   /\\  ___\\   /\\  __ \\   /\\ \"-.\\ \\   ");
        sendPrefixMessage(receiver,"\\/_/  /__  \\ \\____ \\  \\ \\ \\-.  \\  \\ \\  __\\   \\ \\ \\/\\ \\  \\ \\ \\-.  \\  ");
        sendPrefixMessage(receiver,"  /\\_____\\  \\/\\_____\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\ ");
        sendPrefixMessage(receiver,"  \\/_____/   \\/_____/   \\/_/ \\/_/   \\/_____/   \\/_____/   \\/_/ \\/_/ ");
    }

    public void sendConsoleMessage(String message) {
        sendPrefixMessage(Bukkit.getConsoleSender(),message);
    }

    public void sendConsoleWarn(String message) {
        sendWarnMessage(Bukkit.getConsoleSender(),message);
    }

    public void sendConsoleError(String message) {
        sendErrorMessage(Bukkit.getConsoleSender(),message);
    }

    public void sendConsoleCard() {
        sendCard(Bukkit.getConsoleSender());
    }

    public static class Strings {

        public static String noPermission = "§cDas darfst du nicht§8!";

    }
}