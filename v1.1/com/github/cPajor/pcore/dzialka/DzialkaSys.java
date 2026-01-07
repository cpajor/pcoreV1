package com.github.cPajor.pcore.dzialka;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.DBClient;

public class DzialkaSys extends BukkitRunnable {
	
	public static void load() {
		File v = new File(CReg.getPlugin().getDataFolder(), "dzialkaSys.bin");
		if (v.exists()) {
			try {
				final List<String> known = Files.readAllLines(v.toPath());
				new BukkitRunnable() {
					@Override
					public void run() {
						for (String s : known) {
							getDzialkaByOwner(s);
							getDzialkaByOwner(s);
						}
					}
				}.runTaskAsynchronously(CReg.getPlugin()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public static boolean canCreateDzialka1(Location l) {
		if (!l.getWorld().getName().equalsIgnoreCase("world")) return false;
		for (Dzialka d : cachedDzialki) {
			if (d.isCollide(l)) return false;
		}
		return true;
	}
	
	public static List<Dzialka> cachedDzialki = new ArrayList<>();
	
	public static void save() {
		File v = new File(CReg.getPlugin().getDataFolder(), "dzialkaSys.bin");
		if (v.exists()) {
			v.delete();
		}
		try {
			v.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(v);
			for (Dzialka r : cachedDzialki) {
				fw.write(r.getOwner() + "\n");
			}
			fw.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Dzialka getDzialkaByOwner(String ownn) {
		for (Dzialka d : cachedDzialki) {
			if (d.getOwner().equalsIgnoreCase(ownn)) return d;
		}
		String own = ownn.toLowerCase();
		int i = 0;
		try {
			i = Integer.parseInt(DBClient.get("dzialka." + own + ".size"));
		} catch (Exception e) {}
		if (i > 0) {
			Dzialka d = new Dzialka(own);
			d.x = Integer.parseInt(DBClient.get("dzialka." + own + ".x"));
			d.z = Integer.parseInt(DBClient.get("dzialka." + own + ".z"));
			d.size = i;
			d.bx = Integer.parseInt(DBClient.get("dzialka." + own + ".bx"));
			d.by = Integer.parseInt(DBClient.get("dzialka." + own + ".by"));
			d.bz = Integer.parseInt(DBClient.get("dzialka." + own + ".bz"));
			d.passive = Boolean.parseBoolean(DBClient.get("dzialka." + own + ".pvp"));
			int j = 0;
			while (true) {
				String v = DBClient.get("dzialka." + own + ".m" + j);
				if (v.equalsIgnoreCase("null")) break;
				d.members.add(v);
				j++;
			}
			cachedDzialki.add(d);
		}
		return null;
	}
	
	public static Dzialka getDzialkaByLoc(Location l) {
		for (Dzialka d : cachedDzialki) {
			if (d.isOnDzialka(l)) return d;
		}
		return null;
	}
	
	public static void rayDzialka(Location l1, Dzialka d) {
		for (int x = l1.getBlockX(); x < d.x + d.size; x++) {
			Location l = l1.clone();
			l.setX(x);
			l1.getWorld().spawnParticle(Particle.WAX_ON, l, 1);
		}
		//
		for (int x = l1.getBlockZ(); x < d.z + d.size; x++) {
			Location l = l1.clone();
			l.setZ(x);
			l1.getWorld().spawnParticle(Particle.WAX_ON, l, 1);
		}
		Location l = l1.clone();
		l.setX(d.x + d.size);
		l1.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 1, Material.MANGROVE_TRAPDOOR.createBlockData());
		l = l1.clone();
		l.setZ(d.z + d.size);
		l1.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 1, Material.MANGROVE_TRAPDOOR.createBlockData());
	}
	
	public static void rayDzialka2(Dzialka d, double y) {
		for (int x = d.x - d.size; x < d.x + d.size; x++) {
			Location l = new Location(Bukkit.getWorld("world"), x, y, d.z + d.size);
			l.getWorld().spawnParticle(Particle.BLOCK_DUST, l, 1, Material.REDSTONE.createBlockData());
		}
		//
		for (int x = d.z - d.size; x < d.z + d.size; x++) {
			Location l = new Location(Bukkit.getWorld("world"), d.x + d.size, y, x);
			l.getWorld().spawnParticle(Particle.BLOCK_DUST, l, 1, Material.REDSTONE.createBlockData());
		}
		//
		for (int x = d.x - d.size; x < d.x + d.size; x++) {
			Location l = new Location(Bukkit.getWorld("world"), x, y, d.z - d.size);
			l.getWorld().spawnParticle(Particle.BLOCK_DUST, l, 1, Material.REDSTONE.createBlockData());
		}
		//
		for (int x = d.z - d.size; x < d.z + d.size; x++) {
			Location l = new Location(Bukkit.getWorld("world"), d.x - d.size, y, x);
			l.getWorld().spawnParticle(Particle.BLOCK_DUST, l, 1, Material.REDSTONE.createBlockData());
		}
	}
	
	public static List<String> wbs = new ArrayList<>();
	
	@Override
	public void run() {
		for (Player o : Bukkit.getOnlinePlayers()) {
			Dzialka d = getDzialkaByLoc(o.getLocation());
			if (d != null) {
				//rayDzialka2(d, o.getLocation().getY() + 0.5);
				boolean m = d.isMember(o.getName());
				if (!m) {
					o.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE - 1, 3));
				}
				
			} else {
				o.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			}
		}
		
	}

}
