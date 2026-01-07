package com.github.cPajor.pcore.listeners.player;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnchantListener implements Listener {
	
	@EventHandler
	public void onEnch(EnchantItemEvent e) {
		for (Enchantment f : e.getEnchantsToAdd().keySet()) {
			if (f == Enchantment.ARROW_INFINITE) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void res(InventoryClickEvent e) {
		if (e.getCurrentItem() != null) {
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().containsEnchantment(Enchantment.ARROW_INFINITE)) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		try {
			if (!(p.getInventory().getItemInMainHand() != null) && !p.getInventory().getItemInMainHand().hasItemMeta()) {
				return;
			}
			if (p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_INFINITE) != 0) {
				p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.ARROW_INFINITE);
			}
			/*if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) > 4) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DAMAGE_ALL);
				p.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 4);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.FIRE_ASPECT) > 1) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.FIRE_ASPECT);
				p.getItemInHand().addEnchantment(Enchantment.FIRE_ASPECT, 1);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getBoots().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getBoots().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getChestplate().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getHelmet().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getLeggings().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DURABILITY) > 2) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getItemInHand().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DURABILITY) > 2) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getItemInHand().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getBoots().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getBoots().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getBoots().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getChestplate().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getChestplate().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getChestplate().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getHelmet().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getHelmet().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getLeggings().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getLeggings().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getLeggings().addEnchantment(Enchantment.DURABILITY, 2);
			}*/
		} catch (Exception ex) {}
	}
	/*
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		try {
			if (!(p.getItemInHand() != null) && !p.getItemInHand().hasItemMeta()) {
				return;
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_INFINITE) > 0) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.ARROW_INFINITE);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) > 4) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DAMAGE_ALL);
				p.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 4);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.FIRE_ASPECT) > 1) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.FIRE_ASPECT);
				p.getItemInHand().addEnchantment(Enchantment.FIRE_ASPECT, 1);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getBoots().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getBoots().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getChestplate().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getChestplate().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getHelmet().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getInventory().getLeggings().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
				p.getInventory().getLeggings().getItemMeta().removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
				p.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DURABILITY) > 2) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getItemInHand().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DURABILITY) > 2) {
				p.getItemInHand().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getItemInHand().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getBoots().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getBoots().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getBoots().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getChestplate().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getChestplate().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getChestplate().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getHelmet().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getHelmet().addEnchantment(Enchantment.DURABILITY, 2);
			}
			if (p.getInventory().getLeggings().getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
				p.getInventory().getLeggings().getItemMeta().removeEnchant(Enchantment.DURABILITY);
				p.getInventory().getLeggings().addEnchantment(Enchantment.DURABILITY, 2);
			}
		} catch (Exception ex) {}
	}*/

}
