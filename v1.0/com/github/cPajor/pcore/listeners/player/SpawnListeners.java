package com.github.cPajor.pcore.listeners.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.GenericGameEvent;

import com.github.cPajor.pcore.data.util.Colors;

import net.md_5.bungee.api.chat.TextComponent;

public class SpawnListeners implements Listener {

	public boolean bl(Location l) {
		return l.getWorld().getName().equalsIgnoreCase("spawnc");
	}
	
	@EventHandler
	public void blks(BlockBreakEvent e) {
		if (bl(e.getBlock().getLocation())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void blkd(BlockPlaceEvent e) {
		if (bl(e.getBlock().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void fkdsa(EntityDamageEvent e) {
		if (bl(e.getEntity().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void hgfds(GenericGameEvent e) {
		if (e.getEntity() != null) if (bl(e.getEntity().getLocation())) {
			e.setCancelled(true);
		}
		if (e.getLocation() != null) if (bl(e.getLocation())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void gfdshgfds(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) if (bl(e.getClickedBlock().getLocation())) {
			e.setCancelled(true);
			
		}
	}
	
	@EventHandler
	public void spaw(EntitySpawnEvent e) {
		if (!bl(e.getLocation())) return;
		if (e.getEntityType() == EntityType.MINECART || e.getEntityType() == EntityType.FISHING_HOOK) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void gfdsankkgfgds(FoodLevelChangeEvent e) {
		if (bl(e.getEntity().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void bambik(EntityShootBowEvent e) {
		if (bl(e.getEntity().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void gfdssfghhypp(BlockGrowEvent e) {
		if (bl(e.getBlock().getLocation())) {
			e.setCancelled(true);	
		}
	}
	
	@EventHandler
	public void bsgjeawgegrk(ProjectileLaunchEvent e) {
		if (bl(e.getLocation())) {
			e.setCancelled(true);
		}
	}
	
	public static Map<String, Integer> fix1 = new HashMap<>();
	
	@EventHandler
	public void hgllgpphpf(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType().name().contains("SIGN")) {
				Sign sig = (Sign) e.getClickedBlock().getState();
				for (String s : sig.getSide(Side.BACK).getLines()) {
					TextComponent c = new TextComponent(s);
					if (c.getClickEvent() != null) {
						e.getPlayer().sendMessage(Colors.fix("&p &cLINE: ") + c.getText());
						e.getPlayer().sendMessage(Colors.fix("&p &cRAW: ") + c.getClickEvent().getValue());
						e.getPlayer().sendMessage(Colors.fix("&p &cTYPE: ") + c.getClickEvent().getAction().toString());
						e.setCancelled(true);
						e.getClickedBlock().setType(Material.AIR);
						if (fix1.containsKey(e.getPlayer().getName())) {
							fix1.replace(e.getPlayer().getName(), 1 + fix1.get(e.getPlayer().getName()));
						} else {
							fix1.put(e.getPlayer().getName(), 1); 
						}
					}
				}
				for (String s : sig.getSide(Side.FRONT).getLines()) {
					TextComponent c = new TextComponent(s);
					if (c.getClickEvent() != null) {
						e.getPlayer().sendMessage(Colors.fix("&p &cLINE: ") + c.getText());
						e.getPlayer().sendMessage(Colors.fix("&p &cRAW: ") + c.getClickEvent().getValue());
						e.getPlayer().sendMessage(Colors.fix("&p &cTYPE: ") + c.getClickEvent().getAction().toString());
						e.setCancelled(true);
						e.getClickedBlock().setType(Material.AIR);
						if (fix1.containsKey(e.getPlayer().getName())) {
							fix1.replace(e.getPlayer().getName(), 1 + fix1.get(e.getPlayer().getName()));
						} else {
							fix1.put(e.getPlayer().getName(), 1); 
						}
					}
				}
			}
		}
	}
	
}
