package com.github.cPajor.pcore.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.GenericGameEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.data.util.Colors;

public class VanishCommand extends ACmd implements Listener {
	public static List<String> naVixie = new ArrayList<String>();

	public VanishCommand(String s) {
		super(s, true);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("0")) {
				if (naVixie.contains(p.getName())) {
					showPlayer(p);
				}
			} else {
				if (!naVixie.contains(p.getName())) {
					hidePlayer(p);
				}
			}
		} else {
			if (naVixie.contains(p.getName())) {
				showPlayer(p);
			} else {
				hidePlayer(p);
			}
		}
		return true;
	}

	public static void hidePlayer( Player player) {
		player.sendMessage(Colors.fix("&p &7VANISH: &4ON"));
		naVixie.add(player.getName());
        for ( Player a : Bukkit.getOnlinePlayers()) {
        	if (!a.hasPermission("pajor.vanish.bypass"))
        		a.hidePlayer(CReg.getPlugin(), player);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, true, false));
    }
    
    public static void showPlayer( Player player) {
		player.sendMessage(Colors.fix("&p &7VANISH: &4OFF"));
        for ( Player a : Bukkit.getOnlinePlayers()) {
            a.showPlayer(CReg.getPlugin(), player);
           
        } 
        if (naVixie.contains(player.getName())) {
        	naVixie.remove(player.getName());
        }
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
    
    @EventHandler
    public void join(PlayerJoinEvent e) {
    	if (e.getPlayer().hasPermission("pajor.helper")) {
    		hidePlayer(e.getPlayer());
    	}
    }
    
    @EventHandler
    public void quit(PlayerQuitEvent e) {
    	if (naVixie.contains(e.getPlayer().getName())) {
    		showPlayer(e.getPlayer());
    	}
    }
    
    @EventHandler
    public void ted1(PlayerInteractEvent e) {
    	if (e.getAction() == Action.PHYSICAL && naVixie.contains(e.getPlayer().getName())) {
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void ted2(GenericGameEvent e) {
    	if ((e.getEvent() == GameEvent.SCULK_SENSOR_TENDRILS_CLICKING || e.getEvent() == GameEvent.SHRIEK) && e.getEntity() instanceof Player && naVixie.contains(e.getEntity().getName())) {
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void ted3(PlayerInteractEntityEvent e) {
    	if (naVixie.contains(e.getPlayer().getName())) {
    		e.setCancelled(true);
    	}
    }
    
    
    @EventHandler
    public void ted5(PlayerPickupItemEvent e) {
    	if (naVixie.contains(e.getPlayer().getName())) {
    		e.setCancelled(true);
    	}
    }


    @EventHandler
    public void ted4(EntityTargetEvent e) {
    	if (e.getEntity() instanceof Player)
    	if (naVixie.contains(e.getTarget().getName())) {
    		e.setCancelled(true);
    	}
    } 
    
    public static void RestartVanish() {
        for (String s : naVixie) {
        	Player p = Bukkit.getPlayer(s);
        	if (p != null) {
        		showPlayer(p);
        	}
        }
    }
}
