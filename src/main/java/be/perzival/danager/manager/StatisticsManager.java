package be.perzival.danager.manager;

import be.perzival.danager.entities.statistics.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public class StatisticsManager {

    public static StatisticsManager INSTANCE = null;

    private Map<String, StatisticEntities> statisticsMap;

    private StatisticsManager() {
        this.statisticsMap = new HashMap<>();

        this.statisticsMap.put(UserStatsEntities.class.getName(), new UserStatsEntities());
        this.statisticsMap.put(ServerStatsEntities.class.getName(), new ServerStatsEntities());
        this.statisticsMap.put(MessagesStatsEntities.class.getName(), new MessagesStatsEntities());
        this.statisticsMap.put(GamesStatsEntities.class.getName(), new GamesStatsEntities());
        this.statisticsMap.put(ChannelsStatsEntities.class.getName(), new ChannelsStatsEntities());
    }

    /**
     * get the singleton of StatisticManager
     * @return
     */
    public final static StatisticsManager getInstance() {
        return INSTANCE == null ? (INSTANCE = new StatisticsManager()): INSTANCE;
    }

    public <T extends StatisticEntities> T getStatistics(Class<T> statisticsEntities) {
        return (T) statisticsMap.get(statisticsEntities.getName());
    }

    public Map<String, StatisticEntities> getStatisticsMap() {
        return statisticsMap;
    }
}
