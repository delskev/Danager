package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.dev.entities.market.Resource;
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
public class AlbionMarket extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(AlbionMarket.class);
    @Autowired
    private Parser albionMarketCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"aomarket"}, description = "Display informations about a item from the market", usage = "aomarket [item] [tier]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = albionMarketCommandParser.parse(args);
        LOG.info("Asking market for informations about "+ argument.getArgument(ArgumentType.NAME).get()+"["+argument.getArgument(ArgumentType.TIER).get()+"]");
        AlbionServiceImpl albionService = new AlbionServiceImpl();

        String itemName = "";
        if( !argument.getArgument(ArgumentType.LEVEL).isPresent()) {
            itemName = argument.getArgument(ArgumentType.TIER).get() + "_" +
                       argument.getArgument(ArgumentType.NAME).get();
        }else {
            itemName = argument.getArgument(ArgumentType.TIER).get() + "_" +
                       argument.getArgument(ArgumentType.NAME).get() + "_LEVEL"+
                       argument.getArgument(ArgumentType.LEVEL).get();
        }

        Optional<Resource> resource = albionService.getMarketInfo(itemName);
        LOG.info("data present: "+ resource.get() + " | "+ resource.get().getItem().getId());
        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), resource);
        message.getChannelReceiver().sendMessage(null, embed);

    }
}
