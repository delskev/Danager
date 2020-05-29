package be.perzival.dev.danager.api.exceptions;

/**
 * Created by Perzival on 30/07/2017.
 */
public enum ExceptionMessages {
    //Command exception
    COMMAND_HANDLER_ALREADY_ATTACHED("The CommandHandler have already been attached !"),
    COMMAND_HANDLER_NOT_ATTACHED("The CommandHandler is not attached !"),
    NO_USER_MENTION("No user mentioned in the command "),
    //argument exception
    TOO_MANY_ARGUMENTS("Command contains too many arguments !"),
    NOT_ENOUGH_ARGUMENTS("Command contains not enough arguments !"),
    //ParserException
    UNKNOWN_COMMAND_PARSER("This parser is unknown: ");

    private String value;

    ExceptionMessages(String value) {
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
