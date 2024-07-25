package com.deepakchen.fancyessentials.feature.players.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class KickCommand implements CommandExecutor {
    private LocaleManager localeManager;

    public KickCommand(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(localeManager.getMessage("commands.kick.usage"));
            return true;
        }

        Player player = sender.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_found"));
            return true;
        }

        String reason = localeManager.getMessage("commands.kick.default_reason");
        if (args.length > 1) {
            reason = String.join(" ", args).substring(args[0].length() + 1);
        }

        player.kickPlayer(localeManager.getMessage("commands.kick.kicked").replace("{0}", reason));
        sender.sendMessage(localeManager.getMessage("commands.kick.success").replace("{0}", player.getName()).replace("{1}", reason));
        return true;
    }
}
