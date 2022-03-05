package dev.norbiros.denoriabot;
import dev.norbiros.denoriabot.DenoriaBot;

import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Listener implements EventListener {
  @Override
    public void onEvent(GenericEvent event) {
      if (event instanceof ReadyEvent)
        Bukkit.getLogger().info("[DenoriaBot] Bot pomyślnie wystartował!");
      //In future replace Bukkit logger, with plugin logger
    }
}
