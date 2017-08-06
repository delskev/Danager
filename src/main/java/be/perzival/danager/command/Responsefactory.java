package be.perzival.danager.command;

import be.perzival.danager.command.admin.Delete;
import be.perzival.danager.command.admin.Disable;
import be.perzival.danager.command.admin.Enable;
import be.perzival.danager.command.admin.Kick;
import be.perzival.danager.command.utils.Afk;
import be.perzival.danager.command.utils.Alert;
import be.perzival.danager.command.utils.Help;
import be.perzival.danager.command.utils.Info;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;

import java.awt.*;

/**
 * Created by Perzival on 02/08/2017.
 */
public class Responsefactory {

    public final static <T extends AbstractCommand> EmbedBuilder getEmbedResponse(Class<T> command, String message) {
        return getEmbedResponse(command, message, null);
    }

    public final static <T extends AbstractCommand> EmbedBuilder getEmbedResponse(Class<T> command, String message, User user) {
        EmbedBuilder builder = buildBase(message);

        if( message == null || message.equals(""))return null;
        if( command == Delete.class) {
            builder.setColor(Color.BLUE);
        } else if(command ==  Kick.class){
            builder.setColor(Color.ORANGE);
        } else if(command ==  Help.class){
            builder.setColor(Color.green);
        }else if(command ==  Info.class){
            builder.setColor(Color.BLUE);
        }else if(command ==  Enable.class){
            builder.setColor(Color.GREEN);
        }else if(command ==  Disable.class){
            builder.setColor(Color.RED);
        }else if(command ==  Afk.class) {
            builder.setColor(Color.ORANGE);
            builder.setAuthor(user.getName());
            builder.setUrl(null);
            builder.setAuthorIconUrl(null);
        }else if(command ==  Alert.class) {
            builder.setColor(Color.RED);
            builder.setAuthor(user.getName());
            builder.setUrl(null);
            builder.setAuthorIconUrl(null);
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
