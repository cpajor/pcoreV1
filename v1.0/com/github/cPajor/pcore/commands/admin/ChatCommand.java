package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;

import com.github.cPajor.pcore.data.util.Colors;

public class ChatCommand extends ACmd {
	public static boolean chat = true;

	public ChatCommand() {
		super("chat", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on")) {
				sender.sendMessage(Colors.fix("&p &7Ustawiles chat na &4ON"));
				ChatCommand.chat = true;
				return true;
			}
			if (args[0].equalsIgnoreCase("off")) {
				sender.sendMessage(Colors.fix("&p &7Ustawiles chat na &4OFF"));
				ChatCommand.chat = false;
				return true;
			}
			if (args[0].equalsIgnoreCase("cc")) {
				for (Player o : Bukkit.getOnlinePlayers()) {
					for (int i = 0; i < 50; i++) {
						o.sendMessage(Colors.fix("&4 "));
					}
				}
				return true;
			} 
		}
		Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/chat [on|off|cc]"));
		return true;
	}

}
