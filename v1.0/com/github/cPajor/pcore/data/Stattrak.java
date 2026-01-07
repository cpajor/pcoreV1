package com.github.cPajor.pcore.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.cPajor.pcore.data.util.Colors;

public class Stattrak implements Listener {

	public static ItemStack addStattrackSword(ItemStack is, String owner) {
		ItemMeta im = is.getItemMeta();
		List<String> ls = new ArrayList<String>();
		ls.add(Colors.fix("&6Ten przedmiot posiada technologie &f&lStattrack&f™"));
		ls.add(Colors.fix("&6Zabojstwa na stattack: &f&l0"));
		ls.add(Colors.fix("&6Pierwszy wlasciciel: &f" + owner));
		im.setLore(ls);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack addPointSword(ItemStack is, int am) {
		ItemMeta im = is.getItemMeta();
		if (!im.hasLore()) return is;
		if (im.getLore().get(0).contains(Colors.fix("&ePamiatka"))) return is;
		int a = Integer.parseInt(im.getLore().get(1).replaceAll(Colors.fix("&6Zabojstwa na stattack: &f&l"), ""));
		List<String> ls = im.getLore();
		ls.set(1, Colors.fix("&6Zabojstwa na stattack: &f&l" + (a + am)));
		im.setLore(ls);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack addStattrackPickaxe(ItemStack is, String owner) {
		ItemMeta im = is.getItemMeta();
		List<String> ls = new ArrayList<String>();
		ls.add(Colors.fix("&6Ten przedmiot posiada technologie &f&lStattrack&f™"));
		ls.add(Colors.fix("&6Bloki wykopane na stattack: &f&l0"));
		ls.add(Colors.fix("&6Pierwszy wlasciciel: &f" + owner));
		im.setLore(ls);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack addPointPickaxe(ItemStack is, int am) {
		ItemMeta im = is.getItemMeta();
		if (!im.hasLore()) return is;
		if (im.getLore().get(0).contains(Colors.fix("&ePamiatka"))) return is;
		int a = Integer.parseInt(im.getLore().get(1).replaceAll(Colors.fix("&6Bloki wykopane na stattack: &f&l"), ""));
		List<String> ls = im.getLore();
		ls.set(1, Colors.fix("&6Bloki wykopane na stattack: &f&l" + (a + am)));
		im.setLore(ls);
		is.setItemMeta(im);
		return is;
	}
	
	@EventHandler
	public void onSword(EntityDeathEvent e) {
		if (e.getEntity().getKiller() == null) return;
		switch (e.getEntity().getKiller().getInventory().getItemInMainHand().getType()) {
		case WOODEN_SWORD:
		case STONE_SWORD:
		case IRON_SWORD:
		case GOLDEN_SWORD:
		case DIAMOND_SWORD:
		case NETHERITE_SWORD:
			if (e.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				e.getEntity().getKiller().getInventory().setItemInMainHand(addPointSword(e.getEntity().getKiller().getInventory().getItemInMainHand(), 1));
			}
			break;
		default:
			break;
		}
	}
	
	@EventHandler
	public void onPickaxe(BlockBreakEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand() != null) {
			if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			switch (e.getPlayer().getInventory().getItemInMainHand().getType()) {
			case WOODEN_PICKAXE:
			case STONE_PICKAXE:
			case IRON_PICKAXE:
			case GOLDEN_PICKAXE:
			case DIAMOND_PICKAXE:
			case NETHERITE_PICKAXE:
				if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
					e.getPlayer().getInventory().setItemInMainHand(addPointPickaxe(e.getPlayer().getInventory().getItemInMainHand(), 1));
				}
				break;
			default:
				break;
			}
		}
	}
	
}
