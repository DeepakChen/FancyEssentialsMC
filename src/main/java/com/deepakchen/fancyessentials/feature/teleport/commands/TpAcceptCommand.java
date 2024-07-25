package com.deepakchen.fancyessentials.feature.teleport.commands;

import com.deepakchen.fancyessentials.feature.LocaleManager;
import com.deepakchen.fancyessentials.feature.teleport.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAcceptCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final TeleportManager teleportManager;

    public TpAcceptCommand(LocaleManager localeManager, TeleportManager teleportManager) {
        this.localeManager = localeManager;
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;

        if (teleportManager.confirmTeleport(player)) {
            player.sendMessage(localeManager.getMessage("commands.tpaccept.success"));
            return true;
        } else {
            player.sendMessage(localeManager.getMessage("commands.tpaccept.failure"));
        }

        return true;
    }
}
