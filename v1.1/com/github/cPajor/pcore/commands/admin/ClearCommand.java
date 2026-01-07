package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.util.Colors;

public class ClearCommand extends ACmd {
	
	public ClearCommand() {
		super("clear", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			 ((Player) sender).getInventory().clear();
			 sender.sendMessage(Colors.fix("&p &7Wyczyszczono"));
		} else {
			Player p = Bukkit.getPlayer(args[0]); 
			if (p == null) {
				sender.sendMessage(Colors.fix("&p &7Podany gracz jest offline!"));
				return true;
			}
			p.getInventory().clear();
			 sender.sendMessage(Colors.fix("&p &7Wyczyszczono"));
		}
		return true;
	}

}
