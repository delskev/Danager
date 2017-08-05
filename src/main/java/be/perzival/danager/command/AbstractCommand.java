package be.perzival.danager.command;

import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.exceptions.command.CommandHandlerNotAttached;
import be.perzival.danager.manager.PropertiesManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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
     * @param message
     * @return
     */
    protected boolean isadmin(Message message) {
        //get roles of the message's server
        Server server = getServer(message);
        Collection<Role> roles = message.getAuthor().getRoles(server);
        String[] adminRoles = PropertiesManager.getInstance().getServerConfig(server).getAdmin();
        Future<User> futureOwner = getServer(message).getOwner();

        User owner = null;
        try {
            owner = futureOwner.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return owner.equals(message.getAuthor()) || Stream.of(adminRoles).anyMatch(adminrole -> roles.contains(adminrole));
    }

    protected String correctAmountOfArgument(String[] args, int min, int max) {
        if (args.length > max) { // more than max argument
            return "To many arguments!";
        }
        if (args.length == 0 || args.length < min) { // less than min argument
            return "You need to provide more argument !";
        }
        return null;
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
}
