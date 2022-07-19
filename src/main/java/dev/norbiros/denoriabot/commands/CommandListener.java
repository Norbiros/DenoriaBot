package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        String arguments = event.getName() + " ";
        for (OptionMapping s : event.getOptions()) {
            // TODO: Implement other types, not only STRING
            arguments = arguments + " " + s.getAsString();
        }
        EmbedBuilder embed = getEmbed(arguments);
        embed.setFooter("Denoria 🦊")
        if (embed != null) {
            event.getHook().sendMessageEmbeds(embed.build()).queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String content = event.getMessage().getContentRaw();
        if (!content.startsWith(DenoriaBot.getPrefix())) return;
        content = content.replaceFirst(DenoriaBot.getPrefix(), "");
        EmbedBuilder embed = getEmbed(content);
        if (embed != null) {
             event.getChannel().sendMessageEmbeds(embed.build()).queue();
        }
    }

    public EmbedBuilder getEmbed(String command) {
         EmbedBuilder embed = null;
         switch (command.split(" ")[0]) {
            case "github":
                embed = GithubCommand.handleCommand(command);
                break;
            case "tutorial":
                embed = TutorialCommand.handleCommand(command);
                break;
            case "rasa":
            case "rasy":
                embed = RaceCommand.handleCommand(command);
                break;
            case "help":
            case "pomoc":
                embed = HelpCommand.handleCommand(command);
            default:
                break;
        }
        return embed;
    }
}