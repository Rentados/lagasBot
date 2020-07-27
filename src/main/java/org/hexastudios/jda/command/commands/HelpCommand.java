package org.hexastudios.jda.command.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import org.hexastudios.jda.CommandManager;
import org.hexastudios.jda.Config;
import org.hexastudios.jda.command.CommandContext;
import org.hexastudios.jda.command.ICommand;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements ICommand {
    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args= ctx.getArgs();
        TextChannel channel=ctx.getChannel();
        if(args.isEmpty()){
            StringBuilder builder=new StringBuilder();
            builder.append("List of commands\n");
            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it)->builder.append("`").append(Config.get("PREFIX")).append(it).append("`\n")
            );


            channel.sendMessage(builder.toString()).queue();
            return;
        }else{
            String search=args.get(0);
            ICommand command=manager.getCommand(search);
            if(command==null){
                channel.sendMessage("Nothing found for "+search).queue();
                return;
            }
                channel.sendMessage(command.getName()+" : "+command.getHelp()).queue();

        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("commands","cmds","commandlist");
    }
}
