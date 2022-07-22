package dev.norbiros.denoriabot.discord.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class TutorialCommand {
    public static EmbedBuilder handleCommand(String content) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Denoria.pl | Tutorial", null)
                .setColor(new Color(21, 242, 253))
                .setDescription("Aby dowiedzieć się wiele informacji o serwerze\nprzeczytaj nasz poradnik na stronie:\n**https://docs.google.com/document/d/17lBl27wmwS_YI1VTFXzH1Drj-VVZRc9jDn3ASrT_JIs/**");
        return embed;
    }
}