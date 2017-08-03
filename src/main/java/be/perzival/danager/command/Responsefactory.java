package be.perzival.danager.command;

import be.perzival.danager.command.admin.Delete;
import be.perzival.danager.command.admin.Kick;
import be.perzival.danager.command.utils.Help;
import be.perzival.danager.command.utils.Info;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;

import java.awt.*;

/**
 * Created by Perzival on 02/08/2017.
 */
public class Responsefactory {

    public final static <T extends AbstractCommand> EmbedBuilder getEmbedResponse(Class<T> command, String message) {
        EmbedBuilder builder = buildBase(message);


        if( Delete.class.equals(command)) {
            builder.setColor(Color.BLUE);
        } else if(Kick.class.equals(command)){
            builder.setColor(Color.ORANGE);
        } else if(Help.class.equals(command)){
            builder.setColor(Color.green);
        }else if(Info.class.equals(command)){
            builder.setColor(Color.BLUE);
        }

        return builder;
    }

    private final static EmbedBuilder buildBase(String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setUrl("https://github.com/delskev/Danager");
        builder.setAuthor("Danager", "https://github.com/delskev/Danager", "http://s3.amazonaws.com/cdn.roosterteeth.com/uploads/images/de8ca976-2cf3-4435-8e84-2985dbc04409/md/2220309-1451961825223-Ni.jpeg");
        builder.setDescription(message);
        return builder;
    }
}
