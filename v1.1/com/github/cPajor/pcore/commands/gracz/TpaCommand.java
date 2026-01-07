package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.man.TpaMan;
import com.github.cPajor.pcore.data.util.Colors;

public class TpaCommand extends ACmd {

	public TpaCommand() {
		super("tpa");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (args.length != 1) {
			Title.sendTitle(p, " ", Colors.fix("&p &4Uzyj: &7/tpa [gracz]"));
			return true;
        }
        Player cel = Bukkit.getPlayer(args[0]);
        if (cel == null) {
			Title.sendTitle(p, " ", Colors.fix("&p &7Podany gracz jest offilne"));
            return true;
        }
        if (cel.getName().equals(p.getName())) {
			Title.sendTitle(p, " ", Colors.fix("&p &7Nie mozesz wyslac prosby do samego siebie!"));
        	return true;
        }
        if (TpaMan.getTpa(p.getName()).contains(cel.getName())) {
        	p.sendMessage(Colors.fix("&p &7Wyslales juz prosbe do tego gracza!"));
        	return true;
        }
        TpaMan.getTpa(p.getName()).add(cel.getName());
        p.sendMessage(Colors.fix("&p &7Wyslales prosbe do gracza " + cel.getName()));
        cel.sendMessage(Colors.fix("&p &7Gracz &4" + p.getName() + " &7Chce sie do Ciebie przeteleportowac! \n&p &7Aby zaakceptowac prosbe wpisz &4/tpaccept " + p.getName() + " &7w ciagu 30 sekund!"));
        String celN = cel.getName();
        String pN = p.getName();
        new BukkitRunnable() {
			@Override
			public void run() {
				try {
					if (TpaMan.getTpa(celN).contains(pN)) {
						TpaMan.getTpa(celN).remove(pN);
						if (Bukkit.getPlayer(pN) != null) {
							Bukkit.getPlayer(pN).sendMessage(Colors.fix("&p &7Posba o teleportacje do gracza &4" + celN + " &7wygasla!"));
						}
					}
				} catch (Exception e) {
				}
			}
		}.runTaskLater(CReg.getPlugin(), 600);
		return true;
	}
	
	

}
