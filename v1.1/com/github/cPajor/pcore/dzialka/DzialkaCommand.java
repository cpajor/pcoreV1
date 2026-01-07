package com.github.cPajor.pcore.dzialka;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.core.data.Title;
import com.github.cPajor.pcore.data.util.Colors;

public class DzialkaCommand extends ACmd {

	public DzialkaCommand(String s) {
		super(s);
	}
	
	public void tacker(Player p, Location l, int h, Location t) {
		if (move(p.getLocation(), l)) {
			Title.sendTitle(p, " ", Colors.fix("&p &7Teleportacja anulowana!"));
			return;
		}
		if (h <= -1) {
			p.teleport(t);
			return;
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Title.sendTitle(p, " ", Colors.fix("&p &7Teleportacja za " + h + " sekund!"));
				tacker(p, l, h - 1, t);
			}
		}.runTaskLater(CReg.getPlugin(), 20);
	}

	public static boolean move( Location l,  Location x) {
        return l.getBlockX() != x.getBlockX() || l.getBlockY() != x.getBlockY() || l.getBlockZ() != x.getBlockZ();
    }
	
	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("granica")) {
				Player p = (Player) sender;
				if (p.hasCooldown(Material.BARRIER)) {
					p.sendMessage(Colors.fix("&p &4Poczekaj chwile przed nastepnym uzyciem tej komendy!"));
					return true;
				}
				p.setCooldown(Material.BARRIER, 500);
				Dzialka d1 = DzialkaSys.getDzialkaByLoc(p.getLocation());
				if (d1.isMember(p.getName()) || d1.getOwner().equalsIgnoreCase(p.getName())) {
					final WorldBorder prev = p.getWorldBorder();
					WorldBorder wb = Bukkit.createWorldBorder();
					wb.setSize((double)d1.size + 8.D);
					wb.setCenter(d1.x + 0.5, d1.z + 0.5);
					p.setWorldBorder(wb);
					new BukkitRunnable() {
						@Override
						public void run() {
							p.setWorldBorder(prev);
						}
					}.runTaskLater(CReg.getPlugin(), 200);
				} else {
					p.sendMessage(Colors.fix("&p &4Nie nalezysz do tej dzialki!"));
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("miejsce")) {
				Player p = (Player) sender;
				if (DzialkaSys.canCreateDzialka1(p.getLocation())) {
					p.sendMessage(Colors.fix("&p &4Tutaj mozna zalozyc dzialke"));
				} else {
					p.sendMessage(Colors.fix("&p &4To nie jest miejsce na dzialke"));
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("sethome")) {
				Player p = (Player) sender;
				if (DzialkaSys.getDzialkaByOwner(p.getName()) == null) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki"));
					return true;
				}
				Dzialka d1 = DzialkaSys.getDzialkaByOwner(p.getName());
				Dzialka d2 = DzialkaSys.getDzialkaByLoc(p.getLocation());
				if (d2 == null) {
					p.sendMessage(Colors.fix("&p &4To nie Twoja dzialka"));
					return true;
				}
				if (d1.x != d2.x || d1.z != d2.z) {
					p.sendMessage(Colors.fix("&p &4To nie Twoja dzialka"));
					return true;
				}
				d1.setHome(p.getLocation());
				d1.save();
				return true;
			}
			if (args[0].equalsIgnoreCase("home")) {
				Player p = (Player) sender;
				Dzialka d = DzialkaSys.getDzialkaByOwner(p.getName());
				if (d == null) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true;
				}
				tacker(p, p.getLocation(), 6, d.getHome());
				return true;
			}
			if (args[0].equalsIgnoreCase("powieksz")) {
				Player p = (Player) sender;
				Dzialka d = DzialkaSys.getDzialkaByOwner(p.getName());
				if (d == null) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true;
				}
				if (d.size > 25) {
					p.sendMessage(Colors.fix("&p &4Nie mozesz juz powiekszyc dzialki!"));
					return true;
				}
				int next = (int) (d.size * d.size * 0.75);
				if (!p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), next)) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz przedmiotow na dzialke! (" + next + " diamentow)"));
					return true;
				}
				while (next > 64) {
					p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
					next -= 64;
				}
				p.getInventory().removeItem(new ItemStack(Material.DIAMOND, next));
				d.size++;
				d.save();
				p.sendMessage(Colors.fix("&p &7Pomyslnie powiekszono dzialke!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("zaloz")) {
				Player p = (Player) sender;
				if (!p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 64)) {
					p.sendMessage(Colors.fix("&p &4Nie posiadasz przedmiotow na dzialke!"));
					return true;
				}
				if (DzialkaSys.getDzialkaByOwner(p.getName()) != null) {
					p.sendMessage(Colors.fix("&p &4Posiadasz juz dzialke!"));
					return true;
				}
				if (!DzialkaSys.canCreateDzialka1(p.getLocation())) {
					p.sendMessage(Colors.fix("&p &4To nie jest miejsce na dzialke"));
					return true;
				} 
				p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
				Dzialka d = new Dzialka(p);
				DzialkaSys.cachedDzialki.add(d);
				d.save();
				p.sendMessage(Colors.fix("&p &7Dzialka zalozona!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("panel")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true; 
				}
				Player p = (Player) sender;
				Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lDzialka " + sender.getName().toLowerCase()));
				for (int i = 0; i < 20; i++) {
					String v = d.members.size() > i ? d.members.get(i) : null;
					inv.setItem(i, new CItem(Material.PLAYER_HEAD).setName(Colors.fix("&4" + (v == null ? "Wolne miejsce" : v))).setSkullOwner(v != null ? v : "null").toIs());
				}
				inv.setItem(34, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Status dzialki: " + (d.passive ? Colors.fix("&cAgresywna") : Colors.fix("&aPasywna")))).toIs());
				inv.setItem(35, new CItem(Material.COAL_BLOCK).setName(Colors.fix("&4Uprawnienia dla gosci")).toIs());
				p.openInventory(inv);
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("home")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(args[1]);
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &7Nie nalezysz do tej dzialki!"));
					return true; 
				}
				if (d.isMember(sender.getName())) {
					Player p = (Player) sender;
					tacker(p, p.getLocation(), 6, d.getHome());
				} else {
					sender.sendMessage(Colors.fix("&p &7Nie nalezysz do tej dzialki!"));
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("dodaj")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true; 
				}
				if (d.members.size() > 20) {
					sender.sendMessage(Colors.fix("&p &4Osiagnieto limit czlonkow dzialki!"));
					return true; 
				}
				if (d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz jest juz czlonkiem Twojej dzialki!"));
					return true; 
				}
				d.members.add(args[1]);
				d.save();
				sender.sendMessage(Colors.fix("&p &4Podany gracz zostal dodany do Twojej dzialki!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("panel")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true; 
				}
				if (!d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz nie jest czlonkiem Twojej dzialki!"));
					return true; 
				}
				Player p = (Player) sender;
				Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + sender.getName().toLowerCase() + " : " + args[1]));
				inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(args[1], Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(args[1], Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(args[1], Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(args[1], Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(args[1], Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				p.openInventory(inv);
				return true;
			}
			if (args[0].equalsIgnoreCase("usun")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(sender.getName());
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
					return true; 
				}
				if (!d.isMember(args[1])) {
					sender.sendMessage(Colors.fix("&p &4Podany gracz nie jest czlonkiem Twojej dzialki!"));
					return true; 
				}
				d.members.remove(args[1]);
				d.save();
				sender.sendMessage(Colors.fix("&p &4Podany gracz zostal usuniety do Twojej dzialki!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("info")) {
				Dzialka d = DzialkaSys.getDzialkaByOwner(args[1]);
				if (d == null) {
					sender.sendMessage(Colors.fix("&p &4Podana dzialka nie istnieje"));
				} else {
					sender.sendMessage(Colors.fix("&p &fDzialka gracza " + args[1]));
					sender.sendMessage(Colors.fix("&p &fCzlonkowie " + d.members.toString()));
					if (sender.hasPermission("cmd.dzialka.admin")) {	
						sender.sendMessage(Colors.fix("&p &fX: " + d.x));
						sender.sendMessage(Colors.fix("&p &fZ: " + d.z));
						sender.sendMessage(Colors.fix("&p &fSize: " + d.size));
					}
				}
				return true;
			}
		}
		if (args.length >= 2) {
			if (args[0].equalsIgnoreCase("admin")) {
				if (!sender.hasPermission("cmd.dzialka.admin")) {
					sender.sendMessage(Colors.fix("&p &4Nie posiadasz permisji!"));
					return true;
				}
				if (args[1].equalsIgnoreCase("gui")) {
					Player p = (Player) sender;
					Dzialka d = DzialkaSys.getDzialkaByLoc(p.getLocation());
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &7Nie stoisz na dzialce!"));
						return true;
					}
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + d.getOwner() + " : " + sender.getName()));
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(sender.getName(), Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(sender.getName(), Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(sender.getName(), Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(sender.getName(), Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(sender.getName(), Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("tp") && args.length == 3) {
					Dzialka d = DzialkaSys.getDzialkaByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Podana dzialka nie istnieje"));
					} else {
						((Player) sender).teleport(d.getHome());
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("panel") && args.length == 3) {
					Dzialka d = DzialkaSys.getDzialkaByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
						return true; 
					}
					Player p = (Player) sender;
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + d.getOwner() + " : " + args[3]));
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(args[3], Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(args[3], Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(args[3], Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(args[3], Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(args[3], Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("panel")) {
					Dzialka d = DzialkaSys.getDzialkaByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
						return true; 
					}
					Player p = (Player) sender;
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + d.getOwner() + " : " + sender.getName()));
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(args[3], Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(args[3], Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(args[3], Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(args[3], Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(args[3], Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
					return true;
				}
				if (args[1].equalsIgnoreCase("del") && args.length == 3) {
					Dzialka d = DzialkaSys.getDzialkaByOwner(args[2]);
					if (d == null) {
						sender.sendMessage(Colors.fix("&p &4Nie posiadasz dzialki!"));
						return true; 
					}
					d.delete();
					DzialkaSys.cachedDzialki.remove(d);
					sender.sendMessage(Colors.fix("&p &7Dzialka usunieta"));
					return true;
				}
			}
		}
		sender.sendMessage(Colors.fix("&p &e/dzialka &7- &aWysyla tê wiadomoœæ."));
		sender.sendMessage(Colors.fix("&p &e/dzialka zaloz &7- &aZaklada dzialkê."));
		sender.sendMessage(Colors.fix("&p &e/dzialka usun &7- &aUsuwa dzialkê."));
		sender.sendMessage(Colors.fix("&p &e/dzialka powieksz &7- &aPowiêksza dzialkê."));
		//sender.sendMessage(Colors.fix("&p &e/dzialka skarbiec &7- &aOtwiera skarbiec dzialki."));
		sender.sendMessage(Colors.fix("&p &e/dzialka dodaj (nick) &7- &aDodaje wybran¹ osobê do dzialki."));
		sender.sendMessage(Colors.fix("&p &e/dzialka wyrzuc (nick) &7- &aUsuwa wybran¹ osobê z dzialki."));
		sender.sendMessage(Colors.fix("&p &e/dzialka home &7- &aTeleportuje na dom dzialki."));
		sender.sendMessage(Colors.fix("&p &e/dzialka sethome &7- &aZmienia dom dzialki."));
		sender.sendMessage(Colors.fix("&p &e/dzialka miejsce &7- &aPokazuje wolne miejsce."));
		sender.sendMessage(Colors.fix("&p &e/dzialka panel &7- &aOtwiera panel zarz¹dzania dzialk¹."));
		sender.sendMessage(Colors.fix("&p &e/dzialka panel (nick) &7- &aOtwiera uprawnienia dla gracza."));
		sender.sendMessage(Colors.fix("&p &e/dzialka info &7- &aOtwiera informacje o dzialce."));
		sender.sendMessage(Colors.fix("&p &e/dzialka granica &7- &aPokazuje granice dzialek."));
		return true;
	}
	
}
