package be.perzival.danager.listener;

import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.utils.Afk;
import be.perzival.danager.manager.AfkManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Perzival on 05/08/2017.
 */
@Component
public class AfkListener implements MessageCreateListener {
    static final Logger LOG = LoggerFactory.getLogger(AfkListener.class);

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        //if private message or bot message
        if(message.isPrivateMessage() || message.getAuthor().isBot())return;
        AfkManager manager = AfkManager.getInstance();
        // get mention present in the message
        List<User> usersmMention = message.getMentions();

        //remove user automatically when comes back
        if(AfkManager.getInstance().isAfk(message.getAuthor())) {
            AfkManager.getInstance().removeAfkUser(message.getAuthor());
        }

        //send afk message of the user mention
        usersmMention.stream()
                     .filter(user -> manager.isAfk(user))
                     .forEach(afk -> message.reply(null,
                                                   Responsefactory.getEmbedResponse(Afk.class,
                                                           manager.getAFKUserMessage(afk),
                                                           message.getAuthor())));

    }
}
