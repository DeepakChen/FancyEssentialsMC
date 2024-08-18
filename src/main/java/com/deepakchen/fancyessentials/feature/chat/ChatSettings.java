package com.deepakchen.fancyessentials.feature.chat;

import java.time.Duration;

public class ChatSettings {
    private boolean chatEnabled = true;
    private Duration chatDelay = Duration.ofSeconds(5);

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public Duration getChatDelay() {
        return chatDelay;
    }

    public void setChatDelay(Duration chatDelay) {
        this.chatDelay = chatDelay;
    }
}
