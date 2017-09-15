package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.entities.EventEntities;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.manager.EventManager;
import com.vdurmont.emoji.EmojiManager;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Event extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(Event.class);
    private static final String EVENTPVE = "event-pve";
    private static final String EVENTPVP = "event-pvp";
    private static final String EVENTFARM = "event-farm";

    @Autowired
    private Parser eventCommandParser;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"event"}, description = "create a new event", usage = "event [pve|pvp|farm] [date] [start time] [BZ|YZ|RZ|BZ] [description]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = eventCommandParser.parse(args);
        EventEntities eventEntities = new EventEntities(argument.getArgument(ArgumentType.MODE).get().toUpperCase(),
                                                        argument.getArgument(ArgumentType.DATE).get() + "-" + argument.getArgument(ArgumentType.TIME).get(),
                                                        argument.getArgument(ArgumentType.DESTINATION).get(),
                                                        argument.getArgument(ArgumentType.DESCRIPTION).get());

        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), eventEntities.toString());

        Optional<Channel> channelReceiver = Optional.empty();
        switch(eventEntities.getType()) {
            case PVP:
                channelReceiver = findChannelByName(message.getChannelReceiver().getServer(), EVENTPVP) ;
                break;
            case PVE:
                channelReceiver = findChannelByName(message.getChannelReceiver().getServer(), EVENTPVE) ;
                break;
            case FARM:
                channelReceiver = findChannelByName(message.getChannelReceiver().getServer(), EVENTFARM) ;
                break;
        }

        message.delete();
        if( channelReceiver.isPresent()) {

            Future<Message> fmsg = channelReceiver.get().sendMessage(null, embed);
            try {
                Message msg = fmsg.get();
                EventManager.getInstance().addEvent(msg.getId(), eventEntities);
                msg.addUnicodeReaction(EmojiManager.getForAlias(EventEntities.PARTICIPATE).getUnicode()).get();
                Thread.sleep(1000);
                msg.addUnicodeReaction(EmojiManager.getForAlias(EventEntities.DONOTPARTICIPATE).getUnicode()).get();
                Thread.sleep(1000);
                msg.addUnicodeReaction(EmojiManager.getForAlias(EventEntities.MAYBEPARTICIPATE).getUnicode()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

    }
}
