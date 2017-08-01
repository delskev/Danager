package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Delete extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Delete.class);


    /**
     * delete a number of message specified in parameter (delete [number of message] )
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"delete" }, description = "delete message on current channel", usage = "delete [number of message] (max 100)", privateMessages = false)
    public String executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length > 1) { // more than 1 argument
            return "To many arguments!";
        }
        if (args.length == 0) { // more than 1 argument
            return "no argument provided !";
        }
        try {
            if(isOwner(api, message) || isadmin(api, message)) {
                Future<MessageHistory> messageHistory = message.getChannelReceiver().getMessageHistory(Integer.parseInt(args[0]));

                Collection<Message> messagesSorted = messageHistory.get().getMessages();

                //avoid pinned message suppression
                messagesSorted.forEach(m -> {if(message.isPinned())messagesSorted.remove(m);});

                message.getChannelReceiver().bulkDelete(messagesSorted.toArray(new Message[messagesSorted.size()])).get();
            }
            return "Message deleted: "+ args[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
