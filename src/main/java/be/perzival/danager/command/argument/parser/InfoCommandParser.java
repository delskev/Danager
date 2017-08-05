package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.ExceptionsMessages;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class InfoCommandParser implements Parser {


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length > 1) { // more than 1 argument
            throw  new TooManyArgumentException(ExceptionsMessages.INFOTOOMANYARGUMENTS);
        }
        if (args.length == 0) { // !info
            return argument;
        }

        if ("time".toLowerCase().equals(args[0]) || "author".toLowerCase().equals(args[0])) {
            argument.addArgument(ArgumentType.MODE, args[0]);
        }else {
            argument.addArgument(ArgumentType.BAD, args[0]);
        }

        return argument;
    }
}
