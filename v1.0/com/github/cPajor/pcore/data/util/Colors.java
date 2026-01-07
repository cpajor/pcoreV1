package com.github.cPajor.pcore.data.util;

import net.md_5.bungee.api.ChatColor;

public class Colors {
	
	public static String fix(String s) {
		return addColors(s.replace("{.}", "\u2022").replaceAll("&p", "&8&l( &4&l! &8&l)"));
	}

	private static String addColors(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
