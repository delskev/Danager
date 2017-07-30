package be.perzival.danager.command;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Info implements CommandExecutor {

    @Command(aliases = {"info" }, description = "Shows some information about the bot.", usage = "info [author|time]")
    public String onInfoCommand(String[] args) {
        return "Hello world"; // dummy return type
    }
}
