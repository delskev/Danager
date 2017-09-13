package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.dev.entities.Status;
import be.perzival.dev.service.AlbionServiceImpl;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
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
public class AlbionStatus extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(AlbionStatus.class);
    @Autowired
    private Parser afkCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"aostatus"}, description = "give the status of Albion online server", usage = "aostatus")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        AlbionServiceImpl albionService = new AlbionServiceImpl();

        Optional<Status> status = albionService.getStatus();
        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), status);
        message.getChannelReceiver().sendMessage(null, embed);
    }
}
