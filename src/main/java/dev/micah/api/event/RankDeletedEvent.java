package dev.micah.api.event;

import dev.micah.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RankDeletedEvent extends Event {

    Player whoDeleted;
    String rankDeleted;
    public RankDeletedEvent(Player whoDeleted, String rankDeleted) {
        this.whoDeleted = whoDeleted;
        this.rankDeleted = rankDeleted;
    }

    public Player getWhoDeleted() {
        return whoDeleted;
    }

    public String getRankDeleted() {
        return rankDeleted;
    }

    public void revert() {
        Rank.createRank(getRankDeleted());
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

}
