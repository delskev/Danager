package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 01/08/2017.
 */
@Component
public class Strike extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Strike.class);


    /**
     *  )
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"kick" }, description = "kick a user with an optionnal reason", usage = "kick [user][reason]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length == 0 || args.length < 1) { // more than 1 argument
            message.reply("You need to provide more argument !");
        }
    }


}
