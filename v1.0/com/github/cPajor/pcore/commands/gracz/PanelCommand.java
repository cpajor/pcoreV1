package com.github.cPajor.pcore.commands.gracz;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.gui.GuiGracz;
import com.github.cPajor.pcore.data.user.ChatUser;
import com.github.cPajor.pcore.data.util.Colors;

public class PanelCommand extends ACmd implements Listener {

	public PanelCommand() {
		super("panel");
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		return GuiGracz.GuiPanel((Player)sender);
	}
	
	@EventHandler
	public void onGui(InventoryClickEvent e) {
		if (Colors.fix("&7&l{.} &4Gracz Panel").equals(e.getView().getTitle())) {
			e.setCancelled(true);
			if (e.getSlot() == 2) {
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
			/*if (e.getSlot() == 0) {
				GuiGracz.GuiGearBox((Player)e.getWhoClicked());
			}*/
		}
		if (Colors.fix("&7&l{.} &4Gracz Panel Chat").equals(e.getView().getTitle())) {
			 ChatUser u = ChatUser.getUser(e.getWhoClicked().getName());
			e.setCancelled(true);
			if (e.getSlot() == 0) {
				u.set("pandora", !u.get("pandora"));
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
			if (e.getSlot() == 1) {
				u.set("gracze", !u.get("gracze"));
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
			if (e.getSlot() == 2) {
				u.set("is", !u.get("is"));
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
			if (e.getSlot() == 3) {
				u.set("automsg", !u.get("automsg"));
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
			if (e.getSlot() == 4) {
				u.set("priv", !u.get("priv"));
				GuiGracz.GuiPanelChat((Player)e.getWhoClicked());
			}
		}
		/*if (Colors.fix("&7&l{.} &4Gearbox Panel").equals(e.getView().getTitle())) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BEDROCK) {
				e.setCancelled(true);
			}
		}*/
	}

}
