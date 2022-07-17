package dev.norbiros.denoriabot.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class HelpCommand {
    public static EmbedBuilder handleCommand(String content) {
        EmbedBuilder embed = new EmbedBuilder();
        String prefix = DenoriaBot.getPrefix();
        embed.setTitle("Menu Pomocy Bota @DenoriaBot", null)
            .setColor(new Color(21, 242, 253))
            .setDescription("Na razie bot jest w trakcie programowania,\n lecz mamy nadzieję że niedługo będzie tu więcej informacji!\n \n **Do zobaczenia w przyszłości!**\n \n **Komendy:**\n `-` " + prefix + "github\n `-` " + prefix + "tutorial\n `-` " + prefix + "rasa <rasa>\n `-` " + prefix + "pomoc");
        return embed;
    }
}