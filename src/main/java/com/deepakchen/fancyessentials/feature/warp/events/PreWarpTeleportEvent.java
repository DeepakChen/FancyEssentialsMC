package com.deepakchen.fancyessentials.feature.warp.events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PreWarpTeleportEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location warpLocation;
    private boolean cancelled;

    public PreWarpTeleportEvent(UUID playerUUID, Location warpLocation) {
        this.playerUUID = playerUUID;
        this.warpLocation = warpLocation;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Location getWarpLocation() {
        return warpLocation;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
