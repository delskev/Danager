package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Help extends AbstractCommand{


    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"help", "commands"}, description = "Shows this page", usage = "!help or !commands")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        StringBuilder builder = new StringBuilder();

        if(!isCommandHandlerAttached()) {
            return ;
        }

        builder.append("```xml"); // a xml code block looks fancy
        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands()) {
            if (!simpleCommand.getCommandAnnotation().showInHelpPage()) {
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
        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
        message.reply(null, embed);
    }
}
