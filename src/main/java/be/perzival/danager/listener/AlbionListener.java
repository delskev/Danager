package be.perzival.danager.listener;

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
public class AlbionListener implements ReactionAddListener, ReactionRemoveListener {


    public static final String THUMBSDOWN = ":thumbsdown:";
    public static final String THUMBSUP = ":thumbsup:";
    public static final String RAISED_HAND = ":raised_hand:";

    @Override
    public void onReactionAdd(DiscordAPI discordAPI, Reaction reaction, User user) {
        Channel channel = reaction.getMessage().getChannelReceiver();
        Message message = reaction.getMessage();

        //if equals event-pve,event-pvp,event-farm
        if(!user.isBot()) {
            if (channel.getName().contains("event-")) {
                //if the paricipate message doesn't exist
                if (message.getEmbeds().size() == 1 && message.getContent().isEmpty()) {
                    reaction.getMessage().edit(createPlayerList(reaction, user));
                }else {
                    String content = addPlayer(message.getContent(), reaction, user);
                    message.edit(content);
                }
            }
        }
    }

    @Override
    public void onReactionRemove(DiscordAPI discordAPI, Reaction reaction, User user) {
        Channel channel = reaction.getMessage().getChannelReceiver();
        Message message = reaction.getMessage();
        //if equals event-pve,event-pvp,event-farm
        if(!user.isBot()) {
            if (channel.getName().contains("event-")) {
                String content = message.getContent();
                message.edit(removePlayer(content, reaction, user));
            }
        }
    }

    public String createPlayerList(Reaction reaction, User user) {
        String participateMessage = "";
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSUP).getUnicode())) {
            participateMessage = participateMessage.concat("Participe:\n-" + user.getMentionTag() + "\n");
            participateMessage = participateMessage.concat("Ne participe pas:\n");
            participateMessage = participateMessage.concat("Peut-être:\n");
        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSDOWN).getUnicode())) {
            participateMessage = participateMessage.concat("Participe:\n");
            participateMessage = participateMessage.concat("Ne participe pas:\n-" + user.getMentionTag() + "\n");
            participateMessage = participateMessage.concat("Peut-être:\n");

        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(RAISED_HAND).getUnicode())) {
            participateMessage = participateMessage.concat("Participe:\n");
            participateMessage = participateMessage.concat("Ne participe pas:\n");
            participateMessage = participateMessage.concat("Peut-être:\n-" + user.getMentionTag() + "\n");
        }
        participateMessage = participateMessage.concat("-_-_-_-_-_-_-_-_-");
        return participateMessage;
    }

    public String addPlayer(String content, Reaction reaction, User user) {
        String[] splittedContent = splitContent( content);

        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSUP).getUnicode())) {
            splittedContent[0] = splittedContent[0].concat("-" + user.getMentionTag() + "\n");
        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSDOWN).getUnicode())) {
            splittedContent[1] = splittedContent[1].concat("-" + user.getMentionTag() + "\n");
        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(RAISED_HAND).getUnicode())) {
            splittedContent[2] = splittedContent[2].concat("-" + user.getMentionTag() + "\n");
        }
        content = "";
        return content.concat(splittedContent[0]).concat(splittedContent[1]).concat(splittedContent[2]).concat("-_-_-_-_-_-_-_-_-");
    }

    public String removePlayer(String content, Reaction reaction, User user) {
        String[] splittedContent = splitContent( content);

        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSUP).getUnicode())) {
            splittedContent[0] = splittedContent[0].replaceFirst("-" + user.getMentionTag() + "\n","");
        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(THUMBSDOWN).getUnicode())) {
            splittedContent[1] = splittedContent[1].replaceFirst("-" + user.getMentionTag() + "\n","");
        }
        if (reaction.getUnicodeEmoji().equals(EmojiManager.getForAlias(RAISED_HAND).getUnicode())) {
            splittedContent[2] = splittedContent[2].replaceFirst("-" + user.getMentionTag() + "\n","");
        }
        content = "";
        return content.concat(splittedContent[0]).concat(splittedContent[1]).concat(splittedContent[2]).concat("-_-_-_-_-_-_-_-_-");
    }

    public String[] splitContent(String content) {
        String gocontent = content.substring(content.indexOf("Participe:"), content.indexOf("Ne participe pas:"));
        String nogoContent = content.substring(content.indexOf("Ne participe pas:"), content.indexOf("Peut-être:"));
        String dontknowContent = content.substring(content.indexOf("Peut-être:"), content.indexOf("-_-_-_-_-_-_-_-_-"));
        return new String[]{gocontent, nogoContent, dontknowContent};
    }
}
