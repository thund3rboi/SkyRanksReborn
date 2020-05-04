package dev.micah.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RankSetEvent extends Event {

    private Player whoSet;
    private Player whoWasSet;
    private String rankSet;

    public RankSetEvent(Player whoSet, Player whoWasSet, String rankSet) {
        this.whoSet = whoSet;
        this.whoWasSet = whoWasSet;
        this.rankSet = rankSet;
    }

    public Player getWhoSet() {
        return whoSet;
    }

    public Player getWhoWasSet() {
        return whoWasSet;
    }

    public String getRankSet() {
        return rankSet;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

}
