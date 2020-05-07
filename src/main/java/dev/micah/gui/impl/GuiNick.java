package dev.micah.gui.impl;

import dev.micah.SkyRanks;
import dev.micah.gui.Gui;
import dev.micah.gui.Page;
import dev.micah.rank.Rank;
import dev.micah.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiNick extends Gui {

    private Inventory gui;
    private List<String> ranks;
    private Player player;

    public GuiNick(Player player, int page) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 36, Chat.color("&c&lSKYRANKS - Nick"));
        this.setOuterBorder(gui);
        this.ranks = SkyRanks.getDataFile().getStringList("ranks.list");
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            if (Rank.isNickable(ranks.get(i))) {
                items.add(createRankItem(ranks.get(i)));
            }
        }
        ItemStack left;
        ItemStack right;
        if (Page.isPageValid(items, page - 1, 14)) { left = build("&aPrevious Page", Material.ARROW); } else { left = build(" ", Material.STAINED_GLASS_PANE); }
        if (Page.isPageValid(items, page + 1, 14)) { right = build("&aNext Page", Material.ARROW); } else { right = build(" ", Material.STAINED_GLASS_PANE); }
        gui.setItem(35, right);
        gui.setItem(27, left);
        for (ItemStack item : Page.getPageItems(items, page, 14)) {
            gui.setItem(gui.firstEmpty(), item);
        }
        gui.setItem(35, build("&cRemove Nick", Material.BARRIER));
        gui.setItem(0, build("&aPage " + page, Material.PAPER));
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
        lore.add(" ");
        lore.add(Chat.color("&7Click to nick as this rank"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
