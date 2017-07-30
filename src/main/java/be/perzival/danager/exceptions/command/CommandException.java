package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class CommandException extends Exception {
    public CommandException () {

    }

    public CommandException (ExceptionsMessages message) {
        super(message.getValue());
    }

    public CommandException (Throwable cause) {
        super(cause);
    }

    public CommandException (String message, Throwable cause) {
        super(message, cause);
    }
}
