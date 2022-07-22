package dev.norbiros.denoriabot.discord.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class GithubCommand {
    public static EmbedBuilder handleCommand(String content) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Denoria.pl | Github", null)
                .setColor(new Color(21, 242, 253))
                .setDescription("Link do kodu naszego bota: \n**https://github.com/Norbiros/DenoriaBot**");
        return embed;
    }
}
