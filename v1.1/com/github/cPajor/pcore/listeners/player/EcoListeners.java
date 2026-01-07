package com.github.cPajor.pcore.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.cPajor.pcore.data.man.TUserMan;
import com.github.cPajor.pcore.data.user.TUser;

public class EcoListeners implements Listener {
	
	@EventHandler
	public void ff1t(PlayerJoinEvent e) {
		TUser u = TUserMan.getUser(e.getPlayer().getName());
		u.setTime(System.currentTimeMillis() + TUser.KVA);
	}

}
