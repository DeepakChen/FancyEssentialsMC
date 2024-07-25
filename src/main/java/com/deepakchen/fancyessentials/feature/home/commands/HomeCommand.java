package com.deepakchen.fancyessentials.feature.home.commands;

import com.deepakchen.fancyessentials.feature.home.HomeTeleportService;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final HomeTeleportService homeTeleportService;

    public HomeCommand(LocaleManager localeManager, HomeTeleportService homeTeleportService) {
        this.localeManager = localeManager;
        this.homeTeleportService = homeTeleportService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        String homeName = args.length > 0 ? args[0] : "default";

        homeTeleportService.teleportHome(player, homeName);
        return true;
    }
}
