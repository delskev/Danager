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
public class AlbionPlayerCommandParser implements Parser {
    static final Logger LOG = LoggerFactory.getLogger(AlbionPlayerCommandParser.class);


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length > 1) { // more than 1 argument
            throw  new TooManyArgumentException(ExceptionsMessages.TOOMANYARGUMENTS);
        }
        argument.addArgument(ArgumentType.NAME, args[0]);


        return argument;
    }
}
