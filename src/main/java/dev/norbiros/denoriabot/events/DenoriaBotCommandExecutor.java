package dev.norbiros.denoriabot.events;

import dev.norbiros.denoriabot.DenoriaBot;
import dev.norbiros.denoriabot.configuration.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.*;
import java.time.Instant;

public class DenoriaBotCommandExecutor implements CommandExecutor {

	private final DenoriaBot plugin;
	public DenoriaBotCommandExecutor(DenoriaBot plugin) {
		this.plugin = plugin;
	}

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("polacz")) return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda może być wywołana tylko przez gracza!");
            return false;
        }

        Player player = (Player) sender;
        Config config = new Config();
        String uuid = player.getUniqueId().toString();
        String randomString = String.format("%06d", new Random().nextInt(999999));


        Set<String> playerList = config.getCustomConfig().getConfigurationSection("player").getKeys(false);
        for (String key : playerList) {
            if (config.getCustomConfig().get("player." + key + ".uuid").toString().equalsIgnoreCase(uuid)) {
                sender.sendMessage("Już połączyłeś swoje konto Discord!");
                return true;
            }
        }

        sender.sendMessage("Twój kod do połączenia na Discordzie to: " + randomString);

        long unixTime = Instant.now().getEpochSecond();
        unixTime += 60;

        config.getCustomConfig().set("verification." + randomString + ".uuid", uuid);
        config.getCustomConfig().set("verification." + randomString + ".name", player.getName());
        config.getCustomConfig().set("verification." + randomString + ".date", unixTime);
        config.saveCustomConfig();
        return true;
	}
}
