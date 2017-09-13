package be.perzival.danager.manager;

import be.perzival.danager.entities.statistics.*;
import de.btobastian.javacord.entities.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public class StatisticsManager {
    static final Logger LOG = LoggerFactory.getLogger(StatisticsManager.class);

    public static StatisticsManager INSTANCE = null;

    private Map<Server, DanagerStatistics> danagerStatisticsMap;

    private StatisticsManager() {
        this.danagerStatisticsMap = new HashMap<>();
    }

    /**
     * get the singleton of StatisticManager
     * @return
     */
    public final static StatisticsManager getInstance() {
        return INSTANCE == null ? (INSTANCE = new StatisticsManager()): INSTANCE;
    }

    public DanagerStatistics getStatisticsMap(Server server) {
        if( !danagerStatisticsMap.containsKey(server)) {
            danagerStatisticsMap.put(server, new DanagerStatistics());
        }

        return danagerStatisticsMap.get(server);
    }
}
