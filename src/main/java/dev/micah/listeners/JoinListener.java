package dev.micah.listeners;

import dev.micah.SkyRanks;
import dev.micah.mysql.MySQL;
import dev.micah.mysql.MySQLRank;
import dev.micah.permissions.Permission;
import dev.micah.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (SkyRanks.getDataFile().getString("player." + p.getName()) == null) {
            SkyRanks.getDataFile().set("player." + p.getName(), SkyRanks.getDataFile().getString("ranks.default"));
        }
        PermissionAttachment attachment = p.addAttachment(SkyRanks.getInstance());
        Permission.getPermissionMap().put(p.getUniqueId(), attachment);
        Permission.registerPermissionsToPlayerWithGroup(p, Rank.getRank(p));
        if (SkyRanks.mysql) {
            try {
                ResultSet rs = MySQLRank.preparedStatement("SELECT COUNT(UUID) FROM " + MySQL.database() + " WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
