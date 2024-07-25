package com.deepakchen.fancyessentials.feature.teleport.events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PreTeleportEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location destination;
    private boolean cancelled;

    public PreTeleportEvent(UUID playerUUID, Location destination) {
        this.playerUUID = playerUUID;
        this.destination = destination;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Location getDestination() {
        return destination;
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
