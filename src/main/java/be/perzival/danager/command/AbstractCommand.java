package be.perzival.danager.command;

import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.exceptions.command.CommandHandlerNotAttached;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

/**
 * Created by Perzival on 30/07/2017.
 */
public abstract class  AbstractCommand implements CommandExecutor {

    protected CommandHandler commandHandler = null;

    public abstract String executeCommand(String[] args) throws CommandException;

    public void attachCommandHandler(CommandHandler commandHandler) {
        if(this.commandHandler == null) {
            this.commandHandler = commandHandler;
        }
    }

    public boolean isCommandHandlerAttached() throws CommandException {
        if( commandHandler == null) {
            throw new CommandHandlerNotAttached(ExceptionsMessages.COMMANDHANDLERNOTATTACHED);
        }
        return true;
    }
}
