package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class GuildJob extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(GuildJob.class);
    @Autowired
    private Parser afkCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"guildjob"}, description = "give the link to the job's sheet", usage = "guildjob")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = afkCommandParser.parse(args);

        String link = "https://docs.google.com/spreadsheets/d/1ibzutLB2I2crut1DWLypDWGYuUEfx9fOBy1OSyp2mg4/edit#gid=295206140";
        message.getAuthor().sendMessage( link );
    }
}
