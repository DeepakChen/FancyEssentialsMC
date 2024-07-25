package com.deepakchen.fancyessentials.feature.home.events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PreHomeTeleportEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location homeLocation;
    private boolean cancelled;

    public PreHomeTeleportEvent(UUID playerUUID, Location homeLocation) {
        this.playerUUID = playerUUID;
        this.homeLocation = homeLocation;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Location getHomeLocation() {
        return homeLocation;
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
