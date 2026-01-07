package com.github.cPajor.pcore.listeners.cheats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.cPajor.pcore.CReg;

public class ReachListnener implements Listener {
	public List<Player> gracze = new ArrayList<Player>();
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		if (gracze.contains(e.getDamager())) {
			return;
		}
		if (!(e.getEntity().getLocation().distance(e.getDamager().getLocation()) < 4.8D)) {
			gracze.add((Player)e.getDamager());
			Bukkit.getScheduler().runTaskTimer(CReg.getPlugin(), new Runnable() {
				@Override
				public void run() {
					gracze.remove((Player)e.getDamager());
					return;
				}
			}, 100L, 100L);
		}
	}

}
