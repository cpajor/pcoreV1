package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.bazary.CRynekListeners;
import com.github.cPajor.pcore.data.bazary.Rynek;
import com.github.cPajor.pcore.data.bazary.RynekItem;
import com.github.cPajor.pcore.data.man.UserMan;
import com.github.cPajor.pcore.data.util.Colors;

public class RynekCommand extends ACmd {

	public RynekCommand() {
		super("rynek");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("wystaw") || args[0].equalsIgnoreCase("sell")) {
				int pp = 0;
				try {
					pp = Integer.parseInt(args[1]);
				} catch (Exception e) {
					sender.sendMessage(Colors.fix("&p &7To nie liczba!"));
					return true;
				}
				Player p = (Player) sender;
				if (p.getInventory().getItemInMainHand() == null || p.getInventory().getItemInMainHand().getType() == Material.AIR) {
					sender.sendMessage(Colors.fix("&p &7Ten towar nie moze zostac sprzedany"));
					return true;
				}
				Rynek b = Rynek.getRynek(sender.getName());
				b.addBI(p.getInventory().getItemInMainHand(), pp);
				b.save();
				p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				sender.sendMessage(Colors.fix("&p &7Pomyslnie wystawiono towar!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("ex")) {
				int pp = 0;
				try {
					pp = Integer.parseInt(args[1]);
				} catch (Exception e) {
					sender.sendMessage(Colors.fix("&p &7To nie liczba!"));
					return true;
				}
				for (Rynek r : Rynek.Ryneks) {
					RynekItem i = r.getByOId(pp);
					if (i != null) {
						i.extra = !i.extra;
						sender.sendMessage(Colors.fix("&p &7Oferta OID:" + pp + " extraflag:" + i.extra));
						return true;
					}
				}
				sender.sendMessage(Colors.fix("&p &7Nie ma takiej oferty!"));
			}
		}
		if (args.length == 3) {
			if (sender.hasPermission("mv.admin")) {
				if (args[0].equalsIgnoreCase("mset")) {
					int pp = 0;
					try {
						pp = Integer.parseInt(args[2]);
					} catch (Exception e) {
						sender.sendMessage(Colors.fix("&p &7To nie liczba!"));
						return true;
					}
					UserMan.getUser(args[1]).setMoney(pp);
					sender.sendMessage(Colors.fix("&p &7" + args[1] + " $" + pp));
					return true;
				}
				if (args[0].equalsIgnoreCase("mget")) {
					sender.sendMessage(Colors.fix("&p &7" + args[1] + " $" + UserMan.getUser(args[1]).getMoney()));
					return true;
				}
			}
		}
		if (args.length == 0) {
			CRynekListeners.guiRynekMain((Player)sender, 0);
		}
		return true;
	}
}
