package com.deepakchen.fancyessentials.feature.warp.commands;

import com.deepakchen.fancyessentials.feature.warp.WarpTeleportService;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final WarpTeleportService warpTeleportService;

    public WarpCommand(LocaleManager localeManager, WarpTeleportService warpTeleportService) {
        this.localeManager = localeManager;
        this.warpTeleportService = warpTeleportService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.warp.usage"));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        String warpName = args[0];

        warpTeleportService.teleportWarp(player, warpName);
        return true;
    }
}
