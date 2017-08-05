package be.perzival.danager.command;

import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

import java.util.concurrent.ExecutionException;

/**
 * Created by Perzival on 04/08/2017.
 */
public interface DanagerCommand {

    void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException, ExecutionException, InterruptedException;

    boolean isEnabled();
    void setEnabled(boolean enable);
}
