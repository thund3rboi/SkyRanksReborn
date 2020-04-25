package dev.micah.listeners;

import dev.micah.SkyRanks;
import dev.micah.permissions.Permission;
import dev.micah.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

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
    }

}
