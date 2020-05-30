package be.perzival.dev.danager.api.exceptions.command;


/**
 * Created by Perzival on 30/07/2017.
 */


import be.perzival.dev.danager.api.exceptions.ExceptionMessages;
import be.perzival.dev.danager.api.utils.ResponseFactory;
import be.perzival.dev.danager.api.utils.ResponseFactory.ResponseType;
import org.javacord.api.entity.channel.TextChannel;

/**
 * root of the command exception
 */
public class CommandException extends Exception {
    public CommandException(TextChannel textChannel, ResponseType responseType, ExceptionMessages exceptionMessages) {
        ResponseFactory
                .getEmbedResponse(responseType, exceptionMessages)
                .send(textChannel);
    }

    public CommandException(ExceptionMessages message) {
        super(message.value());
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
