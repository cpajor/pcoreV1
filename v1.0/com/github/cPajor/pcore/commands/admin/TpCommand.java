package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;

import com.github.cPajor.pcore.data.util.Colors;

public class TpCommand extends ACmd {

	public TpCommand() {
		super("tp", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		switch (args.length) {
		case 1:
			if (!(sender instanceof Player)) {
				return true;
			}
			Player p = (Player) sender;
			if (Bukkit.getPlayer(args[0]) == null) {
				p.sendMessage(Colors.fix("&p &7Podany gracz jest offline!"));
				return true;
			}
			p.teleport(Bukkit.getPlayer(args[0]));
			return true;
		case 2:
			Player o1 = Bukkit.getPlayer(args[0]);
			Player o2 = Bukkit.getPlayer(args[1]);
			if (o1 != null || o2 != null) {
				o1.teleport(o2);
			}				
			return true;
		case 3:
			if (!(sender instanceof Player)) {
				return true;
			}
			Player p1 = (Player) sender;
			int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            p1.teleport(new Location(p1.getWorld(), x, y, z, p1.getLocation().getPitch(), p1.getLocation().getYaw()));
            return true;
		case 4:
			Player p2 = Bukkit.getPlayer(args[3]);
			int x1 = Integer.parseInt(args[0]);
            int y1 = Integer.parseInt(args[1]);
            int z1 = Integer.parseInt(args[2]);
            if (p2 != null) {
            	p2.teleport(new Location(p2.getWorld(), x1, y1, z1, p2.getLocation().getPitch(), p2.getLocation().getYaw()));
            }
            return true;
		default:
			Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/tp [gracz] | [gracz] do [gracz] | [x/y/z] | [x/y/z] [gracz]"));
			return true;
		}
	}

}
