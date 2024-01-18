package com.zyneonstudios.zetcore.commands.system;

import com.zyneonstudios.zetcore.api.API;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s.hasPermission("zyneon.commands.system.world")) {
            if(args.length==1) {
                String world = args[0].toLowerCase();
                if(Bukkit.getWorld(world)==null) {
                    Bukkit.createWorld(new WorldCreator(world));
                    API.getCommunicator().sendMessage(s,"§7Welt §e"+args[0]+"§7 geladen§8!");
                }
                if(s instanceof Player) {
                    Player p = (Player)s;
                    API.getCommunicator().sendMessage(p,"§7Teleportiere§8...");
                    p.teleport(Bukkit.getWorld(world).getSpawnLocation());
                }
            } else {
                API.getCommunicator().sendErrorMessage(s,"Syntax");
            }
        } else {
            API.getCommunicator().sendErrorMessage(s,"Dazu bist du nicht berechtigt!");
        }
        return false;
    }
}
