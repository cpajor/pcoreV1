package com.github.cPajor.pcore.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import com.github.cPajor.pcore.data.man.UserMan;
import com.github.cPajor.pcore.data.user.Combat;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.data.util.RandomUtil;

public class CombatListener implements Listener {
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)) {
			if (e.getDamage() > 0 && !e.getEntity().getWorld().getName().equalsIgnoreCase("spawnc")) {
				Combat d = Combat.get(e.getDamager().getName());
				Combat v = Combat.get(e.getEntity().getName());
				v.setTime(400);
				d.setTime(400);
			}
		}
	}
	
	@EventHandler
	public void onElytra(PlayerElytraBoostEvent e) {
		e.setCancelled(true);
		e.setShouldConsume(false);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Combat.get(e.getPlayer().getName()) == null) {
			return;
		}
		if (Combat.get(e.getPlayer().getName()).getTime() == 0) {
			return;
		}
		if (e.getBlock().getLocation().getBlockY() < 40) {
			e.getPlayer().sendMessage(Colors.fix("&p &7Nie mozesz stawiac blokow ponizej &4Y: 40 &7podczas walki!"));
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onLogOut(PlayerQuitEvent e) {
		e.setQuitMessage((String)null);
		if (!e.getPlayer().isDead()) 
		if (Combat.get(e.getPlayer().getName()).getTime() != 0) {
			e.getPlayer().setHealth(0.0D);
			e.setQuitMessage(Colors.fix("&p &7Gracz &4" + e.getPlayer().getName() + " &7wylogowal sie podczas walki!"));
		}
	}
	
	@EventHandler
	public void onRRRS(PlayerRespawnEvent e) {
		e.setRespawnLocation(new Location(Bukkit.getWorld("spawnc"), 0, 65, 0));
		e.getPlayer().stopSound(Sound.ENTITY_ENDER_DRAGON_DEATH);
	}
	
	@EventHandler
	public void onPiesXD(PlayerDeathEvent e) {
		Combat.get(e.getEntity().getName()).setTime(0);
		if (e.getEntity().getKiller() != null) {
			int i = RandomUtil.getRandInt(1, 30);
			UserMan.getUser(e.getEntity().getName()).setPvP(UserMan.getUser(e.getEntity().getName()).getPvP() - i);
			UserMan.getUser(e.getEntity().getKiller().getName()).setPvP(UserMan.getUser(e.getEntity().getKiller().getName()).getPvP() + i);
			e.setDeathMessage(Colors.fix("&7Gracz &4" + e.getEntity().getName() + " &7zostal zabity przez &4" + e.getEntity().getKiller().getName() + " "));
		} 
	}
	
}
