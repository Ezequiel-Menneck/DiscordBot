package org.DiscordBot.Listeners;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.DiscordBot.Core.GuildMusicManager;

import java.util.HashMap;
import java.util.Map;

public class MusicListener extends ListenerAdapter {

    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;

    public MusicListener() {
        this.musicManagers = new HashMap<>();

        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    private synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager, guild);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }


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

            if ("?play".equals(command[0]) && command.length == 2) {
                loadAndPlay((TextChannel) event.getChannel(), command[1], channelId);
            } else if ("?skip".equals(command[0])) {
                skipTrack((TextChannel) event.getChannel());
            }

        }
    }

    public void loadAndPlay(final TextChannel channel, final String trackUrl, AudioChannelUnion voiceChannelId) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        System.out.println("musica manager" + musicManager);

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                channel.sendMessage("Adding to queue: " + audioTrack.getInfo().title).queue();

                play(channel.getGuild(), musicManager, audioTrack, voiceChannelId);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                AudioTrack firstTrack = audioPlaylist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = audioPlaylist.getTracks().get(0);
                }

                channel.sendMessage("Adding to queue: " + firstTrack.getInfo().title + " (first trach of playlist " + audioPlaylist.getName() + ")").queue();

                play(channel.getGuild(), musicManager, firstTrack, voiceChannelId);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found by: " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendMessage("Could not play: " + e.getMessage()).queue();
            }
        });
    }

    private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track, AudioChannelUnion voiceChannelId) {


        connectToFirstVoiceChannel(guild.getAudioManager(), voiceChannelId);

        musicManager.scheduler.queue(track);
    }

    public void skipTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.nextTrack();

        channel.sendMessage("Skipped to next track.").queue();
    }

    private static void connectToFirstVoiceChannel(AudioManager audioManager, AudioChannelUnion voiceChannelId) {
        if (!audioManager.isConnected()) {

            audioManager.openAudioConnection(voiceChannelId);
        }
    }

}
