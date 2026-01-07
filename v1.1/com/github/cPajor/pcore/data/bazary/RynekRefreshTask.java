package com.github.cPajor.pcore.data.bazary;

import org.bukkit.scheduler.BukkitRunnable;

public class RynekRefreshTask extends BukkitRunnable {
	
	public void run() { 
		Rynek.saveAll();
		for (Rynek r : Rynek.Ryneks) {
			r.save(); 
		}
		Rynek.Ryneks.clear();
		Rynek.loadAll();
	}
}
