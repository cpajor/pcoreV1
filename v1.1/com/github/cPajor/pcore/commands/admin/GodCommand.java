package com.github.cPajor.pcore.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.github.cPajor.pcore.core.cmd.ACmd;

import com.github.cPajor.pcore.data.util.Colors;

public class GodCommand extends ACmd implements Listener {
	private List<String> god = new ArrayList<String>();

	public GodCommand() {
		super("god", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (god.contains(sender.getName())) {
			sender.sendMessage(Colors.fix("&p &7Wylaczyles god'a!"));
			god.remove(sender.getName());
		} else {
			sender.sendMessage(Colors.fix("&p &7Wlaczyles god'a!"));
			god.add(sender.getName());
		}
		return true;
	}
	
	@EventHandler
	public void onGod(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return; 
		}
		if (god.contains(e.getEntity().getName())) {
			e.setDamage(0);
			e.setCancelled(true);
		}
	}

}
