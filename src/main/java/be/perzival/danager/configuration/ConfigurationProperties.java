package be.perzival.danager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
@PropertySource("config.properties")
public class ConfigurationProperties {

    @Value("${commands.prefix}")
    private String prefix;

    @Value("${roles.admin}")
    private String[] admin;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String[] getAdmin() {
        return admin;
    }

    public void setAdmin(String[] admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("```xml"); // a xml code block looks fancy
        builder.append("\n");
        builder.append("***************************************************************\n");
        builder.append("***************************************************************\n");
        builder.append("****                Application properties                 ****\n");
        builder.append("***                                                         ***\n");
        builder.append("**                                                           **\n");
        builder.append("*                                                             *\n");
        builder.append("***************************************************************\n");
        builder.append("prefix=" + prefix+ "\n");
        builder.append("admin=" + displayPropertyValues(admin));
        builder.append("\n```"); // end of xml code block

        return builder.toString();
    }


    private String displayPropertyValues(String[] values) {
        StringBuilder returnedValues = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            returnedValues.append(values[i]);
            if( i < values.length-1) {
                returnedValues.append(",");
            }
        }
        return returnedValues.toString();
    }

}
