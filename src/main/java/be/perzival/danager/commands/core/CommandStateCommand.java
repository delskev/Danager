package be.perzival.danager.commands.core;

import be.perzival.danager.commands.AbstractDanagerCommand;
import be.perzival.danager.commands.DanagerCommand;
import be.perzival.danager.utils.ResponseFactory;
import de.btobastian.sdcf4j.Command;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;

public class CommandStateCommand extends AbstractDanagerCommand {

    private enum CommandState {
        ENABLED,
        DISBLED
    }


    @Command(aliases = {"disable"}, description = "disable a command", usage = "disable [command]")
    public void onEnableCommand(String[] parameters, TextChannel textChannel) {
        if( parameters.length == 0 || parameters.length > 1 ) return ;

        updateState(textChannel, parameters[0], CommandState.DISBLED);

    }

    @Command(aliases = {"enable"}, description = "enable a command", usage = "enable [command]")
    public void onEnableCommand(String[] parameters, DiscordApi discordApi, TextChannel textChannel) {
        if( parameters.length == 0 || parameters.length > 1 ) return ;

        updateState(textChannel, parameters[0], CommandState.ENABLED);
    }

    private final  void updateState(TextChannel textChannel, String commandToDisable, final CommandState state) {
        this.getCommandByAlias(commandToDisable).ifPresentOrElse(c -> {
            ((DanagerCommand)c.getExecutor()).setEnable(CommandState.ENABLED.equals(state));
            ResponseFactory
                    .getEmbedResponse(ResponseFactory.ResponseType.SUCCESS, "Successfully disable "+ commandToDisable + " command")
                    .send(textChannel);
        }, () -> ResponseFactory
                .getEmbedResponse(ResponseFactory.ResponseType.ALERT, "Command:" + commandToDisable + ", not found")
                .send(textChannel));
    }
}
