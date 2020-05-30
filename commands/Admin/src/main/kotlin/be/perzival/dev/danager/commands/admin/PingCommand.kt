package be.perzival.dev.danager.commands.admin

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import org.springframework.stereotype.Component

@Component
class PingCommand : CommandExecutor {
    @Command(aliases = ["ping"], description = "pong!", usage = "ping")
    fun onCommand(): String = "Pong!"
}