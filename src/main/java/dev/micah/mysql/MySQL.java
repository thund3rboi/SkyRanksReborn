package dev.micah.mysql;

import dev.micah.SkyRanks;
import org.bukkit.configuration.file.FileConfiguration;

public class MySQL {

    private static FileConfiguration conf = SkyRanks.getInstance().getConfig();

    public static String host() {
        return notNull(conf.getString("mysql.host")) ? conf.getString("mysql.host") : "";
    }

    public static String database() {
        return notNull(conf.getString("mysql.datebase")) ? conf.getString("mysql.database") : "";
    }

    public static String username() {
        return notNull(conf.getString("mysql.username")) ? conf.getString("mysql.username") : "";
    }

    public static String password() {
        return notNull(conf.getString("mysql.password")) ? conf.getString("mysql.password") : "";
    }

    public static int port() {
        return conf.getInt("mysql.port");
    }

    static boolean notNull(String value) {
        if (conf.getString(value) != null) {
            return true;
        }
        return false;
    }

}
