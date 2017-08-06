package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Property extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Property.class);

    @Autowired
    private Parser propertyCommandParser;

    /**
     * Display all the property of the bot configuration's file
     * usage: [prefix]property
     * @param args
     * @return
     * @throws CommandException throw cause of introspection
     */
    @Override
    @Command(aliases = {"property" }, description = "Shows bot's configuration", usage = "property [set | get]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = propertyCommandParser.parse(args);

        ConfigurationProperties config = PropertiesManager.getInstance().getServerConfig(getServer(message));

        if(!isadmin(message))return;
        EmbedBuilder builder = null;
        if( !argument.hasArgument()) {
            builder = Responsefactory.getEmbedResponse(this.getClass(), config.toString());
        }
        //want to get or specify a property
        switch(argument.getArgument(ArgumentType.MODE).get()) {
            case "get":
                builder = Responsefactory.getEmbedResponse(this.getClass(), config.getProperty(argument.getArgument(ArgumentType.PROPERTY).get()));
                break;
            case "set":
                config.setProperty(argument.getArgument(ArgumentType.PROPERTY).get(),
                                   argument.getArgument(ArgumentType.VALUE).get());
                PropertiesManager.getInstance().persistServerConfig(getServer(message));
                builder = Responsefactory.getEmbedResponse(this.getClass(), config.getProperty(args[1]));
                break;
        }
        message.reply(null, builder);

    }
}
