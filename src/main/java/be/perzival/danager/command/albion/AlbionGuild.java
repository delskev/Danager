package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.dev.entities.Guild;
import be.perzival.dev.service.AlbionServiceImpl;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class AlbionGuild extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(AlbionGuild.class);
    @Autowired
    private Parser albionGuildCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"aoguild"}, description = "Display informations about a guild", usage = "aoguild [guild name]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = albionGuildCommandParser.parse(args);

        AlbionServiceImpl albionService = new AlbionServiceImpl();

        Optional<Guild> guild = albionService.getGuild(argument.getArgument(ArgumentType.NAME).get());
        if( guild.isPresent()) {
            Guild gl = guild.get();
            StringBuilder builder = new StringBuilder();
            builder.append("Nom: "+ gl.getName() + "\n");
            builder.append("Alliance: "+ (gl.getAllianceName() == null ? "/": gl.getAllianceName()) + "\n");
            if( gl.getKillFame() != null ) {
                builder.append("Renommé en tuant: " + gl.getKillFame() + "\n");
            }
            if( gl.getDeathFame() != null ) {
                builder.append("Renommé en mourrant: " + gl.getDeathFame() + "\n");
            }
            EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
            message.getChannelReceiver().sendMessage(null, embed);
        }else {
            StringBuilder builder = new StringBuilder();
            builder.append("Guilde "+ argument.getArgument(ArgumentType.NAME).get() + " introuvable !");
            EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
            message.getChannelReceiver().sendMessage(null, embed);
        }

    }
}
