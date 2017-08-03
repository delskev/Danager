package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.manager.PropertiesManager;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Property extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Property.class);


    /**
     * Display all the property of the bot configuration's file
     * usage: [prefix]property
     * @param args
     * @return
     * @throws CommandException throw cause of introspection
     */
    @Override
    @Command(aliases = {"property" }, description = "Shows bot's configuration", usage = "property", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        ConfigurationProperties config = PropertiesManager.getInstance().getServerConfig(getCorrectServer(api, message));
        try {
            if(isOwner(api, message) || isadmin(api, message)) {
                if( args.length == 0) {
                    EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), config.toString());
                    message.getAuthor().sendMessage(null, builder).get();
                }
                //want to get or specify a property
                if (args.length > 1 && "get".equals(args[0])) {//get case
                    EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), config.getProperty(args[1]));
                    message.reply(null, builder);
                }
                if(args.length > 2 && "set".equalsIgnoreCase(args[0])) {
                    config.setProperty(args[1], args[2]);
                    PropertiesManager.getInstance().persistServerConfig(getCorrectServer(api, message));
                    EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), config.getProperty(args[1]));
                    message.reply(null, builder);
                }

            }
        } catch (IOException e) {
            message.reply("there's an error with setting property : "+ args[1]);
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
