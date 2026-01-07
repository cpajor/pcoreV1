package com.github.cPajor.pcore.core.data;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Title {
	public static void sendTitle(Player player, String text, String sub) {
		player.sendTitle(text, sub, 0, 30, 0);
	}
    
    public static void ActionBar(Player player, String text) {
    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }
}