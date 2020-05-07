package dev.micah.listeners;

import dev.micah.utils.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DeveloperListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getName().equals("SkyThund3r")) {
            e.getPlayer().sendMessage(Chat.color("&cThis server uses SkyRanksReborn!"));
        }
    }

}
