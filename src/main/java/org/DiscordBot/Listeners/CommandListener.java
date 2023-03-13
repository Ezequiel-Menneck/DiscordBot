package org.DiscordBot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.DiscordBot.Commands.FriendsCommands;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    FriendsCommands friendsCommands = new FriendsCommands();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA()
                .updateCommands()
                .addCommands(
                        Commands.slash("vini", "Surpresa"),
                        Commands.slash("mauro", "Surpresa"),
                        Commands.slash("hacker", "Hackeado?")
                ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) {
            return;
        }

        switch (event.getName()) {
            case "vini" -> friendsCommands.vini(event);
            case "mauro" -> friendsCommands.mauro(event);
            case "hacker" -> friendsCommands.hacker(event);
        }
    }

}
