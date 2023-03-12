package org.DiscordBot.Listeners;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.DiscordBot.Commands.FriendsCommands;
import org.DiscordBot.Utils.Util;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandListener extends ListenerAdapter {

    FriendsCommands friendsCommands = new FriendsCommands();
    MusicListener musicListener = new MusicListener();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA()
                .updateCommands()
                .addCommands(
                        Commands.slash("vini", "Surpresa"),
                        Commands.slash("mauro", "Surpresa"),
                        Commands.slash("hacker", "Hackeado?"),
                        Commands.slash("play", "Escreva a música desejada")
                                .addOption(OptionType.STRING, "musica", "Música que o bot ira tocar", true),
                        Commands.slash("skip", "Skipa a música"),
                        Commands.slash("gejota", "Porra Gejota")
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
            case "play" ->
                    musicListener.loadAndPlay((TextChannel) event.getChannel(), Objects.requireNonNull(event.getOption("musica")).getAsString());
            case "skip" -> musicListener.skipTrack((TextChannel) event.getChannel());
            case "gejota" -> musicListener.loadAndPlay((TextChannel) event.getChannel(), Util.get("porragejota"));
        }
    }

}
