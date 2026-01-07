package com.github.cPajor.pcore.commands.gracz;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.commands.admin.SlavSpyCommand;
import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;

import com.github.cPajor.pcore.data.user.ChatUser;
import com.github.cPajor.pcore.data.util.Colors;

public class MsgCommand extends ACmd {

	public MsgCommand() {
		super("msg");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length < 2) {
			Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/msg [gracz] [wiadomosc]"));
			return true;
		} 
		if (Bukkit.getPlayer(args[0]) == null) { 
			sender.sendMessage(Colors.fix("&p &7Gracz jest offline!"));
			return true;
		}
		String msg = StringUtils.join((Object[]) args, " ", 1, args.length);
		sender.sendMessage(Colors.fix("&p &4[&7Ja &4-> &7" + args[0] + "&4] &7" + msg));
		for (String s : SlavSpyCommand.slav) {
			Player p  =Bukkit.getPlayer(s);
			if (p != null) {
				p.sendMessage(Colors.fix("&p &4[&7" + sender.getName() + " &4-> &7" + args[0] + "&4] &7" + msg));
			}
		}
		if (!ChatUser.getUser(args[0]).get("priv")) {
			return true;
		}
		Bukkit.getPlayer(args[0]).sendMessage(Colors.fix("&p &4[&7" + sender.getName() + " &4-> &7Ja&4] &7" + msg));
		return true;
	}

}
