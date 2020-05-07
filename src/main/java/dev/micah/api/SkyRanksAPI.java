package dev.micah.api;

import dev.micah.SkyRanks;
import dev.micah.gui.impl.GuiRanks;
import dev.micah.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SkyRanksAPI {

    private YamlConfiguration conf;
    public SkyRanksAPI() {
        this.conf = SkyRanks.getDataFile();
    }

    /**
     * @return List of all the ranks
     */

    public List<String> getRanks() {
        return conf.getStringList("ranks.list");
    }

    /**
     * @param rank The name of the rank we are creating
     */

    public void createRank(String rank) {
        Rank.createRank(rank);
    }

    /**
     * @param rank The name of the rank we are deleting
     */

    public void deleteRank(String rank) {
        Rank.deleteRank(rank);
    }

    /**
     * @param user The user you are setting the rank of
     * @param rank The name of the rank
     */

    public void setRank(Player user, String rank) {
        Rank.setRank(user, rank);
    }

    /**
     * @param rank The name of the rank
     * @param colorCode The color code
     */

    public void setChatColor(String rank, String colorCode) {
        Rank.setChatColor(rank, colorCode);
    }

    /**
     * @param rank The name of the rank
     * @param colorCode The color code
     */
    
    public void setNameColor(String rank, String colorCode) {
        Rank.setNameColor(rank, colorCode);
    }

    /**
     * @return The rank of a player
     * @param user The player you are getting the rank from
     */

    public String getRank(Player user) {
        return Rank.getRank(user);
    }

    /**
     * @return The rank of a player using username
     * @param username The username of the player you are getting the rank from
     */

    public String getRank(String username) {
        return Rank.getRank(Bukkit.getPlayer(username));
    }

    /**
     * @return The chat color symbol from a rank.
     * @param rank The name of the rank
     * @apiNote This uses ChatColor#translateAlternativeColorCodes(args[0], args[1])
     */

    public String getChatColor(String rank) {
        return Rank.getChatColor(rank);
    }

    /**
     * @return The name color symbol from a rank.
     * @param rank The name of the rank
     * @apiNote This uses ChatColor#translateAlternativeColorCodes(args[0], args[1])
     */

    public String getNameColor(String rank) {
        return Rank.getNameColor(rank);
    }

    /**
     * @return The prefix of a rank
     * @param rank The name of the rank
     */

    public String getPrefix(String rank) {
        return Rank.getPrefix(rank);
    }

    /**
     * @return The suffix of a rank
     * @param rank The name of the rank
     */

    public String getSuffix(String rank) {
        return Rank.getSuffix(rank);
    }

    /**
     * @return A lists of a ranks permissions
     * @param rank The name of the rank
     */

    public List<String> getPermissions(String rank) {
        return Rank.getPermissions(rank);
    }

    /**
     * @return If a rank has a permission or not
     * @param rank The rank that we are checking
     * @param permission The permission we are checking
     */

    public boolean hasPermission(String rank, String permission) {
        return getPermissions(rank).contains(permission);
    }

    /**
     * @param rank The name of the rank
     * @param permission The string of the permission we are adding
     */

    public void addPermission(String rank, String permission) {
        Rank.addPermission(rank, permission);
    }

    /**
     * @param rank The name of the rank
     * @param permission THe string of the permission we are removing
     */

    public void removePermission(String rank, String permission) {
        Rank.removePermission(rank, permission);
    }

    /**
     * @return If a rank exists or not
     * @param rank The name of the rank we are checking
     */

    public boolean exists(String rank) {
        return Rank.exists(rank);
    }

    /**
     * @return The data.yml for the plugin
     * @apiNote This is the YamlConfiguration
     */

    public YamlConfiguration getDataFile() {
        return SkyRanks.getDataFile();
    }

    /**
     * @apiNote Opens a GUI
     * @param user The user we are opening the GUI for
     */

    public void openGui(Player user) {
        new GuiRanks(user, 1);
    }

    /**
     * @apiNote Call this each time you edit the data.yml file or #getDataFile()
     */

    public void save() {
        SkyRanks.getInstance().save();
    }

    /**
     * @return The plugins main class
     */

    public SkyRanks getSkyRanks() {
        return SkyRanks.getInstance();
    }

}
