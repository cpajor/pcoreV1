package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.user.Combat;

public class ACommand extends ACmd {
	
	public ACommand() {
		super("a", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Combat.get(sender.getName()).setTime(0);
		return true;
	}

}
