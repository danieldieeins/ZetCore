package com.zyneonstudios.zetcore;

import com.zyneonstudios.zetcore.api.API;
import com.zyneonstudios.zetcore.commands.ChatCommand;
import com.zyneonstudios.zetcore.commands.MoneyCommand;
import com.zyneonstudios.zetcore.commands.ZetCommand;
import com.zyneonstudios.zetcore.commands.system.GamemodeCommand;
import com.zyneonstudios.zetcore.commands.system.SpeedCommand;
import com.zyneonstudios.zetcore.commands.system.TestCommand;
import com.zyneonstudios.zetcore.commands.system.MapCommand;
import com.zyneonstudios.zetcore.listeners.PlayerChatListener;
import com.zyneonstudios.zetcore.listeners.PlayerInventoryListener;
import com.zyneonstudios.zetcore.listeners.PlayerJoinListener;
import com.zyneonstudios.zetcore.listeners.PlayerQuitListener;
import com.zyneonstudios.zetcore.modules.locks.commands.LockCommand;
import com.zyneonstudios.zetcore.modules.locks.commands.LockmodeCommand;
import com.zyneonstudios.zetcore.modules.locks.commands.TrustCommand;
import com.zyneonstudios.zetcore.modules.locks.commands.UnlockCommand;
import com.zyneonstudios.zetcore.modules.locks.listeners.LockInteractListener;
import com.zyneonstudios.zetcore.managers.InventoryManager;
import com.zyneonstudios.zetcore.utils.storage.Storage;
import com.zyneonstudios.zetcore.utils.storage.types.Config;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Config config;
    public static Storage storage;

    @Override
    public void onLoad() {
        API.getCommunicator().sendConsoleWarn("Lade §9"+getDescription().getName()+"§7 Version §b"+getDescription().getVersion()+"§7 von §e"+getDescription().getAuthors().get(0)+"§8...");
        instance = this;
        initConfig();
        API.getCommunicator().sendConsoleWarn("§9"+getDescription().getName()+"§7 wurde §egeladen§8.");
    }

    @Override
    public void onEnable() {
        API.getCommunicator().sendConsoleMessage("Aktiviere §9"+getDescription().getName()+"§7 Version §b"+getDescription().getVersion()+"§7 von §e"+getDescription().getAuthors().get(0)+"§8...");
        storage = new Storage("zyneon/storage.db");
        InventoryManager.initInventories();
        initCommands();
        initListeners();
        API.getCommunicator().sendConsoleMessage("§9"+getDescription().getName()+"§7 wurde §aaktiviert§8.");
    }

    @Override
    public void onDisable() {
        API.getCommunicator().sendConsoleError("Deaktiviere §9"+getDescription().getName()+"§7 Version §b"+getDescription().getVersion()+"§7 von §e"+getDescription().getAuthors().get(0)+"§8...");
        API.getCommunicator().sendConsoleError("§9"+getDescription().getName()+"§7 wurde §cdeaktiviert§8.");
    }

    private void initConfig() {
        API.getCommunicator().sendConsoleWarn("§8");
        API.getCommunicator().sendConsoleWarn("§8  §7Initialisiere Config§8...");
        config = new Config("zyneon/config.yml");
        API.getCommunicator().sendConsoleWarn("§8  §7Config initialisiert§8!");
        API.getCommunicator().sendConsoleWarn("§8");
    }

    private void initCommands() {
        API.getCommunicator().sendConsoleMessage("§8");
        API.getCommunicator().sendConsoleMessage("§8  §7Lade Commands§8...");

        GamemodeCommand gamemodeCommand = new GamemodeCommand();
        LockCommand lockCommand = new LockCommand();
        LockmodeCommand lockModeCommand = new LockmodeCommand();
        TrustCommand trustCommand = new TrustCommand();
        MoneyCommand moneyCommand = new MoneyCommand();

        API.getServer().initCommand(gamemodeCommand,gamemodeCommand);
        API.getServer().initCommand(new SpeedCommand(),null);
        API.getServer().initCommand(new TestCommand(),null);
        API.getServer().initCommand(new MapCommand(),null);
        API.getServer().initCommand(new ChatCommand(),null);
        API.getServer().initCommand(moneyCommand,moneyCommand);
        API.getServer().initCommand(new ZetCommand(),null);
        API.getServer().initCommand(lockCommand,lockCommand);
        API.getServer().initCommand(lockModeCommand,lockModeCommand);
        API.getServer().initCommand(trustCommand,trustCommand);
        API.getServer().initCommand(new UnlockCommand(),null);

        API.getCommunicator().sendConsoleMessage("§8  §7Commands geladen§8!");
        API.getCommunicator().sendConsoleMessage("§8");
    }

    private void initListeners() {
        API.getCommunicator().sendConsoleMessage("§8  §7Lade Listeners§8...");
        API.getServer().initListener(new PlayerChatListener());
        API.getServer().initListener(new PlayerInventoryListener());
        API.getServer().initListener(new PlayerJoinListener());
        API.getServer().initListener(new PlayerQuitListener());
        API.getServer().initListener(new LockInteractListener());
        API.getCommunicator().sendConsoleMessage("§8  §7Listener geladen§8!");
        API.getCommunicator().sendConsoleMessage("§8");
    }

    public static Plugin getPlugin() {
        return instance;
    }
}
