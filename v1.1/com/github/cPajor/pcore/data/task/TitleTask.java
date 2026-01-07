package com.github.cPajor.pcore.data.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.commands.admin.VanishCommand;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.man.UserMan;
import com.github.cPajor.pcore.data.user.User;
import com.github.cPajor.pcore.data.util.Colors;

public class TitleTask extends BukkitRunnable {
	
    public void run() {
    	for (Player o : Bukkit.getOnlinePlayers()) {
    		//o.setScoreboard(UserMan.getUser(o.getName()).initSB());
    		if (VanishCommand.naVixie.contains(o.getName())) {
    			Title.ActionBar(o, Colors.fix("&c&lJESTES NIEWIDOCZNY"));
    		} else {
    			User u = UserMan.getUser(o.getName());
    			Title.ActionBar(o, Colors.fix("&9&lMANA: &f&l" + u.mana + " &8&l)I( &e&lENERGIA: &f&l" + u.energy));
    		}/* else {
	    		Combat u = Combat.get(o.getName());
	    		if (u.getTime() > 0) { 
	    			Title.ActionBar(o, Colors.fix("&4&lAntyLogOut: &7" + (u.getTime() / 10)));
	    		}
    		}*/
    	}
    }

}
