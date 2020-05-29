package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class CommandHandlerAlreadyAttached extends CommandException {

    public CommandHandlerAlreadyAttached() {

    }

    public CommandHandlerAlreadyAttached(ExceptionsMessages message) {
        super(message);
    }

    public CommandHandlerAlreadyAttached(Throwable cause) {
        super(cause);
    }

    public CommandHandlerAlreadyAttached(String message, Throwable cause) {
        super(message, cause);
    }
}
