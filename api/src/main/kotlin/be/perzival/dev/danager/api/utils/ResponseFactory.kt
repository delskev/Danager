package be.perzival.dev.danager.api.utils

import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import java.awt.Color

enum class ResponseType {
    ALERT, NORMAL, SUCCESS, ERROR
}

fun createEmbedResponse(responseType: ResponseType, message:String): MessageBuilder {
    val builder = EmbedBuilder()
            .setUrl("https://github.com/delskev/Danager")
            .setAuthor("Danager", "https://github.com/delskev/Danager", "http://s3.amazonaws.com/cdn.roosterteeth.com/uploads/images/de8ca976-2cf3-4435-8e84-2985dbc04409/md/2220309-1451961825223-Ni.jpeg")
            .setDescription(message)
    when (responseType) {
        ResponseType.ALERT -> builder.setColor(Color.ORANGE)
        ResponseType.NORMAL -> builder.setColor(Color.BLUE)
        ResponseType.SUCCESS -> builder.setColor(Color.GREEN)
        ResponseType.ERROR -> builder.setColor(Color.RED)
    }
    return MessageBuilder().setEmbed(builder)
}

