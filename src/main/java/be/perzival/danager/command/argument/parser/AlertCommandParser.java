package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import be.perzival.danager.utils.ParserUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class AlertCommandParser implements Parser {


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length == 0) { // !afk
            return argument;
        }
        argument.addArgument(ArgumentType.REASON, ParserUtils.concatArgWithSpacer(args, " "));

        return argument;
    }
}
