package be.perzival.danager.command.argument.parser;

import be.perzival.danager.command.argument.Argument;
import be.perzival.danager.exceptions.command.TooManyArgumentException;

/**
 * Created by Perzival on 04/08/2017.
 */
public interface Parser {

    Argument parse(String[] args) throws TooManyArgumentException;
}
