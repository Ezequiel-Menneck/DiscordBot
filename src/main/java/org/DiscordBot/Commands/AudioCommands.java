package org.DiscordBot.Commands;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.DiscordBot.Listeners.MusicListener;
import org.DiscordBot.Utils.Util;

public class AudioCommands extends ListenerAdapter {

    MusicListener musicListener = new MusicListener();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ", 2);

        switch (command[0]) {
            case "?gejota" -> musicListener.loadAndPlay((TextChannel) event.getChannel(), Util.get("porragejota"));
            case "?maconha" -> musicListener.loadAndPlay((TextChannel) event.getChannel(), Util.get("maconha"));
        }

    }

}
