package be.perzival.danager.commands;

import de.btobastian.sdcf4j.CommandExecutor;

public interface DanagerCommand extends CommandExecutor {
    default boolean isEnabled() {
        return true;
    }

    void enable();

    void disable();
}
