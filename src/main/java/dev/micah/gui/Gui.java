package dev.micah.gui;

import dev.micah.utils.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui {

    protected void setOuterBorder(Inventory gui) {
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

    protected ItemStack build(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.color(name));
        item.setItemMeta(meta);
        return item;
    }

}
