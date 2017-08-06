package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.manager.AfkManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Afk extends AbstractCommand{
    @Autowired
    private Parser afkCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"afk"}, description = "display message when player is mentioned", usage = "afk message | afk . to stop messaging")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = afkCommandParser.parse(args);

        if(argument.hasArgument()) {
            Optional<String> arg = argument.getArgument(ArgumentType.REASON);
            if( !".".equals(arg.get().trim())  ) {
                //add afk user
                AfkManager.getInstance().addAfkUser(message.getAuthor(), arg.get());
            }else {
                AfkManager.getInstance().removeAfkUser(message.getAuthor());
            }
        }
    }
}
