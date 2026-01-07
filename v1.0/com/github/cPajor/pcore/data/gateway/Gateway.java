package com.github.cPajor.pcore.data.gateway;

import org.bukkit.Location;
import org.bukkit.Particle;

public class Gateway {
	public Location fromPos;
	public Location toPos;
	public Particle flag1;
	
	public Gateway(Location l1, Location l2) {
		fromPos = l1;
		toPos = l2;
		flag1 = Particle.SOUL;
	}
	
	public Gateway(Location l1, Location l2, Particle p) {
		fromPos = l1;
		toPos = l2;
		flag1 = p;
	}

}
