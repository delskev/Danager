package be.perzival.dev.danager.commands.admin

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import de.btobastian.sdcf4j.CommandHandler
import io.vavr.collection.List
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class HelpCommand : CommandExecutor {
    private val commandHandler: CommandHandler;

    @Autowired
    @Lazy
    constructor(commandHandler: CommandHandler) {
        this.commandHandler = commandHandler
    }

    @Command(aliases = ["help", "commands"], description = "Shows this page")
    fun onHelpCommand(): String =
            List.ofAll(commandHandler.commands)
                    .map { t -> t.commandAnnotation }
                    .filter { command -> command.showInHelpPage }
                    .map { a: Command -> formatHelpLine(a) }
                    .intersperse("\n")
                    .fold("```xml\n", String::plus)
                    .plus("\n```")


    private fun formatHelpLine(command: Command): String =
            StringBuilder(commandHandler.defaultPrefix)
                    .append(if (command.usage.isEmpty()) command.aliases[0] else command.usage)
                    .append("\t|\t")
                    .append(command.description)
                    .toString()


}