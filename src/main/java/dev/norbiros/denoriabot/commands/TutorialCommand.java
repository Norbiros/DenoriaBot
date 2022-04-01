package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class TutorialCommand  extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.equals(DenoriaBot.getPrefix() + "github")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Denoria.pl | Tutorial", null)
                    .setColor(new Color(21, 242, 253))
                    .setDescription("Aby dowiedzieć się wiele informacji o serwerze\nprzeczytaj nasz poradnik na stronie:\n**https://docs.google.com/document/d/17lBl27wmwS_YI1VTFXzH1Drj-VVZRc9jDn3ASrT_JIs/**");
            channel.sendMessageEmbeds( embed.build() ).queue();
        }
    }
}

