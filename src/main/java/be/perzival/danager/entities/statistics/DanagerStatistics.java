package be.perzival.danager.entities.statistics;

/**
 * Created by Perzival on 11/08/2017.
 */
public class DanagerStatistics {

    private ChannelsStatsEntities channelsStatsEntities;
    private GamesStatsEntities gamesStatsEntities;
    private MessagesStatsEntities messagesStatsEntities;
    private ServerStatsEntities serverStatsEntities;
    private UserStatsEntities userStatsEntities;

    public DanagerStatistics() {
        this.userStatsEntities = new UserStatsEntities();
        this.serverStatsEntities = new ServerStatsEntities();
        this.messagesStatsEntities = new MessagesStatsEntities();
        this.gamesStatsEntities = new GamesStatsEntities();
        this.channelsStatsEntities = new ChannelsStatsEntities();
    }

    public ChannelsStatsEntities getChannelsStatsEntities() {
        return channelsStatsEntities;
    }

    public GamesStatsEntities getGamesStatsEntities() {
        return gamesStatsEntities;
    }

    public MessagesStatsEntities getMessagesStatsEntities() {
        return messagesStatsEntities;
    }

    public ServerStatsEntities getServerStatsEntities() {
        return serverStatsEntities;
    }

    public UserStatsEntities getUserStatsEntities() {
        return userStatsEntities;
    }
}
