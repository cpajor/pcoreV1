package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class SpawnCommand extends ACmd implements Listener {
	public static Location getSpawn() {
		return new Location(Bukkit.getWorld("spawnc"), 0.5, 65, 0.5);
	}

	public SpawnCommand() {
		super("spawn");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (sender.hasPermission("cmd.spawn")) {
	    	p.teleport(SpawnCommand.getSpawn());
			return true;
		}
		Title.sendTitle(p, " ", Colors.fix("&p &7Teleportacja za 10 sekund!"));
		tacker(p, p.getLocation(), 9);
		return true;
	}
	
	public void tacker(Player p, Location l, int h) {
		if (move(p.getLocation(), l)) {
			Title.sendTitle(p, " ", Colors.fix("&p &7Teleportacja anulowana!"));
			return;
		}
		if (h <= -1) {
			p.teleport(getSpawn());
			p.stopSound(Sound.ENTITY_ENDER_DRAGON_DEATH); 
			return;
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Title.sendTitle(p, " ", Colors.fix("&p &7Teleportacja za " + h + " sekund!"));
				tacker(p, l, h - 1);
			}
		}.runTaskLater(CReg.getPlugin(), 20);
	}

	public static boolean move( Location l,  Location x) {
        return l.getBlockX() != x.getBlockX() || l.getBlockY() != x.getBlockY() || l.getBlockZ() != x.getBlockZ();
    }
}
