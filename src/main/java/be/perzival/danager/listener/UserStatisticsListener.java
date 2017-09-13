package be.perzival.danager.listener;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 06/08/2017.
 */
@Component
public class UserStatisticsListener implements MessageCreateListener, ServerMemberAddListener {
    static final Logger LOG = LoggerFactory.getLogger(UserStatisticsListener.class);

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
//        UserStatsEntities usersStats = StatisticsManager.getInstance()
//                                                        .getStatisticsMap(message.getChannelReceiver()
//                                                                                 .getServer())
//                                                                                 .getUserStatsEntities();
//        usersStats.incrementMessageByUser(message.getAuthor());
    }

    @Override
    public void onServerMemberAdd(DiscordAPI discordAPI, User user, Server server) {
//        UserStatsEntities usersStats = StatisticsManager.getInstance().getStatisticsMap(server).getUserStatsEntities();
//
//        usersStats.addPlayersConnected(server, user);
    }
}
