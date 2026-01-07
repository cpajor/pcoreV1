package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class ItemnameCommand extends ACmd {

	public ItemnameCommand() {
		super("itemname", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			Player p = (Player) sender;
			ItemStack is = p.getInventory().getItemInMainHand();
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Colors.fix(args[0]));
			is.setItemMeta(im);
			p.getInventory().setItemInMainHand(is);
			return true;
		}
		Title.sendTitle((Player) sender, " ", Colors.fix("&p &4Uzyj: &7/itemname [name]"));
		return true;
	}

}
