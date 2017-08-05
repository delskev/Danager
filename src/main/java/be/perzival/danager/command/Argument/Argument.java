package be.perzival.danager.command.argument;

/**
 * Created by Perzival on 04/08/2017.
 */
public interface Argument {

    String getArgument(ArgumentType argument);
    void addArgument(ArgumentType argument, String value);
    boolean hasArgument();

}
