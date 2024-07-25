package com.deepakchen.fancyessentials.feature.randomteleport;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.deepakchen.fancyessentials.feature.LocaleManager;

import java.util.Random;

public class RTPCommand implements CommandExecutor {
    private LocaleManager localeManager;
    private final Random random = new Random();

    public RTPCommand(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(localeManager.getMessage("messages.player.not_a_player"));
            return true;
        }

        Player player = (Player) sender;
        Location randomLocation = getRandomLocation(player.getWorld());

        player.teleport(randomLocation);
        player.sendMessage(localeManager.getMessage("commands.rtp.success"));
        return true;
    }

    private Location getRandomLocation(World world) {
        int x = random.nextInt(10000) - 5000;
        int z = random.nextInt(10000) - 5000;
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
}
