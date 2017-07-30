package be.perzival.danager.command;

import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.sdcf4j.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Property extends AbstractCommand {

    @Autowired
    private ConfigurationProperties configurationProperties;

    /**
     * Display all the property of the bot configuration's file
     * @param args
     * @return
     * @throws CommandException throw cause of introspection
     */
    @Override
    @Command(aliases = {"property" }, description = "Shows bot's configuration", usage = "property")
    public String executeCommand(String[] args) throws CommandException {
        try {
            Class aClass = ConfigurationProperties.class;
            Field[] fields = aClass.getDeclaredFields();

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < fields.length; ++i) {
                fields[i].setAccessible(true);
                builder.append(fields[i].getName() + ": "+ fields[i].get(configurationProperties));
                fields[i].setAccessible(false);
            }
            return builder.toString(); // dummy return type
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "There's an error with the command !";
    }
}
