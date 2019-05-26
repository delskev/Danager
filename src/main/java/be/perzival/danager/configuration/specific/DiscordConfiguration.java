package be.perzival.danager.configuration.specific;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "discord")
public class DiscordConfiguration implements Serializable {
    @NotBlank
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
