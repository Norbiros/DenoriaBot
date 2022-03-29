package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Locale;

public class RaceCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        String[] args = content.split("\\s+");

        if (content.startsWith(DenoriaBot.getPrefix() + "rasa")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Menu Pomocy Bota @DenoriaBot", null)
                    .setColor(new Color(56, 253, 21));
            switch (args[1].toLowerCase(Locale.ROOT)) {
                case "elf":
                    embed.setDescription("Szybki, mały i dobry łucznik!1");
                    break;
                case "człowiek":
                    embed.setDescription("Gruby kanapowy potwór!");
                    break;
                default:
                    embed.setDescription("Nie znaleziono czegość jak: **" + args[1] + "**!");
            }
            channel.sendMessageEmbeds( embed.build() ).queue();
        }
    }
}
