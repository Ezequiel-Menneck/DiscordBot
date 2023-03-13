package org.DiscordBot.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class BasicCommands {

    public void vini(SlashCommandInteractionEvent event) {
        event.reply("Perdeu as conta tudo foi?").queue();
    }

    public void mauro(SlashCommandInteractionEvent event) {
        event.reply("O mauro ta com a rosqueta alargada").queue();
    }

    public void hacker(SlashCommandInteractionEvent event) {
        event.reply("Baixou o Hogwarts Legacy e foi hackeado é?").queue();
    }

    public void help(SlashCommandInteractionEvent event) {
        StringBuilder builder = new StringBuilder();

        builder.append("Lista de **comandos** \n");
        builder.append("`?play [link_da_musica]`\n");
        builder.append("`?skip`");

        event.reply(String.valueOf(builder)).queue();
    }
}
