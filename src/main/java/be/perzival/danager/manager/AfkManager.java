package be.perzival.danager.manager;

import de.btobastian.javacord.entities.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public class AfkManager {

    public static AfkManager INSTANCE = null;

    private Map<User, String> afkUserMap;

    private AfkManager() {
        this.afkUserMap = new HashMap<>();
    }

    /**
     * get the singleton of StatisticManager
     * @return
     */
    public final static AfkManager getInstance() {
        return INSTANCE == null ? (INSTANCE = new AfkManager()): INSTANCE;
    }

    public String getAFKUserMessage(User user) {
        return afkUserMap.get(user);
    }

    public Boolean isAfk(User user) {
        return new Boolean(afkUserMap.get(user) != null);
    }

    public void addAfkUser(User user, String message) {
        afkUserMap.put(user, message);
    }

    public void removeAfkUser(User user) {
        afkUserMap.remove(user);
    }

    public Map<User, String> getAfkUserMap() {
        return afkUserMap;
    }
}
