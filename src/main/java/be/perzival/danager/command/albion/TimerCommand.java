package be.perzival.danager.command.albion;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.command.argument.parser.Parser;
import be.perzival.danager.exceptions.command.CommandException;
import be.perzival.danager.utils.Timer;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class TimerCommand extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(TimerCommand.class);
    @Autowired
    private Parser timerCommandParser;
    private Map<String, Timer> timerMap = new HashMap<>();
    int serial = 0;

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"timer", "t"}, description = "start a timer then send a ping", usage = "Timer [time] [description]")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Argument argument = timerCommandParser.parse(args);
        Timer timer = new Timer(Integer.parseInt(argument.getArgument(ArgumentType.TIME).get()),
                                argument.getArgument(ArgumentType.DESCRIPTION).orElse(""));

        Optional<Channel> channelReceiver = findChannelByName(message.getChannelReceiver().getServer(), "timer") ;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    timerMap.put("Timer-"+serial, timer);
                    channelReceiver.get().sendMessage("Timer-"+serial+" started: "+ timer.getTime());
                    ++serial;
                    Thread.sleep(timer.getTime()*60000);
                    channelReceiver.get().sendMessage("Timer-"+serial+" finished");
                    timerMap.remove("Timer-"+(serial -1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        run.run();

    }
}
