package be.perzival.danager.exceptions;

/**
 * Created by Perzival on 30/07/2017.
 */
public enum ExceptionsMessages {

    //Command Excetpion
    COMMANDHANDLERALREADYATTACHED("The CommandHandler have already been attached !"),
    COMMANDHANDLERNOTATTACHED("The CommandHandler is not attached !");

    private String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public String toString(){
        return this.value;
    }

}
