package dev.micah.listeners;

import dev.micah.SkyRanks;
import dev.micah.api.event.RankDeletedEvent;
import dev.micah.api.event.RankSetEvent;
import dev.micah.conversation.ConversationHandler;
import dev.micah.gui.impl.GuiColorSelector;
import dev.micah.gui.impl.GuiEditor;
import dev.micah.gui.impl.GuiRanks;
import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
import dev.micah.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    @Deprecated
    @EventHandler
    public void event(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (e.getInventory().getTitle().equals(Chat.color("&c&lSKYRANKS - Ranks"))) {
            int page = Integer.parseInt(e.getInventory().getItem(0).getItemMeta().getDisplayName().substring(7));
            if (item != null && item.getType() != null) {
                if (item.getType() == Material.ARROW && item.getItemMeta().getDisplayName().contains("Next")) {
                    new GuiRanks(p, page + 1);
                }
                if (item.getType() == Material.ARROW && item.getItemMeta().getDisplayName().contains("Previous")) {
                    new GuiRanks(p, page - 1);
                }
                if (e.isShiftClick() && item.getType() == Material.NAME_TAG) {
                    new GuiEditor(p, ChatColor.stripColor(item.getItemMeta().getDisplayName()), page);
                }
            }
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().equals(Chat.color("&c&lSKYRANKS - Editor"))) {
            String rank = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
            if (item != null && item.getType() != null) {
                if (item.getType() == Material.LAVA_BUCKET) {
                    Rank.deleteRank(rank);
                    p.closeInventory();
                    new RankDeletedEvent(p, rank);
                    new PlayerUtil(p).sendMessage("&cYou deleted the rank &7" + rank);
                }
                if (item.getType() == Material.BARRIER) {
                    new GuiRanks(p, GuiEditor.getPageComingFrom().get(p));
                }
                if (item.getType() == Material.BOOK_AND_QUILL) {
                    if (item.getItemMeta().getDisplayName().contains("Edit Prefix")) {
                        ConversationHandler.startConversationEditPrefix(p, rank);
                    }
                    if (item.getItemMeta().getDisplayName().contains("Edit Suffix")) {
                        ConversationHandler.startConversationEditSuffix(p, rank);
                    }
                }
                if (item.getType() == Material.EMERALD) {
                    if (item.getItemMeta().getDisplayName().contains("Chat Color")) {
                        new GuiColorSelector(p, rank, GuiColorSelector.ColorSelectorType.CHAT_COLOR);
                    }
                    if (item.getItemMeta().getDisplayName().contains("Name Color")) {
                        new GuiColorSelector(p, rank, GuiColorSelector.ColorSelectorType.NAME_COLOR);
                    }
                }
                if (item.getType() == Material.ANVIL) {
                    if (item.getItemMeta().getDisplayName().contains("Add Permission")) {
                        ConversationHandler.startConversationAddPermission(p, rank);
                    } else {
                        ConversationHandler.startConversationRemovePermission(p, rank);
                    }
                }
                if (item.getType() == Material.IRON_INGOT) {
                    SkyRanks.getDataFile().set("ranks.default", rank);
                    p.closeInventory();
                    new GuiRanks(p, 1);
                }
            }
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().contains(Chat.color("&cSelect a "))) {
            String rank = ChatColor.stripColor(e.getInventory().getItem(0).getItemMeta().getDisplayName());
            boolean chatColor = e.getInventory().getTitle().contains("Chat");
            if (chatColor) {
                Rank.setChatColor(rank, getColorCodeFromClickedItem(item));
            } else {
                Rank.setNameColor(rank, getColorCodeFromClickedItem(item));
            }
            new GuiEditor(p, rank, GuiEditor.getPageComingFrom().get(p));
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().equals(Chat.color("&c&lSKYRANKS - Set Rank"))) {
            String playerName = e.getInventory().getItem(8).getItemMeta().getDisplayName().substring(18);
            String rank = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            Rank.setRank(Bukkit.getOfflinePlayer(playerName).getPlayer(), rank);
            p.closeInventory();
            new RankSetEvent((Player)e.getWhoClicked(), Bukkit.getPlayer(playerName), rank);
            new PlayerUtil(p).sendMessage("&cYou changed &7" + playerName + "&c's rank to &7" + rank);
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().equals(Chat.color("&c&lSKYRANKS - Nick"))) {
            //TODO Nick Listener
        }
    }

    public String getColorCodeFromClickedItem(ItemStack itemIn) {
        String s = itemIn.getItemMeta().getDisplayName();
        if (s.contains("RED") && !(s.contains("DARK"))) { return "&c"; }
        if (s.contains("DARK RED")) { return "&4"; }
        if (s.contains("BLUE") && !(s.contains("DARK"))) { return "&9"; }
        if (s.contains("DARK BLUE")) { return "&1"; }
        if (s.contains("AQUA") && !(s.contains("DARK"))) { return "&b"; }
        if (s.contains("WHITE")) { return "&f"; }
        if (s.contains("GRAY") && !(s.contains("DARK"))) { return "&7"; }
        if (s.contains("DARK GRAY")) { return "&8"; }
        if (s.contains("BLACK")) { return "&0"; }
        if (s.contains("PURPLE") && !(s.contains("DARK"))) { return "&d"; }
        if (s.contains("DARK PURPLE")) { return "&5"; }
        if (s.contains("GOLD")) { return "&6"; }
        if (s.contains("DARK GREEN")) { return "&2"; }
        if (s.contains("GREEN") && !(s.contains("DARK"))) { return "&a"; }
        if (s.contains("YELLOW")) { return "&e"; }
        if (s.contains("DARK AQUA")) { return "&3"; }
        return "&r";
    }

}
