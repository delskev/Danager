package be.perzival.danager.exceptions.command;

import be.perzival.danager.utils.ResponseFactory;
import org.javacord.api.entity.channel.TextChannel;

public abstract class DiscordException extends Exception {


    public DiscordException(String message, TextChannel textChannel) {
        super(message);
        ResponseFactory
                .getEmbedResponse(ResponseFactory.ResponseType.ERROR, message)
                .send(textChannel);
    }
}
