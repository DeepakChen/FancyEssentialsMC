package com.deepakchen.fancyessentials.feature.warp.commands;

import com.deepakchen.fancyessentials.feature.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class DelWarpCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final WarpManager warpManager;

    public DelWarpCommand(LocaleManager localeManager, WarpManager warpManager) {
        this.localeManager = localeManager;
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.delwarp.usage"));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        String warpName = args[0];

        if (warpManager.deleteWarp(warpName)) {
            player.sendMessage(localeManager.getMessage("commands.delwarp.success").replace("{0}", warpName));
        } else {
            player.sendMessage(localeManager.getMessage("commands.delwarp.failure").replace("{0}", warpName));
        }

        return true;
    }
}
