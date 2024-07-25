package com.deepakchen.fancyessentials.feature.warp.commands;

import com.deepakchen.fancyessentials.feature.LocaleManager;
import com.deepakchen.fancyessentials.feature.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;

import java.util.Map;

public class WarpListCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final WarpManager warpManager;

    public WarpListCommand(LocaleManager localeManager, WarpManager warpManager) {
        this.localeManager = localeManager;
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, Location> warps = warpManager.getWarps();

        if (warps.isEmpty()) {
            sender.sendMessage(localeManager.getMessage("commands.warplist.no_warps"));
            return true;
        }

        sender.sendMessage(localeManager.getMessage("commands.warplist.header"));
        for (Map.Entry<String, Location> entry : warps.entrySet()) {
            String warpName = entry.getKey();
            Location loc = entry.getValue();
            sender.sendMessage(localeManager.getMessage("commands.warplist.entry")
                    .replace("{0}", warpName)
                    .replace("{1}", loc.getWorld().getName())
                    .replace("{2}", String.valueOf(loc.getBlockX()))
                    .replace("{3}", String.valueOf(loc.getBlockY()))
                    .replace("{4}", String.valueOf(loc.getBlockZ())));
        }

        return true;
    }
}
