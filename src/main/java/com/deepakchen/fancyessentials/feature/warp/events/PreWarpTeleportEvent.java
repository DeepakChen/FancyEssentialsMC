package com.deepakchen.fancyessentials.feature.warp.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@Getter
public class PreWarpTeleportEvent extends Event {
    @Getter
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;
    private final Location warpLocation;
    @Setter
    private boolean cancelled;

    public PreWarpTeleportEvent(UUID playerUUID, Location warpLocation) {
        this.playerUUID = playerUUID;
        this.warpLocation = warpLocation;
    }

}
