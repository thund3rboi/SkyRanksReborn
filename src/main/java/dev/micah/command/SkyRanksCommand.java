package dev.micah.command;

import dev.micah.SkyRanks;
import dev.micah.api.event.RankCreatedEvent;
import dev.micah.gui.impl.GuiEditor;
import dev.micah.gui.impl.GuiRanks;
import dev.micah.gui.impl.GuiSetRank;
import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
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

public class SkyRanksCommand implements CommandExecutor {

    private static YamlConfiguration conf = SkyRanks.getDataFile();
    private List<String> ranks = SkyRanks.getDataFile().getStringList("ranks.list");

    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player playerIn = (Player) sender;
            PlayerUtil p = new PlayerUtil(playerIn);

            if (args.length <= 0) {
                p.sendMessage("Invalid usage! Use /sr help for a list of commands");
                return false;
            }

            if (args.length >= 1) {
                if (CommandUtil.hasAll(p, args, 0, "help", "skyranks.help")) {
                    p.getPlayer().sendMessage(Chat.color("&7--[ &cX &7]-- &c&lSKYRANKS &7--[ &cX &7]--"));
                    p.getPlayer().sendMessage(" ");
                    p.getPlayer().sendMessage(Chat.color("&c- &7/skyranks setrank <player> - Sets a players rank"));
                    p.getPlayer().sendMessage(Chat.color("&c- &7/skyranks create <rank> - Creates a new rank"));
                    p.getPlayer().sendMessage(Chat.color("&c- &7/skyranks gui - Opens up the rank gui"));
                    p.getPlayer().sendMessage(" ");
                }
                if (CommandUtil.hasAll(p, args, 0, "create", "skyranks.admin.create")) {
                    String rankToCreate = args[1];
                    if (Rank.exists(rankToCreate)) {
                        p.sendMessage("&cA rank with that name already exists!");
                    } else {
                        Rank.createRank(rankToCreate);
                        new RankCreatedEvent(playerIn, rankToCreate);
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
        if (!(sender instanceof Player)) {
            if (args.length <= 0) {
                info("Invalid usage! Use /sr help for commands");
            }
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    info("SKYRANKS CONSOLE COMMANDS:");
                    info("- sr help - Shows this page");
                    info("- sr setrank <player> <rank> - Sets a players rank");
                    info("- sr list - Gives a list of ranks");
                }
                if (args[0].equalsIgnoreCase("setrank")) {
                    if (Rank.exists(args[2])) {
                        SkyRanks.getDataFile().set("player." + args[1], args[2]);
                        info(args[1] + "'s rank has been set to " + args[2]);
                    } else {
                        info("Rank does not exist!");
                    }
                }
                if (args[0].equalsIgnoreCase("list")) {
                    info("Ranks (" + ranks.size() + "): " + ranks.toString().replace("[", "").replace("]", ""));
                }
            }
        }
        return false;
    }

    void info(String message) { Bukkit.getLogger().info(message); }

}
