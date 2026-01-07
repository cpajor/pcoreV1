package com.github.cPajor.pcore.listeners.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.cPajor.pcore.core.data.CItem;

public class WstormListener implements Listener {
	
	public static List<Suprise> pacynki = new ArrayList<Suprise>();	
	
	public static int getByOwner(String onwer) {
		int ret = 0;
		for (Suprise s : pacynki) {
			if (s.owner.getName().equalsIgnoreCase(onwer)) ret++;
		}
		return ret;
	}
	
	@EventHandler
	public void evdst(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
			return;
		}
		if (!e.hasItem()) return;
		if (e.getPlayer().getInventory().contains(Material.BLAZE_POWDER, 20)) 
		if (e.getItem().getType() == Material.NETHER_STAR && e.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 10 && !e.getPlayer().hasCooldown(Material.NETHER_STAR) && getByOwner(e.getPlayer().getName()) < 3) {
			e.getPlayer().getInventory().removeItem(new CItem(Material.BLAZE_POWDER, 20).toIs());
			e.setCancelled(true);
			e.getPlayer().setCooldown(Material.NETHER_STAR, 240);
			pacynki.add(new Suprise(e.getPlayer(), System.currentTimeMillis() + 14000));
			e.getPlayer().getWorld().spawnParticle(Particle.BLOCK_MARKER, e.getPlayer().getLocation().clone().add(0, 0.5, 0), 1, Material.IRON_TRAPDOOR.createBlockData());
		}
	}
	
	public class Suprise {
		public Location loc;
		public long time;
		public Player owner;
		public boolean explode = false;
		
		public Suprise(Player a0, long tim) {
			this.owner = a0;
			this.loc = a0.getLocation();
			this.time = tim;
		}
	}
}