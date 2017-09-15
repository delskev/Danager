package be.perzival.danager.command;

import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.exceptions.command.CommandHandlerNotAttached;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.PermissionState;
import de.btobastian.javacord.entities.permissions.PermissionType;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Perzival on 30/07/2017.
 */
public abstract class  AbstractCommand implements CommandExecutor, DanagerCommand {
    protected CommandHandler commandHandler;
    protected DiscordAPI discordAPI;
    private Boolean enabled = Boolean.TRUE;

    /**
     * this method have to be override
     * the code inside this method is used to execute the command's code
     * @param args
     * @return
     * @throws CommandException
     */
    public abstract void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException, ExecutionException, InterruptedException;

    /**
     * use to attach a command handler to the command
     * @param commandHandler
     */
    public void attachCommandHandler(CommandHandler commandHandler) {
        if(this.commandHandler == null) {
            this.commandHandler = commandHandler;
        }
    }

    /**
     * use to attach a DiscordAPI to the command
     * @param discordAPI
     */
    public void attachDiscordAPI(DiscordAPI discordAPI) {
        if(this.discordAPI == null) {
            this.discordAPI = discordAPI;
        }
    }

    /**
     * know if a command handler is attached
     * @return
     * @throws CommandException
     */
    public boolean isCommandHandlerAttached() throws CommandException {
        if( commandHandler == null) {
            throw new CommandHandlerNotAttached(ExceptionsMessages.COMMANDHANDLERNOTATTACHED);
        }
        return true;
    }

    /**
     * know if a command handler is attached
     * @return
     * @throws CommandException
     */
    public boolean isDiscordAPIAttached() throws CommandException {
        if( commandHandler == null) {
            throw new CommandHandlerNotAttached(ExceptionsMessages.COMMANDHANDLERNOTATTACHED);
        }
        return true;
    }

    /**
     * know if a command is enabled or not
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    /**
     * set if a command is enabled or not
     * @return
     */
    @Override
    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    public static Server getServer(Message message) {
        //retrieve the serverId
        return message.getChannelReceiver().getServer();
    }

    /**
     * know if command's author is admin or not
     * @param user
     * @param server
     * @return
     */
    public static boolean isadmin(User user, Server server) {
        //get roles of the message's server
        Collection<Role> roles = user.getRoles(server);
        Future<User> futureOwner = server.getOwner();

        User owner = null;
        try {
            owner = futureOwner.get();
        } catch (Exception osef) {}
        for(Role role: roles) {
            if( role.getPermissions().getState(PermissionType.ADMINISTRATOR).equals(PermissionState.ALLOWED) ||
                owner.equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find a user no matter how you write it : @user | User | USER | UsEr
     * @param users the collection of user where to find the user
     * @param userToFind the nickname of the user to find
     * @return
     */
    protected User findUser(Collection<User> users, String userToFind) {
        //case of a mention
        User userfinded = null;
        if(userToFind.contains("@")) {
            userToFind = userToFind.substring(2, userToFind.length() -1);
            for (User user : users) {
                if(user.getId().equals(userToFind)) {
                    userfinded = user;
                }
            }
        }else {//case of name
            for (User user : users) {
                if(user.getName().toLowerCase().equals(userToFind.toLowerCase())) {
                    userfinded = user;
                }
            }
        }
        return userfinded;
    }

    protected Optional<Channel> findChannelByName(Server server, String channelName) {
        return server.getChannels()
                     .stream()
                     .filter(channel -> channel.getName().equalsIgnoreCase(channelName)).findFirst();
    }
}
