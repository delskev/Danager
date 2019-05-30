package be.perzival.danager.commands.core;

import be.perzival.danager.commands.AbstractDanagerCommand;
import be.perzival.danager.commands.DanagerCommand;
import be.perzival.danager.handler.DanagerCommandHandler;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand extends AbstractDanagerCommand {

    @Autowired
    @Lazy
    private DanagerCommandHandler commandHandler;


    @Command(aliases = {"help", "commands"}, description = "Shows this page")
    public String onHelpCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append("```xml"); // a xml code block looks fancy

        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands()) {

            //if the command is diasble or don't have help -> return
            if (!((DanagerCommand)simpleCommand.getExecutor()).isEnabled() ||
                    !simpleCommand.getCommandAnnotation().showInHelpPage()) {
                continue; // skip command
            }

            builder.append("\n");
            if (!simpleCommand.getCommandAnnotation().requiresMention()) {
                // the default prefix only works if the command does not require a mention
                builder.append(commandHandler.getDefaultPrefix());
            }
            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty()) { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            builder.append(usage);
            String description = simpleCommand.getCommandAnnotation().description();
            if (!description.equals("none")) {
                builder.append(" | ").append(description);
            }
        }
        builder.append("\n```"); // end of xml code block
        return builder.toString();
    }

}
