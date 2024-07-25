package com.deepakchen.fancyessentials.feature.warp;

import com.deepakchen.fancyessentials.feature.teleport.TeleportTaskService;
import com.deepakchen.fancyessentials.feature.warp.events.WarpTeleportEvent;
import com.deepakchen.fancyessentials.feature.warp.events.PreWarpTeleportEvent;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.UUID;

public class WarpTeleportService {
    private final TeleportTaskService teleportTaskService;
    private final LocaleManager localeManager;
    private final WarpManager warpManager;

    public static final String WARP_BYPASS = "fancyessentials.teleport.bypass";

    public WarpTeleportService(TeleportTaskService teleportTaskService, LocaleManager localeManager, WarpManager warpManager) {
        this.teleportTaskService = teleportTaskService;
        this.localeManager = localeManager;
        this.warpManager = warpManager;
    }

    public void teleportWarp(Player player, String warpName) {
        UUID playerId = player.getUniqueId();
        Location warpLocation = warpManager.getWarp(warpName);

        if (warpLocation == null) {
            player.sendMessage(localeManager.getMessage("commands.warp.failure").replace("{0}", warpName));
            return;
        }

        PreWarpTeleportEvent preEvent = new PreWarpTeleportEvent(playerId, warpLocation);
        PluginManager pm = Bukkit.getPluginManager();
        pm.callEvent(preEvent);

        if (preEvent.isCancelled()) {
            player.sendMessage(localeManager.getMessage("commands.warp.cancelled"));
            return;
        }

        long teleportDelay = player.hasPermission(WARP_BYPASS) ? 0 : 20L * 5; // Example delay time
        teleportTaskService.scheduleDelayedTeleport(player, preEvent.getWarpLocation(), teleportDelay, () -> {
            WarpTeleportEvent postEvent = new WarpTeleportEvent(playerId);
            pm.callEvent(postEvent);
            player.sendMessage(localeManager.getMessage("commands.warp.success").replace("{0}", warpName));
        });
    }
}
