package com.deepakchen.fancyessentials.feature.home;

import com.deepakchen.fancyessentials.feature.teleport.TeleportTaskService;
import com.deepakchen.fancyessentials.feature.home.events.HomeTeleportEvent;
import com.deepakchen.fancyessentials.feature.home.events.PreHomeTeleportEvent;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.UUID;

public class HomeTeleportService {
    private final TeleportTaskService teleportTaskService;
    private final LocaleManager localeManager;
    private final HomeManager homeManager;

    public static final String HOME_BYPASS = "fancyessentials.teleport.bypass";

    public HomeTeleportService(TeleportTaskService teleportTaskService, LocaleManager localeManager, HomeManager homeManager) {
        this.teleportTaskService = teleportTaskService;
        this.localeManager = localeManager;
        this.homeManager = homeManager;
    }

    public void teleportHome(Player player, String homeName) {
        UUID playerId = player.getUniqueId();
        Location homeLocation = homeManager.getHome(player, homeName);

        if (homeLocation == null) {
            player.sendMessage(localeManager.getMessage("commands.home.failure").replace("{0}", homeName));
            return;
        }

        PreHomeTeleportEvent preEvent = new PreHomeTeleportEvent(playerId, homeLocation);
        PluginManager pm = Bukkit.getPluginManager();
        pm.callEvent(preEvent);

        if (preEvent.isCancelled()) {
            player.sendMessage(localeManager.getMessage("commands.home.cancelled"));
            return;
        }

        long teleportDelay = player.hasPermission(HOME_BYPASS) ? 0 : 20L * 5; // Example delay time
        teleportTaskService.scheduleDelayedTeleport(player, preEvent.getHomeLocation(), teleportDelay, () -> {
            HomeTeleportEvent postEvent = new HomeTeleportEvent(playerId);
            pm.callEvent(postEvent);
            player.sendMessage(localeManager.getMessage("commands.home.success").replace("{0}", homeName));
        });
    }
}
