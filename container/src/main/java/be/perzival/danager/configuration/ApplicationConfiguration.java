package be.perzival.danager.configuration;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "discord")
public class ApplicationConfiguration implements Serializable {
    private String token;


    @Bean
    public DiscordApi discordApi() {
        return new DiscordApiBuilder()
                .setToken(token)
                .login()
                .join();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
