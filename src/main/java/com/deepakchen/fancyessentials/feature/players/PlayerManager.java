package com.deepakchen.fancyessentials.feature.players;

import com.deepakchen.fancyessentials.utils.BanResult;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class PlayerManager {
    private File dataFile;
    private YamlConfiguration config;

    public PlayerManager(File playerFile) {
        dataFile = playerFile;
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(dataFile);
    }

    public BanResult banPlayer(UUID uuid, String reason) {
        config.set(uuid.toString() + ".reason", reason);
        config.set(uuid.toString() + ".end", "permanent");
        save();
        // 不再直接踢出玩家，因为他们可能不在线
        return new BanResult(true, uuid, "permanent");
    }

    public BanResult tempBanPlayer(UUID uuid, String reason, String duration) {
        config.set(uuid.toString() + ".reason", reason);
        config.set(uuid.toString() + ".end", System.currentTimeMillis() + parseDuration(duration));
        save();
        // 不再直接踢出玩家，因为他们可能不在线
        return new BanResult(true, uuid, duration);
    }

    public boolean unbanPlayer(UUID uuid) {
        if (config.contains(uuid.toString())) {
            config.set(uuid.toString(), null);
            save();
            return true;
        }
        return false;
    }

    public boolean isBanned(UUID uuid) {
        return config.contains(uuid.toString());
    }

    public long getBanEnd(UUID uuid) {
        if (config.contains(uuid.toString() + ".end")) {
            String end = config.getString(uuid.toString() + ".end");
            if ("permanent".equals(end)) {
                return -1;  // Return -1 for permanent bans
            }
            return Long.parseLong(end);
        }
        return 0;  // Not banned
    }

    public String getBanReason(UUID uuid) { return config.getString(uuid.toString() + ".reason"); }

    public String getLastOnline(UUID uuid){
        return Bukkit.getOfflinePlayer(uuid).getLastPlayed() + "ms";
    }

    private long parseDuration(String duration) {
        // Assuming duration is in the format "1d" for 1 day, "1h" for 1 hour, etc.
        char unit = duration.charAt(duration.length() - 1);
        long time = Long.parseLong(duration.substring(0, duration.length() - 1));
        switch (unit) {
            case 'd': return time * 24 * 60 * 60 * 1000; // days
            case 'h': return time * 60 * 60 * 1000; // hours
            case 'm': return time * 60 * 1000; // minutes
            case 's': return time * 1000; // seconds
            default: return time; // default to milliseconds for invalid format
        }
    }

    private void save() {
        try {
            config.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}