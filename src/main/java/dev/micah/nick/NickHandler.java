package dev.micah.nick;

import dev.micah.rank.Rank;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/** This class will manage the nicks of all the players **/
public class NickHandler {

    /** This will hold all the nicked players **/
    private static HashMap<UUID, String> table = new HashMap<>();
    private static HashMap<UUID, String> rankTable = new HashMap<>();

    /** This will nick a player by putting them into the table **/
    public static void nick(Player player, String nick) {
        table.put(player.getUniqueId(), nick);
    }

    public static void nick(Player player, String nick, String rank) {
        nick(player, nick); rankTable.put(player.getUniqueId(), Rank.getRank(player)); Rank.setRank(player, rank);
    }

    /** This will check a nick by getting them from the table, if null returns there name **/
    public static String see(Player player) {
        return table.get(player.getUniqueId()) == null ? player.getName() : table.get(player.getUniqueId());
    }

    /** This will reset any player nick by removing them from the table **/
    public static void reset(Player player) {
        table.remove(player.getUniqueId()); if (rankTable.containsKey(player.getUniqueId())) { Rank.setRank(player, rankTable.get(player.getUniqueId())); }
    }



}
