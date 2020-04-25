package dev.micah.runnable;

import dev.micah.SkyRanks;
import dev.micah.permissions.Permission;
import dev.micah.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;

public class PermissionCheckRunnable implements Runnable {

    /** First value is group, second is permission **/
    public static HashMap<String, String> waitListToRemove = new HashMap<>();

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Permission.getPermissionMap().get(p) == null) {
                PermissionAttachment attachment = p.addAttachment(SkyRanks.getInstance());
                Permission.getPermissionMap().put(p.getUniqueId(), attachment);
            }
            if (waitListToRemove != null && !waitListToRemove.isEmpty()) {
                if (waitListToRemove.containsKey(Rank.getRank(p))) {
                    Permission.removePermission(p, waitListToRemove.get(Rank.getRank(p)));
                }
            }
        }
        Rank.loopThroughAllRanksAndAddPermissions();
    }

}
