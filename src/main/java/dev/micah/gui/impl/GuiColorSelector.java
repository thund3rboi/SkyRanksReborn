package dev.micah.gui.impl;

import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiColorSelector {

    private Inventory gui;
    private Player player;
    private String rank;

    public GuiColorSelector(Player player, String rank, ColorSelectorType type) {
        this.player = player;
        this.rank = rank;
        if (type.equals(ColorSelectorType.CHAT_COLOR)) {
            this.gui = Bukkit.createInventory(null, 45, Chat.color("&cSelect a Chat Color"));
        } else {
            this.gui = Bukkit.createInventory(null, 45, Chat.color("&cSelect a Name Color"));
        }
        this.setOuterBorder();
        this.setColorItems();
        this.gui.setItem(0, createRankItem(rank));
        player.openInventory(gui);
    }

    private ItemStack createRankItem(String rank) {
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color("&c" + rank));
        List<String> lore = new ArrayList<>();
        lore.add(Chat.color("&7Info about this rank:"));
        lore.add(" ");
        lore.add(Chat.color("&c- &7Prefix: &r" + (Rank.getPrefix(rank) == null ? "&fNONE" : Rank.getPrefix(rank))));
        lore.add(Chat.color("&c- &7Suffix: &r" + (Rank.getSuffix(rank) == null ? "&fNONE" : Rank.getSuffix(rank))));
        lore.add(Chat.color("&c- &7Chat Color: &r" + Rank.getChatColor(rank) + "THIS"));
        lore.add(Chat.color("&c- &7Name Color: &r" + Rank.getNameColor(rank) + "THIS"));
        lore.add("  ");
        lore.add(Chat.color("&7You are currently editing this rank..."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private void setColorItems() {
        gui.setItem(gui.firstEmpty(), build("&cRED", 14));
        gui.setItem(gui.firstEmpty(), build("&4DARK RED", 14));
        gui.setItem(gui.firstEmpty(), build("&9BLUE", 3));
        gui.setItem(gui.firstEmpty(), build("&1DARK BLUE", 11));
        gui.setItem(gui.firstEmpty(), build("&6GOLD", 1));
        gui.setItem(gui.firstEmpty(), build("&eYELLOW", 4));
        gui.setItem(gui.firstEmpty(), build("&aGREEN", 5));
        gui.setItem(gui.firstEmpty(), build("&2DARK GREEN", 13));
        gui.setItem(gui.firstEmpty(), build("&bAQUA", 3));
        gui.setItem(gui.firstEmpty(), build("&3DARK AQUA", 9));
        gui.setItem(gui.firstEmpty(), build("&dPURPLE", 6));
        gui.setItem(gui.firstEmpty(), build("&5DARK PURPLE", 10));
        gui.setItem(gui.firstEmpty(), build("&fWHITE", 0));
        gui.setItem(gui.firstEmpty(), build("&7GRAY", 8));
        gui.setItem(gui.firstEmpty(), build("&8DARK GRAY", 7));
        gui.setItem(gui.firstEmpty(), build("&0BLACK", 15));
    }

    private void setOuterBorder() {
        gui.setItem(0, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(1, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(2, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(3, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(4, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(5, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(6, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(7, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(8, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(9, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(17, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(18, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(26, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(27, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(35, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(36, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(37, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(38, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(39, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(40, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(41, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(42, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(43, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(44, build(" ", Material.STAINED_GLASS_PANE));
    }

    private ItemStack build(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color(name));
        item.setItemMeta(meta);
        return item;
    }

    public enum ColorSelectorType {
        NAME_COLOR, CHAT_COLOR;
    }

    private ItemStack build(String name, int color) {
        ItemStack item = new ItemStack(Material.GLASS, 1, (byte) color);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color("&cChange the color to &r" + name));
        item.setItemMeta(meta);
        return item;
    }

}
