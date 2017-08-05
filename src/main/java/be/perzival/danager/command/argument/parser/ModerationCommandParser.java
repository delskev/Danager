package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class ModerationCommandParser implements Parser {


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length == 0) { // !kick
            return argument;
        }
        //user arg
        argument.addArgument(ArgumentType.USER, args[0]);
        if(args.length >=2) {
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length; ++i) {
                reason.append(args[i]+ " ");
            }
            argument.addArgument(ArgumentType.REASON, reason.toString());
        }
        return argument;
    }
}
