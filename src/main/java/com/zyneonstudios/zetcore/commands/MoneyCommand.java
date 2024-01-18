package com.zyneonstudios.zetcore.commands;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.objects.user.OfflineUser;
import com.zyneonstudios.zetcore.objects.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            User u = API.getUserHandler().getUser((Player)s);
            if(args.length==0) {
                u.sendMessage("Du hast aktuell §e"+u.getMoney()+" Mark§8!");
            } else if(args.length==1) {
                sendSyntax(s);
            } else if(args.length==2) {
                if(args[0].equalsIgnoreCase("get")) {
                    OfflineUser t;
                    if(Bukkit.getPlayer(args[1])!=null) {
                        t = API.getUserHandler().getUser(args[1]);
                    } else {
                        t = new OfflineUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                    }
                    u.sendMessage("§e"+t.getName()+"§7 hat §a"+t.getMoney()+" Mark§8!");
                } else if(s.hasPermission("zyneon.commands.money")) {
                    if(args[0].equalsIgnoreCase("set")) {
                        if(API.getStringUtil().isNumeric(args[1])) {
                            u.setMoney(Integer.parseInt(args[1]));
                            u.sendMessage("Du hast nun §e"+u.getMoney()+" Mark§8!");
                        } else {
                            u.sendErrorMessage("§4"+args[1]+"§c ist keine gültige Zahl§8!");
                        }
                    } else if(args[0].equalsIgnoreCase("add")) {
                        if(API.getStringUtil().isNumeric(args[1])) {
                            u.addMoney(Integer.parseInt(args[1]));
                            u.sendMessage("Du hast nun §e"+u.getMoney()+" Mark§8! (+"+args[1]+")");
                        } else {
                            u.sendErrorMessage("§4"+args[1]+"§c ist keine gültige Zahl§8!");
                        }
                    } else if(args[0].equalsIgnoreCase("remove")) {
                        if(API.getStringUtil().isNumeric(args[1])) {
                            u.removeMoney(Integer.parseInt(args[1]));
                            u.sendMessage("Du hast nun §e"+u.getMoney()+" Mark§8! (-"+args[1]+")");
                        } else {
                            u.sendErrorMessage("§4"+args[1]+"§c ist keine gültige Zahl§8!");
                        }
                    } else {
                        sendSyntax(s);
                    }
                } else {
                    sendSyntax(s);
                }
            } else {
                OfflineUser t;
                if(Bukkit.getPlayer(args[1])!=null) {
                    t = API.getUserHandler().getUser(args[1]);
                } else {
                    t = new OfflineUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                }
                if(API.getStringUtil().isNumeric(args[2])) {
                    int m = Integer.parseInt(args[2]);
                    if(args[0].equalsIgnoreCase("pay")) {
                        int money = u.getMoney();
                        if(money>=m) {
                            u.removeMoney(m);
                            t.addMoney(m);
                            if(Bukkit.getPlayer(args[1])!=null) {
                                API.getUserHandler().getUser(args[1]).sendMessage("§e"+u.getName()+"§7 hat dir §a"+m+" Mark §7überwiesen§8!");
                            }
                            u.sendMessage("Du hast §e"+t.getName()+"§a "+m+" Mark §7überwiesen§8!");
                        } else {
                            u.sendErrorMessage("Dazu hast du nicht genug Geld§8!");
                        }
                    } else {
                        if(s.hasPermission("zyneon.commands.money")) {
                            if(args[0].equalsIgnoreCase("set")) {
                                t.setMoney(m);
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    API.getUserHandler().getUser(args[1]).sendMessage("Du hast nun §e"+t.getMoney()+" Mark§8!");
                                }
                                u.sendMessage("§e"+t.getName()+"§7 hat nun §a"+t.getMoney()+" Mark§8!");
                            } else if(args[0].equalsIgnoreCase("add")) {
                                t.addMoney(m);
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    API.getUserHandler().getUser(args[1]).sendMessage("Du hast nun §e"+t.getMoney()+" Mark§8! (+"+m+")");
                                }
                                u.sendMessage("§e"+t.getName()+"§7 hat nun §a"+t.getMoney()+" Mark§8! (+"+m+")");
                            } else if(args[0].equalsIgnoreCase("remove")) {
                                t.removeMoney(m);
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    API.getUserHandler().getUser(args[1]).sendMessage("Du hast nun §e"+t.getMoney()+" Mark§8! (-"+m+")");
                                }
                                u.sendMessage("§e"+t.getName()+"§7 hat nun §a"+t.getMoney()+" Mark§8! (-"+m+")");
                            } else {
                                sendSyntax(s);
                            }
                        } else {
                            sendSyntax(s);
                        }
                    }
                } else {
                    u.sendErrorMessage("§4"+args[2]+"§c ist keine gültige Zahl§8!");
                }
            }
        } else {
            API.getCommunicator().sendErrorMessage(s,"Dazu musst du ein Spieler sein§8!");
        }
        return false;
    }

    private void sendSyntax(CommandSender s) {
        if(s.hasPermission("zyneon.commands.money")) {
            API.getCommunicator().sendErrorMessage(s,"§4Fehler: §c/money [get/pay/set/add/remove] [Spieler/Betrag] [Geld]");
        } else {
            API.getCommunicator().sendErrorMessage(s,"§4Fehler: §c/money §7[get/§fpay§7] [§fSpieler§7] §f(Betrag zum Überweisen)");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
        boolean team = s.hasPermission("zyneon.commands.money");
        ArrayList<String> completer = new ArrayList<>();
        if(args.length==1) {
            if(args[0].startsWith("p")) {
                completer.add("pay");
            } else if(args[0].startsWith("g")) {
                completer.add("get");
            } else if(team) {
                if(args[0].startsWith("s")) {
                    completer.add("set");
                } else if(args[0].startsWith("a")) {
                    completer.add("add");
                } else if(args[0].startsWith("r")) {
                    completer.add("remove");
                } else {
                    completer.add("pay");
                    completer.add("get");
                    completer.add("set");
                    completer.add("add");
                    completer.add("remove");
                }
            } else {
                completer.add("pay");
                completer.add("get");
            }
        } else if(args.length==2) {
            if(team) {
                if(API.getStringUtil().isNumeric(args[1])) {
                    completer.add("Geldbetrag");
                } else {
                    for(Player all:Bukkit.getOnlinePlayers()) {
                        completer.add(all.getName());
                    }
                    completer.add("Geldbetrag");
                }
            } else {
                for(Player all:Bukkit.getOnlinePlayers()) {
                    completer.add(all.getName());
                }
            }
        } else if(args.length==3) {
            if(!API.getStringUtil().isNumeric(args[1])) {
                completer.add("Geldbetrag");
            }
        }
        return completer;
    }
}
