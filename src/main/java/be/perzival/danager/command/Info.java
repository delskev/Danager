package be.perzival.danager.command;

import be.perzival.danager.exceptions.command.CommandException;
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
    public String executeCommand(String[] args) throws CommandException {
        if (args.length > 1) { // more than 1 argument
            return "To many arguments!";
        }
        if (args.length == 0) { // !info
            return "- **Author:** <YourName>\n" +
                    "- **Language:** Java\n" +
                    "- **Command-Lib:** sdcf4j";
        }
        if (args.length == 1) { // 1 argument
            if (args[0].equals("author")) { // !info author
                return "- **Name:** <YourName>\n" +
                        "- **Age:** <YourAge>\n" +
                        "- **Website:** <YourWebsite>";
            }
            if (args[0].equals("time")) { // !info time
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date currentDate = new Date(System.currentTimeMillis());
                return "It's" + format.format(currentDate);
            }
        }
        return "Unknown argument!";
    }
}
