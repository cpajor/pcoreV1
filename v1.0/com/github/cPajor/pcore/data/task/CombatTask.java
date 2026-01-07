package com.github.cPajor.pcore.data.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.data.man.TUserMan;
import com.github.cPajor.pcore.data.user.Combat;
import com.github.cPajor.pcore.data.user.TUser;

public class CombatTask extends BukkitRunnable {
	
    public void run() {
    	for (Player o : Bukkit.getOnlinePlayers()) {
    		Combat u = Combat.get(o.getName());
    		if (u.getTime() > 0) u.setTime(u.getTime() - 1);
    		TUser t = TUserMan.getUser(o.getName());
    		if (t.getTimeLeft() <= 0) {
    			t.setTime(System.currentTimeMillis() + TUser.KVA);
    			t.setPoints(t.getPoints() + 1);
    		}
    		
    	}
    }

}
