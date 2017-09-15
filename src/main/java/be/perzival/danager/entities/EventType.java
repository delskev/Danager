package be.perzival.danager.entities;

import be.perzival.danager.exceptions.command.BadEventTypeProvided;

/**
 * Created by Perzival on 15/09/2017.
 */
public enum EventType {
    PVE,
    PVP,
    FARM,
    HELLGATE,
    WARCAMP,
    GVG;


    public static EventType fromValue(String value) throws BadEventTypeProvided {
        switch(value) {
            case "PVE":
                return EventType.PVE;
            case "PVP":
                return EventType.PVP;
            case "FARM":
                return EventType.FARM;
            case "HG":
                return EventType.HELLGATE;
            case "WC":
                return EventType.WARCAMP;
            case "GVG":
                return EventType.GVG;
        }
        throw new BadEventTypeProvided(value);
    }
}
