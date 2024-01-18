package com.zyneonstudios.zetcore.commands.system;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length==0) {
            Bukkit.dispatchCommand(s,"minecraft:gamemode");
        } else {
            String arg = args[0];
            if(args[0].equalsIgnoreCase("s")||args[0].equalsIgnoreCase("0")) {
                arg = "survival";
            } else if(args[0].equalsIgnoreCase("c")||args[0].equalsIgnoreCase("1")) {
                arg = "creative";
            } else if(args[0].equalsIgnoreCase("a")||args[0].equalsIgnoreCase("2")) {
                arg = "adventure";
            } else if(args[0].equalsIgnoreCase("w")||args[0].equalsIgnoreCase("3")) {
                arg = "spectator";
            }
            if(args.length>1) {
                Bukkit.dispatchCommand(s,"minecraft:gamemode "+arg+" "+args[1]);
            } else {
                Bukkit.dispatchCommand(s, "minecraft:gamemode " + arg);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length==1) {
            String arg = args[0].toLowerCase();
            ArrayList<String> completer = new ArrayList<>();
            if(arg.startsWith("sp")) {
                completer.add("spectator");
            } else if(arg.startsWith("su")) {
                completer.add("survival");
            } else if(arg.startsWith("s")) {
                completer.add("survival");
                completer.add("spectator");
            } else if(arg.startsWith("c")) {
                completer.add("creative");
            } else if(arg.startsWith("a")) {
                completer.add("adventure");
            } else if(arg.equalsIgnoreCase("")) {
                completer.add("survival");
                completer.add("creative");
                completer.add("adventure");
                completer.add("spectator");
                completer.add("s");
                completer.add("c");
                completer.add("a");
                completer.add("w");
                completer.add("0");
                completer.add("1");
                completer.add("2");
                completer.add("3");
            }
            return completer;
        }
        return null;
    }
}
