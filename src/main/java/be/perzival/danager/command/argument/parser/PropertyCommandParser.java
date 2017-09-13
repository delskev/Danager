package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class PropertyCommandParser implements Parser {
    static final Logger LOG = LoggerFactory.getLogger(PropertyCommandParser.class);

    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length > 3) { // more than 1 argument
            throw  new TooManyArgumentException(ExceptionsMessages.TOOMANYARGUMENTS);
        }

        if (args.length == 0) { // !property
            return argument;
        }

        if ("get".toLowerCase().equals(args[0]) ) {//property get prop
            argument.addArgument(ArgumentType.MODE, args[0]);
            argument.addArgument(ArgumentType.PROPERTY, args[1]);
        }else if( "set".toLowerCase().equals(args[0]) &&  args.length == 3) {//property set prop value
            argument.addArgument(ArgumentType.MODE, args[0]);
            argument.addArgument(ArgumentType.PROPERTY, args[1]);
            argument.addArgument(ArgumentType.VALUE, args[2]);
        }

        return argument;
    }
}
