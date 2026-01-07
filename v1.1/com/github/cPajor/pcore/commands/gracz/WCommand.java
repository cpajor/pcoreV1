package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.command.CommandSender;

import com.github.cPajor.pcore.core.cmd.ACmd;

public class WCommand extends ACmd {
	
	public WCommand() {
		super("w", true);
	}
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		return true;
	}

}
