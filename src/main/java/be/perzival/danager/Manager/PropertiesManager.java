package be.perzival.danager.manager;

import be.perzival.danager.configuration.ConfigurationProperties;
import be.perzival.danager.configuration.PropertiesEnum;
import de.btobastian.javacord.entities.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Perzival on 02/08/2017.
 */
public class PropertiesManager {
    static final Logger LOG = LoggerFactory.getLogger(PropertiesManager.class);
    private static final String PATH = "../Danager-config-file/";

    private static PropertiesManager INSTANCE = null;

    private Map<String, ConfigurationProperties> serversConfigurations;

    private PropertiesManager(){
        serversConfigurations = new HashMap<>();
    };


    public final static PropertiesManager getInstance() {
        return INSTANCE == null ? (INSTANCE = new PropertiesManager()): INSTANCE;
    }


    public final ConfigurationProperties getServerConfig(Server server) {
        //manage bot reconnection
        if( serversConfigurations.get(server.getId()) == null) {
            try {
                String fileName = PATH + server.getId() + "-config.properties";
                InputStream is = new FileInputStream(fileName);
                Properties properties = new Properties();
                // load a properties file
                properties.load(is);
                //add to manager
                ConfigurationProperties configurationProperties = new ConfigurationProperties(properties);
                serversConfigurations.put(server.getId(), configurationProperties);
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return serversConfigurations.get(server.getId());
    }

    public final void persistServerConfig(Server server) {
        try(OutputStream os = new FileOutputStream(PATH + server.getId() +"-config.properties")) {
            ConfigurationProperties config = getServerConfig(server);
            config.getproperties().store(os, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Properties createDefaultConfig(Server server) throws IOException {
        String fileName = PATH + server.getId() + "-config.properties";
        Properties properties = new Properties();
        OutputStream os = new FileOutputStream(fileName);
        //prepare all the properties
        properties.put(PropertiesEnum.ADMIN.value(), "Administrator,moderateur");
        properties.put(PropertiesEnum.PREFIX.value(), "!");
        properties.put(PropertiesEnum.LUCIOLE.value(), "false");
        //add to manager
        ConfigurationProperties configurationProperties = new ConfigurationProperties(properties);
        serversConfigurations.put(server.getId(), configurationProperties);

        // save a properties file
        properties.store(os, null);
        os.close();
        LOG.info("Configuration file created: "+ fileName);

        return properties;
    }
}
