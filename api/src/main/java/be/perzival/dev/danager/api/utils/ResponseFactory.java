package be.perzival.dev.danager.api.utils;


import be.perzival.dev.danager.api.exceptions.ExceptionMessages;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public interface ResponseFactory {

    enum CommonResponse {
        NOT_AND_ADMIN,
        NOT_ENABLE
    }

    enum ResponseType {
        ALERT,
        NORMAL,
        SUCCESS,
        ERROR
    }

    static MessageBuilder getEmbedResponse(CommonResponse commonResponse) {
        switch (commonResponse) {
            case NOT_AND_ADMIN:
                return getEmbedResponse(ResponseType.ALERT, "This command requires admin privillege...");
            case NOT_ENABLE:
                return getEmbedResponse(ResponseType.ALERT, "This command is not Enable...");
            default:
                return getEmbedResponse(ResponseType.ERROR, "Unknown Commands");
        }
    }

    static MessageBuilder getEmbedResponse(ResponseType responseType, ExceptionMessages message) {
        return getEmbedResponse(responseType, message.value());
    }

    static MessageBuilder getEmbedResponse(ResponseType responseType, String message) {
        EmbedBuilder builder = new EmbedBuilder()
                .setUrl("https://github.com/delskev/Danager")
                .setAuthor("Danager", "https://github.com/delskev/Danager", "http://s3.amazonaws.com/cdn.roosterteeth.com/uploads/images/de8ca976-2cf3-4435-8e84-2985dbc04409/md/2220309-1451961825223-Ni.jpeg")
                .setDescription(message);

        switch (responseType) {
            case ALERT:
                builder.setColor(Color.ORANGE);
                break;
            case NORMAL:
                //Nothing for the moment
                break;
            case SUCCESS:
                builder.setColor(Color.GREEN);
                break;
            case ERROR:
                builder.setColor(Color.RED);
                break;
        }

        return new MessageBuilder().setEmbed(builder);
    }
}