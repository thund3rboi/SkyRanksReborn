package dev.micah.utils;

public class CommandUtil {

    public static boolean hasAll(PlayerUtil player, String[] argsIn, int argsNumber, String whatArgsShouldEqual, String permission) {
        return argsIn[argsNumber].equalsIgnoreCase(whatArgsShouldEqual) && player.getPlayer().hasPermission(permission);
    }
    
}
