package com.github.cPajor.pcore.data.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.data.user.Combat;
import com.github.cPajor.pcore.data.util.Colors;

public class TagTask extends BukkitRunnable {
	public float ff = 0;
	
    public void run() {
    	for (Player o : Bukkit.getOnlinePlayers()) {
    		Combat u = Combat.get(o.getName());
    		if (u.getTime() <= 0) continue;
    		String velocity = "(&4" + ((int)u.getTime() / 10) + "s&f)                                       ";
    		o.sendTitle(" ", Colors.fix(velocity), 0, 30, 0);
    	}
    }

}
