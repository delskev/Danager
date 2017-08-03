package be.perzival.danager.manager;


import be.perzival.danager.entities.statistics.StatisticEntities;
import be.perzival.danager.entities.statistics.UserStatsEntities;
import de.btobastian.javacord.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Perzival on 02/08/2017.
 */
public class StatisticsManagerTest {
    Random random = new Random();

    @Test
    public void singletonTest() {
        StatisticsManager managerA = StatisticsManager.getInstance();
        StatisticsManager managerB = StatisticsManager.getInstance();

        Assert.assertEquals(managerA, managerB);
    }

    @Test
    public void StatisticsEntitiesTest() {
        StatisticsManager manager = StatisticsManager.getInstance();

        //to get all the class that implements StatisticsEntities
        Reflections reflections = new Reflections("be.perzival.danager.entities.statistics");
        Set<Class<? extends StatisticEntities>> allClasses =reflections.getSubTypesOf(StatisticEntities.class);

        Assert.assertEquals(allClasses.size(), manager.getStatisticsMap().size());
    }

    @Test
    public void UserStatsEntitiesTest() {
        StatisticsManager manager = StatisticsManager.getInstance();
        UserStatsEntities userStats = manager.getStatistics(UserStatsEntities.class);

        //Verify instance
        Assert.assertNotNull(userStats);

        Integer numberOfPlayer = random.nextInt(100); //generate number [0, 100[

        //test playerConnected and maxPlayer
        for(int i = 0; i < numberOfPlayer; ++i){
            userStats.incrementPlayersConnected();
        }
        Assert.assertEquals(numberOfPlayer, userStats.getPlayersConnected());
        for(int i = 0; i < numberOfPlayer/2; ++i){
            userStats.decrementPlayersConnected();
        }
        Assert.assertEquals(numberOfPlayer, userStats.getMaxPlayersConnected());

        //test userban
        User user = Mockito.mock(User.class);

        String reason = "you got ban";
        userStats.addPlayerBan(user, reason);
        Map<User, String> usersBans = userStats.getPlayersBans();

        Assert.assertNotNull(usersBans);
        Assert.assertNotNull(usersBans.get(user));
        Assert.assertEquals(usersBans.get(user), reason);

        //test userStrikes
        Integer numberOfStrikes = 3;
        for(int i = 0; i < numberOfStrikes; ++i){
            userStats.strikePlayer(user);
        }
        Map<User, Integer> userStrikes = userStats.getPlayersStrikes();

        Assert.assertNotNull(userStrikes);
        Assert.assertNotNull(userStrikes.get(user));
        Assert.assertEquals(numberOfStrikes, userStrikes.get(user) );

    }


}
