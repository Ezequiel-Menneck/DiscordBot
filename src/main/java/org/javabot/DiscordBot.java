package org.javabot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.javabot.Core.CommandListener;
import org.javabot.Listeners.ReadyListener;

public class DiscordBot extends ListenerAdapter {

    public static void main(String[] args) {

        final String TOKEN = "MTA4MjY4MjMyMTE3NTU4ODkzNQ.Grc0rT.r7XcpnYoxbUsffo0ImS2FilO0V7WYZypp7GuYY";

        JDA jdaBuilder = JDABuilder.createDefault(TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(
                        new ReadyListener(),
                        new CommandListener()
                )
                .setActivity(Activity.watching("espionagem"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();
    }

}