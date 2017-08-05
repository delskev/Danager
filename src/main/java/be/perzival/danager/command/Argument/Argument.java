package be.perzival.danager.command.argument;

import java.util.Optional;

/**
 * Created by Perzival on 04/08/2017.
 */
public interface Argument {

    Optional<String> getArgument(ArgumentType argument);
    void addArgument(ArgumentType argument, String value);
    boolean hasArgument();

}
