package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    public String executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        try {
            if(isOwner(api, message) || isadmin(api, message)) {
                message.getAuthor().sendMessage(configurationProperties.toString());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
