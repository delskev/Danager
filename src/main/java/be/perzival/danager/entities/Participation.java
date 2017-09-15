package be.perzival.danager.entities;

import de.btobastian.javacord.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Perzival on 15/09/2017.
 */
public class Participation {
    List<User> participate;
    List<User> doNotParticipate;
    List<User> maybeParticipate;

    public Participation() {
        this(new ArrayList(), new ArrayList(), new ArrayList());
    }

    public Participation(List<User> participate, List<User> doNotParticipate, List<User> maybeParticipate) {
        this.participate = participate;
        this.doNotParticipate = doNotParticipate;
        this.maybeParticipate = maybeParticipate;
    }

    public void addParticipate(User player) {
        this.participate.add(player);
        this.removeDoNotParticipate(player);
        this.removeMaybeParticipate(player);
    }

    public void removeParticipate(User player) {
        this.participate.remove(player);
    }

    public void addDoNotParticipate(User player) {
        this.doNotParticipate.add(player);
        this.removeParticipate(player);
        this.removeMaybeParticipate(player);
    }

    public void removeDoNotParticipate(User player) {
        this.doNotParticipate.remove(player);
    }

    public void addMaybeParticipate(User player) {
        this.maybeParticipate.add(player);
        this.removeParticipate(player);
        this.removeDoNotParticipate(player);
    }

    public void removeMaybeParticipate(User player) {
        this.maybeParticipate.remove(player);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Participe:\n");
        participate.forEach(participate -> builder.append("- "+ participate.getMentionTag()+"\n"));
        builder.append("Ne participe pas:\n");
        doNotParticipate.forEach(participate -> builder.append("- "+ participate.getMentionTag()+"\n"));
        builder.append("Peut-être:\n");
        maybeParticipate.forEach(participate -> builder.append("- "+ participate.getMentionTag()+"\n"));

        return builder.toString();
    }
}
