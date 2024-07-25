package com.deepakchen.fancyessentials.feature.home.commands;

import com.deepakchen.fancyessentials.feature.home.HomeManager;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class SetHomeCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final HomeManager homeManager;

    public SetHomeCommand(LocaleManager localeManager, HomeManager homeManager) {
        this.localeManager = localeManager;
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        String homeName = args.length > 0 ? args[0] : "default";
        Location location = player.getLocation();

        homeManager.setHome(player, homeName, location);
        player.sendMessage(localeManager.getMessage("commands.sethome.success").replace("{0}", homeName));
        return true;
    }
}
