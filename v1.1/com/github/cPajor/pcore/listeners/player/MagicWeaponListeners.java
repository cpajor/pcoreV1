package com.github.cPajor.pcore.listeners.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class MagicWeaponListeners implements Listener {
	public static final ItemStack FLOPPY_X = new CItem(Material.MUSIC_DISC_5, 1).setName(Colors.fix("&4Floppy X")).addEnchant(Enchantment.ARROW_FIRE, 5).toIs();
	
	@EventHandler
	public void cross1(EntityShootBowEvent e) {
		if (e.getBow() != null && e.getBow().getType() == Material.CROSSBOW && e.getBow().getItemMeta().hasCustomModelData() && (e.getBow().getItemMeta().getCustomModelData() > 0 && e.getBow().getItemMeta().getCustomModelData() < 4)) {
			e.getProjectile().setGravity(false);
		}
		if (e.getBow() != null && e.getBow().getType() == Material.CROSSBOW && e.getBow().hasItemMeta() && e.getBow().getEnchantmentLevel(Enchantment.RIPTIDE) == 4) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
			}
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
			}.runTaskLater(CReg.getPlugin(), 200);
		}
		//if (!(e.getEntity() instanceof Player)) return;
		/*if (e.getBow() != null && e.getBow().getType() == Material.BOW && e.getBow().hasItemMeta() && e.getBow().getEnchantmentLevel(Enchantment.SWIFT_SNEAK) == 4) {
			Entity rs = ((Player) e.getEntity()).getTargetEntity(75);
			if (rs != null && rs instanceof LivingEntity) {
				e.getProjectile().remove();
				Vibration v = new Vibration(new Vibration.Destination.EntityDestination(rs), 2);
				LivingEntity lv = (LivingEntity) rs;
				for (int i = 0; i < 7; i++) new BukkitRunnable() {
					public void run() {
						e.getEntity().getWorld().spawnParticle(Particle.VIBRATION, e.getEntity().getLocation().clone().add(0, 1, 0), 1, v);
					};
				}.runTaskLater(CReg.getPlugin(), i * 3);
				new BukkitRunnable() {
					
					@Override
					public void run() {
						lv.damage(e.getForce() * 4.0);
						//lv.getWorld().spawnParticle(Particle.SHRIEK, lv.getLocation(), 1, 0);
						new ParticleBuilder(Particle.SHRIEK).allPlayers().data(0).location(lv.getLocation()).spawn();
					}
				}.runTaskLater(CReg.getPlugin(), 20);
			} else {
				e.setConsumeItem(false);
				e.setCancelled(true);
			}
		}
		if (e.getBow() != null && e.getBow().getType() == Material.BOW && e.getBow().hasItemMeta() && e.getBow().getEnchantmentLevel(Enchantment.IMPALING) == 10) {
			Entity rs = ((Player) e.getEntity()).getTargetEntity(75);
			if (rs != null && rs instanceof LivingEntity) {
				e.getProjectile().remove();
				LivingEntity lv = (LivingEntity) rs;
				lv.damage(10);
				Player p = (Player) e.getEntity();
				p.setCooldown(Material.BOW, 200);
				p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20, 1, true));
				//lv.getWorld().spawnParticle(Particle.SHRIEK, lv.getLocation(), 1, 10);
				new ParticleBuilder(Particle.SHRIEK).allPlayers().data(10).location(lv.getLocation()).spawn();
			} else {
				e.setConsumeItem(false);
				e.setCancelled(true);
			}
		}*/
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
			e.getPlayer().setCooldown(Material.FEATHER, 100);
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(10.1).normalize());
		}
		if (e.getItem().getType() == Material.RECOVERY_COMPASS && e.getItem().getEnchantmentLevel(Enchantment.ARROW_FIRE) == 10 && !e.getPlayer().hasCooldown(Material.RECOVERY_COMPASS)) {
			e.setCancelled(true);
			if (e.getPlayer().getInventory().contains(Material.TNT)) {
				
				RayTraceResult rs = e.getPlayer().rayTraceBlocks(35);
    			if (rs != null && rs.getHitPosition() != null) { 
    				e.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
    				e.getPlayer().setCooldown(Material.RECOVERY_COMPASS, 5);	
    				Location l = rs.getHitPosition().toLocation(rs.getHitBlock().getWorld());
    				e.getPlayer().getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 1, Material.TNT.createBlockData());
    				new BukkitRunnable() {
						
						@Override
						public void run() {
							l.getWorld().createExplosion(l, 2, false, false, e.getPlayer());
						}
					}.runTaskLater(CReg.getPlugin(), 80);
    			}
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
