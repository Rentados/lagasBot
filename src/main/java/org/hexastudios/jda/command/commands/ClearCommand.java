package org.hexastudios.jda.command.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import org.hexastudios.jda.command.CommandContext;
import org.hexastudios.jda.command.ICommand;

import java.util.List;

public class ClearCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args= ctx.getArgs();
        if(ctx.getEvent().getMember().isOwner()){
            TextChannel channel = ctx.getChannel();
            int num = Integer.parseInt(args.get(0));
            channel.sendMessage(num+" messages deleted.").queue();
            MessageHistory history=new MessageHistory(ctx.getChannel());
            List<Message> msgs;
            msgs=history.retrievePast(num).complete();
            channel.deleteMessages(msgs).queue();
        }
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "This command is cleared messages";
    }
}
