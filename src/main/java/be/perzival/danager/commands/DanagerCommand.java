package be.perzival.danager.commands;

import de.btobastian.sdcf4j.CommandExecutor;

public interface DanagerCommand extends CommandExecutor {

    boolean isEnabled();
    void setEnable(boolean enable);
    void enable();
    void disable();
}
