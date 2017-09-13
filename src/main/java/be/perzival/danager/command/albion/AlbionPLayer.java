package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.dev.entities.Player;
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
public class AlbionPLayer extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(AlbionPLayer.class);
    @Autowired
    private Parser albionPlayerCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"aoplayer"}, description = "Display informations about a player", usage = "aoplayer [playerName]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = albionPlayerCommandParser.parse(args);

        AlbionServiceImpl albionService = new AlbionServiceImpl();

        Optional<Player> player = albionService.getPlayers(argument.getArgument(ArgumentType.NAME).get());

        if( player.isPresent()) {
            Player pl = player.get();
            StringBuilder builder = new StringBuilder();
            builder.append("Pseudo: "+ pl.getName() + "\n");
            builder.append("Guilde: "+ pl.getGuildName() + "\n");
            builder.append("Renommé en tuant: "+ pl.getKillFame() + "\n");
            builder.append("Renommé en mourrant: "+ pl.getDeathFame() + "\n");
            builder.append("Ratio K/D: "+ pl.getFameRatio() + "\n");
            builder.append("Kills: "+ (pl.getTotalKills() == null ? "0": pl.getTotalKills())+ "\n");
            builder.append("GvG kills: "+ (pl.getGvgKills() == null ? "0": pl.getGvgKills()) + "\n");
            builder.append("GvG gagnés: "+ (pl.getGvgWon() == null ? "0": pl.getGvgWon()) + "\n");
            EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
            message.getChannelReceiver().sendMessage(null, embed);
        }else {
            StringBuilder builder = new StringBuilder();
            builder.append("Joueur "+ argument.getArgument(ArgumentType.NAME).get() + " introuvable !");
            EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), builder.toString());
            message.getChannelReceiver().sendMessage(null, embed);
        }

    }
}
