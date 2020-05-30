package be.perzival.dev.danager.commands.admin

import de.btobastian.sdcf4j.CommandExecutor

interface DanagerCommand : CommandExecutor {
    val isEnabled: Boolean
        get() = true

    fun enable()
    fun disable()
}