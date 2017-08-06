package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.sdcf4j.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Alert extends AbstractCommand {

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
            ConfigurationProperties config = PropertiesManager.getInstance().getServerConfig(server);
            for(User user: server.getMembers()) {
                for(Role role: user.getRoles(server)) {
                    for(String serverAdminRoles: config.getAdmin()) {
                        if(serverAdminRoles.equals(role.getName())) {
                            user.sendMessage(null, Responsefactory.getEmbedResponse(Afk.class, arg.get(), user));
                        }
                    }
                }
            }

        }
    }
}
