package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.man.TpaMan;
import com.github.cPajor.pcore.data.util.Colors;

public class TpacceptCommand extends ACmd {

	public TpacceptCommand() {
		super("tpaccept");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (args.length != 1) {
			Title.sendTitle(p, " ", Colors.fix("&p &4Uzyj: &7/tpaccept [gracz]"));
			return true;
        }
		Player tpc = (Player) sender;
		Player tpa = Bukkit.getPlayer(args[0]);
        if (tpa == null) {
			Title.sendTitle((Player) sender, " ", Colors.fix("&p &7Nie mozesz wyslac prosby do samego siebie!"));
        	return true;
        }
		if (TpaMan.tpa.get(tpc.getName()) == null) {
			sender.sendMessage(Colors.fix("&p &7Podany gracz nie wyslal do Ciebie prosby o teleportacje!"));
			return true;
		}
		if (!TpaMan.getTpa(tpc.getName()).contains(tpa.getName())) {
			tpa.teleport(tpc);
			TpaMan.getTpa(tpc.getName()).clear();
			return true;
		}
        return true;
	}
	
	
	

}
