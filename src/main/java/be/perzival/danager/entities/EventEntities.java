package be.perzival.danager.entities;

import be.perzival.danager.exceptions.command.BadEventTypeProvided;

/**
 * Created by Perzival on 15/09/2017.
 */
public class EventEntities {
    public static final String DONOTPARTICIPATE = ":no_entry:";
    public static final String PARTICIPATE = ":white_check_mark:";
    public static final String MAYBEPARTICIPATE = ":question:";

    EventType type;
    String date;
    String destination;
    String description;
    Participation participation;

    public EventEntities() throws BadEventTypeProvided {
        this(null, null, null, null);
    }

    public EventEntities(String eventType, String date, String destination, String description, Participation participation) throws BadEventTypeProvided {
        this.type = EventType.fromValue(eventType);
        this.date = date;
        this.destination = destination;
        this.description = description;
        this.participation = participation;
    }

    public EventEntities(String eventType, String date, String destination, String description) throws BadEventTypeProvided {
        this(eventType, date, destination, description, new Participation());
    }

    public EventType getType() {
        return type;
    }

    public String getDate() {
        return date;
    }


    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public Participation getParticipation() {
        return participation;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Type: " + this.type + "\n");
        builder.append("Date: " + this.date + "\n");
        builder.append("Destination: " + this.destination + "\n");
        builder.append("Description: " + this.description + "\n");

        return builder.toString();
    }
}
