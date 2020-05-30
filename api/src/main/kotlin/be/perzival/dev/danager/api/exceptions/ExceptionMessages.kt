package be.perzival.dev.danager.api.exceptions

/**
 * Created by Perzival on 30/07/2017.
 */
enum class ExceptionMessages(private val value: String) {
    //Command exception
    COMMAND_HANDLER_ALREADY_ATTACHED("The CommandHandler have already been attached !"),
    COMMAND_HANDLER_NOT_ATTACHED("The CommandHandler is not attached !"),
    NO_USER_MENTION("No user mentioned in the command "),
    BAD_PARAMETER("There's an error with the provided (or not!) parameter(s)"),
    ADMIN_COMMAND("This command requires admin privillege..."),
    NOT_ENABLE("This command is not Enable..."),
    UNKNOWN("Unknown Commands");
}