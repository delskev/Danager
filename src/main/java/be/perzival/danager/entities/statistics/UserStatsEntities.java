package be.perzival.danager.entities.statistics;

import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.permissions.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public final class UserStatsEntities implements StatisticEntities {

    private List<User> playersConnected;
    private Integer maxPlayersConnected;
    private Map<User, String> playersBans;
    private Map<User, Integer> playersStrikes;
    private Map<User, List<Role>> playersRoles;
    private Map<User, Integer> messagesPerUser;

    public UserStatsEntities() {
        this.playersConnected = new ArrayList<>();
        this.maxPlayersConnected = 0;
        this.playersBans = new HashMap<>();
        this.playersStrikes = new HashMap<>();
        this.playersRoles = new HashMap<>();
        this.messagesPerUser = new HashMap<>();
    }

    public final List<User> getPlayersConnected() {
        return playersConnected;
    }

    public final Integer getMaxPlayersConnected() {
        return maxPlayersConnected;
    }

    public final Map<User, String>getPlayersBans() {
        return playersBans;
    }

    public final Map<User, Integer> getPlayersStrikes() {
        return playersStrikes;
    }

    public final Map<User, List<Role>> getPlayersRoles() {
        return playersRoles;
    }

    public final Map<User, Integer> getMessagesPeruser() {
        return messagesPerUser;
    }

    public final void addPlayersConnected(Server server, User user) {
        this.playersConnected.add(user);
        updatemaxPlayerConnected();
    }
    public final void removePlayersConnected(Server server, User user) {
        this.playersConnected.remove(user);
    }

    public final void updatemaxPlayerConnected() {
        if( this.maxPlayersConnected < playersConnected.size()) {
            this.maxPlayersConnected = playersConnected.size();
        }
    }

    public final void addPlayerBan(User user, String reason) {
        playersBans.put(user, reason);
    }

    public final void strikePlayer(User user) {
        incrementValueOrElseone(this.playersStrikes, user);
    }

    public final void incrementMessageByUser(User user) {
        incrementValueOrElseone(this.messagesPerUser, user);
    }

    private void incrementValueOrElseone(Map<User, Integer> map, User user) {
        if( map.containsKey(user)) {
            map.put(user, map.get(user) + 1 );
        }else {
            map.put(user, 1);
        }
    }
}
