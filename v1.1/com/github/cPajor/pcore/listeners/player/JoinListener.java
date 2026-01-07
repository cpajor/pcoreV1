package com.github.cPajor.pcore.listeners.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.cPajor.pcore.data.man.TUserMan;
import com.github.cPajor.pcore.data.man.UserMan;

public class JoinListener implements Listener {
	public static Map<String, Location> backs = new HashMap<String, Location>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage((String)null);
		//e.getPlayer().setScoreboard(UserMan.getUser(e.getPlayer().getName());//.initSB());
		TUserMan.getUser(e.getPlayer().getName()).getPoints();
		backs.put(e.getPlayer().getName(), e.getPlayer().getLocation());
		/*if (e.getPlayer().getName().equalsIgnoreCase("cPajor") && "cPajor".equalsIgnoreCase(e.getPlayer().getName())) {
			e.getPlayer().setPlayerListName("SlimSudol");
			e.getPlayer().setDisplayName("SlimSudol");
			e.getPlayer().setCustomName("SlimSudol");
		}*/
	}
	
	@EventHandler
	public void uqdgf(PlayerQuitEvent e) {
		if (backs.containsKey(e.getPlayer().getName())) {
			backs.remove(e.getPlayer().getName()); 
		}
	}
	
	@EventHandler
	public void tfgdbgsfasdp(PlayerTeleportEvent e) {
		backs.replace(e.getPlayer().getName(), e.getFrom());
	}

}
