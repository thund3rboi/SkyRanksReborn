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

public class GuiEditor {

    private Player player;
    private String rank;
    private Inventory gui;
    private static HashMap<Player, Integer> pageComingFrom;

    public GuiEditor(Player player, String rank, int pageComingFrom) {
        this.player = player;
        this.rank = rank;
        this.pageComingFrom = new HashMap<>();
        this.gui = Bukkit.createInventory(null, 36, Chat.color("&c&lSKYRANKS - Editor"));
        this.setOuterBorder();
        this.pageComingFrom.put(player, pageComingFrom);
        gui.setItem(0, createRankItem(rank));
        setEditorItems();
        player.openInventory(gui);
    }

    private void setEditorItems() {
        gui.setItem(10, build("&cEdit Prefix", Material.BOOK_AND_QUILL));
        gui.setItem(19, build("&cEdit Suffix", Material.BOOK_AND_QUILL));
        gui.setItem(11, build("&cEdit Name Color", Material.EMERALD));
        gui.setItem(20, build("&cEdit Chat Color", Material.EMERALD));
        gui.setItem(25, build("&c&lDELETE RANK", Material.LAVA_BUCKET));
        gui.setItem(35, build("&cBack", Material.BARRIER));
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
        gui.setItem(28, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(29, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(30, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(31, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(32, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(33, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(34, build(" ", Material.STAINED_GLASS_PANE));
        gui.setItem(35, build(" ", Material.STAINED_GLASS_PANE));
    }

    private ItemStack build(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color(name));
        item.setItemMeta(meta);
        return item;
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

    public static HashMap<Player, Integer> getPageComingFrom() {
        return pageComingFrom;
    }
}
