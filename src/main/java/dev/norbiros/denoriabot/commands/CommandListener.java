package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String content = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        EmbedBuilder embed = null;
        if (!content.startsWith(DenoriaBot.getPrefix())) return;
        content = content.replaceFirst(DenoriaBot.getPrefix(), "");

        switch (content.split(" ")[0]) {
            case "github":
                embed = GithubCommand.handleCommand(content);
                break;
            case "tutorial":
                embed = TutorialCommand.handleCommand(content);
                break;
            case "rasa":
            case "rasy":
                embed = RaceCommand.handleCommand(content);
                break;
            case "help":
            case "pomoc":
                embed = HelpCommand.handleCommand(content);
            default:
                break;
        }

        if (embed != null) {
             channel.sendMessageEmbeds(embed.build()).queue();
        }
    }
}