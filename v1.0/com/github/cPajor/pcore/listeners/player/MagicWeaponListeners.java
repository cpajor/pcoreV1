package com.github.cPajor.pcore.listeners.player;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class MagicWeaponListeners implements Listener {
	
	@EventHandler
	public void phktf(ProjectileHitEvent e) {
		if (e.getEntity().getType() == EntityType.SNOWBALL && e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(Colors.fix("&cWEB"))) {
			if (e.getHitEntity() != null) {
				e.getHitEntity().getLocation().add(0, 0.25, 0).getBlock().setType(Material.COBWEB);
				e.getEntity().remove();
				e.setCancelled(true);
				return;
			}
			if (e.getHitBlock() != null) {
				if (e.getHitBlockFace() == BlockFace.UP) {
					e.getHitBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.COBWEB);
					e.getEntity().remove();
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void cross1(EntityShootBowEvent e) {
		if (e.getBow() != null && e.getBow().getType() == Material.CROSSBOW && e.getBow().hasItemMeta() && e.getBow().getEnchantmentLevel(Enchantment.RIPTIDE) == 4) {
			//if (e.getEntity() instanceof Player) {
			///	Player p = (Player) e.getEntity();
			//}
			Trident t = e.getEntity().launchProjectile(Trident.class, e.getProjectile().getVelocity());
			t.setVelocity(e.getProjectile().getVelocity());
			t.setPickupStatus(PickupStatus.DISALLOWED);
			e.getProjectile().remove();
			e.setProjectile(t);
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if (t != null) t.remove();
				}
			}.runTaskLater(CReg.getPlugin(), 160);
		}
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e) {
		try {
			if (e.getItemInHand().getItemMeta().hasCustomModelData()) e.setCancelled(true);
		} catch (Exception ef) {
			
		}
	}
	
	@EventHandler
	public void cross2(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
			return;
		}
		if (!e.hasItem()) return;
		if (e.getItem().getType() == Material.FEATHER && e.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 10 && !e.getPlayer().hasCooldown(Material.FEATHER)) {
			e.setCancelled(true);
			if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
				e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getItem().getAmount() - 1);
			} else {
				e.getPlayer().getInventory().setItemInMainHand(new CItem(Material.AIR).toIs());
			}
			e.getPlayer().setCooldown(Material.FEATHER, 50);
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(10.1).normalize().multiply(1.5));
		}
		if (e.getItem().getType() == Material.BLAZE_ROD && e.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 10 && !e.getPlayer().hasCooldown(Material.BLAZE_ROD)) {
			e.setCancelled(true);
			e.getPlayer().setCooldown(Material.BLAZE_ROD, 20);
			if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
				e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getItem().getAmount() - 1);
			} else {
				e.getPlayer().getInventory().setItemInMainHand(new CItem(Material.AIR).toIs());
			}
			if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.COBWEB) {
				for (int i = -2; i < 3; i++) {
					for (int j = -2; j < 3; j++) {
						for (int k = -2; k < 3; k++) {
							Block b = e.getClickedBlock().getLocation().add(i, j, k).getBlock();
							if (b.getType() == Material.COBWEB) {
								b.setType(Material.AIR);
								b.getWorld().spawnParticle(Particle.FLAME, b.getLocation(), 5);
							}
						}	
					}
				}
			}
		}
		if (e.getItem().getType() == Material.COBWEB && e.getItem().getEnchantmentLevel(Enchantment.DURABILITY) == 10) {
			if (e.getPlayer().hasCooldown(Material.COBWEB)) {
				return;
			}
			e.setCancelled(true);
			e.getPlayer().setCooldown(Material.COBWEB, 5);
			Snowball b = e.getPlayer().launchProjectile(Snowball.class);
			b.setCustomNameVisible(false);
			b.setCustomName(Colors.fix("&cWEB"));
			if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
				e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getItem().getAmount() - 1);
			} else {
				e.getPlayer().getInventory().setItemInMainHand(new CItem(Material.AIR).toIs());
			}
		}
	}
	
	@EventHandler
	public void freezeEnch(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player l = (Player) e.getDamager(); 
			if (l.getInventory().getItemInMainHand() == null || !l.getInventory().getItemInMainHand().hasItemMeta()) return;
			if (l.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.FROST_WALKER) == 2 && !l.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("BOOTS")) {
				e.getEntity().setFreezeTicks(100);
			}
		}
	}

	@EventHandler
	public void craftCrystal(CraftItemEvent e) {
		if (e.getRecipe().getResult().getType() == Material.END_CRYSTAL) {
			e.setCancelled(true);
			e.getWhoClicked().sendMessage(Colors.fix("&p &cKrysztaly sa zablokowane!"));
		}
	}
}
