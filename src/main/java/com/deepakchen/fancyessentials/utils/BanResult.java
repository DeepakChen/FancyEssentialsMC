package com.deepakchen.fancyessentials.utils;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BanResult {
    private final boolean success;
    private final UUID uuid;
    private final String duration;

    public BanResult(boolean success, UUID uuid, String duration) {
        this.success = success;
        this.uuid = uuid;
        this.duration = duration;
    }

}
