package org.hexastudios.jda;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private static final Logger logger= LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager=new CommandManager();
    @Override
    public void onReady(ReadyEvent event) {
        logger.info("{} is ready",event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user=event.getAuthor();
        if (user.isBot() || event.isWebhookMessage()){
            return;
        }


        String prefix=Config.get("PREFIX");
        String raw=event.getMessage().getContentRaw();
        if(raw.equalsIgnoreCase(prefix+"shutdown")
                && event.getAuthor().getId().equals(Config.get("OWNER_ID"))){
            logger.info("Shutting down");
            event.getJDA().shutdown();
        }
        if(raw.startsWith(prefix)){
            manager.handle(event);
        }
    }
}
