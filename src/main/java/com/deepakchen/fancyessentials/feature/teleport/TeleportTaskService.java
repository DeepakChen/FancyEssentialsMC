package com.deepakchen.fancyessentials.feature.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.teleport.events.PreTeleportEvent;
import com.deepakchen.fancyessentials.feature.teleport.events.TeleportEvent;
import org.bukkit.plugin.Plugin;

public class TeleportTaskService {
    private final TeleportManager teleportManager;
    private final Plugin plugin;

    public TeleportTaskService(Plugin plugin, TeleportManager teleportManager) {
        this.plugin = plugin;
        this.teleportManager = teleportManager;
    }

    public void teleport(Player player, Location location) {
        PreTeleportEvent preEvent = new PreTeleportEvent(player.getUniqueId(), location);
        Bukkit.getServer().getPluginManager().callEvent(preEvent);

        if (!preEvent.isCancelled()) {
            player.teleport(location);
            player.setFallDistance(0); // Reset fall distance
            Bukkit.getServer().getPluginManager().callEvent(new TeleportEvent(player.getUniqueId(), location));
        }
    }

    public void scheduleDelayedTeleport(Player player, Location location, long delaySeconds, Runnable callback) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            teleport(player, location);
            if (callback != null) {
                callback.run();
            }
        }, delaySeconds);
    }
}
