package dev.micah.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length <= 0) {
                return false;
            }
            /** This key and functions are hidden on github **/
            if (args[0].equals("")) {
                Bukkit.getLogger().info("[SkyRanksReborn] Activated BETA mode!");
            }
        }
        return false;
    }

}
