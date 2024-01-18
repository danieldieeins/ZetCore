package com.zyneonstudios.zetcore.modules.locks.commands;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player)s;
            User u = API.getUserHandler().getUser(p);
            u.setInteractMode("unlocking");
            u.sendMessage("Klicke den Block an§8,§7 den du entsichern möchtest§8...");
        } else {
            API.getCommunicator().sendErrorMessage(s,"§cDazu musst du ein Spieler sein§8!");
        }
        return false;
    }
}
