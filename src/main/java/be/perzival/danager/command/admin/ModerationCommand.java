package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

/**
 * Created by Perzival on 05/08/2017.
 */
public abstract class ModerationCommand extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(ModerationCommand.class);

    @Autowired
    @Qualifier("moderationCommandParser")
    private Parser moderationCommandParser;

    protected void execute(DiscordAPI api, Message message, String[]args, Moderationtype moderationtype) throws CommandException {
        Argument argument = moderationCommandParser.parse(args);

        if(!argument.hasArgument()) { // more than 1 argument
            message.reply("You need to provide more argument !");
            return;
        }
        if(!isadmin(message))return;

        Server server = getServer(message);
        Collection<User> members = server.getMembers();

        User user  = findUser(members, argument.getArgument(ArgumentType.USER).get());
        switch(moderationtype) {
            case KICK:
                server.kickUser(user);
                break;
            case BAN:
                server.banUser(user);
                break;
            case UNBAN:
                server.unbanUser(user.getId());
                break;
        }
        LOG.info(moderationtype.getType() + "player "+ user + "from server "+ message.getChannelReceiver().getServer());
        String reason = argument.getArgument(ArgumentType.REASON).orElse("/");
        user.sendMessage(reason.toString());
        EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), moderationtype.getType()+ user.getName() + "\nReason: "+reason);
        user.sendMessage(null, builder);
        message.getChannelReceiver().sendMessage(null, builder);
    }

    protected enum Moderationtype {
        KICK("Kick ", "You've got kicked from"),
        BAN("Ban ", "You've got ban from"),
        UNBAN("Unban ", "You've got unban from");

        private String type;
        private String message;

        private Moderationtype(String type, String message) {
            this.message = message;
            this.type = type;
        }

        public String getType() {return this.type;}
        public String getMessage() {return this.message;}
    }
}
