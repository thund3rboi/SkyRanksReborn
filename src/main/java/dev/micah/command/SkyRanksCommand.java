package dev.micah.command;

import dev.micah.SkyRanks;
import dev.micah.gui.impl.GuiEditor;
import dev.micah.gui.impl.GuiRanks;
import dev.micah.gui.impl.GuiSetRank;
import dev.micah.rank.Rank;
import dev.micah.utils.CommandUtil;
import dev.micah.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class SkyRanksCommand implements CommandExecutor {

    private static YamlConfiguration conf = SkyRanks.getDataFile();
    private List<String> ranks = SkyRanks.getDataFile().getStringList("ranks.list");

    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //player
        if (sender instanceof Player) {
            Player playerIn = (Player) sender;
            PlayerUtil p = new PlayerUtil(playerIn);

            if (args.length <= 0) {
                p.sendMessage("Invalid usage! Use /sr help for a list of commands");
                return false;
            }

            if (args.length >= 1) {
                if (CommandUtil.hasAll(p, args, 0, "create", "skyranks.admin.create")) {
                    String rankToCreate = args[1];
                    if (Rank.exists(rankToCreate)) {
                        p.sendMessage("&cA rank with that name already exists!");
                    } else {
                        Rank.createRank(rankToCreate);
                        p.sendMessage("&cCreated the rank &7" + rankToCreate);
                    }
                }
                if (CommandUtil.hasAll(p, args, 0, "reload", "skyranks.admin.reload")) {
                    Bukkit.getPluginManager().disablePlugin(SkyRanks.getInstance());
                    Bukkit.getPluginManager().enablePlugin(SkyRanks.getInstance());
                    p.sendMessage("Plugin has been reloaded!");
                }
                if (CommandUtil.hasAll(p, args, 0, "gui", "skyranks.gui")) {
                    new GuiRanks(p.getPlayer(), 1);
                }
                if (CommandUtil.hasAll(p, args, 0, "setrank", "skyranks.admin.setrank")) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                    if (args.length != 2) {
                        p.sendMessage("&cInvalid usage! Use /sr setrank <player>");
                    }
                    if (player.hasPlayedBefore()) {
                        new GuiSetRank(p.getPlayer(), player.getPlayer(), 1);
                    } else {
                        p.sendMessage("&7" + player.getName() + " &chas never played before!");
                    }
                }
            } else {
                p.sendMessage("Invalid usage! Use /sr help for a list of commands");
            }
        }

        //console

        return false;
    }

}
