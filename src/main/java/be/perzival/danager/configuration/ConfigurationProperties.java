package be.perzival.danager.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class ConfigurationProperties {
    static final Logger LOG = LoggerFactory.getLogger(ConfigurationProperties.class);

    private Properties applicationProperties;

    public ConfigurationProperties() {
        this.applicationProperties = null;
    }

    public ConfigurationProperties(Properties properties) {
        this.applicationProperties = properties;
    }

    public String getPrefix() {
        return applicationProperties.get(PropertiesEnum.PREFIX.value()).toString();
    }

    public void setPrefix(String prefix) {
        applicationProperties.setProperty(PropertiesEnum.PREFIX.value(), prefix);
    }

    public String[] getAdmin() {
        return applicationProperties.get(PropertiesEnum.ADMIN.value()).toString().split(",");
    }

    public void setAdmin(String[] admin) {
        String result = "";
        for(int i = 0; i < admin.length; ++i) {
            result = result.concat(admin[i]);
            if(!(i < admin.length-1)) {
                result = result.concat(",");
            }
        }
        applicationProperties.setProperty(PropertiesEnum.ADMIN.value(), result);
    }

    public Boolean getLuciole() {
        return applicationProperties.get(PropertiesEnum.LUCIOLE.value()).toString().equalsIgnoreCase("true");
    }

    public void setLuciole(Boolean luciole) {
        applicationProperties.setProperty(PropertiesEnum.LUCIOLE.value(), luciole.toString());
    }

    public Boolean getNewConnection() {
        return applicationProperties.get(PropertiesEnum.NEWCONNECTION.value()).toString().equalsIgnoreCase("true");
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
        applicationProperties.forEach((o, o2) -> builder.append(o + "=" + o2+ "\n"));
        builder.append("\n```"); // end of xml code block

        return builder.toString();
    }

    public final String getProperty(String key) {
        return key  + "=" + applicationProperties.getProperty(key);
    }

    public final String setProperty(String key, String value){
        LOG.debug("Setting property: "+ key );
        String property = applicationProperties.setProperty(key, value).toString();
        return property != null ? property: "Bad property:" + key;
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

    public final Properties getproperties() {
        return this.applicationProperties;
    }

}
