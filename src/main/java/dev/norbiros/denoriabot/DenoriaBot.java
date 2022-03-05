package dev.norbiros.denoriabot;

import java.util.*;

import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Activity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;


public class DenoriaBot extends JavaPlugin {
  
    @Override
    public void onEnable() {
      this.saveDefaultConfig();
      try {
        String BOT_TOKEN = this.getConfig().getString("token");
        JDABuilder.createDefault(BOT_TOKEN)
          .setActivity(Activity.playing("mc.DENORIA.pl"))
          .addEventListeners(new Listener())
          .build();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    @Override
    public void onDisable() {
      getLogger().info("Pomyślnie wyłączono plugin DenoriaBot!");
    }

}