package com.deepakchen.fancyessentials.feature;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Locale;

public class LocaleManager {
    private YamlConfiguration messages;
    private static LocaleManager instance;
    private JavaPlugin plugin;

    private LocaleManager(Locale locale, JavaPlugin plugin) {
        this.plugin = plugin;
        File messageFile = new File(plugin.getDataFolder() + File.separator + "langs", locale.toString() + ".yml");


        if (!messageFile.exists()) {
            messageFile.getParentFile().mkdirs();
            try {

                InputStream is = plugin.getResource("langs/" + locale.toString() + ".yml");
                if (is != null) {
                    Files.copy(is, messageFile.toPath());
                    is.close();
                } else {

                    messageFile.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        messages = YamlConfiguration.loadConfiguration(messageFile);
    }

    public static LocaleManager getInstance(Locale locale, JavaPlugin plugin) {
        if (instance == null) {
            instance = new LocaleManager(locale, plugin);
        }
        return instance;
    }

    public String getMessage(String key) {
        return messages.getString(key, "Message not found for key: " + key);
    }

    public void setMessage(String key, String value) {
        messages.set(key, value);
        saveMessages();
    }

    private void saveMessages() {
        try {
            messages.save(new File(plugin.getDataFolder() + File.separator + "langs", messages.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
