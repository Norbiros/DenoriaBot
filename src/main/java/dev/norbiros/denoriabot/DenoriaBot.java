package dev.norbiros.denoriabot;

import dev.norbiros.denoriabot.commands.*;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;

import org.bukkit.plugin.java.JavaPlugin;


public class DenoriaBot extends JavaPlugin {
    private static DenoriaBot instance;
    private static String BOT_PREFIX;
    private static JDA jda;
  
    @Override
    public void onEnable() {
      instance = this;
      BOT_PREFIX = this.getConfig().getString("prefix");
      
      this.saveDefaultConfig();
      try {
        String BOT_TOKEN = this.getConfig().getString("token");
        jda = JDABuilder.createDefault(BOT_TOKEN)
          .setActivity(Activity.playing("mc.DENORIA.pl"))
          .addEventListeners( new CommandListener() )
          .build();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    @Override
    public void onDisable() {
      instance = null;
      BOT_PREFIX = null;
      jda.getPresence().setStatus(OnlineStatus.OFFLINE);
      jda.shutdown();
      getLogger().info("Pomyślnie wyłączono plugin DenoriaBot!");
    }

    public static DenoriaBot getInstance(){
      return instance;
    }

    public static String getPrefix(){
      return BOT_PREFIX;
    }

}