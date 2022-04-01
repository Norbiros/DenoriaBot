package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GithubCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.equals(DenoriaBot.getPrefix() + "github")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Denoria.pl | Github", null)
                    .setColor(new Color(21, 242, 253))
                    .setDescription("Link do kodu naszego bota: \n**https://github.com/Norbiros/DenoriaBot**");
            channel.sendMessageEmbeds( embed.build() ).queue();
        }
    }
}
