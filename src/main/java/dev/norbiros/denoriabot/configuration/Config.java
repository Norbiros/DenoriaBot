package dev.norbiros.denoriabot.configuration;

import dev.norbiros.denoriabot.DenoriaBot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.logging.Level;
import java.io.IOException;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Config {

    private FileConfiguration customConfig = null;
    private File customConfigFile = null;
    private DenoriaBot plugin = DenoriaBot.getInstance();

    public void reloadCustomConfig() {
        if (customConfigFile == null) {
        customConfigFile = new File(plugin.getDataFolder(), "discord-auth-data.yml");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        try {
            Reader defConfigStream = new InputStreamReader(plugin.getResource("discord-auth-data.yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                customConfig.setDefaults(defConfig);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() {
        if (customConfig == null) {
            reloadCustomConfig();
        }
        return customConfig;
    }

    public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }
        try {
            this.getCustomConfig().save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), "discord-auth-data.yml");
        }
        if (!customConfigFile.exists()) {
             plugin.saveResource("discord-auth-data.yml", false);
         }
    }
}