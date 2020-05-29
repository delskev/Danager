package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class NotEnoughArgumentException extends CommandException {

    public NotEnoughArgumentException() {

    }

    public NotEnoughArgumentException(ExceptionsMessages message) {
        super(message);
    }

    public NotEnoughArgumentException(Throwable cause) {
        super(cause);
    }

    public NotEnoughArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
