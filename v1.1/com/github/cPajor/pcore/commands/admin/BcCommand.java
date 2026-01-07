package com.github.cPajor.pcore.commands.admin;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class BcCommand extends ACmd {

	public BcCommand() {
		super("bc", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length > 0) {
			String msg = StringUtils.join(args, " ", 0, args.length); 
			//Bukkit.broadcastMessage(Colors.fix("&p &c&l" + msg));
			for (Player o : Bukkit.getOnlinePlayers()) {
				o.sendMessage(Colors.fix("&p &c&l" + msg));
				Title.sendTitle(o, " ", "&c&l" + msg);
			}
		} else {
			Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/bc [args]"));
		}
		return true;
	}

}
