package com.github.cPajor.pcore.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RandomTPListener implements Listener {
	
	@EventHandler
	public void button(PlayerMoveEvent e) {
		if (!e.getTo().getWorld().getName().equalsIgnoreCase("spawnc")) {
			return;
		}
		/*if (!(e.getTo().getX() > -3 && e.getTo().getX() < 2 && e.getTo().getZ() > 2 && e.getTo().getZ() < 8 && e.getTo().getY() < 107)) {
			return;
		}
		e.setCancelled(true);
		int x = (int) ((System.currentTimeMillis() % 10000) - 5000);
		int z = (int) ((System.currentTimeMillis() % 10000) - 5000);
		int y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);
		Location l = new Location(Bukkit.getWorld("world"), x, y, z);
//		int i = 0;
//		while (l.getY() < 50 && i++ < 10) {
//			x = (new Random().nextInt() % 10000) / 2;
//			z = (new Random().nextInt() % 10000) / 2;
//			l.setX(x);
//			l.setZ(z);
//			l.setY(Bukkit.getWorld("world").getHighestBlockYAt(x, z));
//		}
		e.getPlayer().teleport(l);*/
		//e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.1F, 1);
	}

}
