package com.github.cPajor.pcore.core.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.data.util.Colors;

public abstract class ACmd extends org.bukkit.command.Command {
    private String name;
    private boolean perm;
    
    public ACmd(String name, boolean perm) {
        super(name);
        this.name = name;
        this.perm = perm;
    }
    
    public ACmd(String name) {
    	this(name, false);
    }
    
    public boolean execute( CommandSender sender,  String label,  String[] args) {
    	if (sender instanceof Player && this.perm) {
	    	if (!sender.hasPermission("pajor.cmd." + this.name)) {
	    		sender.sendMessage(Colors.fix("&p &7Nie posiadasz uprawnien!"));
	    		return true;
	    	}
    	}
        return this.onExecute(sender, args);
    }
    
    public abstract boolean onExecute( CommandSender sender,  String[] args);
    
    public String getName() {
        return this.name;
    }
    
}
