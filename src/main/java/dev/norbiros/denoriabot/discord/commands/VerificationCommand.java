package dev.norbiros.denoriabot.discord.commands;

import dev.norbiros.denoriabot.configuration.Config;
import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class VerificationCommand {
    public static EmbedBuilder handleCommand(String content, long id) {
        String[] args = content.split("\\s+");
        EmbedBuilder embed = new EmbedBuilder();
        Config config = new Config();

        if (config.getCustomConfig().get("player." + id + "name") != null) {
           embed.setTitle("Denoria.pl | Połącz Swoje Konto", null)
                .setColor(new Color(255, 0, 25))
                .setDescription("Już połączyłeś swoje konto!");
            return embed;
        }

        if (config.getCustomConfig().get("verification." + args[1]) == null) {
            embed.setTitle("Denoria.pl | Połącz Swoje Konto", null)
                .setColor(new Color(255, 0, 25))
                .setDescription("Podałeś/aś błędny kod!");
            return embed;
        }

        String uuid = config.getCustomConfig().get("verification." + args[1] + ".uuid").toString();
        String name = config.getCustomConfig().get("verification." + args[1] + ".name").toString();

        if (config.getCustomConfig().getDouble("verification." + args[1] + ".date") < Instant.now().getEpochSecond()) {
            clearVerificationData(args[1], uuid, config);
            config.saveCustomConfig();
            embed.setTitle("Denoria.pl | Połącz Swoje Konto", null)
                .setColor(new Color(255, 0, 25))
                .setDescription("Twój kod wygasł!");
            return embed;
        }

        config.getCustomConfig().set("player." + id + ".uuid", uuid);
        config.getCustomConfig().set("player." + id + ".name", name);
        clearVerificationData(args[1], uuid, config);

        config.saveCustomConfig();

        embed.setTitle("Denoria.pl | Połącz Swoje Konto", null)
                .setColor(new Color(21, 242, 253))
                .setDescription("Twoje nazwa w Minecraft: " + name)
                .setImage("https://minotar.net/avatar/" + name + ".png");
        return embed;
    }

    private static void clearVerificationData(String arg, String uuid, Config config) {
        config.getCustomConfig().set("verification." + arg + ".name", null);
        config.getCustomConfig().set("verification." + arg + ".uuid", null);
        config.getCustomConfig().set("verification." + arg + ".date", null);
        config.getCustomConfig().set("player." + uuid + ".status", null);
    }
}