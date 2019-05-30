package be.perzival.danager.commands.core;

import be.perzival.danager.commands.AbstractDanagerCommand;
import be.perzival.danager.commands.DanagerCommand;
import be.perzival.danager.handler.DanagerCommandHandler;
import be.perzival.danager.utils.ResponseFactory;
import de.btobastian.sdcf4j.Command;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EnableCommand extends AbstractDanagerCommand {
    private static final Logger LOG = LoggerFactory.getLogger(EnableCommand.class);

    @Autowired
    @Lazy
    private DanagerCommandHandler commandHandler;


    @Command(aliases = {"enable"}, description = "enable a command", usage = "enable [command]")
    public void onEnableCommand(String[] parameters, DiscordApi discordApi, TextChannel textChannel) {
        if( parameters.length == 0 || parameters.length > 1 ) return ;

        String commandToDisable = parameters[0];

        this.getCommandByAlias(commandToDisable).ifPresentOrElse(c -> {
            ((DanagerCommand)c.getExecutor()).enable();
            ResponseFactory
                    .getEmbedResponse(ResponseFactory.ResponseType.SUCCESS, "Successfully disable "+ parameters[0] + " command")
                    .send(textChannel);
        }, () -> ResponseFactory
                .getEmbedResponse(ResponseFactory.ResponseType.ALERT, "Command:" + commandToDisable + ", not found")
                .send(textChannel));
    }
}
