package com.github.cPajor.pcore.hook.placeholderapi;

import org.bukkit.entity.Player;

//import com.github.cPajor.cpWebAPI.WebAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderHook extends PlaceholderExpansion {

	@Override
	public String getAuthor() {
		return "cpajor";
	}

	@Override
	public String getIdentifier() {
		return "pcore";
	}

	@Override
	public String getVersion() {
		return "r6-lf";
	}
	
	public String onPlaceholderRequest(Player p, String id) {
        if (p == null) {
            return null;
        }
        
        /*if (id.equalsIgnoreCase("saldo")) {
        	return "" + WebAPI.getEmerald(p.getName());
        }*/
        
        return null;
    }
	
	public static void Hhook() {
		new PlaceholderHook().register();
	}

}
