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

        if (content.startsWith(DenoriaBot.getPrefix() + "rasa ")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(("Opis Ras" + (args.length >= 2 ? "y: " + args[1].toLowerCase(Locale.ROOT) : "" )), null)
                    .setColor(new Color(56, 253, 21));
            switch (args.length >= 2 ? args[1].toLowerCase(Locale.ROOT) : "empty") {
                case "człowiek":
                    embed.setDescription("Posiada 5 serc więcej.\n Gdy zostanie mu połowa życia otrzymuje regenerację 3 na 5 sekund [Cooldown: 2 minuty]");
                    break;
                case "elf":
                    embed.setDescription("Posiada szybkość 1 oraz zmniejszony dmg od upadku, ale szybkość upadku pozostanie taka sama.\n Gdy zada obrażenia z łuku dostaje speed 2 na 5 sekund");
                    break;
                case "krasnolud":
                    embed.setDescription("Posiada wytrzymałość 1 oraz możliwość czołgania.\n Gdy zablokuje cios tarczą otrzymuje wytrzymałość 2 na 5 sekund [Cooldown: 2 minuty]");
                    break;
                case "demon":
                    embed.setDescription("Posiada odporność na ogień, spowolnienie, mdłości, oślepienie, słabość, zatrucie, obumarcie.\n Jego atak nakłada obumarcie 1 na 5 sekund [Cooldown: 15 sekund]. Może chodzić po lawie. Wciskając szybko dwa razy shift może włączać lub wyłączać tą funkcję.");
                    break;
                case "ork":
                    embed.setDescription("Posiada siłe 1.\n Gdy zostanie mu połowa życia otrzymuje siłę 2 na 5 sekund [Cooldown: 2 minuty]");
                    break;
                case "goblin":
                    embed.setDescription("Ataki nakładają truciznę 1 na 2 sek, a także dają goblinowi haste 1 na 5 sekund oraz możliwość czołganie");
                    break;
                case "troll":
                    embed.setDescription("Ataki nakładają slowness i nausea na 1 sek, a także dają trollowi haste 2 na 5 sekund gdy ten atakuje za pomocą siekiery");
                    break;
                default:
                    embed.setDescription("Niestety nie znaleźliśmy takiego czegoś!\n Spróbuj wpisać:\n`-` człowiek\n`-` elf\n`-` krasnolud \n`-` demon \n `-` ork \n`-` goblin\n`-` troll")
                           .setColor(new Color(255, 0, 25));
                    break;
            }
            channel.sendMessageEmbeds( embed.build() ).queue();
        }
    }
}
