package be.perzival.danager;


import be.perzival.danager.configuration.specific.DanagerConfiguratrion;
import be.perzival.danager.configuration.specific.DiscordConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

/**
 * Created by Perzival on 30/07/2017.
 */
@SpringBootApplication
@EnableConfigurationProperties({DanagerConfiguratrion.class, DiscordConfiguration.class})
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private final Environment environment;


    @Autowired
    public Application(Environment environment) {
        this.environment = environment;
    }

    private Application() {
        this(null);
    }

    public static void main(String [] args) {
        LOG.info("Starting Spring boot Application");
        SpringApplication.run(Application.class, args);
    }
}
