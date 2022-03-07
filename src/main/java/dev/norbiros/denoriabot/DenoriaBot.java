package dev.norbiros.denoriabot;

import dev.norbiros.denoriabot.commands.*;

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
    private static DenoriaBot instance;
    private static String BOT_PREFIX;
  
    @Override
    public void onEnable() {
      instance = this;
      BOT_PREFIX = this.getConfig().getString("prefix");
      
      this.saveDefaultConfig();
      try {
        String BOT_TOKEN = this.getConfig().getString("token");
        getLogger().info(this.getConfig().getString("prefix"));
        JDABuilder.createDefault(BOT_TOKEN)
          .setActivity(Activity.playing("mc.DENORIA.pl"))
          .addEventListeners(
            new Listener(),
            new HelpCommand()
          )
          .build();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    @Override
    public void onDisable() {
      instance = null;
      BOT_PREFIX = null;
      getLogger().info("Pomyślnie wyłączono plugin DenoriaBot!");
    }

    public static DenoriaBot getInstance(){
      return instance;
    }

    public static String getPrefix(){
      return BOT_PREFIX;
    }

}