package com.github.cPajor.pcore.data.bazary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.DBClient;
import com.github.cPajor.pcore.data.util.Serializer;

public class Rynek {
	public static List<Rynek> Ryneks = new ArrayList<Rynek>();
	public static int totalBI = 0;
	//private static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create(); 
	
	public static Rynek getRynek(String name) {
		for (Rynek b : Ryneks) {
			if (b.name.equalsIgnoreCase(name)) {
				return b;
			}
		}
		if (!DBClient.get("Rynek." + name + ".bl").equalsIgnoreCase("null") && DBClient.get("Rynek." + name + ".bl").length() > 1) {
			Rynek b = new Rynek(name);
			int[] ll = fromList(DBClient.get("Rynek." + name + ".bl"));
			for (int i : ll) {
				if (DBClient.get("Rynek." + name + ".b" + i).equalsIgnoreCase("null")) continue;
				try {
					ItemStack is = Serializer.itemStackArrayFromBase64(DBClient.get("Rynek." + name + ".b" + i))[0];
					RynekItem bb = new RynekItem(Integer.parseInt(DBClient.get("Rynek." + name + ".p" + i)), is, totalBI, name, Boolean.parseBoolean(DBClient.get("Rynek." + name + ".ex" + i)), Long.parseLong(DBClient.get("Rynek." + name + ".time" + i)));
					totalBI++; 
					b.items.add(bb);
					i++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Ryneks.add(b);
			return b;
		}
		Rynek b = new Rynek(name);
		Ryneks.add(b);
		return b;
	}
	
	public String name;
	public List<RynekItem> items;
	
	public RynekItem getByOId(int oid) {
		for (RynekItem i : items) {
			if (i.getId() == oid) return i;
		}
		return null;
	}
	
	public Rynek(String name) {
		this.name = name.toLowerCase();
		this.items = new ArrayList<RynekItem>();
	}
	
	public static String intList(int[] nt) {
		String s = ""; 
		for (int i : nt) {
			s += (i + ":");
		}
		return s;
	}

	public static int[] intListF(int l) {
		int[] s = new int[l]; 
		for (int i = 0; i < s.length; i++) {
			s[i] = i;
		}
		return s;
	}
	
	public static int[] fromList(String s) {
		String[] a = s.split(":");
		int[] ll = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			ll[i] = Integer.parseInt(a[i]);
		}
		return ll;
	}
	
	public boolean remove(int i) {
		DBClient.set("Rynek." + this.name + ".b" + i, "null");
		RynekItem ilf = null;
		for (RynekItem it : items) {
			if (it.getId() == i) ilf = it;
		}
		if (ilf != null) {
			items.remove(ilf);
			return true;
		} else {
			return false;
		}
	}
	
	public RynekItem addBI(ItemStack is, int am) {
		RynekItem i = new RynekItem(am, is, totalBI, name, false, System.currentTimeMillis() + 17280000);
		totalBI++;
		items.add(i);
		return i;
	}
	
	public static List<String> known = new ArrayList<String>();
	
	public static void loadAll() {
		File v = new File(CReg.getPlugin().getDataFolder(), "Rynek.bin");
		if (v.exists()) {
			try {
				known = Files.readAllLines(v.toPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (String s : known) {
					getRynek(s);
				}
			}
		}.runTaskAsynchronously(CReg.getPlugin()); 
	}
	
	public static void saveAll() {
		File v = new File(CReg.getPlugin().getDataFolder(), "Rynek.bin");
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
			for (Rynek r : Ryneks) {
				fw.write(r.name + "\n");
			}
			fw.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		for (int i = 0; i < items.size(); i++) {
			DBClient.set("Rynek." + this.name + ".b" + i, Serializer.itemStackArrayToBase64(new ItemStack[] {  items.get(i).is }));
			DBClient.set("Rynek." + this.name + ".p" + i, "" + items.get(i).price);
			DBClient.set("Rynek." + this.name + ".ex" + i, "" + items.get(i).extra);
			DBClient.set("Rynek." + this.name + ".time" + i, "" + items.get(i).time);
		}
		DBClient.set("Rynek." + name + ".bl", intList(intListF(items.size())));
	} 
}
