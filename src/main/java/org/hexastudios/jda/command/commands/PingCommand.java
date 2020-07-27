package org.hexastudios.jda.command.commands;

import net.dv8tion.jda.api.JDA;
import org.hexastudios.jda.command.CommandContext;
import org.hexastudios.jda.command.ICommand;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda=ctx.getJDA();
        jda.getRestPing().queue(
                (ping)->ctx.getChannel()
                .sendMessageFormat("Reset ping:%sms\nWS ping:%sms",ping,jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Shows the list with commands in the bot\n" +
                "Usage: `!!help [command]`";
    }
}
