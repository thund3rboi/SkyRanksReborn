package dev.micah.command;

import dev.micah.utils.Chat;
import dev.micah.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor {

    private static List<Player> vanishedList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("You cannot run this through console!");
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            boolean vanished = vanishedList.contains(p);
            for (Player all : Bukkit.getOnlinePlayers()) {
                PlayerUtil util = new PlayerUtil(p);
                if (vanished) {
                    all.showPlayer(p);
                    vanishedList.remove(p);
                    util.sendMessage("&cYou are no longer vanished!");
                } else {
                    all.hidePlayer(p);
                    vanishedList.add(p);
                    util.sendMessage("&cYou are now vanished!");
                }
            }
        }
        return false;
    }

    public static List<Player> getVanishedList() { return vanishedList; }

}
