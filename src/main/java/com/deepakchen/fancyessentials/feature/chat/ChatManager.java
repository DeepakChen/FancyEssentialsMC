package com.deepakchen.fancyessentials.feature.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatManager implements Listener {
    private final ChatService chatService;
    private final ChatSettings chatSettings;

    public ChatManager(JavaPlugin plugin, ChatService chatService, ChatSettings chatSettings) {
        this.chatService = chatService;
        this.chatSettings = chatSettings;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!chatSettings.isChatEnabled()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Chat is currently disabled.");
            return;
        }

        if (!chatService.canChat(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You must wait before sending another message.");
        } else {
            chatService.markChatUsed(event.getPlayer());
        }
    }
}
