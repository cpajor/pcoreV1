package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.listeners.player.JoinListener;

public class BackCommand extends ACmd {
	
	public BackCommand() {
		super("back", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		((Player) sender).teleport(JoinListener.backs.get(sender.getName()));
		return true;
	}

}
