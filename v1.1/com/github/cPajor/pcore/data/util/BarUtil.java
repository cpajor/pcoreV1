package com.github.cPajor.pcore.data.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BarUtil {
	private static Map<Player, BossBar> bars = new HashMap<Player, BossBar>();

	public static void setMessage(Player p, String s, float f) {
		if (!bars.containsKey(p)) {
			BossBar b = Bukkit.createBossBar(s, BarColor.RED, BarStyle.SOLID);
			b.addPlayer(p);
			b.setProgress(f);
			bars.put(p, b);
		} else {
			if (bars.get(p) != null) {
				bars.get(p).removeAll();
				bars.get(p).addPlayer(p);
				bars.get(p).setTitle(s);
				bars.get(p).setProgress(f);
			} else {
				bars.remove(p);
				setMessage(p, s, f);
			}
		}
	}
	
	public static void removeBar(Player p) {
		if (bars.containsKey(p)) {
			bars.get(p).setVisible(false);
			bars.remove(p);
		}
	}
	
	public static void removeAll() {
		for (BossBar b : bars.values()) {
			b.removeAll();
			b.setVisible(false);
		}
		bars.clear();
	}
	
}
