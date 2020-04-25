package dev.micah;

import dev.micah.command.SkyRanksCommand;
import dev.micah.command.VanishCommand;
import dev.micah.io.FileManager;
import dev.micah.listeners.ChatListener;
import dev.micah.listeners.GuiListener;
import dev.micah.listeners.JoinListener;
import dev.micah.runnable.TablistRunnable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
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

    //files
    private File dataFile;

    //data file
    private YamlConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        dataFile = new File(Bukkit.getPluginManager().getPlugin("SkyRanks").getDataFolder(), "data.yml");
        fileManager = new FileManager(dataFile, "data");
        fileManager.init();
        config = YamlConfiguration.loadConfiguration(fileManager.getFile());
        instance = this;
        this.getCommand("skyranks").setExecutor(new SkyRanksCommand());
        this.getCommand("vanish").setExecutor(new VanishCommand());
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TablistRunnable(), 1L, 1L);
    }

    @Override
    public void onDisable() {}

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
