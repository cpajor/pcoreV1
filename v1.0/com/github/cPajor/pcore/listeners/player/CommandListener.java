package com.github.cPajor.pcore.listeners.player;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.help.HelpTopic;

import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.user.Combat;
import com.github.cPajor.pcore.data.util.Colors;

public class CommandListener implements Listener {
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		if (SpawnListeners.fix1.containsKey(e.getPlayer().getName())) {
			e.getPlayer().sendMessage(Colors.fix("&p &f") + e.getMessage());
			e.setCancelled(true);
			if (SpawnListeners.fix1.get(e.getPlayer().getName()) >= 2) {
				SpawnListeners.fix1.replace(e.getPlayer().getName(), SpawnListeners.fix1.get(e.getPlayer().getName()) - 1);
			} else {
				SpawnListeners.fix1.remove(e.getPlayer().getName());
			}
			return;
		}
		if (e.getMessage().startsWith("/gm") || e.getMessage().startsWith("/a")) {
			return;
		}
		if (Combat.get(e.getPlayer().getName()).getTime() != 0) {
			e.getPlayer().sendMessage(Colors.fix("&p &7Jestes podczas walki!"));
			e.setCancelled(true);
			return;
		}
		 String s = e.getMessage().split(" ")[0];
         HelpTopic t = Bukkit.getHelpMap().getHelpTopic(s);
        if (t == null) {
            Title.ActionBar(e.getPlayer(), Colors.fix("&p &7Podana komenda nie istnieje!"));
            e.getPlayer().playNote(e.getPlayer().getLocation(),	Instrument.BIT, Note.sharp(1, Tone.G));
            e.setCancelled(true);
        }
	}
	
	@EventHandler
	public void tab(TabCompleteEvent e) {
		e.setCompletions(Arrays.asList(" "));
		e.setCancelled(true);
	}

	@EventHandler
	public void tab(PlayerChatTabCompleteEvent e) {
		e.getTabCompletions().clear();
	}
}
