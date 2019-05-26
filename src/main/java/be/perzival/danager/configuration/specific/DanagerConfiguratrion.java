package be.perzival.danager.configuration.specific;

import be.perzival.danager.Application;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "danager")
public class DanagerConfiguratrion implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(DanagerConfiguratrion.class);


    @NotBlank
    private Commands commands;
    private String test;


    private List<CommandExecutor> commandExecutorList;

    @Autowired
    public DanagerConfiguratrion(List<CommandExecutor> commandExecutorList) {
        this.commandExecutorList = commandExecutorList;
    }

    @Bean
    public CommandHandler commandHandler(DiscordApi discordApi) {
        CommandHandler commandHandler = new JavacordHandler(discordApi);
        commandHandler.setDefaultPrefix(this.commands.getPrefix());
        commandExecutorList.forEach(commandHandler::registerCommand);

        LOG.info("Availlable commands: {}", commandExecutorList );
        return commandHandler;
    }

    public Commands getCommands() {
        return commands;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
