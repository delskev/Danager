package be.perzival.dev.danager.api.exceptions.command;

import be.perzival.dev.danager.api.exceptions.ExceptionMessages;
import be.perzival.dev.danager.api.utils.ResponseFactory;
import org.javacord.api.entity.channel.TextChannel;

/**
 * Created by Perzival on 30/07/2017.
 */
public class NoUserMentionException extends CommandException {

    public NoUserMentionException(TextChannel textChannel) {
        super(textChannel,
                ResponseFactory.ResponseType.ALERT,
                ExceptionMessages.NO_USER_MENTION);
    }
}
