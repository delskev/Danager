package be.perzival.danager.commands.core;


import be.perzival.danager.commands.AbstractDanagerCommand;
import de.btobastian.sdcf4j.Command;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
public class CoffeeCommand extends AbstractDanagerCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CoffeeCommand.class);


    @Command(aliases = {"coffee"}, description = "need coffee", usage = "coffee")
    public void onCoffeeCommand(@NotNull TextChannel textChannel ) {
        if(textChannel == null) return ;

        LOG.info("Danager is getting a coffee");
        new MessageBuilder().setContent("Let's have a :coffee: !").send(textChannel).join();
    }
}
