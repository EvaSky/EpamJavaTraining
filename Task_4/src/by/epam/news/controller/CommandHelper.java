package by.epam.news.controller;

import by.epam.news.command.Command;
import by.epam.news.command.impl.FindNewsCommand;
import by.epam.news.command.impl.SaveNewNewsCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    CommandHelper(){
        commands.put(CommandName.SAVE_NEW_NEWS, new SaveNewNewsCommand());

        commands.put(CommandName.FIND_NEWS, new FindNewsCommand());
    }

    public Command getCommand(String name){
        CommandName commandName = CommandName.valueOf(name);
        Command command = commands.get(commandName);

        return command;

    }
}
