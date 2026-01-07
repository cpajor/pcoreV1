package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class InvSeeCommand extends ACmd implements Listener {

	public InvSeeCommand() {
		super("invsee", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length != 1) {
			Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj &7/invsee [gracz]"));
			return true;
		}
		if (Bukkit.getPlayer(args[0]) == null) {
			sender.sendMessage(Colors.fix("&p &7Gracz jest offline!"));
			return true;
		}
		Player o = Bukkit.getPlayer(args[0]);
		Inventory inv = Bukkit.createInventory((Player)sender, 45, Colors.fix("&4INV: ") + args[0]);
		for (int i = 0; i < 36; i++) {
			inv.setItem(i, o.getInventory().getItem(i));
		}
		inv.setItem(36, o.getInventory().getHelmet());
		inv.setItem(37, o.getInventory().getChestplate());
		inv.setItem(38, o.getInventory().getLeggings());
		inv.setItem(39, o.getInventory().getBoots());
		inv.setItem(40, o.getInventory().getItemInOffHand());
		((Player) sender).openInventory(inv);
		return true;
	}

	@EventHandler
	public void clcl1(InventoryClickEvent e) {
		if (e.getView().getTitle().startsWith(Colors.fix("&4INV: "))) {
			String o = e.getView().getTitle().replaceAll(Colors.fix("&4INV: "), "");
			PlayerInventory inv = Bukkit.getPlayer(o).getInventory();
			Inventory inv2 = e.getInventory();
			for (int i = 0; i < 36; i++) inv.setItem(i, e.getInventory().getItem(i));
			inv.setHelmet(inv2.getItem(36));
			inv.setChestplate(inv2.getItem(37));
			inv.setLeggings(inv2.getItem(38));
			inv.setBoots(inv2.getItem(39));
			inv.setItemInOffHand(inv2.getItem(40));
		}
	}
}
