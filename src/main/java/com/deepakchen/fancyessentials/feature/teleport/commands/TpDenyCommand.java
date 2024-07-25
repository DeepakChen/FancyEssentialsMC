package com.deepakchen.fancyessentials.feature.teleport.commands;

import com.deepakchen.fancyessentials.feature.LocaleManager;
import com.deepakchen.fancyessentials.feature.teleport.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpDenyCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final TeleportManager teleportManager;

    public TpDenyCommand(LocaleManager localeManager, TeleportManager teleportManager) {
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
        UUID requesterUUID = teleportManager.denyTeleport(player);

        if (requesterUUID != null) {
            Player requester = player.getServer().getPlayer(requesterUUID);
            if (requester != null) {
                requester.sendMessage(localeManager.getMessage("commands.tpdeny.denied"));
            }
            player.sendMessage(localeManager.getMessage("commands.tpdeny.success"));
        } else {
            player.sendMessage(localeManager.getMessage("commands.tpdeny.failure"));
        }

        return true;
    }
}
