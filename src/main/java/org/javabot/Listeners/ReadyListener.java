package org.javabot.Listeners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Ready");
        event.getJDA().getTextChannelById("780587281303339023").
                sendMessage("On the line!")
                .queue();

    }
}
