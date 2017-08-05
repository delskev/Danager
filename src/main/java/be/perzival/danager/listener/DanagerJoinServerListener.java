package be.perzival.danager.listener;

import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.listener.server.ServerJoinListener;

import java.io.IOException;

/**
 * Created by Perzival on 31/07/2017.
 */
public class DanagerJoinServerListener implements ServerJoinListener {
    @Override
    public void onServerJoin(DiscordAPI discordAPI, Server server) {
        try {
            PropertiesManager.getInstance().createDefaultConfig(server);
            server.getChannelById(server.getId()).sendMessage("Bonjour je suis Danager !");

            server.getChannels().forEach(chan -> { if("mychannel".equals(chan.getName()))server.getChannelById(chan.getId()).sendMessage("mymessage");});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
