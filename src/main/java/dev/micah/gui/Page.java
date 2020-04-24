package dev.micah.gui;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public static List<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces) {
        int upper = page * spaces;
        int lower = upper - spaces;

        List<ItemStack> newList = new ArrayList<>();
        for (int i = lower; i < upper; i++) {
            if (i >= items.size()) {
                return newList;
            }
            newList.add(items.get(i));
        }
        return newList;
    }

    public static boolean isPageValid(List<ItemStack> items, int page, int spaces) {
        if (page <= 0) {
            return false;
        }

        int upper = page * spaces;
        int lower = upper - spaces;

        return items.size() > lower;
    }

}
