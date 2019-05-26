package be.perzival.danager.commands.common;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.entity.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrefixCommand implements CommandExecutor {

    @Autowired
    private CommandHandler commandHandler;


    @Command(aliases = {"prefix"}, description = "Change the command prefix", usage = "prefix [new prefix]", requiredPermissions = "admin")
    public void onHelpCommand(String command, String[] parameters, Server server) {
        if(server == null || parameters.length == 0 || parameters.length > 1) return ;

        commandHandler.setDefaultPrefix(parameters[0]);
    }

}
