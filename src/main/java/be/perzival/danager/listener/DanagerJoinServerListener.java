package be.perzival.danager.listener;

import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.server.ServerJoinListener;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by Perzival on 31/07/2017.
 */
public class DanagerJoinServerListener implements ServerJoinListener, ServerMemberAddListener {
    static final Logger LOG = LoggerFactory.getLogger(DanagerJoinServerListener.class);

    @Override
    public void onServerJoin(DiscordAPI discordAPI, Server server) {
        PropertiesManager.getInstance().createDefaultConfig(server);
        server.getChannelById(server.getId()).sendMessage("Bonjour je suis Danager !");
    }

    @Override
    public void onServerMemberAdd(DiscordAPI discordAPI, User user, Server server) {
        if( PropertiesManager.getInstance().getServerConfig(server).getNewConnection()) {
            for (User member : server.getMembers()) {
                Collection<Role> roles = member.getRoles(server);
                String[] adminRoles = PropertiesManager.getInstance().getServerConfig(server).getAdmin();
                //if user is admin
                if (Stream.of(adminRoles).anyMatch(adminrole -> roles.contains(adminrole))) {
                    member.sendMessage("User " + user.getName() + " has join your server.");
                }
            }
        }
    }


}
