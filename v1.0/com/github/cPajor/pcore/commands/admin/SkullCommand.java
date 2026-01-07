package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.CItem;

import com.github.cPajor.pcore.data.util.Colors;

public class SkullCommand extends ACmd {

	public SkullCommand() {
		super("skull", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			((Player)sender).getInventory().addItem(new CItem(Material.PLAYER_HEAD).setDurability((short)3).setSkullOwner(args[0]).setAmount(1).toIs());
			return true;
		}
		sender.sendMessage(Colors.fix("&p &4Uzyj: &7/skull [nick]"));
		return true;
	}

}
