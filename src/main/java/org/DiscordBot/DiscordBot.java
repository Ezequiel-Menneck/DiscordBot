package org.DiscordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.DiscordBot.Listeners.CommandListener;
import org.DiscordBot.Listeners.ReadyListener;
import org.DiscordBot.Utils.Util;

public class DiscordBot {

    public static void main(String[] args) {

        JDA jdaBuilder = JDABuilder.createDefault(Util.get("token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES)
                .addEventListeners(
                        new ReadyListener(),
                        new CommandListener()
                )
                .setActivity(Activity.listening("um sãum"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }

}