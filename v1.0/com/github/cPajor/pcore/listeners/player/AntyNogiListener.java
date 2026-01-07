package com.github.cPajor.pcore.listeners.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class AntyNogiListener implements Listener {
	public static List<String> prot = new ArrayList<String>();
	
	@EventHandler
	public void an(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			if (prot.contains(e.getEntity().getName())) {
				prot.remove(e.getEntity().getName());
				e.getEntity().teleport(e.getDamager());
				e.getDamager().sendMessage(Colors.fix("&p &7St00pkarz z Ciebie!"));
			}
		}
	}
	
	@EventHandler
	public void useXXXX(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			return;
		}
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() != Material.IRON_HORSE_ARMOR) {
			return;
		}
		if (!e.getItem().hasItemMeta()) {
			return;
		}
		if (!e.getItem().getItemMeta().getDisplayName().equals(Colors.fix("&4Anty-Nogi"))) {
			return;
		}
		e.getPlayer().getInventory().removeItem(new CItem(Material.IRON_HORSE_ARMOR, 1).setName(Colors.fix("&4Anty-Nogi")).toIs());
		e.setCancelled(true);
		if (!AntyNogiListener.prot.contains(e.getPlayer().getName())) AntyNogiListener.prot.add(e.getPlayer().getName());
	}

}
