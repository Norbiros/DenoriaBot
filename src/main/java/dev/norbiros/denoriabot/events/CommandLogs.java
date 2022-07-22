package dev.norbiros.denoriabot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.entity.Player;
import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.EmbedBuilder;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

import java.util.regex.*;
import java.io.*;
import java.awt.*;


public class CommandLogs implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        // Get logs channel
        String channelId = DenoriaBot.getInstance().getConfig().getString("logs-channel-id");
        TextChannel txtChannel = DenoriaBot.getInstance().jda.getTextChannelById(channelId);

        // Check if commands list contains executed command
        String[] arguments = event.getMessage().split(" ");
        List<Map<?,?>> commands = DenoriaBot.getInstance().getConfig().getMapList("commands");
        Map<?,?> commandData = null;
        for (Map<?,?> data : commands) {
            if (arguments[0].equalsIgnoreCase(data.get("command").toString())) {
                commandData = data;
                break;
            }
        }

        if (commandData == null) return; // If it is not set, stop code

        // Get permissions list, and check if player has it
        ArrayList<?> permissions = (ArrayList<?>) commandData.get("permissions");
        boolean hasPermission = false;
        for (Object i : permissions) {
            if (event.getPlayer().hasPermission(i.toString()))
                hasPermission = true;
        }

        if (!hasPermission) return; // If doesn't have, stop

        // Get config values
        String message = commandData.get("description").toString();
        String title = commandData.get("title").toString();
        int[] color = new int[3];
        for (int i = 0; i < commandData.get("color").toString().split(", ").length; i++) {
            color[i] = Integer.parseInt(commandData.get("color").toString().split(", ")[i]);
        }

        // Parse description arguments
        Pattern pattern = Pattern.compile("\\{argument (\\d)\\}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            int argumentInt = Integer.parseInt(matcher.group(1));
            message = message.replace(matcher.group(0), argumentInt < arguments.length && argumentInt > 0 ? arguments[argumentInt] : "<none>");
        }
        message = message.replaceAll("\\{player\\}", event.getPlayer().getName());
        message = message.replaceAll("\\{command\\}", event.getMessage());

        // Send embed to text channel
        // TODO: Make it works with webhooks
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title, null)
            .setColor(new Color(color[0], color[1], color[2]))
            .setDescription(message)
            .setFooter("Denoria 🦊")
            .setTimestamp(Instant.now()); // You can't send icon in other way
        txtChannel.sendMessageEmbeds(embed.build()).queue();
    }
}