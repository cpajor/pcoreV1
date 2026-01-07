package com.github.cPajor.pcore.data.man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

public class TpaMan {
	public static HashMap<String, List<String>> tpa = new HashMap<String, List<String>>();
	
	public static List<String> getTpa(String s) {
		if (tpa.containsKey(s)) {
			return tpa.get(s);
		}
		tpa.put(s, new ArrayList<String>()); 
		return new ArrayList<String>();
	}
    
    public static boolean move( Location l,  Location x) {
        return l.getBlockX() != x.getBlockX() || l.getBlockY() != x.getBlockY() || l.getBlockZ() != x.getBlockZ();
    }
}
