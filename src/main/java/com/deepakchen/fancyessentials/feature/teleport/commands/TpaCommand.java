package com.deepakchen.fancyessentials.feature.teleport.commands;

import com.deepakchen.fancyessentials.feature.teleport.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class TpaCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final TeleportManager teleportManager;

    public TpaCommand(LocaleManager localeManager, TeleportManager teleportManager) {
        this.localeManager = localeManager;
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(localeManager.getMessage("commands.tpa.usage"));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(localeManager.getMessage("messages.player.not_found"));
            return true;
        }

        teleportManager.requestTeleport(player, target, target.getLocation(), false);
        target.sendMessage(localeManager.getMessage("commands.tpa.request").replace("{0}", player.getName()));
        player.sendMessage(localeManager.getMessage("commands.tpa.success").replace("{0}", target.getName()));

        return true;
    }
}
