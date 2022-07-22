package dev.norbiros.denoriabot;
import dev.norbiros.denoriabot.DenoriaBot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Listener extends ListenerAdapter {
  
  @Override
  public void onReady(ReadyEvent event) {
    DenoriaBot.getInstance().getLogger().info("Bot pomyślnie wystartował!");
  }
}
