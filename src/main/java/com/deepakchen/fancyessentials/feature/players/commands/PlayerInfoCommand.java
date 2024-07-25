package com.deepakchen.fancyessentials.feature.players.commands;

import com.deepakchen.fancyessentials.feature.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class PlayerInfoCommand implements CommandExecutor {
    private LocaleManager localeManager;
    private PlayerManager playerManager;

    public PlayerInfoCommand(LocaleManager localeManager, PlayerManager playerManager) {
        this.localeManager = localeManager;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.playerinfo.usage"));
            return true;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        if (player != null) {
            sender.sendMessage(localeManager.getMessage("commands.playerinfo.details")
                    .replace("{0}", player.getName())
                    .replace("{1}", player.getDisplayName())
                    .replace("{2}", player.getLocation().toVector().toString())
                    .replace("{3}", player.getAddress().getAddress().getHostAddress()));
        } else {
            String lastOnline = playerManager.getLastOnline(Bukkit.getOfflinePlayer(args[0]).getUniqueId());
            if (lastOnline == null) {
                sender.sendMessage(localeManager.getMessage("messages.player.not_found"));
            } else {
                sender.sendMessage(localeManager.getMessage("commands.playerinfo.details.offline")
                        .replace("{0}", player.getName())
                        .replace("{1}", player.getDisplayName())
                        .replace("{2}", player.getAddress().getAddress().getHostAddress())
                        .replace("{3}", lastOnline)
                        .replace("{4}", player.getLocation().toVector().toString()));
            }
        }
        return true;
    }
}
