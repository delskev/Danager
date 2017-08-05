package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.DanagerCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Perzival on 01/08/2017.
 */
@Component
public class Disable extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Disable.class);

    @Autowired
    Map<String, AbstractCommand> commandExecutorssMap;

    /**
     *  )
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"disable" }, description = "disable a command", usage = "disable [command]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length == 0) { // more than 1 argument
            message.reply("You need to provide more argument !");
            return;
        }

        if(!isadmin(message))return;

        DanagerCommand command = commandExecutorssMap.get(args[0]);
        if(command != null) {
            command.setEnabled(false);
            EmbedBuilder embed = Responsefactory.getEmbedResponse(this, "command "+args[0] + " disabled");
            message.reply(null, embed);
        }
    }

}
