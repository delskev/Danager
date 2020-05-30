package be.perzival.dev.danager.api.exceptions.command

import be.perzival.dev.danager.api.exceptions.ExceptionMessages
import be.perzival.dev.danager.api.utils.ResponseType
import be.perzival.dev.danager.api.utils.createEmbedResponse
import org.javacord.api.entity.channel.TextChannel

/**
 * Created by Perzival on 30/07/2017.
 */
/**
 * root of the command exception
 */
open class CommandException(textChannel: TextChannel, responseType: ResponseType, exceptionMessages: ExceptionMessages) : Exception() {
    init{ createEmbedResponse(responseType, exceptionMessages.toString()).send(textChannel) }
}


class NoUserMentionException(textChannel: TextChannel) :
        CommandException(textChannel, ResponseType.ALERT, ExceptionMessages.NO_USER_MENTION)

class NotEnougArgumentException(textChannel: TextChannel) :
        CommandException(textChannel, ResponseType.ALERT, ExceptionMessages.BAD_PARAMETER)