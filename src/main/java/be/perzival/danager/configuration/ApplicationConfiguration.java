package be.perzival.danager.configuration;

import be.perzival.danager.configuration.specific.DanagerConfiguratrion;
import be.perzival.danager.configuration.specific.DiscordConfiguration;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Perzival on 30/07/2017.
 */
@Configuration
public class ApplicationConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private final AbstractEnvironment environment;

    @Autowired
    public ApplicationConfiguration(AbstractEnvironment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void printProperties() {
        LOG.info("**** APPLICATION PROPERTIES SOURCES ****");

        Set<String> properties = new TreeSet<>();
        for (PropertiesPropertySource p : findPropertiesPropertySources()) {
            LOG.info(p.toString());
            properties.addAll(Arrays.asList(p.getPropertyNames()));
        }
        LOG.info("**** APPLICATION PROPERTIES VALUES ****");
        print(properties);

    }

    private List<PropertiesPropertySource> findPropertiesPropertySources() {
        List<PropertiesPropertySource> propertiesPropertySources = new LinkedList<>();
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource instanceof PropertiesPropertySource) {
                propertiesPropertySources.add((PropertiesPropertySource) propertySource);
            }
        }
        return propertiesPropertySources;
    }

    private void print(Set<String> properties) {
        for (String propertyName : properties) {
            LOG.info("{}={}", propertyName, environment.getProperty(propertyName));
        }
    }




}
