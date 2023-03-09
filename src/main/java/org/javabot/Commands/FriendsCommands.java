package org.javabot.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FriendsCommands {

    public void vini(SlashCommandInteractionEvent event) {
        event.reply("Perdeu as conta tudo foi?").queue();
    }

    public void mauro(SlashCommandInteractionEvent event) {
        event.reply("O mauro ta com a rosqueta alargada").queue();
    }

    public void hacker(SlashCommandInteractionEvent event) {
        event.reply("Baixou o Hogwarts Legacy e foi hackeado é?").queue();
    }
}
