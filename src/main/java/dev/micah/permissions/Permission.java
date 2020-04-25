package dev.micah.permissions;

import dev.micah.SkyRanks;
import dev.micah.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class Permission {

    private static HashMap<UUID, PermissionAttachment> permissionMap = new HashMap<>();

    public static void addPermission(Player p, String permission) {
        PermissionAttachment attachment = permissionMap.get(p.getUniqueId());
        attachment.setPermission(permission, true);
    }

    public static void removePermission(Player p, String permission) {
        PermissionAttachment attachment = permissionMap.get(p.getUniqueId());
        attachment.setPermission(permission, false);
    }

    public static void registerPermissionsToPlayerWithGroup(Player p, String rank) {
        for (String perm : Rank.getPermissions(rank)) {
            addPermission(p, perm);
            SkyRanks.getInstance().save();
        }
    }

    public static HashMap<UUID, PermissionAttachment> getPermissionMap() {
        return permissionMap;
    }
}
