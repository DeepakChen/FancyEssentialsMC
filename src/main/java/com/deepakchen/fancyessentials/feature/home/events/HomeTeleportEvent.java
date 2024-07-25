package com.deepakchen.fancyessentials.feature.home.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@Getter
public class HomeTeleportEvent extends Event {
    @Getter
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private final UUID playerUUID;

    public HomeTeleportEvent(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

}
