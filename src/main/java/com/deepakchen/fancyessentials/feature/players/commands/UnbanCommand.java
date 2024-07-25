package com.deepakchen.fancyessentials.feature.players.commands;

import com.deepakchen.fancyessentials.feature.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.deepakchen.fancyessentials.feature.LocaleManager;

import java.util.UUID;

public class UnbanCommand implements CommandExecutor {
    private PlayerManager playerManager;
    private LocaleManager localeManager;

    public UnbanCommand(LocaleManager localeManager, PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.localeManager = localeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.unban.usage"));
            return true;
        }

        UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        if (playerManager.unbanPlayer(uuid)) {
            sender.sendMessage(localeManager.getMessage("commands.unban.success").replace("{0}", args[0]));
        } else {
            sender.sendMessage(localeManager.getMessage("commands.unban.failure").replace("{0}", args[0]));
        }

        return true;
    }
}
