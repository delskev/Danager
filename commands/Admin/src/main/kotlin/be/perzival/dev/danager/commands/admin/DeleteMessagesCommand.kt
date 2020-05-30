package be.perzival.dev.danager.commands.admin

import be.perzival.dev.danager.api.exceptions.command.NotEnougArgumentException
import be.perzival.dev.danager.api.utils.ResponseType
import be.perzival.dev.danager.api.utils.createEmbedResponse
import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.message.MessageSet
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DeleteMessagesCommand : CommandExecutor {
    private val LOG = LoggerFactory.getLogger(DeleteMessagesCommand::class.java)

    @Command(aliases = ["delete"], description = "Delete message(s) into channel (max 99)", usage = "delete [number]")
    fun onDeleteCommand(parameters: Array<String>, textChannel: TextChannel) {
        check(parameters.size > 1) { throw NotEnougArgumentException(textChannel) }
        LOG.info("Trying to delete {} message(s)", parameters[0])
        textChannel.getMessages(Integer.valueOf(parameters[0]))
                .thenAcceptAsync { obj: MessageSet -> obj.deleteAll() }
        createEmbedResponse(ResponseType.NORMAL, "Successfully delete  $parameters[0]  message(s)")
    }

}