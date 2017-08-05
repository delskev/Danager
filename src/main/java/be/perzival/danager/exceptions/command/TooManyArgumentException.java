package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class TooManyArgumentException extends CommandException {

    public TooManyArgumentException() {

    }

    public TooManyArgumentException(ExceptionsMessages message) {
        super(message);
    }

    public TooManyArgumentException(Throwable cause) {
        super(cause);
    }

    public TooManyArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
