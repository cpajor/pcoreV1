package com.github.cPajor.pcore.data.man;

import org.bukkit.entity.Player;

public class TagMan {
	
    @SuppressWarnings("deprecation")
	public static void init( Player p) {}/*
    	Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
    	int ph = UserMan.getUser(p.getName()).getFall();
    	String tag = GUserManager.getUser(p.getName()).getTag();
    	if (Combat.get(p.getName()).isIncognito())
    	if (ph == 0) {
    		if (!tag.equalsIgnoreCase("noguild")) {
	    		String team = tag + g.getLevel() + 0;
	    		if (sb.getTeam(team) == null) {
	    			sb.registerNewTeam(team);
		    	}
		    	if (g.getLevel() == 0) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&7&k"));
		    	}
		    	if (g.getLevel() == 1) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6&k"));
		    	}
		    	if (g.getLevel() == 2) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&4&k"));
		    	}
		    	if (g.getLevel() == 3) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&4&k"));
		    	}
		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&3&l&k"));
		    	}
		    	if (g.getLevel() == 6) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&2"));
		    	}
		    	if (g.getLevel() == 7) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&c"));
		    	}
		    	if (g.getLevel() == 8 || g.getLevel() == 9) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
		    	}
		    	sb.getTeam(team).setSuffix(Colors.fix("&7 (&c" + tag + "&7)"));
		    	sb.getTeam(team).addPlayer(p);
	    	} else {
	    		String team = "noguild" + g.getLevel();
	    		if (sb.getTeam(team) == null) {
	    			sb.registerNewTeam(team);
		    	}
		    	if (g.getLevel() == 0) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&7&k"));
		    	}
		    	if (g.getLevel() == 1) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6&k"));
		    	}
		    	if (g.getLevel() == 2) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&4&k"));
		    	}
		    	if (g.getLevel() == 3) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&3&k"));
		    	}
		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&3&l&k"));
		    	}
		    	if (g.getLevel() == 6) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&2"));
		    	}
		    	if (g.getLevel() == 7) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&c"));
		    	}
		    	if (g.getLevel() == 9 || g.getLevel() == 8) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
		    	}
		    	sb.getTeam(team).addPlayer(p);
	    	}
    	} else {
    		if (!tag.equalsIgnoreCase("noguild")) {
	    		String team = tag + g.getLevel() + ph;
	    		if (sb.getTeam(team) == null) {
	    			sb.registerNewTeam(team);
		    	}
		    	if (g.getLevel() == 0) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &7&k"));
		    	}
		    	if (g.getLevel() == 1) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &6&k"));
		    	}
		    	if (g.getLevel() == 2) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4&k"));
		    	}
		    	if (g.getLevel() == 3) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&k"));
		    	}
		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&l&k"));
		    	}
		    	if (g.getLevel() == 6) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &2"));
		    	}
		    	if (g.getLevel() == 7) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &c"));
		    	}
		    	if (g.getLevel() == 9 || g.getLevel() == 8) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
		    	}
		    	sb.getTeam(team).setSuffix(Colors.fix("&7 (&c" + tag + "&7)"));
		    	sb.getTeam(team).addPlayer(p);
	    	} else {
	    		String team = "noguild" + g.getLevel();
	    		if (sb.getTeam(team) == null) {
	    			sb.registerNewTeam(team);
		    	}
		    	if (g.getLevel() == 0) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &7&k"));
		    	}
		    	if (g.getLevel() == 1) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &6&k"));
		    	}
		    	if (g.getLevel() == 2) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4&k"));
		    	}
		    	if (g.getLevel() == 3) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&k"));
		    	}
		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&l&k"));
		    	}
		    	if (g.getLevel() == 6) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &2"));
		    	}
		    	if (g.getLevel() == 7) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &c"));
		    	}
		    	if (g.getLevel() == 9 || g.getLevel() == 8) {
		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
		    	}
		    	sb.getTeam(team).addPlayer(p);
	    	}
    	}
    	else 
    		if (ph == 0) {
        		if (!tag.equalsIgnoreCase("noguild")) {
    	    		String team = tag + g.getLevel() + 0;
    	    		if (sb.getTeam(team) == null) {
    	    			sb.registerNewTeam(team);
    		    	}
    		    	if (g.getLevel() == 0) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&7"));
    		    	}
    		    	if (g.getLevel() == 1) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6"));
    		    	}
    		    	if (g.getLevel() == 2) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
    		    	}
    		    	if (g.getLevel() == 3) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&3"));
    		    	}
    		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&3&l"));
    		    	}
    		    	if (g.getLevel() == 6) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&2"));
    		    	}
    		    	if (g.getLevel() == 7) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&c"));
    		    	}
    		    	if (g.getLevel() == 9 || g.getLevel() == 8) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
    		    	}
    		    	sb.getTeam(team).setSuffix(Colors.fix("&7 (&c" + tag + "&7)"));
    		    	sb.getTeam(team).addPlayer(p);
    	    	} else {
    	    		String team = "noguild" + g.getLevel();
    	    		if (sb.getTeam(team) == null) {
    	    			sb.registerNewTeam(team);
    		    	}
    		    	if (g.getLevel() == 0) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&7"));
    		    	}
    		    	if (g.getLevel() == 1) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6"));
    		    	}
    		    	if (g.getLevel() == 2) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
    		    	}
    		    	if (g.getLevel() == 3) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&3"));
    		    	}
    		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&3&l"));
    		    	}
    		    	if (g.getLevel() == 6) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&2"));
    		    	}
    		    	if (g.getLevel() == 7) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&c"));
    		    	}
    		    	if (g.getLevel() == 8 || g.getLevel() == 9) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&4"));
    		    	}
    		    	sb.getTeam(team).addPlayer(p);
    	    	}
        	} else {
        		if (!tag.equalsIgnoreCase("noguild")) {
    	    		String team = tag + g.getLevel() + ph;
    	    		if (sb.getTeam(team) == null) {
    	    			sb.registerNewTeam(team);
    		    	}
    		    	if (g.getLevel() == 0) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &7"));
    		    	}
    		    	if (g.getLevel() == 1) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &6"));
    		    	}
    		    	if (g.getLevel() == 2) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
    		    	}
    		    	if (g.getLevel() == 3) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3"));
    		    	}
    		    	if (g.getLevel() == 4 || g.getLevel() == 5) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&l"));
    		    	}
    		    	if (g.getLevel() == 6) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &2"));
    		    	}
    		    	if (g.getLevel() == 7) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &c"));
    		    	}
    		    	if (g.getLevel() == 9 || g.getLevel() == 8) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
    		    	}
    		    	sb.getTeam(team).setSuffix(Colors.fix("&7 (&c" + tag + "&7)"));
    		    	sb.getTeam(team).addPlayer(p);
    	    	} else {
    	    		String team = "noguild" + g.getLevel();
    	    		if (sb.getTeam(team) == null) {
    	    			sb.registerNewTeam(team);
    		    	}
    		    	if (g.getLevel() == 0) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &7"));
    		    	}
    		    	if (g.getLevel() == 1) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &6"));
    		    	}
    		    	if (g.getLevel() == 2) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
    		    	}
    		    	if (g.getLevel() == 3) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3"));
    		    	}
    		    	if (g.getLevel() == 4  || g.getLevel() == 5) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &3&l"));
    		    	}
    		    	if (g.getLevel() == 6) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &2"));
    		    	}
    		    	if (g.getLevel() == 7) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &c"));
    		    	}
    		    	if (g.getLevel() == 8 || g.getLevel() == 9) {
    		    		sb.getTeam(team).setPrefix(Colors.fix("&6" + ph + " &4"));
    		    	}
    		    	sb.getTeam(team).addPlayer(p);
    	    	}
        	}
    	p.setScoreboard(sb);
    }*/
   
}
