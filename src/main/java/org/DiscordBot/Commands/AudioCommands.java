package org.DiscordBot.Commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.DiscordBot.Listeners.MusicListener;
import org.DiscordBot.Utils.Util;

public class AudioCommands extends ListenerAdapter {

    MusicListener musicListener = new MusicListener();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ", 2);

        Guild guild = event.getGuild();
        Member member = guild.getMemberById(event.getAuthor().getId());
        assert member != null;

        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;

        if (voiceState.inAudioChannel()) {
            AudioChannelUnion channelId = voiceState.getChannel();

            switch (command[0]) {
                case "?gejota" ->
                        musicListener.loadAndPlay((TextChannel) event.getChannel(), Util.get("porragejota"), channelId);
                case "?maconha" ->
                        musicListener.loadAndPlay((TextChannel) event.getChannel(), Util.get("maconha"), channelId);
            }

        }


    }

}
