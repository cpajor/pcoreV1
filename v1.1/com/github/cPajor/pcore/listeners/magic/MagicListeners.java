package com.github.cPajor.pcore.listeners.magic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class MagicListeners implements Listener {
	
	public static void spell(Player p) {
		
	}
	
	@EventHandler
	public void handler(PlayerSwapHandItemsEvent e) {
		if (e.getPlayer().isSneaking()) {
			e.setCancelled(true);
			spell(e.getPlayer());
		}
	}

}
