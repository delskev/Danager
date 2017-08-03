package be.perzival.danager.command.admin;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

/**
 * Created by Perzival on 01/08/2017.
 */
@Component
public class Kick extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Kick.class);


    /**
     *  )
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"kick" }, description = "kick a user with an optionnal reason", usage = "kick [user][reason]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length == 0 || args.length < 1) { // more than 1 argument
            message.reply("You need to provide more argument !");
        }

        try {
            if( isadmin(api, message) || isOwner(api, message)) {
                Server server = getCorrectServer(api, message);
                Collection<User> members = server.getMembers();

                User user  = findUser(members, args[0]);
                StringBuilder reason = new StringBuilder();
                if(args.length >=2) {
                    //StringBuilder builder = new StringBuilder();
                    for(int i = 1; i < args.length; ++i) {
                        reason.append(args[i]+ " ");
                    }
                    user.sendMessage(reason.toString());
                    EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), "tu as été kické du serveur "+ server.getName() + "\nraison: " +reason );
                    user.sendMessage(null, builder).get();
                }
                server.kickUser(user).get();
                EmbedBuilder builder = Responsefactory.getEmbedResponse(this.getClass(), "Kick "+ user.getName() + "\nraison: " +reason );
                message.getChannelReceiver().sendMessage(null, builder).get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } {

        }
    }

}
