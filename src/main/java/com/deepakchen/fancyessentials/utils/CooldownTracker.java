package com.deepakchen.fancyessentials.utils;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownTracker {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public void setCooldown(Player player, int seconds) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (seconds * 1000L));
    }

    public boolean isOnCooldown(Player player) {
        Long expireTime = cooldowns.get(player.getUniqueId());
        if (expireTime == null) {
            return false;
        }

        if (System.currentTimeMillis() > expireTime) {
            cooldowns.remove(player.getUniqueId());
            return false;
        }

        return true;
    }

    public int getCooldown(Player player) {
        Long expireTime = cooldowns.get(player.getUniqueId());
        if (expireTime == null) {
            return 0;
        }

        long timeLeft = expireTime - System.currentTimeMillis();
        return timeLeft > 0 ? (int) (timeLeft / 1000) : 0;
    }
}
