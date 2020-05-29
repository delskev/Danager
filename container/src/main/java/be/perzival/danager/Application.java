package be.perzival.danager;


import be.perzival.danager.configuration.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by Perzival on 30/07/2017.
 */
@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("Starting Spring boot Application");
        SpringApplication.run(Application.class, args);
    }
}
