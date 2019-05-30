package be.perzival.danager.commands;

import de.btobastian.sdcf4j.CommandExecutor;

public interface DanagerCommand extends CommandExecutor {

    boolean isEnabled();
    void enable();
    void disable();
}
