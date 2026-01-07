package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;

import com.github.cPajor.pcore.data.gateway.Gateway;
import com.github.cPajor.pcore.data.gateway.GatewayListeners;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.data.util.LocationUtil;

public class GatewayCommand extends ACmd {
	
	public GatewayCommand() {
		super("gateway", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			sender.sendMessage(Colors.fix("&p &4/gateway add"));
			sender.sendMessage(Colors.fix("&p &4/gateway setloc1 [id]"));
			sender.sendMessage(Colors.fix("&p &4/gateway setloc2 [id]"));
			return true;
		}
		if (args[0].equalsIgnoreCase("add")) {
			sender.sendMessage(Colors.fix("&p &7NEWID: " + GatewayListeners.portals.size()));
			GatewayListeners.portals.add(new Gateway(p.getLocation(), p.getLocation()));
			return true;
		} 
		if (args[0].equalsIgnoreCase("list")) {
			sender.sendMessage(Colors.fix("&p &7Portale:"));
			int i = 0;
			for (Gateway g : GatewayListeners.portals) {
				sender.sendMessage(Colors.fix("&p &7ID: " + i)); i++;
				sender.sendMessage(Colors.fix("&p &7FROM: " + LocationUtil.LocationToString(g.fromPos)));
				sender.sendMessage(Colors.fix("&p &7TO: " + LocationUtil.LocationToString(g.toPos)));
				sender.sendMessage(Colors.fix("&p &7= = = ="));
			}
			return true;
		} 
		if (args.length != 2) {
			sender.sendMessage(Colors.fix("&p &4/gateway add"));
			sender.sendMessage(Colors.fix("&p &4/gateway setloc1 [id]"));
			sender.sendMessage(Colors.fix("&p &4/gateway setloc2 [id]"));
			return true;
		}
		if (args[0].equalsIgnoreCase("del")) {
			sender.sendMessage(Colors.fix("&p &7DELID: " + GatewayListeners.portals.size()));
			GatewayListeners.portals.remove(Integer.parseInt(args[1]));
			return true;
		} 
		if (args[0].equalsIgnoreCase("setloc1")) {
			GatewayListeners.portals.get(Integer.parseInt(args[1])).fromPos = p.getLocation();
			sender.sendMessage(Colors.fix("&p &7Ustawiono!"));
			GatewayListeners.save();
			return true;
		}
		if (args[0].equalsIgnoreCase("setloc2")) {
			GatewayListeners.portals.get(Integer.parseInt(args[1])).toPos = p.getLocation();
			sender.sendMessage(Colors.fix("&p &7Ustawiono!"));
			GatewayListeners.save();
			return true;
		}
		sender.sendMessage(Colors.fix("&p &4/gateway add"));
		sender.sendMessage(Colors.fix("&p &4/gateway setloc1 [id]"));
		sender.sendMessage(Colors.fix("&p &4/gateway setloc2 [id]"));
		return true;
	}

}
