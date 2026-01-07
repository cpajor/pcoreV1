package com.github.cPajor.pcore.hook.citizens;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.commands.admin.CNpcCommand;
import com.github.cPajor.pcore.core.CmdM;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;

public class CitizensHook implements Listener {
	
private static List<NpcImpl> npcs = new ArrayList<NpcImpl>();
	
	public static void HHook() {
		new BukkitRunnable() {			
			@Override
			public void run() {
				npcs.forEach(npc -> {
					//npc.raw.getOrAddTrait(SentinelTrait.class).tryUpdateChaseTarget(null);
					npc.raw.setProtected(false);
					npc.raw.getOrAddTrait(SentinelTrait.class).health += 0.25; 
				});
			}
		}.runTaskTimer(CReg.getPlugin(), 25, 25);
		new BukkitRunnable() {			
			@Override
			public void run() {
				npcs.forEach(npc -> {
					//npc.raw.getOrAddTrait(SentinelTrait.class).tryUpdateChaseTarget(null);
					Location l = npc.raw.getNavigator().getTargetAsLocation();
					for (String t : CNpcCommand.toggers) {
						Player p = Bukkit.getPlayer(t);
						if (p != null) {
							
						}
					}
				});
			}
		}.runTaskTimer(CReg.getPlugin(), 25, 25);
		Bukkit.getPluginManager().registerEvents(new CitizensHook(), CReg.getPlugin());
		CmdM.register(new CNpcCommand());
	}
	
	//private static int npc_count = -1;
	
	@EventHandler
	public void scgf(NPCDeathEvent e) {
		e.getDrops().clear();
	}
	
	@EventHandler
	public void uiqt(PlayerQuitEvent e) {
		if (CNpcCommand.toggers.contains(e.getPlayer().getName())) {
			CNpcCommand.toggers.remove(e.getPlayer().getName());
		}
	}
	
	public static void Hunhook() {
		npcs.forEach(t -> {
			CitizensAPI.getNPCRegistry().deregister(t.raw);
		});
		npcs.clear();
	}
	
	public static void spawnWarding(Location loc) { 
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VINDICATOR, Colors.fix("&0 "));
		npc.getOrAddTrait(SentinelTrait.class).setHealth(35);
		npc.setProtected(false);
		npc.getOrAddTrait(Equipment.class).set(EquipmentSlot.HAND, new CItem(Material.IRON_SWORD).addEnchant(Enchantment.FIRE_ASPECT, 1).toIs());
		npc.getOrAddTrait(Equipment.class).set(EquipmentSlot.OFF_HAND, new CItem(Material.IRON_SWORD).addEnchant(Enchantment.FIRE_ASPECT, 1).toIs());
		//npc.getOrAddTrait(SkinTrait.class).setTexture(SKIN1_VAL, SKIN1_SIG);
		npc.spawn(loc);
		NpcImpl impl = new NpcImpl(npc);
		npcs.add(impl);
	}
	
	private static final String SKIN1_VAL = "ewogICJ0aW1lc3RhbXAiIDogMTcwMzE1NTA0MDIzNCwKICAicHJvZmlsZUlkIiA6ICJkODZlMDcyZmUyNDU0NjIzYjUwODFlZDI2ZWNkY2I1MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJPY2FkYW1lbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80N2Q5MDZlMTI1ODZmNTI4N2I1N2JjNzU3ZjE4MjBjOTJiMTJkNGU1MTgwNzFhNmRiM2YwNTA4ZDNhZWQwOTU1IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
	private static final String SKIN1_SIG = "Lyvv94jzqplVh87r1QZc6VM6GBjT2goW/yW2IeiJcVkstbCxYb/cva4hHfnKuEm2bU8dZVu7PLAIqQrh9bt+8CfeanD1EamIx+gYsmzAydxmsjQjQcgwFBq4aZzmIo1ckRhc6U4F22QCn1hSlYTnfjJH2O/2IEdW0jGDWZ7NER73QUdJR9osi0r6VnVZ3kOdpumSeUTCtxqtCsGKpQfO3U7oEpAkTXYJQ9XBdmWIsCZ1l9gfRrxP3oBIt/+2HNXufBJRpgBFW/ePh1YVfNcl1qS9qnEk0hYJkhlVnbCWbowK/UevyF10YUqK5RAP/0bKtOhIwul/hZxHKx1ZZMQknUZYkB0doINeDg6zmR7EJB+BS3ykAXD1lgF8hjAlDrD7ougEuM1d4eXBOpKNI0ipBaEEEd1beaOUqKR2jF426xdVoVGJwQ92GL4Galsp5jzYonfugg4fSDpAU/uv2Ss0NgJvzeOh6BpmwcZVOIwAt9ogvZcs3zjjXD8YVtvb/ocnHE65f/3JCnFvs45mdy2OHtY1SwoCNkM9FQOULRYIqGVVfPZqJzipvz55ImRHCTM4YdtrRaZVvTfHabC1qKIkFk+0cr6b0scVOQQtyZc632FPl8bNJkiwqD/yNHwnIqr+DEhJyAGEOt7p0cXtRHUlzZKZuERTYGakeYGkKOhZKYs=";

}
