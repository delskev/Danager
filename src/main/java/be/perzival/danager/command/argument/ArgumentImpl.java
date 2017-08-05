package be.perzival.danager.command.argument;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Perzival on 04/08/2017.
 */
public class ArgumentImpl implements Argument {
    private Map<ArgumentType, String> argumentMap;

    public ArgumentImpl() {
        argumentMap = new HashMap<>();
    }


    public String getArgument(ArgumentType argument) {
        return argumentMap.get(argument) == null ? "": argumentMap.get(argument);
    }

    public void addArgument(ArgumentType argument, String value) {
        argumentMap.put(argument, value);
    }

    @Override
    public boolean hasArgument() {
        return !argumentMap.isEmpty();
    }
}
