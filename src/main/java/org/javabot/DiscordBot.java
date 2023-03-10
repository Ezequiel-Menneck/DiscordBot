package org.javabot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.javabot.Core.CommandListener;
import org.javabot.Listeners.ReadyListener;
import org.javabot.Utils.Util;

public class DiscordBot extends ListenerAdapter {

    public static void main(String[] args) {

        JDA jdaBuilder = JDABuilder.createDefault(Util.get("token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(
                        new ReadyListener(),
                        new CommandListener()
                )
                .setActivity(Activity.watching("espionagem"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }

}