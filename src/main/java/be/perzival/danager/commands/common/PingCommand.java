package be.perzival.danager.commands.common;

import be.perzival.danager.Application;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PingCommand implements CommandExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);


    @Command(aliases = {"ping"}, description = "pong!")
    public String onCommand() {
        LOG.info("Execute ping command");
        return "Pong!";
    }

    @Override
    public String toString() {
        return "Ping";
    }
}
