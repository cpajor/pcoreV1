package com.github.cPajor.pcore.data.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.user.ChatUser;
import com.github.cPajor.pcore.data.util.Colors;

public class GuiGracz {
	
 	public static boolean GuiPanelChat( Player player) {
		 ChatUser u = ChatUser.getUser(player.getName());
		 Inventory inv = Bukkit.createInventory(player, InventoryType.HOPPER, Colors.fix("&7&l{.} &4Gracz Panel Chat"));
		 ItemStack chatPandora1 = new CItem(Material.BOOK, 1).setName(Colors.fix("&4Pandora: &7OFF")).toIs();
		 ItemStack chatPandora2 = new CItem(Material.ENCHANTED_BOOK, 1).setName(Colors.fix("&4Pandora: &7ON")).toIs();
		 ItemStack chatgracz1 = new CItem(Material.BOOK, 1).setName(Colors.fix("&4Gracze: &7OFF")).toIs();
		 ItemStack chatgracz2 = new CItem(Material.ENCHANTED_BOOK, 1).setName(Colors.fix("&4Gracze: &7ON")).toIs();
		 ItemStack chatis1 = new CItem(Material.BOOK, 1).setName(Colors.fix("&4ItemShop: &7OFF")).toIs();
		 ItemStack chatis2 = new CItem(Material.ENCHANTED_BOOK, 1).setName(Colors.fix("&4ItemShop: &7ON")).toIs();
		 ItemStack chatauto1 = new CItem(Material.BOOK, 1).setName(Colors.fix("&4AutoMSG: &7OFF")).toIs();
		 ItemStack chatauto2 = new CItem(Material.ENCHANTED_BOOK, 1).setName(Colors.fix("&4AutoMSG: &7ON")).toIs();
		 ItemStack chatpriv1 = new CItem(Material.BOOK, 1).setName(Colors.fix("&4Priv MSG: &7OFF")).toIs();
		 ItemStack chatpriv2 = new CItem(Material.ENCHANTED_BOOK, 1).setName(Colors.fix("&4Priv MSG: &7ON")).toIs();
		if (u.get("pandora")) 
			inv.setItem(0, chatPandora2);
		else 
			inv.setItem(0, chatPandora1);
		if (u.get("gracze")) 
			inv.setItem(1, chatgracz2);
		else 
			inv.setItem(1, chatgracz1);
		if (u.get("is")) 
			inv.setItem(2, chatis2);
		else 
			inv.setItem(2, chatis1);
		if (u.get("automsg")) 
			inv.setItem(3, chatauto2);
		else 
			inv.setItem(3, chatauto1);
		if (u.get("priv")) 
			inv.setItem(4, chatpriv2);
		else 
			inv.setItem(4, chatpriv1);
		player.openInventory(inv);
		return true;
	}
	
	public static boolean GuiPanel( Player player) {
		 Inventory inv = Bukkit.createInventory(player, InventoryType.HOPPER, Colors.fix("&7&l{.} &4Gracz Panel"));
		 ItemStack chat = new CItem(Material.BOOK, 1).setName(Colors.fix("&4CHAT")).toIs();
		 //ItemStack gb = new CItem(Material.SADDLE, 1).setName(Colors.fix("&4GearBox")).toIs();
		inv.setItem(2, chat);
		//inv.setItem(0, gb);
		inv.setItem(0, new CItem(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setDurability((short)7).toIs());
		inv.setItem(1, new CItem(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setDurability((short)7).toIs());
		inv.setItem(3, new CItem(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setDurability((short)7).toIs());
		inv.setItem(4, new CItem(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setDurability((short)7).toIs());
		player.openInventory(inv);
		return true;
	}

}
