package com.github.cPajor.pcore.data.user;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.github.cPajor.cpWebAPI.WebAPI;
import com.github.cPajor.pcore.core.DBClient;

public class User {
	private String name;
	//private Scoreboard sb;
	//public String velocity;
	public Location wloc = new Location(Bukkit.getWorld("world"), 0.5, 65, 0.5);
	public Location joinLoc;
	public float mana;
	public float energy;
	
    public User(String name) {
        this.name = name;
    }
    
    //private int monCache = 0;
    
    /*public Scoreboard initSB() {
    	sb = Bukkit.getScoreboardManager().getNewScoreboard();
    	Objective obj = sb.getObjective(DisplaySlot.SIDEBAR);
    	if (obj != null) {
    		sb.getEntries().forEach(t -> {
    			sb.resetScores(t);
    		});
    	}
    	obj = sb.registerNewObjective("INFO", Criteria.DUMMY, Colors.fix("&4&lINFO"));
    	obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    	obj.getScore(Colors.fix("&4&lNICK &f&l" + this.name)).setScore(9);
    	obj.getScore(Colors.fix("&4&lPLN &f&l" + this.monCache)).setScore(8);
    	obj.getScore(Colors.fix("&4&lPUNKTY CZASU &f&l" + TUserMan.getUser(this.name).getCachedPoints())).setScore(7);
    	obj.getScore(Colors.fix("&4&lNASTEPNY PUNKT ZA:")).setScore(6);
    	obj.getScore(Colors.fix("&f&l" + TimeUnit.MILLISECONDS.toMinutes(TUserMan.getUser(this.name).getTimeLeft()) + " minut " + (TimeUnit.MILLISECONDS.toSeconds(TUserMan.getUser(this.name).getTimeLeft()) % 60) + " sekund")).setScore(5);
    	//obj.getScore(Colors.fix("&f&l✫")).setScore(4);
    	return sb;
    }*/
    
    public String getName() {
        return this.name;
    }
    
    public int getMoney() {
    	//if (DBClient.get("user." + this.name + ".money").equalsIgnoreCase("null")) return monCache = 0;
    	//int m = Integer.parseInt(DBClient.get("user." + this.name + ".money"));
    	//monCache = m;
    	return WebAPI.getEmerald(name);
    }
    
    public void setMoney(int v) {
    	WebAPI.setEmerald(name, v); 
    	//monCache = rank;
        //DBClient.set("user." + this.name + ".money", "" + rank);
    }
    
    public int getPvP() {
    	if (DBClient.get("user." + this.name + ".pvp").equalsIgnoreCase("null")) return 0;
        return Integer.parseInt(DBClient.get("user." + this.name + ".pvp"));
    }
    
    public void setPvP( int pvp) {
        DBClient.set("user." + this.name + ".pvp", "" + pvp);
    }
    
    //public String getVelocity() {
    //	return velocity != null ? velocity : "";
    //}
}
