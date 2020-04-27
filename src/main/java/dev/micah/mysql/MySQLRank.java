package dev.micah.mysql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLRank {

    public static PreparedStatement preparedStatement(final String query) {
        PreparedStatement ps = null;
        try {
            ps = MySQLSetup.getConnection().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static void setRank(Player p, String rankName) {

    }

}
