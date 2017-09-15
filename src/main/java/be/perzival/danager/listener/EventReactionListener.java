package be.perzival.danager.listener;

import be.perzival.danager.entities.EventEntities;
import be.perzival.danager.manager.EventManager;
import com.vdurmont.emoji.EmojiManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.Reaction;
import de.btobastian.javacord.listener.message.ReactionAddListener;
import de.btobastian.javacord.listener.message.ReactionRemoveListener;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 25/08/2017.
 */
@Component
public class EventReactionListener implements ReactionAddListener, ReactionRemoveListener {


    @Override
    public void onReactionAdd(DiscordAPI discordAPI, Reaction reaction, User user) {
        Channel channel = reaction.getMessage().getChannelReceiver();
        Message message = reaction.getMessage();
        EventEntities event = EventManager.getInstance().getEvent(message.getId());
        //if equals event-pve,event-pvp,event-farm
        if(!user.isBot()) {
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.PARTICIPATE).getUnicode())) {
                event.getParticipation().addParticipate(user);
            }
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.DONOTPARTICIPATE).getUnicode())) {
                event.getParticipation().addDoNotParticipate(user);
            }
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.MAYBEPARTICIPATE).getUnicode())) {
                event.getParticipation().addMaybeParticipate(user);
            }
            message.edit(event.getParticipation().toString());
            for(Reaction react : message.getReactions()) {
                if( !react.equals(reaction)) {
                    react.removeUser(user);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @Override
    public void onReactionRemove(DiscordAPI discordAPI, Reaction reaction, User user) {
        Channel channel = reaction.getMessage().getChannelReceiver();
        Message message = reaction.getMessage();
        EventEntities event = EventManager.getInstance().getEvent(message.getId());
        //if equals event-pve,event-pvp,event-farm
        if(!user.isBot()) {
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.PARTICIPATE).getUnicode())) {
                event.getParticipation().removeParticipate(user);
            }
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.DONOTPARTICIPATE).getUnicode())) {
                event.getParticipation().removeDoNotParticipate(user);
            }
            if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(EventEntities.MAYBEPARTICIPATE).getUnicode())) {
                event.getParticipation().removeMaybeParticipate(user);
            }

            message.edit(event.getParticipation().toString());
        }

    }
}
