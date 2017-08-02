package be.perzival.danager.command;

import be.perzival.danager.configuration.PropertiesManager;
import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.exceptions.command.CommandHandlerNotAttached;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Perzival on 30/07/2017.
 */
public abstract class  AbstractCommand implements CommandExecutor {

    protected CommandHandler commandHandler = null;
    protected DiscordAPI discordAPI = null;

    private Boolean enabled = true;

    /**
     * this method have to be override
     * the code inside this method is used to execute the command's code
     * @param args
     * @return
     * @throws CommandException
     */
    public abstract String executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException;

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
    public boolean isenabled() {
        return this.enabled;
    }

    public static Server getCorrectServer(DiscordAPI api, Message message) {
        //retrieve the serverId
        return message.getChannelReceiver().getServer();
    }

    /**
     * know if command's author is admin or not
     * @param api
     * @param message
     * @return
     */
    protected boolean isadmin(DiscordAPI api, Message message) {
        //get roles of the message's server
        Collection<Role> roles = message.getAuthor().getRoles(getCorrectServer(api, message));
        String[] adminRoles = PropertiesManager.getInstance().getServerConfig(getCorrectServer(api, message)).getAdmin();
        Iterator itr = roles.iterator();

        for(Role role: roles) {
            for(int i = 0; i < adminRoles.length; ++i) {
                if( adminRoles[i].toLowerCase().equals(role.getName().toLowerCase())) {
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * know if command's author is owner or not
     * @param api
     * @param message
     * @return
     * @throws ExecutionException cause of FutureCallback
     * @throws InterruptedException cause of FutureCallback
     */
    protected boolean isOwner(DiscordAPI api, Message message) throws ExecutionException, InterruptedException {
        Future<User> owner = getCorrectServer(api, message).getOwner();

        return owner.get().equals(message.getAuthor());
    }

    protected String correctAmountOfArgument(String[] args, int min, int max) {
        if (args.length > max) { // more than 1 argument
            return "To many arguments!";
        }
        if (args.length == 0 || args.length < min) { // more than 1 argument
            return "You need to provide more argument !";
        }
        return null;
    }
}
