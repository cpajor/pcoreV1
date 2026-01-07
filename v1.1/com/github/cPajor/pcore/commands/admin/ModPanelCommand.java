package com.github.cPajor.pcore.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.github.cPajor.pcore.core.cmd.ACmd;

import com.github.cPajor.pcore.data.gui.GuiMod;
import com.github.cPajor.pcore.data.util.Colors;

public class ModPanelCommand extends ACmd implements Listener {

	public ModPanelCommand() {
		super("modpanel", true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		GuiMod.GuiPanel((Player)sender);
		return true;
	}

	@EventHandler
	public void onGui(InventoryClickEvent e) {
		if (Colors.fix("&7&l{.} &4Mod Panel").equals(e.getView().getTitle())) {
			e.setCancelled(true);
			if (e.getSlot() == 0) {
				e.getWhoClicked().getWorld().setTime(3000);
			}
			if (e.getSlot() == 1) {
				e.getWhoClicked().getWorld().setTime(20000);
			}
		}
	}
	
}
