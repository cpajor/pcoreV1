package com.github.cPajor.pcore.klan;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class KlanListeners implements Listener {
	
	@EventHandler
	public void cla1(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
				Klan d1l = KlanSys.getKlanByMember(e.getDamager().getName());
				Klan d2l = KlanSys.getKlanByMember(e.getEntity().getName());
				if (d1l.getOwner().equalsIgnoreCase(d2l.getOwner())) {
					e.setCancelled(true);
					return;
				}
			}
			if (e.getDamager().getType() == EntityType.ARROW || e.getDamager().getType() == EntityType.SPECTRAL_ARROW) {				
				Projectile pr = (Projectile) e.getDamager();
				if (!(pr.getShooter() instanceof Player)) {
					return;
				}
				Player pp = (Player) pr.getShooter();
				Klan d1l = KlanSys.getKlanByMember(pp.getName());
				Klan d2l = KlanSys.getKlanByMember(e.getEntity().getName());
				if (d1l.getOwner().equalsIgnoreCase(d2l.getOwner())) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void gfdjdhgskkk(InventoryClickEvent e) {
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lklan "))) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			String[] a = e.getView().getTitle().split(" ");
			Klan d = KlanSys.getKlanByMember(a[1]);
			if (a.length > 2) {
				boolean b = d.getPermission(a[3], e.getSlot() + 1) == 1;
				d.setPermission(a[3], e.getSlot() + 1, b ? 0 : 1);
				Inventory inv = Bukkit.createInventory(p, 9, e.getView().getTitle());
				inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(a[3], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(a[3], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(a[3], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(a[3], Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				/*
				inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(a[3], Klan.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(a[3], Klan.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(a[3], Klan.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(a[3], Klan.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(a[3], Klan.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				*/p.openInventory(inv);
			} else {
				if (e.getSlot() < 20) {
					String entry = e.getCurrentItem().getTranslationKey().replaceFirst(Colors.fix("&4"), "");
					if (entry.contains("Wolne miejsce")) {
						return;
					}
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lklan " + d.getOwner() + " : " + entry));
					inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(entry, Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(entry, Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(entry, Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(entry, Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					
					/*
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(entry, Klan.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(entry, Klan.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(entry, Klan.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(entry, Klan.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(entry, Klan.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					*/
					p.openInventory(inv);
				}
			}
		}
	}
}
