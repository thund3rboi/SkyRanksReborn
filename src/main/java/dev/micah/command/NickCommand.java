package dev.micah.command;

import dev.micah.gui.impl.GuiNick;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            new GuiNick(player, 1);
        } else {
            Bukkit.getLogger().info("You cannot do this here!");
        }
        return false;
    }

}
