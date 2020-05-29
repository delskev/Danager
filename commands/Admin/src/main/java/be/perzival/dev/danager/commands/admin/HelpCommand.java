package be.perzival.dev.danager.commands.admin;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements CommandExecutor {

    @Autowired
    @Lazy
    private CommandHandler commandHandler;


    @Command(aliases = {"help", "commands"}, description = "Shows this page")
    public String onHelpCommand() {
        List<CommandHandler.SimpleCommand> commandList = List.ofAll(commandHandler.getCommands());
        return commandList
                .map(CommandHandler.SimpleCommand::getCommandAnnotation)
                .filter(Command::showInHelpPage)
                .map(this::formatHelpLine)
                .intersperse("\n")
                .fold("```xml\n", String::concat)
                .concat("\n```");
    }

    private String formatHelpLine(Command a) {
        return new StringBuilder(commandHandler.getDefaultPrefix())
                .append((a.usage().isEmpty() ? a.aliases()[0] : a.usage()))
                .append("\t|\t")
                .append(a.description())
                .toString();
    }


}
