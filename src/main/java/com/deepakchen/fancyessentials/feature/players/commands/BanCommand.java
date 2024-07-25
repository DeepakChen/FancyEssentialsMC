package com.deepakchen.fancyessentials.feature.players.commands;

import com.deepakchen.fancyessentials.feature.players.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import com.deepakchen.fancyessentials.feature.LocaleManager;
import com.deepakchen.fancyessentials.utils.BanResult;
import java.util.UUID;

public class BanCommand implements CommandExecutor {
    private PlayerManager playerManager;
    private LocaleManager localeManager;

    public BanCommand(LocaleManager localeManager, PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.localeManager = localeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(localeManager.getMessage("commands.ban.usage"));
            return true;
        }
        UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        String reason = args.length > 1 ? args[1] : localeManager.getMessage("commands.ban.default_reason");
        BanResult result;
        if (args.length > 2) {
            result = playerManager.tempBanPlayer(uuid, reason, args[2]);
            if(Bukkit.getOfflinePlayer(uuid).isOnline()){
                Bukkit.getPlayer(uuid).kickPlayer(localeManager.getMessage("messages.login.ban_temp").replace("{0}", result.getDuration()+"+").replace("{1}", reason));
            }
        } else {
            result = playerManager.banPlayer(uuid, reason);
            if(Bukkit.getOfflinePlayer(uuid).isOnline()){
                Bukkit.getPlayer(uuid).kickPlayer(localeManager.getMessage("messages.login.ban_permanent").replace("{0}", reason));
            }
        }

        if (result.isSuccess()) {
            sender.sendMessage(String.format(localeManager.getMessage("commands.ban.success").replace("{0}", Bukkit.getPlayer(result.getUuid()).getName()).replace("{1}", reason).replace("{2}", result.getDuration()),
                    Bukkit.getOfflinePlayer(result.getUuid()).getName(), reason, result.getDuration()));
        } else {
            sender.sendMessage(localeManager.getMessage("commands.ban.failure").replace("{0}", Bukkit.getOfflinePlayer(result.getUuid()).getName()));
        }
        return true;
    }
}
