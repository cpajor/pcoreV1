package com.github.cPajor.pcore.klan;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class KlanCommand extends ACmd {

	public KlanCommand(String s) {
		super(s);
	} 
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("zaloz")) {
				Player p = (Player) sender;
				if (!p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 64)) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz przedmiotow na klan!"));
					return true;
				}
				if (KlanSys.getklanByOwner(p.getName()) != null) {
					p.sendMessage(Colors.fix("&p &4Posiadasz juz klan!"));
					return true;
				}
				p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
				Klan d = new Klan(p);
				KlanSys.cached.add(d);
				d.save();
				p.sendMessage(Colors.fix("&p &7klan zalozona!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("panel")) {
				Klan d = KlanSys.getklanByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
					return true; 
				}
				Player p = (Player) sender;
				Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lklan " + sender.getName().toLowerCase()));
				for (int i = 0; i < 20; i++) {
					String v = d.members.size() > i ? d.members.get(i) : null;
					inv.setItem(i, new CItem(Material.PLAYER_HEAD).setName(Colors.fix("&4" + (v == null ? "Wolne miejsce" : v))).setSkullOwner(v != null ? v : "null").toIs());
				}
				inv.setItem(35, new CItem(Material.COAL_BLOCK).setName(Colors.fix("&4Uprawnienia dla gosci")).toIs());
				p.openInventory(inv);
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("dodaj")) {
				Klan d = KlanSys.getklanByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
					return true; 
				}
				if (d.members.size() > 20) {
					sender.sendMessage(Colors.fix("&p &4Osiagnieto limit czlonkow klanu!"));
					return true; 
				}
				if (d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz jest juz czlonkiem Twojej klanu!"));
					return true; 
				}
				d.members.add(args[1]);
				d.save();
				sender.sendMessage(Colors.fix("&p &4Podany gracz zostal dodany do Twojej klanu!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("panel")) {
				Klan d = KlanSys.getklanByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
					return true; 
				}
				if (!d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz nie jest czlonkiem Twojej klanu!"));
					return true; 
				}
				Player p = (Player) sender;
				Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lklan " + sender.getName().toLowerCase() + " : " + args[1]));
				inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(args[1], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(args[1], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(args[1], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(args[1], Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				p.openInventory(inv);
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				Klan d = KlanSys.getklanByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
					return true; 
				}
				if (!d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz nie jest czlonkiem Twojej klanu!"));
					return true; 
				}
				d.members.remove(args[1]);
				d.save();
				sender.sendMessage(Colors.fix("&p &4Podany gracz zostal usuniety do Twojej klanu!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("info")) {
				Klan d = KlanSys.getklanByOwner(args[1]);
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Podana klan nie istnieje"));
				} else {
					sender.sendMessage(Colors.fix("&p &fklan gracza " + args[1]));
					sender.sendMessage(Colors.fix("&p &fCzlonkowie " + d.members.toString()));
					if (sender.hasPermission("cmd.klan.admin")) {	
					}
				}
				return true;
			}
		}
		if (args.length >= 2) {
			if (args[0].equalsIgnoreCase("admin")) {
				if (!sender.hasPermission("cmd.klan.admin")) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz permisji!"));
					return true;
				}
				if (args[1].equalsIgnoreCase("gui")) {
					Player p = (Player) sender;
					Klan d = KlanSys.getklanByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &7Nie ma takiego klanu!"));
						return true;
					}
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lklan " + d.getOwner() + " : " + sender.getName()));
					inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(sender.getName(), Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(sender.getName(), Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(sender.getName(), Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(sender.getName(), Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					
					/*inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(sender.getName(), Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(sender.getName(), Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(sender.getName(), Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(sender.getName(), Klan.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					//inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(sender.getName(), Klan.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					*/
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("panel") && args.length == 3) {
					Klan d = KlanSys.getklanByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
						return true; 
					}
					Player p = (Player) sender;
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lklan " + d.getOwner() + " : " + args[3]));
					inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(args[3], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(args[3], Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					/*
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(args[3], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(args[3], Klan.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					*/
					//inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(args[3], Klan.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("panel")) {
					Klan d = KlanSys.getklanByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
						return true; 
					}
					Player p = (Player) sender;
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lklan " + d.getOwner() + " : " + sender.getName()));
					inv.setItem(0, new CItem(Material.ENCHANTED_BOOK).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BOOK).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.LECTERN).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(args[3], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.KNOWLEDGE_BOOK).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(args[3], Klan.PANEL_PANELOTHER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					/*inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Dodawanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_ADDUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Usuwanie czlonkow")).addLore(d.getPermission(args[3], Klan.PANEL_REMOVEUSER) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Dostep do panelu")).addLore(d.getPermission(args[3], Klan.PANEL_USEPANEL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("Zmiana uprawnien innym czlonkom")).addLore(d.getPermission(args[3], Klan.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					*///inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(args[3], Klan.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("del") && args.length == 3) {
					Klan d = KlanSys.getklanByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz klanu!"));
						return true; 
					}
					d.delete();
					KlanSys.cached.remove(d);
					sender.sendMessage(Colors.fix("&p &7klan usunieta"));
					return true;
				}
			}
		}
		sender.sendMessage(Colors.fix("&p &e/klan &7- &aWysyla tê wiadomoœæ."));
		sender.sendMessage(Colors.fix("&p &e/klan zaloz &7- &aZaklada klan."));
		sender.sendMessage(Colors.fix("&p &e/klan usun &7- &aUsuwa klan."));
		//sender.sendMessage(Colors.fix("&p &e/klan skarbiec &7- &aOtwiera skarbiec klanu."));
		sender.sendMessage(Colors.fix("&p &e/klan dodaj (nick) &7- &aDodaje wybran¹ osobê do klanu."));
		sender.sendMessage(Colors.fix("&p &e/klan wyrzuc (nick) &7- &aUsuwa wybran¹ osobê z klanu."));
		sender.sendMessage(Colors.fix("&p &e/klan panel &7- &aOtwiera panel zarz¹dzania klanem."));
		sender.sendMessage(Colors.fix("&p &e/klan panel (nick) &7- &aOtwiera uprawnienia dla gracza."));
		sender.sendMessage(Colors.fix("&p &e/klan info &7- &aPokazuje informacje o klanie."));
		return true;
	}
	
}
