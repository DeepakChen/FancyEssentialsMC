package com.deepakchen.fancyessentials.utils;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class CommandRegistrar {
    private JavaPlugin plugin;

    public CommandRegistrar(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands(Map<String, CommandExecutor> commands) {
        commands.forEach((commandName, executor) -> {
            plugin.getCommand(commandName).setExecutor(executor);
        });
    }
}
