package be.perzival.danager;

import be.perzival.danager.Callback.DanagerCallback;
import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.configuration.ConfigurationProperties;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class DanagerBot {
    static final Logger LOG = LoggerFactory.getLogger(DanagerBot.class);

    DiscordAPI api;

    CommandHandler handler;

    @Autowired
    private ConfigurationProperties configurationProperties;

    @Autowired
    Map<String, AbstractCommand> commandExecutorssMap;

    @Autowired
    private ApplicationContext appContext;


    public DanagerBot() {
    }

    /**
     * connect the bot to the discord
     */
    public void Connect(String apiKey) {
        //API instance
        api = Javacord.getApi(apiKey, true);

        handler = new JavacordHandler(api);
        handler.setDefaultPrefix(configurationProperties.getPrefix());

        commandExecutorssMap  = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, AbstractCommand.class, true, true);

        BiConsumer<String, AbstractCommand> biConsumer = (key, value) -> {
            LOG.info("command : "+ key + "[ADDED]");
            value.attachCommandHandler(handler);
            value.attachDiscordAPI(api);
            handler.registerCommand(value);
        };
        commandExecutorssMap.forEach(biConsumer);

        // connect
        api.connect(new DanagerCallback());
    }
}
