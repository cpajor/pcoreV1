package com.github.cPajor.pcore.pquake;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class PQuakeReg {

	public static void init(Plugin p) {
		Bukkit.getPluginManager().registerEvents(new Listener() {
			
			@EventHandler
			public void qmove1(PlayerMoveEvent e) {
				
			}
			
		}, p);
	}

}
