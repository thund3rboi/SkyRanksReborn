package dev.micah.rank;

import dev.micah.SkyRanks;
import dev.micah.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private static SkyRanks plugin = SkyRanks.getInstance();
    private static YamlConfiguration conf = SkyRanks.getDataFile();
    private static List<String> ranks = conf.getStringList("ranks.list");

    public static void createRank(String rankName) {
        conf.set("ranks." + rankName + ".prefix", "&7[&f" + rankName + "&7]");
        conf.set("ranks." + rankName + ".suffix", null);
        conf.set("ranks." + rankName + ".nameColor", "&f");
        conf.set("ranks." + rankName + ".chatColor", "&f");
        conf.set("ranks." + rankName + ".inGui", true);
        ranks.add(rankName);
        conf.set("ranks.list", ranks);
        Bukkit.getLogger().info(rankName + " was created...");
        plugin.save();
    }

    public static void deleteRank(String rankName) {
        conf.set("ranks." + rankName, null);
        ranks.remove(rankName);
        Bukkit.getLogger().info(rankName + " was deleted!");
        plugin.save();
    }

    public static void setPrefix(String rankName, String prefix) {
        conf.set("ranks." + rankName + ".prefix", prefix);
        plugin.save();
    }

    public static String getPrefix(String rankName) {
        return conf.getString("ranks." + rankName + ".prefix");
    }

    public static void setSuffix(String rankName, String suffix) {
        conf.set("ranks." + rankName + ".suffix", suffix);
        plugin.save();
    }

    public static String getSuffix(String rankName) {
        return conf.getString("ranks." + rankName + ".suffix");
    }

    public static void setNameColor(String rankName, String colorCode) {
        conf.set("ranks." + rankName + ".nameColor" , colorCode);
        plugin.save();
    }

    public static String getNameColor(String rankName) {
        String code = conf.getString("ranks." + rankName + ".nameColor");
        if (code != null && code.contains("&")) {
            return code;
        } else {
            Bukkit.getLogger().warning("Could not load color code " + conf.getString("ranks." + rankName + ".nameColor" + " for rank " + rankName + " make sure that the color code is vaild!"));
        }
        return "&f";
    }

    public static void setChatColor(String rankName, String colorCode) {
        conf.set("ranks." + rankName + ".chatColor" , colorCode);
        plugin.save();
    }

    public static String getChatColor(String rankName) {
        String code = conf.getString("ranks." + rankName + ".chatColor");
        if (code != null && code.contains("&")) {
            return code;
        } else {
            Bukkit.getLogger().warning("Could not load color code " + conf.getString("ranks." + rankName + ".chatColor" + " for rank " + rankName + " make sure that the color code is vaild!"));
        }
        return "&f";
    }

    public static void setInGui(String rankName, boolean inGui) {
        conf.set("ranks." + rankName + ".inGui", inGui);
        plugin.save();
    }

    public static boolean isInGui(String rankName) {
        return conf.getBoolean("ranks." + rankName + ".inGui");
    }

    public static boolean exists(String rankName) {
        if (conf.getString("ranks." + rankName) != null) {
            return true;
        }
        return false;
    }

    public static String getRank(Player p) {
        return conf.getString("player." + p.getName())  == null ? conf.getString("ranks.default") : conf.getString("player." + p.getName());
    }

    public static void setRank(Player p, String rankName) {
        conf.set("player." + p.getName(), rankName);
        plugin.save();
    }

}
