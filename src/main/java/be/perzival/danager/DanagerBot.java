package be.perzival.danager;

import be.perzival.danager.Callback.DanagerCallback;
import be.perzival.danager.command.Info;
import be.perzival.danager.command.Property;
import be.perzival.danager.configuration.ConfigurationProperties;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.sdcf4j.CommandExecutor;
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
    Map<String,CommandExecutor> commandExecutorssMap;

    @Autowired
    private ApplicationContext appContext;


    public DanagerBot() {
    }

    public void Connect() {
        api = Javacord.getApi("MzQxMTgwNTQ4MDQxMTQ2MzY5.DF9UvA.MecwVYfKXf71rAC_yrXlEzcwtiY", true);; // Your api instance. Of course it should be initialized!

        handler = new JavacordHandler(api);
        handler.setDefaultPrefix(configurationProperties.getPrefix());
        handler.registerCommand(new Info());
        handler.registerCommand(appContext.getBean(Property.class));

        commandExecutorssMap  = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, CommandExecutor.class, true, true);

        BiConsumer<String, CommandExecutor> biConsumer = (key, value) -> { LOG.info("command : "+ key + "[ADDED]"); ;handler.registerCommand(value);};
        commandExecutorssMap.forEach(biConsumer);

        // connect
        api.connect(new DanagerCallback());
    }
}
