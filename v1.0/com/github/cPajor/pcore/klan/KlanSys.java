package com.github.cPajor.pcore.klan;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.DBClient;

public class KlanSys {
	
	public static void load() {
		File v = new File(CReg.getPlugin().getDataFolder(), "klanSys.bin");
		if (v.exists()) {
			try {
				final List<String> known = Files.readAllLines(v.toPath());
				new BukkitRunnable() {
					@Override
					public void run() {
						for (String s : known) {
							getklanByOwner(s);
							getklanByOwner(s);
						}
					}
				}.runTaskAsynchronously(CReg.getPlugin()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public static List<Klan> cached = new ArrayList<>();
	
	public static void save() {
		File v = new File(CReg.getPlugin().getDataFolder(), "klanSys.bin");
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
			for (Klan r : cached) {
				fw.write(r.getOwner() + "\n");
			}
			fw.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Klan getKlanByMember(String member) {
		for (Klan d : cached) {
			if (d.isMember(member)) return d;
		}
		return null;
	}
	
	public static Klan getklanByOwner(String ownn) {
		for (Klan d : cached) {
			if (d.getOwner().equalsIgnoreCase(ownn)) return d;
		}
		String own = ownn.toLowerCase();
		int i = 0;
		try {
			i = Integer.parseInt(DBClient.get("klan." + own + ".size"));
		} catch (Exception e) {}
		if (i > 0) {
			Klan d = new Klan(own);
			int j = 0;
			while (true) {
				String v = DBClient.get("klan." + own + ".m" + j);
				if (v.equalsIgnoreCase("null")) break;
				d.members.add(v);
				j++;
			}
			cached.add(d);
		}
		return null;
	}

}
