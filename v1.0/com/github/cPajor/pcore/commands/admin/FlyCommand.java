package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;

import com.github.cPajor.pcore.data.util.Colors;

public class FlyCommand extends ACmd {

	public FlyCommand() {
		super("fly", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (p.getAllowFlight()) {
			sender.sendMessage(Colors.fix("&p &7Wylaczyles latanie!"));
			p.setAllowFlight(false);
		} else {
			sender.sendMessage(Colors.fix("&p &7Wlaczyles latanie!"));
			p.setAllowFlight(true);
		}
		return true;
	}

}
