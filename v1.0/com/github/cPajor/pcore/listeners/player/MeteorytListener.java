package com.github.cPajor.pcore.listeners.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.github.cPajor.pcore.data.util.Chat;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.data.util.SpaceUtil;

public class MeteorytListener implements Listener {
	
	@EventHandler
	public void expllll(EntityExplodeEvent e) {
		if (e.getEntity().getCustomName() == null) return;
		if (!e.getEntity().getCustomName().equalsIgnoreCase(Colors.fix("&cMETEORYT"))) return;
		int y = e.getLocation().getBlockY();
		for (Block b : e.blockList()) {
			b.setType(Material.END_STONE);
			if (y > b.getY()) {
				y = b.getY();
			}
		}
		Location c = e.getLocation();
		c.setY(y);
		for ( Location loc : SpaceUtil.getSquare(c, 3, 4)) {
            loc.getBlock().setType(Material.AIR);
        }
        for ( Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.END_STONE);
        }
        c.add(0, 1, 0);
		c.getBlock().setType(Material.END_PORTAL_FRAME);
	}
	
	public static void meteoryt(Location l) {
		if (!l.getChunk().isLoaded()) l.getChunk().load();
		Fireball b = (Fireball) l.getWorld().spawnEntity(l, EntityType.FIREBALL);
		b.setCustomName(Colors.fix("&cMETEORYT"));
		b.setDirection(new Vector(0, -1, 0));
		b.setYield(10);
	}
	
	@EventHandler
	public void useMeteoryt(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		if (e.getClickedBlock().getType() != Material.END_PORTAL_FRAME) {
			return;
		}
		e.getClickedBlock().setType(Material.AIR);
		Chat.sendToAllP("&p &7Gracz &4" + e.getPlayer().getName() + " &7otworzyl meteoryt!");
 	} 

}
