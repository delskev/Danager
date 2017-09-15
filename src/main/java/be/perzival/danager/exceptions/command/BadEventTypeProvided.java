package be.perzival.danager.exceptions.command;

import be.perzival.danager.exceptions.ExceptionsMessages;

/**
 * Created by Perzival on 30/07/2017.
 */
public class BadEventTypeProvided extends CommandException{

    public BadEventTypeProvided() {
        super();
    }

    public BadEventTypeProvided(String argument) {
        super(ExceptionsMessages.BADEVENTYPEPROVIDED + argument);
    }

    public BadEventTypeProvided(ExceptionsMessages message) {
        super(message);
    }

    public BadEventTypeProvided(Throwable cause) {
        super(cause);
    }

    public BadEventTypeProvided(String message, Throwable cause) {
        super(message, cause);
    }

}
