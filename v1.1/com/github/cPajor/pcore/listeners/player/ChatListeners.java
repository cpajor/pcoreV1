package com.github.cPajor.pcore.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.cPajor.pcore.commands.admin.ChatCommand;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class ChatListeners implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		//e.setCancelled(true);
		/*if (e.getPlayer().getName().equalsIgnoreCase("cPajor") && "cPajor".equalsIgnoreCase(e.getPlayer().getName())) {
			Chat.sendToAllP("&7(&4@&cAdmin&7) &4SlimSudol&4: &c" + e.getMessage());
			return;
		}*/
		if (!ChatCommand.chat) {
			Title.sendTitle(e.getPlayer(), " ", Colors.fix("&p &7Chat jest &4wylaczony&7!"));
			e.setCancelled(true);
			return;
		}
		/*if (e.getMessage().contains(".pl") || e.getMessage().contains(".eu") || e.getMessage().contains(".com")) {
			Title.sendTitle(e.getPlayer(), " ", Colors.fix("&p &7Chat zablokowal Twoja wiadomosc&7!"));
			return;
		}
		if (e.getPlayer().hasPermission("pajor.hadmin")) {
			Chat.sendToAllP("&7(&4@&cAdmin&7) &4" + e.getPlayer().getName() + "&4: &c" + e.getMessage());
			return;
		}
		if (e.getPlayer().hasPermission("pajor.admin")) {
			Chat.sendToAllP("&7(&cAdmin&7) &c" + e.getPlayer().getName() + "&4: &c" + e.getMessage());
			return;
		}
		if (e.getPlayer().hasPermission("pajor.mod")) {
			Chat.sendToAllP("&7(&2Mod&7) &c" + e.getPlayer().getName() + "&4: &c" + e.getMessage());
			return;
		}
		if (e.getPlayer().hasPermission("pajor.vmod")) {
			Chat.sendToAllP("&7(&ev&2Mod&7) &2" + e.getPlayer().getName() + "&4: &2" + e.getMessage());
			return;
		}
		if (e.getPlayer().hasPermission("pajor.helper")) {
			Chat.sendToAllP("&7(&bHelper&7) &2" + e.getPlayer().getName() + "&4: &f" + e.getMessage());
			return;
		}
		if (e.getPlayer().hasPermission("pajor.mediaplus")) {
			Chat.sendToAll(Colors.fix("&7(&3Media&e+&7)  &3&l" + e.getPlayer().getName() + "&4: &7") + e.getMessage(), "gracze");
			return;
		}
		if (e.getPlayer().hasPermission("pajor.media")) {
			Chat.sendToAll(Colors.fix("&7(&3Media&7)  &3" + e.getPlayer().getName() + "&4: &7") + e.getMessage(), "gracze");
			return;
		} 
		if (e.getPlayer().hasPermission("pajor.svip")) {
			Chat.sendToAll(Colors.fix("&7(&eS&6VIP&7)   &7" + e.getPlayer().getName() + "&4: &7") + e.getMessage(), "gracze");
			return;
		}
		if (e.getPlayer().hasPermission("pajor.vip")) {
			Chat.sendToAll(Colors.fix("&7(&6VIP&7)  &7" + e.getPlayer().getName() + "&4: &7") + e.getMessage(), "gracze");
			return;
		}	
		Chat.sendToAll(Colors.fix("&7(&7Gracz&7)  &7" + e.getPlayer().getName() + "&4: &7") + e.getMessage(), "gracze");*/
	}
	
}
