package dev.micah.listeners;

import dev.micah.SkyRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (SkyRanks.getDataFile().getString("player." + p.getName()) == null) {
            SkyRanks.getDataFile().set("player." + p.getName(), SkyRanks.getDataFile().getString("ranks.default"));
        }
    }

}
