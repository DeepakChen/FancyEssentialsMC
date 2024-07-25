package com.deepakchen.fancyessentials;

import com.deepakchen.fancyessentials.feature.LocaleManager;
import com.deepakchen.fancyessentials.feature.home.HomeManager;
import com.deepakchen.fancyessentials.feature.home.HomeTeleportService;
import com.deepakchen.fancyessentials.feature.home.commands.*;
import com.deepakchen.fancyessentials.feature.players.PlayerManager;
import com.deepakchen.fancyessentials.feature.players.commands.*;
import com.deepakchen.fancyessentials.feature.randomteleport.RTPCommand;
import com.deepakchen.fancyessentials.feature.teleport.TeleportManager;
import com.deepakchen.fancyessentials.feature.teleport.TeleportTaskService;
import com.deepakchen.fancyessentials.feature.teleport.commands.*;
import com.deepakchen.fancyessentials.feature.warp.WarpManager;
import com.deepakchen.fancyessentials.feature.warp.WarpTeleportService;
import com.deepakchen.fancyessentials.feature.warp.commands.*;
import com.deepakchen.fancyessentials.listener.BanListener;
import com.deepakchen.fancyessentials.utils.CommandRegistrar;
import com.deepakchen.fancyessentials.utils.CooldownTracker;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class FancyEssentials extends JavaPlugin {
    @Getter
    private LocaleManager localeManager;
    @Getter
    private CommandRegistrar commandRegistrar;

    @Getter
    private final PlayerManager playerManager = new PlayerManager(new File(getDataFolder(), "banned-players.yml"));

    @Getter
    private final TeleportManager teleportManager = new TeleportManager(new File(getDataFolder(), "teleport.dat"), new CooldownTracker());

    @Getter
    private final HomeManager homeManager = new HomeManager(new File(getDataFolder(), "players_homes.dat"));

    @Getter
    private final WarpManager warpManager = new WarpManager(new File(getDataFolder(), "server_warps.dat"));

    @Override
    public void onEnable() {
        // Initialize managers and services
        localeManager = LocaleManager.getInstance(new Locale("zh_cn"), this);
        commandRegistrar = new CommandRegistrar(this);

        registerAllCommands();
        registerAllEvents();

        getLogger().info(localeManager.getMessage("messages.plugin.enable"));
    }

    private void registerAllCommands() {
        Map<String, CommandExecutor> commands = new HashMap<>();

        // Player Manager Commands
        commands.put("ban", new BanCommand(localeManager, playerManager));
        commands.put("unban", new UnbanCommand(localeManager, playerManager));
        commands.put("kick", new KickCommand(localeManager));
        commands.put("playerinfo", new PlayerInfoCommand(localeManager, playerManager));

        // Teleport commands
        TeleportTaskService teleportTaskService = new TeleportTaskService(this, teleportManager);
        commands.put("tp", new TpCommand(localeManager, teleportManager, teleportTaskService));
        commands.put("tpa", new TpaCommand(localeManager, teleportManager));
        commands.put("tpahere", new TpaHereCommand(localeManager, teleportManager));
        commands.put("tpaccept", new TpAcceptCommand(localeManager, teleportManager));
        commands.put("tpdeny", new TpDenyCommand(localeManager, teleportManager));

        // Random Teleport commands
        commands.put("rtp", new RTPCommand(localeManager));

        // Home commands
        HomeTeleportService homeTeleportService = new HomeTeleportService(teleportTaskService, localeManager, homeManager);
        commands.put("home", new HomeCommand(localeManager, homeTeleportService));
        commands.put("sethome", new SetHomeCommand(localeManager, homeManager));
        commands.put("delhome", new DelHomeCommand(localeManager, homeManager));
        commands.put("homelist", new HomeListCommand(localeManager, homeManager));

        // Warp commands
        WarpTeleportService warpTeleportService = new WarpTeleportService(teleportTaskService, localeManager, warpManager);
        commands.put("warp", new WarpCommand(localeManager, warpTeleportService));
        commands.put("setwarp", new SetWarpCommand(localeManager, warpManager));
        commands.put("delwarp", new DelWarpCommand(localeManager, warpManager));
        commands.put("warplist", new WarpListCommand(localeManager, warpManager));

        // Register all commands using CommandRegistrar
        commandRegistrar.registerCommands(commands);
    }

    private void registerAllEvents() {
        getServer().getPluginManager().registerEvents(new BanListener(localeManager, playerManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(localeManager.getMessage("messages.plugin.disable"));
    }
}
