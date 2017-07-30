package be.perzival.danager.command;

import be.perzival.danager.configuration.ConfigurationProperties;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Property implements CommandExecutor {

    @Autowired
    private ConfigurationProperties configurationProperties;

    @Command(aliases = {"property" }, description = "Shows bot's configuration", usage = "property")
    public String onPropertyCommand() throws IllegalAccessException {
        Class aClass = ConfigurationProperties.class;
        Field[] fields = aClass.getDeclaredFields();

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < fields.length; ++i) {
            fields[i].setAccessible(true);
            builder.append(fields[i].getName() + ": "+ fields[i].get(configurationProperties));
            fields[i].setAccessible(false);
        }

        return builder.toString(); // dummy return type
    }
}
