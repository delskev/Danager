package be.perzival.dev.danager.commands.admin;

import de.btobastian.sdcf4j.CommandExecutor;

public interface DanagerCommand extends CommandExecutor {
    default boolean isEnabled() {
        return true;
    }

    void enable();

    void disable();
}
