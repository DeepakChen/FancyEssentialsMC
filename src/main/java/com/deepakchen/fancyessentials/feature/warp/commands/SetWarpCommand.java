package com.deepakchen.fancyessentials.feature.warp.commands;

import com.deepakchen.fancyessentials.feature.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class SetWarpCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final WarpManager warpManager;

    public SetWarpCommand(LocaleManager localeManager, WarpManager warpManager) {
        this.localeManager = localeManager;
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.setwarp.usage"));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        String warpName = args[0];
        Location location = player.getLocation();

        warpManager.setWarp(warpName, location);
        player.sendMessage(localeManager.getMessage("commands.setwarp.success").replace("{0}", warpName));
        return true;
    }
}
