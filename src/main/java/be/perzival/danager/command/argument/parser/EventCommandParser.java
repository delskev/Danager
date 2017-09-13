package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import be.perzival.danager.utils.ParserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class EventCommandParser implements Parser {
    static final Logger LOG = LoggerFactory.getLogger(EventCommandParser.class);


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length == 0) { // !afk
            return argument;
        }
        argument.addArgument(ArgumentType.MODE, args[0]);
        argument.addArgument(ArgumentType.DATE, args[1]);
        argument.addArgument(ArgumentType.TIME, args[2]);
        argument.addArgument(ArgumentType.DESTINATION, args[3]);

        argument.addArgument(ArgumentType.DESCRIPTION, ParserUtils.concatArgWithSpacer(4, args, " "));

        return argument;
    }
}
