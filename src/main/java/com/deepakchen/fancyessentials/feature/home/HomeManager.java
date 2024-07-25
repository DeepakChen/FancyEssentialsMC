package com.deepakchen.fancyessentials.feature.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeManager {
    private final Map<UUID, Map<String, Location>> playerHomes;
    private final File homesFile;

    public HomeManager(File file) {
        this.playerHomes = new HashMap<>();
        this.homesFile = file;
        loadHomes();
    }

    public void setHome(Player player, String homeName, Location location) {
        UUID playerId = player.getUniqueId();
        playerHomes.computeIfAbsent(playerId, k -> new HashMap<>()).put(homeName.toLowerCase(), location);
        saveHomes();
    }

    public boolean deleteHome(Player player, String homeName) {
        UUID playerId = player.getUniqueId();
        Map<String, Location> homes = playerHomes.get(playerId);
        if (homes != null && homes.remove(homeName.toLowerCase()) != null) {
            saveHomes();
            return true;
        }
        return false;
    }

    public Location getHome(Player player, String homeName) {
        UUID playerId = player.getUniqueId();
        return playerHomes.getOrDefault(playerId, new HashMap<>()).get(homeName.toLowerCase());
    }

    private void saveHomes() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(homesFile))) {
            out.writeObject(playerHomes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomes() {
        if (homesFile.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(homesFile))) {
                Map<UUID, Map<String, Location>> loadedHomes = (Map<UUID, Map<String, Location>>) in.readObject();
                playerHomes.putAll(loadedHomes);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Location> getHomes(Player player) {
        return playerHomes.get(player.getUniqueId());
    }

}
