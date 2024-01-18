package com.zyneonstudios.zetcore.modules.locks.commands;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.modules.locks.managers.TrustManager;
import com.zyneonstudios.zetcore.objects.user.User;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrustCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (s instanceof Player) {
            Player p = (Player)s;
            if (args.length > 1) {
                User u = API.getUserHandler().getUser(p);
                if (args[0].equalsIgnoreCase("add")) {
                    OfflinePlayer oP = Bukkit.getOfflinePlayer(args[1]);
                    if (TrustManager.isTrusted(oP.getUniqueId(), p.getUniqueId())) {
                        u.sendErrorMessage("§cDiese*r Spieler*In ist bereits getrustet§8.");
                    } else {
                        TrustManager.addTrusted(oP.getUniqueId(), p.getUniqueId());
                        u.sendMessage("Du hast §e" + oP.getName() + "§7 erfolgreich getrusted§8!");
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    u.sendRawMessage("§8=============[§eMember§7,§e denen du vertraust§8]=============");
                    if(TrustManager.trustedUsers(u.getUUID()).size()<1) {
                        u.sendErrorMessage("Du vertraust zurzeit niemandem!");
                        u.sendMessage("Mache §e/trust add [Spieler*In] §7um jemandem zu vertrauen§8.");
                        return false;
                    }
                    for(String list:TrustManager.trustedUsers(u.getUUID())) {
                        TextComponent message = new TextComponent("§8- §e"+Bukkit.getOfflinePlayer(UUID.fromString(list)).getName());
                        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e"+list).create()));
                        TextComponent component = new TextComponent(" §8| §cEntfernen");
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§cKlicke zum entfernen").create()));
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/trust remove "+Bukkit.getOfflinePlayer(UUID.fromString(list)).getName()));
                        message.addExtra(component);
                        p.spigot().sendMessage(message);
                    }
                } else if (args[0].equalsIgnoreCase("who")) {
                    u.sendRawMessage("§8=============[§eMember§7,§e die dir vertrauen§8]=============");
                    if(TrustManager.trustedList(u.getUUID()).size()<1) {
                        u.sendErrorMessage("Niemand vertraut dir!");
                        u.sendMessage("Sobald dich jemand mit §e/trust add "+p.getName()+"§7 zu seinen vertrauten Spieler*Innen hinzufügt, siehst du das hier§8.");
                        return false;
                    }
                    for(String list:TrustManager.trustedList(u.getUUID())) {
                        TextComponent message = new TextComponent("§8- §e"+Bukkit.getOfflinePlayer(UUID.fromString(list)).getName());
                        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e"+list).create()));
                        TextComponent component = new TextComponent(" §8| §cEntfernen");
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§cKlicke zum entfernen").create()));
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/trust removeSelf "+Bukkit.getOfflinePlayer(UUID.fromString(list)).getName()));
                        message.addExtra(component);
                        p.spigot().sendMessage(message);
                    }
                } else if (args[0].equalsIgnoreCase("removeSelf")) {
                    OfflinePlayer oP = Bukkit.getOfflinePlayer(args[1]);
                    if (TrustManager.isTrusted(p.getUniqueId(), oP.getUniqueId())) {
                        TrustManager.removeTrusted(p.getUniqueId(), oP.getUniqueId());
                        u.sendMessage("Du hast dich erfolgreich aus den Trusts von §e" + oP.getName() + "§7 entfernt§8!");
                    } else {
                        u.sendErrorMessage("§cDiese*r Spieler*In vertraut dir nicht§8.");
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    OfflinePlayer oP = Bukkit.getOfflinePlayer(args[1]);
                    if (TrustManager.isTrusted(oP.getUniqueId(), p.getUniqueId())) {
                        TrustManager.removeTrusted(oP.getUniqueId(), p.getUniqueId());
                        u.sendMessage("Du hast §e" + oP.getName() + "§7 erfolgreich aus deinen Trusts entfernt§8!");
                    } else {
                        u.sendErrorMessage("§cDiese*r Spieler*In ist nicht getrustet§8.");
                    }
                } else {
                    API.getCommunicator().sendErrorMessage(s, "§c/trust [add/list/remove/removeSelf/who] [Spieler*In]");
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    p.performCommand("trust list list");
                } else if (args[0].equalsIgnoreCase("who")) {
                    p.performCommand("trust who who");
                } else {
                    API.getCommunicator().sendErrorMessage(s, "§c/trust [add/list/remove/removeSelf/who] [Spieler*In]");
                }
            } else {
                API.getCommunicator().sendErrorMessage(s, "§c/trust [add/list/remove/removeSelf/who] [Spieler*In]");
            }
        } else {
            API.getCommunicator().sendErrorMessage(s, "§cDazu musst du ein Spieler sein§8!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender s, Command command, String label, String[] args) {
        ArrayList<String> completer = new ArrayList<>();
        if(args.length==1) {
            completer.add("add");
            completer.add("who");
            completer.add("removeSelf");
            completer.add("remove");
            completer.add("list");
        } else if(args.length==2) {
            if(args[0].equalsIgnoreCase("remove")||args[0].equalsIgnoreCase("removeSelf")||args[0].equalsIgnoreCase("add")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    completer.add(all.getName());
                }
            }
        }
        return completer;
    }
}
