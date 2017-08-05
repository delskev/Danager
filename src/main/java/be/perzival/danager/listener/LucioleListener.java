package be.perzival.danager.listener;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Perzival on 01/08/2017.
 */
@Component
public class LucioleListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if(!message.isPrivateMessage()) {
            ConfigurationProperties config = PropertiesManager.getInstance().getServerConfig(AbstractCommand.getServer(message));
            // check the content of the message
            if (!message.getAuthor().isBot() && config.getLuciole()) {
                String lucioleleries = message.getContent().toLowerCase();
                Pattern pattern = Pattern.compile("\\b([a-z]*di|dy)\\S*");
                Matcher matcher = pattern.matcher(lucioleleries);

                StringBuilder builder = new StringBuilder();
                while (matcher.find()) {
                    builder.append(matcher.group(0).substring(2) + " ");
                }

                message.reply(builder.toString());
            }
        }
    }
}
