package com.github.cPajor.pcore.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import com.github.cPajor.pcore.commands.gracz.SpawnCommand;
import com.github.cPajor.pcore.data.man.UserMan;

public class PackListener implements Listener {
	
	@EventHandler
	public void pack(PlayerResourcePackStatusEvent e) {
		if (e.getStatus() == Status.SUCCESSFULLY_LOADED) {
			e.getPlayer().teleport(UserMan.getUser(e.getPlayer().getName()).joinLoc);
			e.getPlayer().setInvisible(false);
		}
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		UserMan.getUser(e.getPlayer().getName()).joinLoc = e.getPlayer().getLocation();
		e.getPlayer().teleport(SpawnCommand.getSpawn());
		e.getPlayer().setInvisible(true);
	}

}
