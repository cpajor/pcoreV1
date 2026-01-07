package com.github.cPajor.pcore.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.cPajor.pcore.core.cmd.ACmd;

import com.github.cPajor.pcore.data.util.Colors;

public class SlavSpyCommand extends ACmd {
	public static List<String> slav = new ArrayList<String>();

	public SlavSpyCommand() {
		super("slavspy", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (slav.contains(sender.getName())) {
			sender.sendMessage(Colors.fix("&p &7Wylaczyles SlavSpy!"));
			slav.remove(sender.getName());
		} else {
			sender.sendMessage(Colors.fix("&p &7Wlaczyles SlavSpy!"));
			slav.add(sender.getName());
		}
		return true;
	}

}
