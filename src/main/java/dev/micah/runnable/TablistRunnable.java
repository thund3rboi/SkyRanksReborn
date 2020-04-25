package dev.micah.runnable;

import dev.micah.command.VanishCommand;
import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TablistRunnable implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (VanishCommand.getVanishedList().contains(p)) { p.setPlayerListName(" ");return; }
            boolean prefix = false;
            boolean suffix = false;
            boolean completed = false;
            String rank = Rank.getRank(p);
            if (Rank.getPrefix(rank) != null) { prefix = true; }
            if (Rank.getSuffix(rank) != null) { suffix = true; }
            if (suffix && prefix) { p.setPlayerListName(Chat.color(Rank.getPrefix(rank) + " &r" + Rank.getNameColor(rank) + p.getName() + " " + Rank.getSuffix(rank)));completed = true; }
            if (prefix && !suffix) { p.setPlayerListName(Chat.color(Rank.getPrefix(rank) + " &r" + Rank.getNameColor(rank) + p.getName()));completed = true; }
            if (suffix && !prefix) { p.setPlayerListName(Chat.color(p.getName() + " " + Rank.getSuffix(rank)));completed = true; }
            if (!completed) { p.setPlayerListName((p.getName())); }
        }
    }

}
