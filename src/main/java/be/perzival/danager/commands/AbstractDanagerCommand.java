package be.perzival.danager.commands;

import be.perzival.danager.handler.DanagerCommandHandler;
import de.btobastian.sdcf4j.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;
import java.util.Optional;


public abstract class AbstractDanagerCommand implements DanagerCommand {
    private boolean enable;

    @Autowired
    @Lazy
    protected DanagerCommandHandler commandHandler;

    public AbstractDanagerCommand() {
        this.enable = Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void enable() {
        this.enable = true;
    }

    @Override
    public void disable() {
        this.enable = false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected Optional<CommandHandler.SimpleCommand> getCommandByAlias(String alias) {
        return commandHandler.getCommands()
                .stream()
                .filter(command -> Arrays.asList(command.getCommandAnnotation().aliases()).contains(alias))
                .findFirst();
    }
}
