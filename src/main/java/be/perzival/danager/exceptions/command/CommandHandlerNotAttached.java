package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class CommandHandlerNotAttached extends CommandException {

    public CommandHandlerNotAttached() {

    }

    public CommandHandlerNotAttached(ExceptionsMessages message) {
        super(message);
    }

    public CommandHandlerNotAttached(Throwable cause) {
        super(cause);
    }

    public CommandHandlerNotAttached(String message, Throwable cause) {
        super(message, cause);
    }
}
