package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.man.TUserMan;
import com.github.cPajor.pcore.data.man.UserMan;
import com.github.cPajor.pcore.data.util.Colors;

public class EcoCommand extends ACmd {
	
	public EcoCommand() {
		super("eco", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length > 1)
		if (args[0].equalsIgnoreCase("time")) {
			if (args.length < 3) {
				sender.sendMessage(Colors.fix("&p &7Wpisz&4 /eco"));
				return true; 
			}
			if (args[1].equalsIgnoreCase("get")) {
				sender.sendMessage(Colors.fix("&p &7Gracz " + args[2] + " posiada " + TUserMan.getUser(args[2]).getCachedPoints() + " pkt czasu"));
			}
			int i = 0;
			try {
				i = Integer.parseInt(args[3]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i < 1) {
				sender.sendMessage(Colors.fix("&p &7Zla liczba!"));
				return true;
			}
			if (args[1].equalsIgnoreCase("set")) {
				TUserMan.getUser(args[2]).setPoints(i);
				sender.sendMessage(Colors.fix("&p &7Gracz " + args[2] + " posiada " + i + " pkt czasu"));
			}
		}
		if (args[0].equalsIgnoreCase("dolce")) {
			if (args[1].equalsIgnoreCase("set")) {
				int pp = 0;
				try {
					pp = Integer.parseInt(args[2]);
				} catch (Exception e) {
					sender.sendMessage(Colors.fix("&p &7To nie liczba!"));
					return true;
				}
				UserMan.getUser(args[1]).setMoney(pp);
				sender.sendMessage(Colors.fix("&p &7Gracz &4" + args[1] + " &7posiada: &6" + pp + " &7dolcow"));
				return true;
			}
			if (args[0].equalsIgnoreCase("get")) {
				sender.sendMessage(Colors.fix("&p &7Gracz &4" + args[1] + " &7posiada: &6" + UserMan.getUser(args[1]).getMoney() + " &7dolcow"));
				return true;
			}
		}
		
		sender.sendMessage(Colors.fix("&p &7/eco [time/dolce] [set/get] [nick] [int]"));
		return true;
	}

}
