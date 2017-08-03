package be.perzival.danager.command.utils;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Info extends AbstractCommand {

    /**
     * Show info about the bot
     * @param args [author|time]
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"info" }, description = "Shows some information about the bot.", usage = "info [author|time]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length > 1) { // more than 1 argument
            message.reply("To many arguments!");
        }
        StringBuilder builder = new StringBuilder();
        if (args.length == 0) { // !info
            builder.append("- **Author:** Perzival\n" +
                            "- **Language:** Java\n" +
                            "- **Command-Lib:** sdcf4j");
        }
        if (args.length == 1) { // 1 argument
            if (args[0].equals("author")) { // !info author
                builder.append("- **Name:** Perzival\n" +
                                "- **Age:** 26\n" +
                                "- **Website:** NONE");
            }
            if (args[0].equals("time")) { // !info time
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date currentDate = new Date(System.currentTimeMillis());
                builder.append("il est " + format.format(currentDate) + " et tout va bien !");
            }
        }
        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
        message.reply(null, embed);
    }
}
