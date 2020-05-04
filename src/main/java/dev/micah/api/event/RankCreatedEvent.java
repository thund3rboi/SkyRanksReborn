package dev.micah.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RankCreatedEvent extends Event {

    Player whoCreated;
    String rankCreated;

    public RankCreatedEvent(Player whoCreated, String rankCreated) {
        this.whoCreated = whoCreated;
        this.rankCreated = rankCreated;
    }

    public String getRankCreated() {
        return rankCreated;
    }

    public Player getWhoCreated() {
        return whoCreated;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

}
