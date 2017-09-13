package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.command.argument.ArgumentImpl;
import be.perzival.danager.command.argument.ArgumentType;
import be.perzival.danager.exceptions.command.TooManyArgumentException;
import be.perzival.dev.entities.market.ItemsNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 04/08/2017.
 */
@Component
public class AlbionMarketCommandParser implements Parser {
    static final Logger LOG = LoggerFactory.getLogger(AlbionMarketCommandParser.class);


    @Override
    public Argument parse(String[] args) throws TooManyArgumentException {
        Argument argument = new ArgumentImpl();

        if (args.length == 0) { // !afk
            return argument;
        }

        for(ItemsNames itemName : ItemsNames.values()) {
            //if fr
            if(args[0].equalsIgnoreCase(itemName.getFRValue()) || args[0].equalsIgnoreCase(itemName.getENValue())) {
                argument.addArgument(ArgumentType.NAME, itemName.getENValue());
            }
        }
        String level = "";
        if( args[1].contains("T") || args[1].contains("t") ) {
            int tierAndLevel = args[1].indexOf(".");
            if( tierAndLevel != -1) {
                argument.addArgument(ArgumentType.LEVEL, args[1].substring(tierAndLevel+1));
            }
            argument.addArgument(ArgumentType.TIER, "T"+args[1].substring(1, tierAndLevel));

        }else {
            int tierAndLevel = args[1].indexOf(".");
            if( tierAndLevel != -1) {
                argument.addArgument(ArgumentType.LEVEL, args[1].substring(tierAndLevel+1));
            }
            argument.addArgument(ArgumentType.TIER, "T"+args[1].substring(0, tierAndLevel));

        }


        return argument;
    }
}
