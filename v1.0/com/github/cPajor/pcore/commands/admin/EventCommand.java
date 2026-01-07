package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.util.Chat;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.data.util.RandomUtil;
import com.github.cPajor.pcore.listeners.player.MeteorytListener;

public class EventCommand extends ACmd {

	public EventCommand() {
		super("event", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("meteoryt")) {
				Location l = Bukkit.getWorld("farland").getHighestBlockAt(RandomUtil.getRandInt(-3000, 3000), RandomUtil.getRandInt(-3000, 3000)).getLocation().setDirection(new Vector(0, -1, 0));
				l.setY(255);
				if (l.getX() < 500 && l.getX() > -500 && l.getZ() < 500 && l.getZ() > -500) {
					l.add(500, 0, 500);
				}
				Chat.sendToAllP("&p &4Meteoryt &7spadl na kordach:\n&4X&7: " + l.getX() + "\n&4Z&7: " + l.getZ());
				MeteorytListener.meteoryt(l);
				return true;
			}
			if (args[0].equalsIgnoreCase("pmeteoryt")) {
				MeteorytListener.meteoryt(((Player) sender).getLocation().add(0, -5, 0));
				return true;
			}
			if (args[0].equalsIgnoreCase("freeze")) {
				Player p = (Player) sender;
				ItemStack is = p.getInventory().getItemInMainHand();
				is.addEnchantment(Enchantment.FROST_WALKER, 2);
				p.getInventory().setItemInMainHand(is);
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("tp")) {
				if (args[1].equalsIgnoreCase("world")) {
					Player p = (Player) sender;
					p.teleport(new Location(Bukkit.getWorld("world"), 0, 150, 0));
				}
				if (args[1].equalsIgnoreCase("rtp2")) {
					Player p = (Player) sender;
					int x = (int) ((System.currentTimeMillis() % 20000) - 10000);
					int z = (int) ((System.currentTimeMillis() % 20000) - 10000);
					int y = Bukkit.getWorld("farlands").getHighestBlockYAt(x, z);
					Location l = new Location(Bukkit.getWorld("farlands"), x, y, z);
					p.teleport(l);
				}
				/*if (args[1].equalsIgnoreCase("nicasil")) {
					Player p = (Player) sender;
					p.teleport(new Location(Bukkit.getWorld("nicasil"), 0, 150, 0));
				}
				if (args[1].equalsIgnoreCase("jg")) {
					Player p = (Player) sender;
					p.teleport(new Location(Bukkit.getWorld("jungle"), 0, 150, 0));
				}*/
				return true;
			}
		}
		sender.sendMessage(Colors.fix("&p &4Uzyj: &7/event [arg]"));
		return true;
	}

}
