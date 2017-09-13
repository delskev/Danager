package be.perzival.danager.command.admin;

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
public class Unban extends ModerationCommand {
    static final Logger LOG = LoggerFactory.getLogger(Unban.class);
    /**
     *  unban from server
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"unban" }, description = "Unban a user with an optionnal reason", usage = "Unban [user]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        this.execute(api, message, args, Moderationtype.UNBAN);
    }

}
