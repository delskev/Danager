package be.perzival.danager.listener;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.VoiceChannel;
import de.btobastian.javacord.listener.voice.UserJoinVoiceChannelListener;
import de.btobastian.javacord.listener.voice.UserLeaveVoiceChannelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class DanagerVoiceChannelListener implements UserJoinVoiceChannelListener, UserLeaveVoiceChannelListener {
    static final Logger LOG = LoggerFactory.getLogger(DanagerVoiceChannelListener.class);

    private static final String CHANNELEXTENSION = "-voicechannel";
    Map<VoiceChannel, List<User>> voiceChannelUserMap;

    public DanagerVoiceChannelListener() {
        voiceChannelUserMap = new HashMap<>();
    }

    @Override
    public void onUserJoinVoiceChannel(DiscordAPI discordAPI, User user, VoiceChannel voiceChannel) {
        LOG.info("User "+user.getName()+" join voice channel: "+voiceChannel.getName());
        String chanName = voiceChannel.getName()+CHANNELEXTENSION;
        Server server = voiceChannel.getServer();

        List userList = voiceChannelUserMap.get(voiceChannel);
        //if no user into channel
        if (userList == null) {
            //Avoid duplicate
            if( !server.getChannels().stream().anyMatch(chan -> chan.getName().equals(chanName))) {
                server.createChannel(chanName);
            }
            userList = new ArrayList<>();
        }
        //increment User in Channel
        userList.add(user);
        //send connection message
        Optional<Channel> channel = server.getChannels()
                                          .stream()
                                          .filter(chan -> chan.getName().equals(chanName))
                                          .findFirst();
        channel.get().sendMessage(user.getMentionTag() + " nous a rejoints");
        voiceChannelUserMap.put(voiceChannel, userList);
    }

    @Override
    public void onUserLeaveVoiceChannel(DiscordAPI discordAPI, User user) {
        final VoiceChannel voiceChannel = retrieveVoiceChannel(user).get();
        LOG.info("User "+user.getName()+" leave voice channel: "+voiceChannel.getName());
        //decrement User in Channel
        List userList = voiceChannelUserMap.get(voiceChannel);
        userList.remove(user);
        voiceChannelUserMap.put(voiceChannel, userList);
        if (voiceChannelUserMap.get(voiceChannel).isEmpty()) {
            //remove empty channel
            discordAPI.getChannels()
                    .stream()
                    .filter(chan -> chan.getName().equals(getChannelName(voiceChannel)))
                    .findFirst()
                    .get()
                    .delete();
            LOG.info("Delete voice channel: %", voiceChannel.getName());
            voiceChannelUserMap.remove(voiceChannel);
        }
    }

    private String getChannelName(VoiceChannel voiceChannel) {
        return voiceChannel.getName().toLowerCase()+CHANNELEXTENSION;
    }

    private Optional<VoiceChannel> retrieveVoiceChannel(User user) {
        Optional<VoiceChannel> voiceChannel = Optional.empty();
        //retieve voicechannel
        for(Map.Entry<VoiceChannel, List<User>> voicechan: voiceChannelUserMap.entrySet()) {
            //found VoiceChannel
            if( ((List)voicechan.getValue()).contains(user)) {
                voiceChannel = Optional.of(voicechan.getKey());
            }
        }

        return voiceChannel;
    }
}
