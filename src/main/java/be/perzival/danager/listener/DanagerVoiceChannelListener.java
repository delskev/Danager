package be.perzival.danager.listener;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.VoiceChannel;
import de.btobastian.javacord.entities.permissions.PermissionState;
import de.btobastian.javacord.entities.permissions.PermissionType;
import de.btobastian.javacord.entities.permissions.PermissionsBuilder;
import de.btobastian.javacord.entities.permissions.impl.ImplPermissionsBuilder;
import de.btobastian.javacord.listener.server.ServerJoinListener;
import de.btobastian.javacord.listener.voice.UserJoinVoiceChannelListener;
import de.btobastian.javacord.listener.voice.UserLeaveVoiceChannelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Perzival on 04/08/2017.
 */

public class DanagerVoiceChannelListener implements ServerJoinListener, UserJoinVoiceChannelListener, UserLeaveVoiceChannelListener {
    static final Logger LOG = LoggerFactory.getLogger(DanagerVoiceChannelListener.class);

    private static final String CHANNELEXTENSION = "-voicechannel";
    Map<VoiceChannel, List<User>> voiceChannelUserMap;

    public DanagerVoiceChannelListener() {
        voiceChannelUserMap = new HashMap<>();
    }

    @Override
    public void onUserJoinVoiceChannel(DiscordAPI discordAPI, User user, VoiceChannel voiceChannel) {
        LOG.info("User "+user.getName()+" join voice channel: "+voiceChannel.getName());

        String channelNametoCreate = getChannelNametoCreate(voiceChannel);
        Server server = voiceChannel.getServer();

        removeFromchannel(user);
        removeUnusedChannel(discordAPI, server);
        //get user into the channel
        List userList = voiceChannelUserMap.get(voiceChannel);
        //if no user into channel
        if (userList == null) {
            voiceChannelUserMap.put(voiceChannel, (userList = new ArrayList<>()) );
        }
        updateChannel(server, voiceChannel, Arrays.asList(user));
        Optional<Channel> channel = getCreatedChannel(server, channelNametoCreate);
        channel.get().sendMessage(user.getMentionTag() + " join us");
    }

    @Override
    public void onUserLeaveVoiceChannel(DiscordAPI discordAPI, User user) {
        Optional<VoiceChannel> optional = retrieveVoiceChannel(user);

        if( !optional.isPresent()) return;

        VoiceChannel voiceChannel = optional.get();
        LOG.info("User "+user.getName()+" leave voice channel: "+voiceChannel.getName());
        //send connection message
        String channelNametoCreate = getChannelNametoCreate(voiceChannel);
        Optional<Channel> channel = getCreatedChannel(voiceChannel.getServer(), channelNametoCreate);
        channel.get().deleteOverwrittenPermissions(user);

        //decrement User in Channel
        List userList = voiceChannelUserMap.get(voiceChannel);
        userList.remove(user);
        voiceChannelUserMap.put(voiceChannel, userList);
        removeUnusedChannel(discordAPI, voiceChannel.getServer());
    }

    @Override
    public void onServerJoin(DiscordAPI discordAPI, Server server) {
        for (VoiceChannel channel : server.getVoiceChannels()) {
            List<User> users = new ArrayList<>();
            for(User user: channel.getConnectedUsers()) {
                users.add(user);
            }
            voiceChannelUserMap.put(channel, users);
            if( !users.isEmpty()) {
                updateChannel(server, channel, users);
            }

        }

    }

    private String getChannelNametoCreate(VoiceChannel voiceChannel) {
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

    private void removeFromchannel(User user) {
        for(List<User> userList : voiceChannelUserMap.values()) {
            if(userList != null) {
                for (User user1 : userList) {
                    if (user1.getId().equals(user.getId())) {
                        userList.remove(user1);
                        break;
                    }
                }
            }
        }
    }

    private Optional<Channel> getCreatedChannel(Server server, String channelName) {
        Optional<Channel> channel = server.getChannels()
                .stream()
                .filter(chan -> chan.getName().equals(channelName))
                .findFirst();
        return channel;
    }

    public void removeUnusedChannel(DiscordAPI api, Server server) {
        for (VoiceChannel channel : server.getVoiceChannels()) {
            if (voiceChannelUserMap.get(channel) != null && voiceChannelUserMap.get(channel).isEmpty()) {
                //remove empty channel
                try {
                    api.getChannels()
                            .stream()
                            .filter(chan -> chan.getName().equals(getChannelNametoCreate(channel)))
                            .findFirst()
                            .get()
                            .delete().get();
                    LOG.info("Delete voice channel: " + channel.getName() + " from server: " + channel.getServer().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void updateChannel(Server server, VoiceChannel voiceChannel, List<User> users) {
        String channelNametoCreate = getChannelNametoCreate(voiceChannel);
        List userList = voiceChannelUserMap.get(voiceChannel);
        //Avoid duplicate
        if( !server.getChannels().stream().anyMatch(chan -> chan.getName().equals(channelNametoCreate))) {
            try {
                server.createChannel(channelNametoCreate).get();
                PermissionsBuilder permissionsBuilder = new ImplPermissionsBuilder();
                permissionsBuilder.setState(PermissionType.READ_MESSAGES, PermissionState.DENIED);
                permissionsBuilder.setState(PermissionType.SEND_MESSAGES, PermissionState.DENIED);
                Optional<Channel> channel = getCreatedChannel(server, channelNametoCreate);
                channel.get().updateOverwrittenPermissions(server.getRoleById(server.getId()), permissionsBuilder.build());

            } catch (InterruptedException  | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //increment User in Channel
        for(User user: users) {
            userList.add(user);
            //send connection message
            Optional<Channel> channel = getCreatedChannel(server, channelNametoCreate);
            //Manage Permissions
            PermissionsBuilder permissionsBuilder = new ImplPermissionsBuilder();
            permissionsBuilder.setState(PermissionType.READ_MESSAGES, PermissionState.ALLOWED);
            permissionsBuilder.setState(PermissionType.SEND_MESSAGES, PermissionState.ALLOWED);
            channel.get().updateOverwrittenPermissions(user, permissionsBuilder.build());
        }
    }


}
