package com.github.cPajor.pcore.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.util.Colors;

public class CNpcCommand extends ACmd {
	
	public CNpcCommand() {
		super("cnpc", true);
	}
	
	public static List<String> toggers = new ArrayList<>();
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("ls") || args[0].equalsIgnoreCase("type")) {
				sender.sendMessage(Colors.fix("&p &4NPC TYPES: "));
				sender.sendMessage(Colors.fix("&p &7WARRIOR"));
				sender.sendMessage(Colors.fix("&p &7WARDING"));
				return true;
			}
			if (args[0].equalsIgnoreCase("WARDING")) {
				//FancyNPCsHook.spawn(EnumNpcType.NPC_WARD, ((Player) sender).getLocation());
				//CitizensHook.spawnWarding(((Player) sender).getLocation());
				
				return true;
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				if (toggers.contains(sender.getName())) {
					toggers.remove(sender.getName());
				} else {
					toggers.add(sender.getName());
				}
				sender.sendMessage(Colors.fix("&p &4TOGGLE: " + toggers.contains(sender.getName())));
				return true;
			}
		}
		sender.sendMessage(Colors.fix("&p &4Uzyj: &7/cnpc [TYPE]"));
		return true;
	}

}
