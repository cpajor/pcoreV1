package com.github.cPajor.pcore.data.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class GuiMod {
	
	public static boolean GuiPanel( Player player) {
		 Inventory inv = Bukkit.createInventory((InventoryHolder)player, InventoryType.HOPPER, Colors.fix("&7&l{.} &4Mod Panel"));
		inv.setItem(0, new CItem(Material.DAYLIGHT_DETECTOR).setName(Colors.fix("&4Dzien")).toIs());
		inv.setItem(1, new CItem(Material.DAYLIGHT_DETECTOR).setName(Colors.fix("&4Noc")).toIs());
		for (int iii = 0; iii < inv.getSize(); iii++) {
            if (inv.getItem(iii) == null || inv.getItem(iii).getType() == Material.AIR) {
                inv.setItem(iii, new CItem(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setDurability((short)7).toIs());
            }
        }
		player.openInventory(inv);
		return true;
	}

}
