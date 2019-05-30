package be.perzival.danager.commands.core;

import be.perzival.danager.commands.AbstractDanagerCommand;
import be.perzival.danager.utils.ResponseFactory;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class PrefixCommand extends AbstractDanagerCommand {

    @Autowired
    @Lazy
    private CommandHandler commandHandler;


    @Command(aliases = {"prefix"}, description = "Change the command prefix", usage = "prefix [new prefix]")
    public void onPrefixCommand(String[] parameters, Server server, MessageAuthor user, TextChannel textChannel) {
        if(this.isEnabled() || server == null || parameters.length == 0 || parameters.length > 1 ) return ;

        if( !user.isServerAdmin()) {
            ResponseFactory.getEmbedResponse(ResponseFactory.CommonResponse.NOTANDADMIN)
                    .send(textChannel);
            return;
        }

        String oldPrefix = commandHandler.getDefaultPrefix();
        commandHandler.setDefaultPrefix(parameters[0]);

        ResponseFactory
                .getEmbedResponse(ResponseFactory.ResponseType.SUCCESS, "Successfully update prefix from "+ oldPrefix + " to " + parameters[0])
                .send(textChannel);
    }
}

