package be.perzival.danager.command;

import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.sdcf4j.Command;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Info extends AbstractCommand {

    @Command(aliases = {"info" }, description = "Shows some information about the bot.", usage = "info [author|time]")
    public String onInfoCommand(String[] args) {
        return "Hello world"; // dummy return type
    }


    @Override
    public String executeCommand(String[] args) throws CommandException {
        return this.onInfoCommand(args);
    }
}
