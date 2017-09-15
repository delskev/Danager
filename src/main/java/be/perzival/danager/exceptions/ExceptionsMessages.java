package be.perzival.danager.exceptions;

/**
 * Created by Perzival on 30/07/2017.
 */
public enum ExceptionsMessages {

    //Command exception
    COMMANDHANDLERALREADYATTACHED("The CommandHandler have already been attached !"),
    COMMANDHANDLERNOTATTACHED("The CommandHandler is not attached !"),
    //Argument exception
    TOOMANYARGUMENTS("Command contains too many arguments !"),
    NOTENOUGHARGUMENTS("Command contains not enough arguments !"),
    //ParserException
    UNKNOWNCOMMANDPARSER("This parser is unknown: "),
    //EventType Exception
    BADEVENTYPEPROVIDED("You have provided a bad event type: ");


    private String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
    public String toString(){
        return this.value;
    }

}
