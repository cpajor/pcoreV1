package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;

import com.github.cPajor.pcore.data.user.Combat;
import com.github.cPajor.pcore.data.util.Colors;

public class GmCommand extends ACmd {
	
	public GmCommand(String s) {
		super(s, true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (sender instanceof Player && args.length == 1) {
			Player p = (Player) sender;
			if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
				p.setGameMode(GameMode.SURVIVAL);
			}
			if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
				p.setGameMode(GameMode.CREATIVE);
			}
			if (args[0].equalsIgnoreCase("2")) {
				p.setGameMode(GameMode.ADVENTURE);
			}
			if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
				p.setGameMode(GameMode.SPECTATOR);
			}
			return true;
		}
		if (args.length == 2) {
			Player p = Bukkit.getPlayer(args[1]); 
			if (args[0].equalsIgnoreCase("0")) {
				p.setGameMode(GameMode.SURVIVAL);
			}
			if (args[0].equalsIgnoreCase("1")) {
				p.setGameMode(GameMode.CREATIVE);
			}
			if (args[0].equalsIgnoreCase("2")) {
				p.setGameMode(GameMode.ADVENTURE);
			}
			if (args[0].equalsIgnoreCase("3")) {
				p.setGameMode(GameMode.SPECTATOR);
			} 
			return true;
		}
		if (Combat.get(sender.getName()).getTime() != 0) Combat.get(sender.getName()).setTime(0);
		Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/gm [0/1/2/3] [gracz]"));
		return true;
	} 

}
