package be.perzival.danager.command.argument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Perzival on 04/08/2017.
 */
public class ArgumentImpl implements Argument {
    private Map<ArgumentType, String> argumentMap;

    public ArgumentImpl() {
        argumentMap = new HashMap<>();
    }


    public Optional<String> getArgument(ArgumentType argument) {
        return Optional.ofNullable(argumentMap.get(argument));
    }

    public void addArgument(ArgumentType argument, String value) {
        argumentMap.put(argument, value);
    }

    @Override
    public boolean hasArgument() {
        return !argumentMap.isEmpty();
    }
}
