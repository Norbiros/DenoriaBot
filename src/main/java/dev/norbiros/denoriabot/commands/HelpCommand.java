package dev.norbiros.denoriabot.commands;
import dev.norbiros.denoriabot.DenoriaBot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import java.awt.Color;

public class HelpCommand extends ListenerAdapter {
  
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;
    Message message = event.getMessage();
    String content = message.getContentRaw(); 
    MessageChannel channel = event.getChannel();
    if (content.equals(DenoriaBot.getPrefix() + "help")) {
      EmbedBuilder embed = new EmbedBuilder();
      embed.setTitle("Menu Pomocy Bota @DenoriaBot", null) 
        .setColor(new Color(21, 242, 253))
        .setDescription("Na razie bot jest w trakcie programowania,\n lecz mamy nadzieję że niedługo będzie tu więcej informacji!\n \n **Do zobaczenia w przyszłości!**\n \n **Komendy:**\n`-`!rasa <rasa>");
      channel.sendMessageEmbeds( embed.build() ).queue();
    }
  }
}
