package be.perzival.danager.handler;

import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import io.vavr.collection.List;
import org.javacord.api.DiscordApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * this class is defined to avoid circular dependencies
 * between @commandHandler and @CommandExecutor
 * it's only define a @PostConstruct method
 * but keeps the same behaviour
 */
@Component
public class DanagerCommandHandler extends JavacordHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DanagerCommandHandler.class);

    @Value("${danager.commands.prefix:/}")
    private String prefix;
    private List<CommandExecutor> commandExecutorList;

    @Autowired
    public DanagerCommandHandler(DiscordApi api, java.util.List<CommandExecutor> commandExecutorList) {
        super(api);
        this.commandExecutorList = List.ofAll(commandExecutorList);
    }

    @PostConstruct
    public void initCommand() {
        this.setDefaultPrefix(this.prefix);
        this.commandExecutorList.forEach(this::registerCommand);
        LOG.info("Availlable commands: {}", commandExecutorList);
    }

}
