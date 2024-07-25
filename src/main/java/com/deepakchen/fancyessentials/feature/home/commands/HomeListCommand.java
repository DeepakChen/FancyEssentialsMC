package com.deepakchen.fancyessentials.feature.home.commands;

import com.deepakchen.fancyessentials.feature.home.HomeManager;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.util.Map;

public class HomeListCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final HomeManager homeManager;

    public HomeListCommand(LocaleManager localeManager, HomeManager homeManager) {
        this.localeManager = localeManager;
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;

        if (args.length == 1 && (sender.isOp() || !(sender instanceof Player))) {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(localeManager.getMessage("messages.player_not_found"));
                return true;
            }
        } else if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage(localeManager.getMessage("commands.homelist.usage"));
            return true;
        }

        Map<String, Location> homes = homeManager.getHomes(player);
        if (homes.isEmpty()) {
            sender.sendMessage(localeManager.getMessage("commands.homelist.no_homes").replace("{0}", player.getName()));
            return true;
        }

        sender.sendMessage(localeManager.getMessage("commands.homelist.header").replace("{0}", player.getName()));
        for (Map.Entry<String, Location> entry : homes.entrySet()) {
            String homeName = entry.getKey();
            Location loc = entry.getValue();
            sender.sendMessage(localeManager.getMessage("commands.homelist.entry")
                    .replace("{0}", homeName)
                    .replace("{1}", loc.getWorld().getName())
                    .replace("{2}", String.valueOf(loc.getBlockX()))
                    .replace("{3}", String.valueOf(loc.getBlockY()))
                    .replace("{4}", String.valueOf(loc.getBlockZ())));
        }

        return true;
    }
}
