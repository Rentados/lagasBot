package org.hexastudios.jda;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.hexastudios.jda.command.CommandContext;
import org.hexastudios.jda.command.ICommand;
import org.hexastudios.jda.command.commands.ClearCommand;
import org.hexastudios.jda.command.commands.HelpCommand;
import org.hexastudios.jda.command.commands.PingCommand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands= new ArrayList<>();
    public CommandManager(){
        addCommand(new PingCommand());
        addCommand(new HelpCommand(this));
        addCommand(new ClearCommand());
    }
    private void addCommand(ICommand cmd){
        boolean nameFound=this.commands.stream().anyMatch((it)-> it.getName().equalsIgnoreCase(cmd.getName()));
        if(nameFound){
        throw new IllegalArgumentException("A command with this name is already exists");
        }
        commands.add(cmd);
    }
    @Nullable
    public ICommand getCommand(String search){
        String searchLower=search.toLowerCase();
        for(ICommand cmd:this.commands){
            if(cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)){
                return cmd;
            }
        }
        return null;
    }
    public List<ICommand> getCommands() {
        return commands;
    }
    void handle(GuildMessageReceivedEvent event){
        String[] split=event.getMessage().getContentRaw()
                .replaceFirst("(?i)"+ Pattern.quote(Config.get("PREFIX")),"")
                .split("\\s");
        String invoke=split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);
        if(cmd!=null){
            event.getChannel().sendTyping().queue();
            List<String> args= Arrays.asList(split).subList(1,split.length);
            CommandContext ctx = new CommandContext(event,args);
            cmd.handle(ctx);
        }
    }
}
