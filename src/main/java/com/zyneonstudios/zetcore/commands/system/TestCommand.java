package com.zyneonstudios.zetcore.commands.system;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length==0) {
            Bukkit.dispatchCommand(s,"skin set GommeHD");
        } else {
            Bukkit.dispatchCommand(s,"skin clear");
        }
        return false;
    }
}
