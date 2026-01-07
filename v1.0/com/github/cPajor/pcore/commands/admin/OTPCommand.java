package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.util.Colors;

public class OTPCommand extends ACmd {
	
	public OTPCommand() {
		super("otp", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
			if (o == null) {
				sender.sendMessage(Colors.fix("&p &7Tego gracza nigdy nie bylo na serwerze!"));
				return true;
			}
			Player p = (Player) sender;
			p.teleport(o.getPlayer().getLocation());
			return true;
		}
		sender.sendMessage(Colors.fix("&p &4/otp [nick gracza]"));
		return true;
	}

}
