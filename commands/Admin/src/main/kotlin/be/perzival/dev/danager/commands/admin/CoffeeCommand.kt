package be.perzival.dev.danager.commands.admin

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.message.MessageBuilder
import org.springframework.stereotype.Component

@Component
class CoffeeCommand : CommandExecutor {
    @Command(aliases = ["coffee"], description = "need coffee", usage = "coffee")
    fun onCoffeeCommand(textChannel: TextChannel?) {
        MessageBuilder().setContent("Let's have a :coffee: !").send(textChannel)
    }
}