package be.perzival.dev.danager.commands.admin;

import be.perzival.dev.danager.api.utils.ResponseFactory;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class DeleteMessagesCommand implements CommandExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteMessagesCommand.class);


    @Command(aliases = {"delete"}, description = "Delete message(s) into channel (max 99)", usage = "delete [number]")
    public void onDeleteCommand(String[] parameters, TextChannel textChannel) {
        if (textChannel == null || parameters.length != 1) return;

        LOG.info("Trying to delete {} message(s)", parameters[0]);
        textChannel.getMessages(Integer.valueOf(parameters[0]))
                .thenAcceptAsync(MessageSet::deleteAll);

        ResponseFactory
                .getEmbedResponse(ResponseFactory.ResponseType.SUCCESS, "Successfully delete " + parameters[0] + " message(s")
                .send(textChannel);
    }
}
