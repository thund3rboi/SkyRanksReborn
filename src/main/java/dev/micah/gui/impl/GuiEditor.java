package dev.micah.gui.impl;

import dev.micah.gui.Gui;
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

public class GuiEditor extends Gui {

    private Player player;
    private String rank;
    private Inventory gui;
    private static HashMap<Player, Integer> pageComingFrom;

    public GuiEditor(Player player, String rank, int pageComingFrom) {
        this.player = player;
        this.rank = rank;
        this.pageComingFrom = new HashMap<>();
        this.gui = Bukkit.createInventory(null, 36, Chat.color("&c&lSKYRANKS - Editor"));
        this.setOuterBorder(gui);
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
