package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Alert extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Alert.class);

    @Autowired
    private Parser alertCommandParser;

    /**
     * Show info about the bot
     * @param args [author|time]
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"alert" }, description = "send private message to admin", usage = "alert message")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = alertCommandParser.parse(args);

        if(argument.hasArgument()) {
            Optional<String> arg = argument.getArgument(ArgumentType.REASON);
            Server server = getServer(message);
            for(User user: server.getMembers()) {
                if(isadmin(user, server)) {
                    user.sendMessage(null, Responsefactory.getEmbedResponse(Afk.class, arg.get(), user));
                    message.delete();
                }
            }

        }
    }
}
