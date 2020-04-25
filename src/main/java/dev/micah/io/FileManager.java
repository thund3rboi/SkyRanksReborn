package dev.micah.io;

import dev.micah.SkyRanks;
import dev.micah.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private File file;
    private String name;

    public FileManager(File fileIn, String name) {
        file = fileIn;
        this.name = name;
    }

    public static void loadPremadeFile() {
        YamlConfiguration c = SkyRanks.getDataFile();
        boolean loadDefaults = SkyRanks.getInstance().getConfig().getBoolean("load-defaults");
        if (loadDefaults) {
            c.set("ranks.default", "Member");
            Rank.createRank("Member");
            Rank.setPrefix("Member", "&7[&fMember&7]");
            Rank.setNameColor("Member", "&7");
            Rank.createRank("Moderator");
            Rank.setPrefix("Moderator", "&7[&aModerator&7]");
            Rank.setNameColor("Moderator", "&a");
            Rank.setChatColor("Moderator", "&a");
            Rank.createRank("Owner");
            Rank.setPrefix("Owner", "&7[&cOwner&7]");
            Rank.setNameColor("Owner", "&c");
            Rank.setChatColor("Owner", "&c");
            SkyRanks.getInstance().getConfig().set("load-defaults", false);
            Bukkit.getLogger().info("The configs defaults were loaded, this is most likely your first time running this.");
            SkyRanks.getInstance().save();
            SkyRanks.getInstance().saveConfig();
        }
    }

    public void init() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getLogger().info("[SkyRanks] Loaded " + name + ".yml for the first time!");
            } catch (IOException e) {
                Bukkit.getLogger().info("[SkyRanks] Could not load " + name + ".yml! The plugin might now work...");
                e.printStackTrace();
            }
        }
        System.out.println("[SkyRanks] Successfully loaded " + name + ".yml!");
    }

    public File getFile() {
        return file;
    }
}
