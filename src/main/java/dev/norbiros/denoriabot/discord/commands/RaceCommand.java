package dev.norbiros.denoriabot.discord.commands;

import dev.norbiros.denoriabot.DenoriaBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Locale;

public class RaceCommand {
    public static EmbedBuilder handleCommand(String content) {
        String[] args = content.split("\\s+");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(("Opis Ras" + (args.length >= 2 ? "y: " + args[1].toLowerCase(Locale.ROOT) : "" )), null)
                .setColor(new Color(56, 253, 21));

        switch (args.length >= 2 ? args[1].toLowerCase(Locale.ROOT) : "empty") {
            case "czlowiek":
            case "człowiek":
                embed.setDescription("<:minecraftheart:958978822991147078> Posiada 5 serc więcej.\n<:minecraftpotion:958985620326334514> Gdy zostanie mu połowa życia otrzymuje regenerację na 5 sekund!");
                break;
            case "elf":
                embed.setDescription("<:minecraftpotion:958985620326334514> Posiada szybkość 1 oraz zmniejszony dmg od upadku.\n <:minecraftbow:958985620334706738> Gdy zada obrażenia z łuku dostaje speed 2 na 5 sekund");
                break;
            case "krasnolud":
                embed.setDescription("<:minecraftbow:958985620334706738> Posiada wytrzymałość 1 oraz możliwość czołgania.\n <:netheritechestplate:958987494341349388> Gdy zablokuje cios tarczą otrzymuje wytrzymałość na 5 sekund!");
                break;
            case "demon":
                embed.setDescription("<:minecraftpotion:958985620326334514> Posiada odporność na ogień, spowolnienie, mdłości, oślepienie, słabość, zatrucie, obumarcie.\n<:minecraftsword:958985620594769930> Jego atak nakłada obumarcie 1 na 5 sekund [Cooldown: 15 sekund]. \n<:lavabucket:958988061012799530> Może chodzić po lawie.");
                break;
            case "ork":
                embed.setDescription("<:minecraftsword:958985620594769930> Posiada siłe 1.\n <:minecraftheart:958978822991147078> Gdy zostanie mu połowa życia otrzymuje siłę 2 na 5 sekund!");
                break;
            case "goblin":
            case "trol":
            case "troll":
                embed.setDescription("<:minecraftheart:958978822991147078> Ataki nakładają truciznę na 2 sekundy, a także dają haste 1 na 5 sekund");
                break;
            default:
                embed.setDescription("**Niestety taka rasa nie istnieje!**\n Spróbuj wpisać:\n`-` człowiek\n`-` elf\n`-` krasnolud \n`-` demon \n `-` ork \n`-` goblin\n`-` troll")
                       .setColor(new Color(255, 0, 25));
                break;
        }
        return embed;
    }
}