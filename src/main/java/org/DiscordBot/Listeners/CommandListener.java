package org.DiscordBot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.DiscordBot.Commands.BasicCommands;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    BasicCommands basicCommands = new BasicCommands();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA()
                .updateCommands()
                .addCommands(
                        Commands.slash("vini", "Surpresa"),
                        Commands.slash("mauro", "Surpresa"),
                        Commands.slash("hacker", "Hackeado?"),
                        Commands.slash("help", "Lista de comandos será exibida")
                ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) {
            return;
        }

        switch (event.getName()) {
            case "vini" -> basicCommands.vini(event);
            case "mauro" -> basicCommands.mauro(event);
            case "hacker" -> basicCommands.hacker(event);
            case "help" -> basicCommands.help(event);
        }
    }

}
