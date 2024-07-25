package com.deepakchen.fancyessentials.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import com.deepakchen.fancyessentials.feature.players.PlayerManager;
import com.deepakchen.fancyessentials.feature.LocaleManager;

public class BanListener implements Listener {
    private PlayerManager playerManager;
    private LocaleManager localeManager;

    public BanListener(LocaleManager localeManager, PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.localeManager = localeManager;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isBanned(player.getUniqueId())){
            String banReason = playerManager.getBanReason(player.getUniqueId());
            long banEnd = playerManager.getBanEnd(player.getUniqueId());
            if (banEnd == -1) {  // 永久封禁
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, localeManager.getMessage("messages.ban.permanent").replace("{0}", banReason));
            } else if (banEnd > System.currentTimeMillis()) {  // 临时封禁
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, localeManager.getMessage("messages.ban.temp").replace("{0}", new java.util.Date(banEnd).toString()).replace("{1}", banReason));
            } else {
                playerManager.unbanPlayer(player.getUniqueId());  // 自动解封
            }
        }
    }
}
