package com.github.cPajor.pcore.data.gateway;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.DBClient;
import com.github.cPajor.pcore.data.util.LocationUtil;
import com.github.cPajor.pcore.data.util.RandomUtil;

public class GatewayListeners {
	public static List<Gateway> portals = new ArrayList<Gateway>();
	
	public static void init() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Gateway g : portals) {
					g.fromPos.getWorld().spawnParticle(Particle.SONIC_BOOM, g.fromPos, 1);
					g.fromPos.getWorld().spawnParticle(g.flag1, g.fromPos, RandomUtil.getRandInt(2, 5));
					//for (Entity e : g.fromPos.get(1, 1, 1)) {
					//	e.teleport(g.toPos);
					//}
				}
			}
		}.runTaskTimer(CReg.getPlugin(), 15, 15);
		int i = 0;
		while (true) {
			if (DBClient.get("portal.l1" + i).equalsIgnoreCase("null")) break;
			Location l1 = LocationUtil.LocationFromString(DBClient.get("portal.l1" + i));
			Location l2 = LocationUtil.LocationFromString(DBClient.get("portal.l2" + i));
			Particle lf1 = Particle.valueOf(DBClient.get("portal.lf1" + i));
			portals.add(new Gateway(l1, l2, lf1)); 
			i++;
		}
	}
	
	public static void save() {
		int i = 0;
		for (i = 0; i < portals.size(); i++) {
			Gateway g = portals.get(i);
			DBClient.set("portal.l1" + i, LocationUtil.LocationToString(g.fromPos));
			DBClient.set("portal.l2" + i, LocationUtil.LocationToString(g.toPos));
			DBClient.set("portal.lf1" + i, g.flag1.toString());
		}
		i++;
		DBClient.set("portal.l1" + i, "null");
		DBClient.set("portal.l2" + i, "null");
		DBClient.set("portal.lf1" + i, "null");	
	}

}
