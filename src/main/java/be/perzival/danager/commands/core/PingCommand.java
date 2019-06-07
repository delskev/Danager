package be.perzival.danager.commands.core;

import be.perzival.danager.Application;
import be.perzival.danager.commands.AbstractDanagerCommand;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class PingCommand extends AbstractDanagerCommand {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);


    @Command(aliases = {"ping"}, description = "pong!", usage = "ping")
    public String onCommand() {
        LOG.info("Execute ping command");
        return "Pong!";
    }
}
