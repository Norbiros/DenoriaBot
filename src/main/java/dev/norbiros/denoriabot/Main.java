package dev.norbiros.denoriabot;

import java.util.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
      this.saveDefaultConfig();
      System.out.println("TOKEN:" );
      System.out.println(this.getConfig().getString("token"));
    }

    @Override
    public void onDisable() {
    }
}
