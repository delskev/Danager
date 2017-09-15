package be.perzival.danager.manager;

import be.perzival.danager.entities.EventEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public class EventManager {
    static final Logger LOG = LoggerFactory.getLogger(EventManager.class);

    public static EventManager INSTANCE = null;

    private Map<String, EventEntities> eventMap;

    private EventManager() {
        this.eventMap = new HashMap<>();
    }

    /**
     * get the singleton of StatisticManager
     * @return
     */
    public final static EventManager getInstance() {
        return INSTANCE == null ? (INSTANCE = new EventManager()): INSTANCE;
    }

    public void addEvent(String messageId, EventEntities eventEntities) {
        eventMap.put(messageId, eventEntities);
    }

    public EventEntities getEvent(String messageId) {
        return eventMap.get(messageId);
    }


}
