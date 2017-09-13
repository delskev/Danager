package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Rand extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(Rand.class);
    @Autowired
    private Parser afkCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"rand"}, description = "give a random number", usage = "rand")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Random rand = new Random();

        message.getChannelReceiver().sendMessage("nombre: " + rand.nextInt(101));
    }
}
