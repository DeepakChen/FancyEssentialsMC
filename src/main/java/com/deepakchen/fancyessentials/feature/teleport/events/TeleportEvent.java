package com.deepakchen.fancyessentials.feature.teleport.events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class TeleportEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location destination;

    public TeleportEvent(UUID playerUUID, Location destination) {
        this.playerUUID = playerUUID;
        this.destination = destination;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
