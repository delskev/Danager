package be.perzival.danager.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
public class PingCommand implements CommandExecutor {

    @Command(aliases = {"ping"}, description = "pong!", usage = "ping")
    public String onCommand() {
        return "Pong!";
    }
}
