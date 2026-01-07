package com.github.cPajor.pcore.data.util;

import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.cPajor.pcore.data.user.ChatUser;

public class Chat {
	
	public static void sendToAll( String s1,  String kto) {
		for (Player o : Bukkit.getOnlinePlayers()) {
			ChatUser u = ChatUser.getUser(o.getName());
			if (u.get(kto)) {
				o.sendMessage(s1);
			} 
 		}
	}
	
	public static void sendToAllP( String s1) {
		for (Player o : Bukkit.getOnlinePlayers()) {
			o.sendMessage(Colors.fix(s1));
 		}
	}
	
	public static boolean isAlphaNumeric( String s) {
        return s.matches("^[a-zA-Z0-9_]*$");
    }
	
	public static boolean isInteger( String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }

}
