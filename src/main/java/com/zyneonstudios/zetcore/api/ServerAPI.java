package com.zyneonstudios.zetcore.api;

import com.zyneonstudios.zetcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Random;

public class ServerAPI {

    public void initCommand(CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        String command = commandExecutor.getClass().getSimpleName().replace("Command","");
        API.getCommunicator().sendConsoleMessage("§8  §f-> §7Lade Command §e"+command+"§8...");
        try {
            Main.instance.getCommand(command).setExecutor(commandExecutor);
            if (tabCompleter != null) {
                Main.instance.getCommand(command).setTabCompleter(tabCompleter);
            }
        } catch (Exception e) {
            API.getCommunicator().sendConsoleError("§8  §f-> §7Der Command §e"+command+"§7 konnte §cnicht§7 geladen werden§8!");
        }
    }

    public void initListener(Listener listener) {
        String name = listener.getClass().getSimpleName().replace("Listener","");
        API.getCommunicator().sendConsoleMessage("§8  §f-> §7Lade Listener §e"+name+"§8...");
        try {
            Bukkit.getPluginManager().registerEvents(listener,Main.instance);
        } catch (Exception e) {
            API.getCommunicator().sendConsoleError("§8  §f-> §7Der Listener §e"+name+"§7 konnte §cnicht§7 geladen werden§8!");
        }
    }

    public Player getRandomPlayer() {
        try {
            ArrayList<Player> allPlayers = getOnlinePlayers();
            return allPlayers.get(new Random().nextInt(allPlayers.size()));
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Player> getOnlinePlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }
}