package be.perzival.danager.command;

import be.perzival.danager.command.admin.Delete;
import be.perzival.danager.command.admin.Disable;
import be.perzival.danager.command.admin.Enable;
import be.perzival.danager.command.admin.Kick;
import be.perzival.danager.command.albion.AlbionMarket;
import be.perzival.danager.command.albion.AlbionStatus;
import be.perzival.danager.command.albion.Event;
import be.perzival.danager.command.utils.Afk;
import be.perzival.danager.command.utils.Alert;
import be.perzival.danager.command.utils.Help;
import be.perzival.danager.command.utils.Info;
import be.perzival.dev.entities.Status;
import be.perzival.dev.entities.market.Resource;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.Optional;

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
        }else if(command == Event.class) {
            builder.setColor(Color.GREEN);
        }else if(command == AlbionStatus.class) {
            builder.setAuthor("Albion Online");
            builder.setUrl("https://albiononline.statuspage.io");
            builder.setAuthorIconUrl("https://dka575ofm4ao0.cloudfront.net/pages-transactional_logos/retina/48818/G0oedmgcT5GA0hN2cOMu");
            if(message.equalsIgnoreCase("GOOD")) {
                builder.setColor(Color.GREEN);
                builder.setDescription("Tout les services sont en ligne");
            }else if(message.equalsIgnoreCase("BAD")) {
                builder.setColor(Color.RED);
                builder.setDescription("le serveur de jeu ne répond pas");
            }else {
                builder.setColor(Color.ORANGE);
                builder.setDescription("Il semble y avoir un problème avec les serveurs de jeu");
            }
        }

        return builder;
    }

    public final static <T extends AbstractCommand> EmbedBuilder getEmbedResponse(Class<T> command, Object message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setUrl("https://github.com/delskev/Danager");

        if( message == null || message.equals(""))return null;
        if( command == AlbionMarket.class) {
            builder.setAuthor("Informations marché:");
            Optional<Resource> resourceOptional = (Optional<Resource>)message;
            if( resourceOptional.isPresent()) {
                Resource resource = resourceOptional.get();
                builder.addField("Objet:",
                                "Id: " + resource.getItem().getId() + "\n" +
                                "Nom: " + resource.getItem().getName() + "\n" +
                                "Type: " + resource.getItem().getSubCategoryName(),
                        false);
                builder.addField("Vente:",
                                "Prix moyen: " + resource.getStats().getSell().getPriceAverage() + "\n" +
                                "Prix min: " + resource.getStats().getSell().getPriceMinimum() + "\n" +
                                "Prix max: " + resource.getStats().getSell().getPriceMaximum() + "\n" +
                                "Volume mis en vente: " + resource.getStats().getSell().getTotalVolume() + "\n" +
                                "Ordre de vente: " + resource.getStats().getSell().getOrderCount(),
                        true);
                builder.addField("Achat:",
                                "Prix moyen: " + resource.getStats().getBuy().getPriceAverage() + "\n" +
                                "Prix min: " + resource.getStats().getBuy().getPriceMinimum() + "\n" +
                                "Prix max: " + resource.getStats().getBuy().getPriceMaximum() + "\n" +
                                "Volume mis en vente: " + resource.getStats().getBuy().getTotalVolume() + "\n" +
                                "Ordre d'achat: " + resource.getStats().getBuy().getOrderCount(),
                        true);
                builder.setColor(Color.ORANGE);
            }
        }
        else if( command == AlbionStatus.class) {
            Optional<Status> status = (Optional<Status>)message;
            builder.setAuthor("Status Serveur:");
            builder.addField("Status:", status.get().getStatus(),false);
            builder.addField("Message:", status.get().getMessage(),false);
            if( "online".equalsIgnoreCase(status.get().getStatus())) {
                builder.setColor(Color.GREEN);
            }else {
                builder.setColor(Color.RED);
            }

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
