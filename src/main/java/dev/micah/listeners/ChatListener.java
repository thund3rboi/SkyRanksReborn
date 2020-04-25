package dev.micah.listeners;

import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String rank = Rank.getRank(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            boolean prefix = false;
            boolean suffix = false;
            boolean completed = false;
            if (Rank.getPrefix(rank) != null) { prefix = true; }
            if (Rank.getSuffix(rank) != null) { suffix = true; }
            if (suffix && prefix) { all.sendMessage(Chat.color(Rank.getPrefix(rank) + " &r" + Rank.getNameColor(rank) + p.getName() + " " + Rank.getSuffix(rank) + "&r: " + Rank.getChatColor(rank) + e.getMessage()));completed = true; }
            if (prefix && !suffix) { all.sendMessage(Chat.color(Rank.getPrefix(rank) + " &r" + Rank.getNameColor(rank) + p.getName() + "&r: " + Rank.getChatColor(rank) + e.getMessage()));completed = true; }
            if (suffix && !prefix) { all.sendMessage(Chat.color(p.getName() + " " + Rank.getSuffix(rank) + "&r: " + Rank.getChatColor(rank) + e.getMessage()));completed = true; }
            if (!completed) { all.sendMessage(p.getName() + ": " + e.getMessage()); }
        }
        e.setCancelled(true);
    }

}
