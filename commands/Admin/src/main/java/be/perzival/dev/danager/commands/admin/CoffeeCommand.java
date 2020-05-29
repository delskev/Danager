package be.perzival.dev.danager.commands.admin;


import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CoffeeCommand implements CommandExecutor {

    @Command(aliases = {"coffee"}, description = "need coffee", usage = "coffee")
    public void onCoffeeCommand(TextChannel textChannel) {
        new MessageBuilder().setContent("Let's have a :coffee: !").send(textChannel);
    }
}
