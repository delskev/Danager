package be.perzival.dev.danager.configuration

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.Serializable

@Configuration
@ConfigurationProperties(prefix = "discord")
open class ApplicationConfiguration : Serializable {
    var token: String? = null

    @Bean
    open fun discordApi(): DiscordApi =
            DiscordApiBuilder()
                    .setToken(token)
                    .login()
                    .join()

}