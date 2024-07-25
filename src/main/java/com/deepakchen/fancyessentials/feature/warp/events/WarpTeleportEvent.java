package com.deepakchen.fancyessentials.feature.warp.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@Getter
public class WarpTeleportEvent extends Event {
    @Getter
    private static final HandlerList handlers = new HandlerList();
    private final UUID playerUUID;

    public WarpTeleportEvent(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }


}
