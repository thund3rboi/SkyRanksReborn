package dev.micah;

import dev.micah.command.SkyRanksCommand;
import dev.micah.command.VanishCommand;
import dev.micah.io.FileManager;
import dev.micah.listeners.ChatListener;
import dev.micah.listeners.GuiListener;
import dev.micah.listeners.JoinListener;
import dev.micah.runnable.PermissionCheckRunnable;
import dev.micah.runnable.TablistRunnable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/*
 Developed and created by Micah
 */

public final class SkyRanks extends JavaPlugin {

    //instances
    private static SkyRanks instance;
    private FileManager fileManager;
    private YamlConfiguration config;
    private File dataFile;


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        /** File Management Init **/
        dataFile = new File(Bukkit.getPluginManager().getPlugin("SkyRanks").getDataFolder(), "data.yml");
        fileManager = new FileManager(dataFile, "data");
        fileManager.init();
        config = YamlConfiguration.loadConfiguration(fileManager.getFile());
        /** Commands **/
        this.getCommand("skyranks").setExecutor(new SkyRanksCommand());
        this.getCommand("vanish").setExecutor(new VanishCommand());
        /** Events **/
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        /** Runnables **/
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TablistRunnable(), 1L, 1L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PermissionCheckRunnable(), 1L, 1L);
        /** Other **/
        FileManager.loadPremadeFile();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static SkyRanks getInstance() {
        return instance;
    }

    public YamlConfiguration getYamlConfig() {
        return config;
    }

    public static YamlConfiguration getDataFile() {
        return instance.getYamlConfig();
    }

    public void save() {
        try {
            getDataFile().save(fileManager.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
