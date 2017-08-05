package be.perzival.danager.entities.statistics;

import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.permissions.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Perzival on 02/08/2017.
 */
public final class UserStatsEntities implements StatisticEntities {

    private Integer playersConnected;
    private Integer maxPlayersConnected;
    private Map<User, String> playersBans;
    private Map<User, Integer> playersStrikes;
    private Map<User, List<Role>> playersRoles;
    private Map<User, Integer> messagesPeruser;

    public UserStatsEntities() {
        this.playersConnected = 0;
        this.maxPlayersConnected = 0;
        this.playersBans = new HashMap();
        this.playersStrikes = new HashMap();
        this.playersRoles = new HashMap();
        this.messagesPeruser = new HashMap();
    }

    public final Integer getPlayersConnected() {
        return playersConnected;
    }

    public final Integer getMaxPlayersConnected() {
        return maxPlayersConnected;
    }

    public final Map<User, String> getPlayersBans() {
        return playersBans;
    }

    public final Map<User, Integer> getPlayersStrikes() {
        return playersStrikes;
    }

    public final Map<User, List<Role>> getPlayersRoles() {
        return playersRoles;
    }

    public final Map<User, Integer> getMessagesPeruser() {
        return messagesPeruser;
    }

    public final void incrementPlayersConnected() {
        this.playersConnected++;
        updatemaxPlayerConnected();
    }
    public final void decrementPlayersConnected() {
        this.playersConnected--;
    }

    public final void updatemaxPlayerConnected() {
        if( this.maxPlayersConnected < playersConnected) {
            this.maxPlayersConnected = playersConnected;
        }
    }

    public final void addPlayerBan(User user, String reason) {
        playersBans.put(user, reason);
    }

    public final void strikePlayer(User user) {
        Integer strikes = playersStrikes.get(user);
        playersStrikes.put(user, strikes == null ? 1: strikes+1);
    }
}
