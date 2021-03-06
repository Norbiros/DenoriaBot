package dev.norbiros.denoriabot;

import dev.norbiros.denoriabot.discord.commands.*;
import dev.norbiros.denoriabot.events.*;
import dev.norbiros.denoriabot.configuration.Config;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.OnlineStatus;
import org.jetbrains.annotations.NotNull;

import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;


public class DenoriaBot extends JavaPlugin {
    private static DenoriaBot instance;
    private static String BOT_PREFIX;
    public static JDA jda;
    private LuckPerms luckPerms;

  
    @Override
    public void onEnable() {
      instance = this;
      BOT_PREFIX = this.getConfig().getString("prefix");

      this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);
      new LuckyPermsHandler(this, this.luckPerms).register();

      Config config = new Config();

      this.saveDefaultConfig();
      config.saveDefaultConfig();

      // Clear verification data
      config.getCustomConfig().set("verification", null);
      config.saveCustomConfig();

      try {
        String BOT_TOKEN = this.getConfig().getString("token");
        jda = JDABuilder.createDefault(BOT_TOKEN)
          .setActivity(Activity.playing("mc.DENORIA.pl"))
          .addEventListeners( new CommandListener() )
          .build().awaitReady();
        Guild guild = jda.getGuildById(this.getConfig().getString("guild-id"));
        if(guild != null){
            guild.upsertCommand("rasa", "Przeczytaj opis rasy")
                    .addOption(OptionType.STRING, "rasa", "Nazwa rasy", true)
                    .queue();
            guild.upsertCommand("pomoc", "Dowiedz się jakie komendy ten bot posiada!").queue();
            guild.upsertCommand("github", "Kiedy wywołasz tą komendę, dostaniesz link do kodu bota!").queue();
            guild.upsertCommand("tutorial", "Poznaj informacje o denorii!").queue();
            guild.upsertCommand("połącz", "Zweryfikuj swoje konto MC z DC!")
                    .addOption(OptionType.STRING, "kod", "Twój kod z mc", true) // To lazy to implement int
                    .queue();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      getServer().getPluginManager().registerEvents(new CommandLogs(), this);
      this.getCommand("polacz").setExecutor(new DenoriaBotCommandExecutor(this));
    }

    @Override
    public void onDisable() {
      instance = null;
      BOT_PREFIX = null;
      jda.getPresence().setStatus(OnlineStatus.OFFLINE);
      jda.shutdown();
      getLogger().info("Pomyślnie wyłączono plugin DenoriaBot!");
    }

    public static DenoriaBot getInstance(){
      return instance;
    }

    public static String getPrefix(){
      return BOT_PREFIX;
    }
}
