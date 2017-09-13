package be.perzival.danager.command.fun;

import be.perzival.danager.command.AbstractCommand;
import be.perzival.danager.command.Responsefactory;
import be.perzival.danager.exceptions.command.CommandException;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.sdcf4j.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Perzival on 30/07/2017.
 */
@Component
public class Expression extends AbstractCommand{
    static final Logger LOG = LoggerFactory.getLogger(Expression.class);

    static final List<String> quote = new ArrayList() {{
        add("Il y a rire et rire...");
        add("Il y a rire et rire ! Mais pisser dans mon dos et dire que je transpire... ça, c'est plus rire !");
        add("Toi ! tu n'as pas toutes les frites dans le même sachet...");
        add("Faut pas pousser mémé dans les orgies...");
        add("C'est pas faux !");
        add("Il était temps... petit navire!");
        add("Apporte moi un kebab sauce cocktail avec double portion de frites !");
        add("Ce serait super chouette qu'on aille se manger une couque un de ces quatre");
        add("Non ! Peut être... ");
        add("J'ai un serpent dans ma botte !");
        add("Espèce de Zinneke !");
        add("Baraki de kermesse !");
    }};

    /**
     * show an helping message
     * @param args no need for this command
     * @return
     * @throws CommandException
     */
    @Override
    @Command(aliases = {"expression", "e"}, description = "give a random quote", usage = "e")
    public void executeCommand(DiscordAPI api, Message message, String[]args) throws CommandException {
        Random rand = new Random();

        EmbedBuilder embed = Responsefactory.getEmbedResponse(this.getClass(), quote.get(rand.nextInt(quote.size())));
        message.getChannelReceiver().sendMessage(null, embed);
    }
}
