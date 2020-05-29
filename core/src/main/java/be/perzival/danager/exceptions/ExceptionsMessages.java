package be.perzival.danager.exceptions;

/**
 * Created by Perzival on 30/07/2017.
 */
public enum ExceptionsMessages {

    //Command exception
    COMMAND_HANDLER_ALREADY_ATTACHED("The CommandHandler have already been attached !"),
    COMMAND_HANDLER_NOT_ATTACHED("The CommandHandler is not attached !"),
    //argument exception
    TOO_MANY_ARGUMENTS("Command contains too many arguments !"),
    NOT_ENOUGH_ARGUMENTS("Command contains not enough arguments !"),
    //ParserException
    UNKNOWN_COMMAND_PARSER("This parser is unknown: ");

    private String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
