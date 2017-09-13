package be.perzival.danager.listener;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.InviteBuilder;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Perzival on 01/08/2017.
 */
@Component
public class HolyGrailListener implements MessageCreateListener {
    static final Logger LOG = LoggerFactory.getLogger(HolyGrailListener.class);
    private static final String COMMAND = "holygrail";

    Optional<User> currentUser = null;
    int questionNumber = 1;

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(!message.isPrivateMessage()) {
            if (!message.getAuthor().isBot() ) {
                ConfigurationProperties config = PropertiesManager.getInstance()
                                                                  .getServerConfig(AbstractCommand.getServer(message));
                Channel channel = message.getChannelReceiver();
                User user = message.getAuthor();
                User danagerbot = null;
                for(User serverUser: AbstractCommand.getServer(message).getMembers()) {
                    if( serverUser.isBot() && serverUser.getName().equals("Danager")) {
                        danagerbot = serverUser;
                        break;
                    }
                }

                String content = message.getContent();
                //content is !holygrail = start of question
                if(content.startsWith(config.getPrefix()) && content.regionMatches(1, COMMAND, 0, COMMAND.length())) {
                    currentUser = Optional.of(user);
                    channel.sendMessage(user.getMentionTag() + " What is your name ?");
                }
                //first response with mention
                List<User> usersmention = message.getMentions();
                if( usersmention.contains(danagerbot) && (currentUser.isPresent() && currentUser.get().equals(user)) && questionNumber == 1) {
                    if( content.toLowerCase().contains(user.getName().toLowerCase())) {
                        //send second question
                        channel.sendMessage(user.getMentionTag() + " What is your favorite color ?");
                        questionNumber = 2;
                        return;
                    }
                }
                //second response with mention
                if( usersmention.contains(danagerbot) && (currentUser.isPresent() && currentUser.get().equals(user)) && questionNumber == 2) {
                    Pattern pattern = Pattern.compile("\\b(blue|red|green|yellow|cyan|pink|black|white|magenta|orange|gray|purple)\\S*");
                    Matcher matcher = pattern.matcher(content.toLowerCase());

                    StringBuilder builder = new StringBuilder();
                    while (matcher.find()) {
                        channel.sendMessage(user.getMentionTag() + " What is your quest ?");
                        questionNumber = 3;
                        return;
                    }
                }
                if(usersmention.contains(danagerbot) && (currentUser.isPresent() && currentUser.get().equals(user)) && questionNumber == 3) {
                    if( content.toLowerCase().trim().contains("toseektheholygrail")) {
                        channel.sendMessage(user.getMentionTag() + " Right ! Off you go oh...");
                        currentUser = Optional.empty();
                        questionNumber = 1;
                        return;
                    }

                }
            }
        }
    }

    private void kickUser(DiscordAPI api, Server server, User user, Message message) {
        InviteBuilder inviteBuilder = message.getChannelReceiver().getInviteBuilder();
        server.kickUser(user);
        inviteBuilder.create();
    }
}
