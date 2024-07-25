package com.deepakchen.fancyessentials.feature.teleport;

import com.deepakchen.fancyessentials.utils.CooldownTracker;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.*;

public class TeleportManager {
    private final CooldownTracker cooldownTracker;
    private final Map<UUID, TeleportRequest> teleportRequests = new HashMap<>();
    private final File teleportsFile;

    public TeleportManager(File teleportsFile, CooldownTracker cooldownTracker) {
        this.teleportsFile = teleportsFile;
        this.cooldownTracker = cooldownTracker;
        loadTeleports();
    }

    public void requestTeleport(Player requester, Player target, Location location, boolean isTpaHere) {
        teleportRequests.put(target.getUniqueId(), new TeleportRequest(requester.getUniqueId(), location, isTpaHere));
    }

    public boolean confirmTeleport(Player target) {
        TeleportRequest request = teleportRequests.remove(target.getUniqueId());
        if (request == null) {
            return false;
        }
        Player requester = target.getServer().getPlayer(request.getRequesterUUID());
        if (requester == null) {
            return false;
        }
        if (request.isTpaHere()) {
            target.teleport(request.getLocation());
            target.setFallDistance(0); // Reset fall distance
        } else {
            requester.teleport(request.getLocation());
            requester.setFallDistance(0); // Reset fall distance
        }
        return true;
    }

    public UUID denyTeleport(Player target) {
        TeleportRequest request = teleportRequests.remove(target.getUniqueId());
        if (request != null) {
            saveTeleports();
            return request.getRequesterUUID();
        }
        return null;
    }

    public boolean isOnCooldown(Player player) {
        return cooldownTracker.isOnCooldown(player);
    }

    private void loadTeleports() {
        if (!teleportsFile.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(teleportsFile))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                Map<UUID, TeleportRequest> loadedTeleports = (Map<UUID, TeleportRequest>) obj;
                teleportRequests.putAll(loadedTeleports);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTeleports() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teleportsFile))) {
            oos.writeObject(teleportRequests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CooldownTracker getCooldownTracker(){
        return cooldownTracker;
    }

    private static class TeleportRequest {
        private final UUID requesterUUID;
        private final Location location;
        private final boolean isTpaHere;

        public TeleportRequest(UUID requesterUUID, Location location, boolean isTpaHere) {
            this.requesterUUID = requesterUUID;
            this.location = location;
            this.isTpaHere = isTpaHere;
        }

        public UUID getRequesterUUID() {
            return requesterUUID;
        }

        public Location getLocation() {
            return location;
        }

        public boolean isTpaHere() {
            return isTpaHere;
        }
    }
}
