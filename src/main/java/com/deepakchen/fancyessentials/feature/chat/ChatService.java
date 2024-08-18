package com.deepakchen.fancyessentials.feature.chat;

import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatService {
    private final ChatSettings settings;
    private final Map<UUID, Instant> cooldowns = new HashMap<>();

    public ChatService(ChatSettings settings) {
        this.settings = settings;
    }

    public void markChatUsed(Player player) {
        cooldowns.put(player.getUniqueId(), Instant.now().plus(settings.getChatDelay()));
    }

    public boolean canChat(Player player) {
        return !cooldowns.getOrDefault(player.getUniqueId(), Instant.MIN).isAfter(Instant.now());
    }
}
