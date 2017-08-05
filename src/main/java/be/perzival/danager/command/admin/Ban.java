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
public class Ban extends AbstractCommand {
    static final Logger LOG = LoggerFactory.getLogger(Ban.class);


    /**
     *  )
     * @param api
     * @param message
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"ban" }, description = "ban a user with an optionnal reason", usage = "ban [user][reason]", privateMessages = false)
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        if (args.length == 0) { // more than 1 argument
            message.reply("You need to provide more argument !");
            return;
        }
        try {
            if(!isadmin(api, message))return;
            Server server = getServer(message);
            Collection<User> members = server.getMembers();

            User user  = findUser(members, args[0]);
            StringBuilder reason = new StringBuilder();
            if(args.length >=2) {
                for(int i = 1; i < args.length; ++i) {
                    reason.append(args[i]+ " ");
                }
                user.sendMessage(reason.toString());
                EmbedBuilder builder = Responsefactory.getEmbedResponse(this, "tu as été bannis du serveur "+ server.getName() + "\nraison: " +reason );
                user.sendMessage(null, builder).get();
            }
            server.banUser(user).get();
            EmbedBuilder builder = Responsefactory.getEmbedResponse(this, "Ban "+ user.getName() + "\nraison: " +reason );
            message.getChannelReceiver().sendMessage(null, builder).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } {

        }
    }

}
