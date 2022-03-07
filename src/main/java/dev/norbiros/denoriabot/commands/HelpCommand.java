package dev.norbiros.denoriabot.commands;
import dev.norbiros.denoriabot.DenoriaBot;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends ListenerAdapter {
  
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;
    Message message = event.getMessage();
    String content = message.getContentRaw(); 
    MessageChannel channel = event.getChannel();
    if (content.equals(DenoriaBot.getPrefix() + "help")) {
        channel.sendMessage("Menu pomocy!").queue();
    }
  }
}
