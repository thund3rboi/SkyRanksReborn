package dev.micah.utils;

import org.bukkit.entity.Player;

public class PlayerUtil {

    private Player p;
    public PlayerUtil(Player playerIn) {
        p = playerIn;
    }

    public Player sendMessage(String message) {
        p.sendMessage(Chat.color("&7[&cSkyRanks&7] &r" + message));
        return p;
    }

    public Player getPlayer() {
        return p;
    }
}
