package com.deepakchen.fancyessentials.feature.home.events;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
public class PreHomeTeleportEvent extends Event {
    @Getter
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location homeLocation;
    @lombok.Setter
    private boolean cancelled;

    public PreHomeTeleportEvent(UUID playerUUID, Location homeLocation) {
        this.playerUUID = playerUUID;
        this.homeLocation = homeLocation;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

}
