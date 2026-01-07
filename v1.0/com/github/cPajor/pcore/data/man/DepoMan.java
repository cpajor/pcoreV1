package com.github.cPajor.pcore.data.man;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.DBClient;
import com.github.cPajor.pcore.data.user.Depozyt;

public class DepoMan extends BukkitRunnable {
	public static ConcurrentHashMap<String, Depozyt> depo;
    
    static {
        depo = new ConcurrentHashMap<String, Depozyt>();
    }
    
    public static Depozyt getDepozyt( String name) {
        for ( Depozyt u : DepoMan.depo.values()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        } 
        String s = DBClient.get("user." + name + ".var1");
        if (s != null && !s.equalsIgnoreCase("null")) {
        	Depozyt u = new Depozyt(name, Integer.parseInt(DBClient.get("user." + name + ".var1")), Integer.parseInt(DBClient.get("user." + name + ".var2")), Integer.parseInt(DBClient.get("user." + name + ".var3")), Integer.parseInt(DBClient.get("user." + name + ".var4")));
        	depo.put(name, u);
        } else
        createrDepozyt(name);
        return getDepozyt(name);
    }
    
    public static void createrDepozyt( String p) {
        Depozyt u = new Depozyt(p);
        u.save();
        DepoMan.depo.put(p, u);
    }
    
    public void run() {
        new BukkitRunnable() {
            public void run() {
            	for (Depozyt s : DepoMan.depo.values()) {
            		s.save();
            	}
            }
        }.runTaskLaterAsynchronously(CReg.getPlugin(), 300L);
    }

}
