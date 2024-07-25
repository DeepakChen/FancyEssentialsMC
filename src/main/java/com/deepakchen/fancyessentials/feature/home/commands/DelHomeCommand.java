package com.deepakchen.fancyessentials.feature.home.commands;

import com.deepakchen.fancyessentials.feature.home.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class DelHomeCommand implements CommandExecutor {
    private final LocaleManager localeManager;
    private final HomeManager homeManager;

    public DelHomeCommand(LocaleManager localeManager, HomeManager homeManager) {
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
        if (args.length == 0) {
            sender.sendMessage(localeManager.getMessage("commands.delhome.usage"));
            return true;
        }

        String homeName = args[0];
        if (homeManager.deleteHome(player, homeName)) {
            player.sendMessage(localeManager.getMessage("commands.delhome.success").replace("{0}", homeName));
        } else {
            player.sendMessage(localeManager.getMessage("commands.delhome.failure").replace("{0}", homeName));
        }

        return true;
    }
}
