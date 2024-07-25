package com.deepakchen.fancyessentials.feature.warp;

import org.bukkit.Location;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class WarpManager {
    private final Map<String, Location> warps = new HashMap<>();
    private final File warpsFile;

    public WarpManager(File warpsFile) {
        this.warpsFile = warpsFile;
        loadWarps();
    }

    public void setWarp(String warpName, Location location) {
        warps.put(warpName, location);
        saveWarps();
    }

    public Location getWarp(String warpName) {
        return warps.get(warpName);
    }

    public boolean deleteWarp(String warpName) {
        if (warps.remove(warpName) != null) {
            saveWarps();
            return true;
        }
        return false;
    }

    private void loadWarps() {
        if (!warpsFile.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(warpsFile))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                Map<String, Location> loadedWarps = (Map<String, Location>) obj;
                warps.putAll(loadedWarps);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveWarps() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(warpsFile))) {
            oos.writeObject(warps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Location> getWarps() {
        return warps;
    }
}
