package com.github.cPajor.pcore.dzialka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.DBClient;

public class Dzialka {
	private String owner;
	public int x;
	public int z;
	public int size;
	public int bx;
	public int by;
	public int bz;
	public boolean passive;
	public List<String> members = new ArrayList<String>();
	private Map<String, Map<Integer, Integer>> permCache;
	
	public static final int PANEL_BUILD = 1;
	public static final int PANEL_USE = 2;
	public static final int PANEL_LIQUID = 3;
	public static final int PANEL_KILL_ANIMAL = 4;
	public static final int PANEL_KILL_HOSTILE = 5;
	
	public Dzialka(String nme) {
		this.owner = nme.toLowerCase();
		permCache = new HashMap<String, Map<Integer, Integer>>();
	}
	
	public Dzialka(Player p) {
		this.owner = p.getName().toLowerCase();
		this.x = (int) p.getLocation().getX();
		this.z = (int) p.getLocation().getZ();
		this.setHome(p.getLocation());
		this.size = 5;
		this.passive = true;
		permCache = new HashMap<String, Map<Integer, Integer>>();
		for (int i = 0; i < 5; i++) {
			this.setPermission(p.getName(), i, 1);
		}
	}
	
	public boolean isMember(String s) {
		return members.contains(s.toLowerCase()) || (s.equalsIgnoreCase(owner) && owner.equalsIgnoreCase(s));
	}
	
	public boolean isOnDzialka(Location l) {
		return l.getX() <= x + size && l.getX() >= x - size && l.getZ() <= z + size && l.getZ() >= z - size;
	}
	
	public boolean isCollide(Location l) {
		double s = Math.sqrt(2) * 101;
		return l.distance(new Location(l.getWorld(), x, l.getY(), z)) < s;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setHome(Location l) {
		this.bx = (int) l.getX();
		this.by = (int) l.getY();
		this.bz = (int) l.getZ();
	}
	
	public Location getHome() {
		return new Location(Bukkit.getWorld("world"), bx + 0.5, by + 0.5, bz + 0.5, 0, 0);
	}
	
	public int getPermission(String member, int perm) {
		if (permCache.containsKey(member) && permCache.get(member).containsKey(perm)) {
			return permCache.get(member).get(perm);
		}
		String r = DBClient.get("dzialka." + this.owner + ".perm." + member + ".p" + perm);
		if (r != null) {
			if (!r.equalsIgnoreCase("null")) {
				int v = Integer.parseInt(r);
				if (!permCache.containsKey(member)) {
					permCache.put(member, new HashMap<Integer, Integer>());
				}
				if (permCache.get(member).containsKey(perm)) {
					permCache.get(member).replace(perm, v);
				} else {
					permCache.get(member).put(perm, v);
				}
				return v;
			}
		}
		return 0;
	}
	
	public void setPermission(String member, int perm, int newPerm) {
		if (!permCache.containsKey(member)) {
			permCache.put(member, new HashMap<Integer, Integer>());
		}
		if (permCache.get(member).containsKey(perm)) {
			permCache.get(member).replace(perm, newPerm);
		} else {
			permCache.get(member).put(perm, newPerm);
		}
		DBClient.set("dzialka." + this.owner + ".perm." + member + ".p" + perm, "" + newPerm);
	}
	
	public void delete() {
		DBClient.set("dzialka." + this.owner + ".x", "null");
		DBClient.set("dzialka." + this.owner + ".z","null");
		DBClient.set("dzialka." + this.owner + ".size", "null");
		DBClient.set("dzialka." + this.owner + ".bx", "null");
		DBClient.set("dzialka." + this.owner + ".by", "null");
		DBClient.set("dzialka." + this.owner + ".bz", "null");
		DBClient.set("dzialka." + this.owner + ".pvp", "null");
		for (int i = 0; i < 20; i++) {
			DBClient.set("dzialka." + this.owner + ".m" + i, "null");
		}
		for (String key : permCache.keySet()) {
			Map<Integer, Integer> mp = permCache.get(key);
			for (Integer i : mp.keySet()) {
				DBClient.set("dzialka." + this.owner + ".perm." + key + ".p" + i, "null");
			}
		}
	}
 
	public void save() {
		DBClient.set("dzialka." + this.owner + ".x", this.x + "");
		DBClient.set("dzialka." + this.owner + ".z", this.z + "");
		DBClient.set("dzialka." + this.owner + ".size", this.size + "");
		DBClient.set("dzialka." + this.owner + ".bx", this.bx + "");
		DBClient.set("dzialka." + this.owner + ".by", this.by + "");
		DBClient.set("dzialka." + this.owner + ".bz", this.bz + "");
		DBClient.set("dzialka." + this.owner + ".pvp", this.passive + "");
		for (int i = 0; i < members.size(); i++) {
			DBClient.set("dzialka." + this.owner + ".m" + i, members.get(i) + "");
		}
		DBClient.set("dzialka." + this.owner + ".m" + members.size(), "null");
	}
}
