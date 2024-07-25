package com.deepakchen.fancyessentials.feature.teleport.commands;

import com.deepakchen.fancyessentials.feature.teleport.TeleportManager;
import com.deepakchen.fancyessentials.feature.teleport.TeleportTaskService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class TpCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final TeleportManager teleportManager;
    private final TeleportTaskService teleportTaskService;

    public TpCommand(LocaleManager localeManager, TeleportManager teleportManager, TeleportTaskService teleportTaskService) {
        this.localeManager = localeManager;
        this.teleportManager = teleportManager;
        this.teleportTaskService = teleportTaskService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                teleportTaskService.teleport(player, target.getLocation());
            } else {
                player.sendMessage(localeManager.getMessage("messages.player.not_found"));
            }
        } else if (args.length == 3) {
            try {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                Location loc = new Location(player.getWorld(), x, y, z);
                teleportTaskService.teleport(player, loc);
            } catch (NumberFormatException e) {
                player.sendMessage(localeManager.getMessage("messages.input.invalid_coordinates"));
            }
        } else {
            player.sendMessage(localeManager.getMessage("commands.tp.usage"));
        }

        return true;
    }
}
