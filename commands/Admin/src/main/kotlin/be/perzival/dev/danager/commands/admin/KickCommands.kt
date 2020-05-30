package be.perzival.dev.danager.commands.admin

import be.perzival.dev.danager.api.exceptions.command.NoUserMentionException
import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import io.vavr.collection.List
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.server.Server
import org.springframework.stereotype.Component

@Component
class KickCommands : CommandExecutor {
    @Command(aliases = ["kick"], description = "kick someone if you can", usage = "kick @mention")
    fun onKickCommand(server: Server, message: Message) =
            List.ofAll(message.mentionedUsers)
                    .toTry { NoUserMentionException(message.channel) }
                    .map { server.kickUser(it) }

}