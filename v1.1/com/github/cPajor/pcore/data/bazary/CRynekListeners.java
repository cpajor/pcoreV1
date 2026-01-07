package com.github.cPajor.pcore.data.bazary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.man.UserMan;
import com.github.cPajor.pcore.data.user.User;
import com.github.cPajor.pcore.data.util.Colors;

import net.wesjd.anvilgui.AnvilGUI;

public class CRynekListeners implements Listener {
	
	public static void expiried(Player p) {
		Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lTwoje wygasle oferty"));
		int i = 10; 
		List<RynekItem> in = Rynek.getRynek(p.getName()).items;
		for (RynekItem it : in) {
			if (it.time > System.currentTimeMillis()) continue;
			ItemStack is = it.is.clone();
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&6&lCena: " + it.price));
			ll.add(Colors.fix("&9&lOID:") + it.getId());
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
			if (i >= 16) i = 19;
			if (i > 14) continue;
		}
		inv.setItem(30, new CItem(Material.NETHER_STAR).setName(Colors.fix("&4&lDodaj oferte")).toIs());
		inv.setItem(31, new CItem(Material.PAPER).setName(Colors.fix("&4&lTwoj balans: ")).addLore(Colors.fix("&f&l$ " + UserMan.getUser(p.getName()).getMoney())).toIs());
		inv.setItem(32, new CItem(Material.CHEST).setName(Colors.fix("&4&lAktywne oferty")).toIs());	
		for (i = 0; i < 36; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	public static void sell(Player p) {
		if (p.getInventory().getItemInMainHand() == null || p.getInventory().getItemInMainHand().getType() == Material.AIR) {
			p.sendMessage(Colors.fix("&p &7Ten towar nie moze zostac sprzedany"));
			return;
		}
		Rynek b = Rynek.getRynek(p.getName());
		if (b.items.size() > 14) {
			p.sendMessage(Colors.fix("&p &7Osiagnieto limit wystawionych towarow"));
			return;
		}
		new AnvilGUI.Builder()
		.plugin(CReg.getPlugin())
		.title(Colors.fix("&4Wpisz cene:"))
		.text(" ")
		.itemLeft(new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs())
		.onClick((ti, us) -> {
			int u = Integer.parseInt(us.getText().replaceAll(" ", ""));
			new BukkitRunnable() {
				public void run() {
					b.addBI(p.getInventory().getItemInMainHand(), u);
					b.save();
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
					p.sendMessage(Colors.fix("&p &7Pomyslnie wystawiono towar!"));
				}
			}.runTaskAsynchronously(CReg.getPlugin());
			return Arrays.asList(AnvilGUI.ResponseAction.close());
		}).open(p);
	}
	
	public static void guiConfirm(Player p, ItemStack is, int price, boolean ex) {
		Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lZfinalizuj sprzedaz"));
		inv.setItem(0, new CItem(Material.RED_CANDLE).setName(Colors.fix("&c&lAnuluj")).toIs());
		inv.setItem(8, new CItem(Material.GREEN_CANDLE).setName(Colors.fix("&c&lWystaw")).toIs());
		ItemStack iss = is.clone();
		if (!is.hasItemMeta()) {
			is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
		}
		List<String> ll = is.getItemMeta().getLore();
		if (ll == null) {
			ll = new ArrayList<String>();
		}
		ll.add(Colors.fix("&9&kMID:") + p.getName());
		ll.add(Colors.fix("&6&lCena: " + price));
		ll.add(Colors.fix("&6&lHandlarz: " + p.getName()));
		ll.add(Colors.fix("&9&lOID: X"));
		ItemMeta im = is.getItemMeta();
		im.setLore(ll);
		is.setItemMeta(im);
		inv.setItem(3, iss);
		inv.setItem(5, is);
		for (int i = 0; i < 9; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(ex ? Material.MAGENTA_STAINED_GLASS_PANE : Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	public static void guiSell2(Player p, ItemStack is, int price, boolean ex) {
		Inventory inv = Bukkit.createInventory(p, 27, Colors.fix("&4&lPotwierdz sprzedaz"));
		inv.setItem(12, is);
		inv.setItem(13, new CItem(Material.PAPER).setName(Colors.fix("&4&lCena")).addLore(Colors.fix("&f&l: " + price)).toIs());
		if (ex) inv.setItem(14, new CItem(Material.MOJANG_BANNER_PATTERN).addEnchant(Enchantment.DURABILITY, 10).setName(Colors.fix("&4&lWyroznienie oferty")).addLore(Colors.fix("&f&lWlaczone")).toIs());
		else inv.setItem(14, new CItem(Material.MOJANG_BANNER_PATTERN).setName(Colors.fix("&4&lWyroznienie oferty")).addLore(Colors.fix("&f&lWylaczone")).toIs());
		inv.setItem(10, new CItem(Material.RED_CANDLE).setName(Colors.fix("&c&lAnuluj")).toIs());
		inv.setItem(16, new CItem(Material.GREEN_CANDLE).setName(Colors.fix("&c&lWystaw")).toIs());
		for (int i = 0; i < 27; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	public static void guiSell(Player p, ItemStack is, boolean ex) {
		if (is == null || is.getType() == Material.AIR) {
			p.sendMessage(Colors.fix("&p &7Ten towar nie moze zostac sprzedany"));
			return;
		}
		Rynek b = Rynek.getRynek(p.getName());
		if (b.items.size() > 14) {
			p.sendMessage(Colors.fix("&p &7Osiagnieto limit wystawionych towarow"));
			return;
		}
		new AnvilGUI.Builder()
		.plugin(CReg.getPlugin())
		.title(Colors.fix("&4Wpisz cene:"))
		.text(" ")
		.itemLeft(new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs())
		.onClick((ti, us) -> {
			int u = Integer.parseInt(us.getText().replaceAll(" ", ""));
			if (u < 1) {
				guiSell(p, is, ex);
			} else {
				guiSell2(p, is, u, ex);
			}
			return Arrays.asList(AnvilGUI.ResponseAction.close());
		}).open(p);
	}
	
	public static void guiChoose(Player p) {
		Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lWybierz przedmiot"));
		for (int i = 0; i < 36; i++) {
			inv.setItem(i, p.getInventory().getItem(i));
		}
		p.openInventory(inv);
	}
	
	public static void guisnz(Player p, int page, String u) {
		List<RynekItem> bb = forName(u, 27 * page);
		if (bb.size() == 0) {
			p.closeInventory();
			p.sendMessage(Colors.fix("&p &7Nie znaleziono podanego towaru!"));
			return;
		}
		//
		Inventory inv = Bukkit.createInventory(p, 54, Colors.fix("&4&lRynek znajdz " + page + " " + u));
		int i = 0;
		List<RynekItem> iv = getExtraNode(9, 0);
		for (RynekItem it : iv) {
			ItemStack is = it.is.clone();
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&9&kMID:") + it.getName());
			ll.add(Colors.fix("&6&lCena: " + it.price));
			ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
			ll.add(Colors.fix("&9&lOID:") + it.getId());
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
		}
		for (i = 9; i < 18; i++) {
			inv.setItem(i, new CItem(Material.MAGENTA_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
		}
		i = 18;
		for (RynekItem bi : bb) {
			if (bi.is == null || bi.time < System.currentTimeMillis()) continue; 
			ItemStack is = bi.is.clone();
			if (is.getItemMeta().getDisplayName().equalsIgnoreCase(u)) continue;
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&9&kMID:") + bi.getName());
			ll.add(Colors.fix("&6&lCena: " + bi.price));
			ll.add(Colors.fix("&6&lHandlarz: " + bi.getName()));
			ll.add(Colors.fix("&9&lOID:") + bi.getId());
			ll.add(Colors.fix("&c&lWygasa: &7") + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(bi.time));
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
			if (i > 44) break;
		}
		inv.setItem(49, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj inny przedmiot")).toIs());

		inv.setItem(48, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l<--")).toIs());
		inv.setItem(50, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l-->")).toIs());
		for (i = 0; i < 54; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	public static void search(Player p) {
		new AnvilGUI.Builder()
		.plugin(CReg.getPlugin())
		.title(Colors.fix("&4Wyszukaj przedmiot:"))
		.text(" ")
		.itemLeft(new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs())
		.onClick((ti, us) -> {
			String u = us.getText().replaceAll(" ", "");
			guisnz(p, 0, u);
			return Arrays.asList(AnvilGUI.ResponseAction.close());
		}).open(p);
	}
	
	public static void byNick(Player p) {
		new AnvilGUI.Builder()
		.plugin(CReg.getPlugin())
		.title(Colors.fix("&4Wpisz nick handlarza:"))
		.text(" ")
		.itemLeft(new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs())
		.onClick((ti, us) -> {
			String u = us.getText().replaceAll(" ", "");
			new BukkitRunnable() {
				public void run() {
					Rynek b = Rynek.getRynek(u);
					new BukkitRunnable() {
						public void run() {
							if (b == null || b.items.size() == 0) {
								us.getPlayer().sendMessage(Colors.fix("&p &7Podany handlarz nic nie sprzedaje."));
								return;
							}	
							
							int i = 10;
							Inventory inv = Bukkit.createInventory(p, 54, Colors.fix("&4&lRynek gracza ") + b.name);
							List<RynekItem> iv = getExtraNode(7, 0);
							for (RynekItem it : iv) {
								ItemStack is = it.is.clone();
								if (!is.hasItemMeta()) {
									is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
								}
								List<String> ll = is.getItemMeta().getLore();
								if (ll == null) {
									ll = new ArrayList<String>();
								}
								ll.add(Colors.fix("&9&kMID:") + it.getName());
								ll.add(Colors.fix("&6&lCena: " + it.price));
								ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
								ll.add(Colors.fix("&9&lOID:") + it.getId());
								ItemMeta im = is.getItemMeta();
								im.setLore(ll);
								is.setItemMeta(im);
								inv.setItem(i, is);
								i++;
							}
							for (i = 19; i < 26; i++) {
								inv.setItem(i, new CItem(Material.MAGENTA_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
							}
							i = 28;
							for (RynekItem it : b.items) {
								if (it.time < System.currentTimeMillis()) continue;
								ItemStack is = it.is.clone();
								if (!is.hasItemMeta()) {
									is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
								}
								List<String> ll = is.getItemMeta().getLore();
								if (ll == null) {
									ll = new ArrayList<String>();
								}
								ll.add(Colors.fix("&9&kMID:") + it.getName());
								ll.add(Colors.fix("&6&lCena: " + it.price));
								ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
								ll.add(Colors.fix("&9&lOID:") + it.getId());
								ItemMeta im = is.getItemMeta();
								im.setLore(ll);
								is.setItemMeta(im);
								inv.setItem(i, is);
								i++;
								if (i >= 34) i = 37;
							}
							inv.setItem(49, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj innego gracza")).toIs());
							for (i = 0; i < 54; i++) {
								if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
									inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
								}
							}
							p.openInventory(inv);
						}
					}.runTask(CReg.getPlugin());
				}
			}.runTaskAsynchronously(CReg.getPlugin());
			return Arrays.asList(AnvilGUI.ResponseAction.close());
		}).open(p);
	}

	@EventHandler
	public void init1(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() != null && e.getRightClicked().getCustomName() != null) {
			if (e.getRightClicked().getCustomName().equalsIgnoreCase(Colors.fix("&4&lRynek"))) {
				e.setCancelled(true);
				guiRynekMain(e.getPlayer(), 0);
			}
		}
	}
	
	public static void myOffers(Player p) {
		Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lTwoje oferty"));
		int i = 10; 
		List<RynekItem> in = Rynek.getRynek(p.getName()).items;
		for (RynekItem it : in) {
			if (it.time < System.currentTimeMillis()) continue;
			ItemStack is = it.is.clone();
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&6&lCena: " + it.price));
			ll.add(Colors.fix("&9&lOID:") + it.getId());
			ll.add(Colors.fix("&c&lWygasa: &7") + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(it.time));
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
			if (i >= 17) i = 19;
		}
		inv.setItem(30, new CItem(Material.NETHER_STAR).setName(Colors.fix("&4&lDodaj oferte")).toIs());
		inv.setItem(31, new CItem(Material.PAPER).setName(Colors.fix("&4&lTwoj balans: ")).addLore(Colors.fix("&f&l$ " + UserMan.getUser(p.getName()).getMoney())).toIs());
		inv.setItem(32, new CItem(Material.ENDER_CHEST).setName(Colors.fix("&4&lWygasle oferty")).toIs());	
		for (i = 0; i < 36; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	public static void guiRynekMain(Player p, int page) {
		Inventory inv = Bukkit.createInventory(p, 54, Colors.fix("&4&lRynek strona 0"));
		int i = 0; 
		List<RynekItem> iv = getExtraNode(9, 0);
		for (RynekItem it : iv) {
			ItemStack is = it.is.clone();
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&9&kMID:") + it.getName());
			ll.add(Colors.fix("&6&lCena: " + it.price));
			ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
			ll.add(Colors.fix("&9&lOID:") + it.getId());
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
		}
		for (i = 9; i < 18; i++) {
			inv.setItem(i, new CItem(Material.MAGENTA_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
		} 
		List<RynekItem> in = getNode(27, 0);
		for (RynekItem it : in) {
			ItemStack is = it.is.clone();
			if (!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
			}
			List<String> ll = is.getItemMeta().getLore();
			if (ll == null) {
				ll = new ArrayList<String>();
			}
			ll.add(Colors.fix("&9&kMID:") + it.getName());
			ll.add(Colors.fix("&6&lCena: " + it.price));
			ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
			ll.add(Colors.fix("&9&lOID:") + it.getId());
			ll.add(Colors.fix("&c&lWygasa: &7") + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(it.time));
			ItemMeta im = is.getItemMeta();
			im.setLore(ll);
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
		}
		inv.setItem(45, new CItem(Material.CHEST).setName(Colors.fix("&4&lAktywne oferty")).toIs());
		inv.setItem(48, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l<--")).toIs());
		inv.setItem(50, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l-->")).toIs());
		inv.setItem(49, new CItem(Material.NETHER_STAR).setName(Colors.fix("&4&lDodaj oferte")).toIs());
		inv.setItem(53, new CItem(Material.ENDER_CHEST).setName(Colors.fix("&4&lWygasle oferty")).toIs());
		
		inv.setItem(46, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nicku")).toIs());
		inv.setItem(52, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nazwie")).toIs());
		for (i = 0; i < 54; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
			}
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void lcck(InventoryClickEvent e) {
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lTwoje wygasle oferty"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lDodaj oferte"))) {
				guiChoose((Player)e.getWhoClicked());
				return;
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lAktywne oferty"))) {
				myOffers((Player)e.getWhoClicked());
				return;
			}
			if (!e.getCurrentItem().getItemMeta().hasLore()) return;
			int oid = -1;
			for (String s : e.getCurrentItem().getItemMeta().getLore()) {
				if (s.startsWith(Colors.fix("&9&lOID:"))) {
					oid = Integer.parseInt(s.replaceAll(Colors.fix("&9&lOID:"), ""));
				}
			}
			if (oid > -1) {
				Rynek b = Rynek.getRynek(e.getWhoClicked().getName());
				RynekItem bi = b.getByOId(oid);
				ItemStack is = bi.is.clone();
				b.remove(bi.getId()); 
				e.getWhoClicked().getInventory().addItem(is);
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage(Colors.fix("&p &7Odebrano oferte!"));
			}
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lRynek znajdz "))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getSlot() == 49) {
				e.getWhoClicked().closeInventory();
				search((Player)e.getWhoClicked());
				return;
			}
			if (e.getSlot() == 48) {
				int page = Integer.parseInt(e.getView().getTitle().split(" ")[2]) - 1;
				if (page < 0) return;
				String u = e.getView().getTitle().split(" ")[3];
				guisnz((Player)e.getWhoClicked(), page, u);
				return;
			}
			if (e.getSlot() == 50) {
				int page = Integer.parseInt(e.getView().getTitle().split(" ")[2]) + 1;
				String u = e.getView().getTitle().split(" ")[3];
				guisnz((Player)e.getWhoClicked(), page, u);
				return;
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getSlot() < 52) {
				int oid = -1;
				String mid = null;
				for (String s : e.getCurrentItem().getItemMeta().getLore()) {
					if (s.startsWith(Colors.fix("&9&kMID:"))) {
						mid = s.replaceAll(Colors.fix("&9&kMID:"), "");
					}
					if (s.startsWith(Colors.fix("&9&lOID:"))) {
						oid = Integer.parseInt(s.replaceAll(Colors.fix("&9&lOID:"), ""));
					}
				}
				if (oid > -1 && mid != null) {
					if (mid.equalsIgnoreCase(e.getWhoClicked().getName()) || e.getWhoClicked().getName().equalsIgnoreCase(mid)) {
						e.getWhoClicked().sendMessage(Colors.fix("&p &7Nie mozesz kupic towaru od siebie!"));
						e.getWhoClicked().closeInventory();
						return;
					}
					Rynek b = Rynek.getRynek(mid);
					RynekItem bi = b.getByOId(oid);
					User u = UserMan.getUser(e.getWhoClicked().getName());
					if (bi != null) {
						//
						if (u.getMoney() < bi.price) {
							e.getWhoClicked().sendMessage(Colors.fix("&p &7Nie stac cie na ten przedmiot!"));
							return;
						}
						e.getWhoClicked().closeInventory();
						if (!b.items.contains(bi) || !b.remove(oid)) {
							e.getWhoClicked().sendMessage(Colors.fix("&p &7Ten towar zostal juz kupiony!"));	
							return;
						}
						u.setMoney(u.getMoney() - bi.price);
						User u2 = UserMan.getUser(mid);
						u2.setMoney(u.getMoney() + (int) (bi.price * 0.9));
						e.getWhoClicked().getInventory().addItem(bi.is);
						b.save();
						if (Bukkit.getPlayer(mid) != null) {
							Bukkit.getPlayer(mid).sendMessage(Colors.fix("&p &7Ktos zakupil twoj towar."));
						}
					}
				}
			}
			return;
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lZfinalizuj sprzedaz"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getSlot() == 0) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getSlot() == 8) {
				Player p = (Player) e.getWhoClicked();
				Rynek b = Rynek.getRynek(p.getName());
				int i = 0;
				for (String s : e.getInventory().getItem(5).getItemMeta().getLore()) {
					if (s.startsWith(Colors.fix("&6&lCena: "))) {
						i = Integer.parseInt(s.replaceAll(Colors.fix("&6&lCena: "), ""));
					}
				}
				RynekItem vv = b.addBI(e.getInventory().getItem(3), i);
				b.save();
				vv.extra = false;// e.getInventory().getItem(1).getType() == Material.MAGENTA_STAINED_GLASS_PANE;
				ItemStack is = e.getInventory().getItem(3);
				p.closeInventory();
				p.getInventory().removeItem(is);
				p.sendMessage(Colors.fix("&p &7Pomyslnie wystawiono towar!"));
			}
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lWybierz przedmiot"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				e.getWhoClicked().closeInventory();
				guiSell2((Player)e.getWhoClicked(), e.getCurrentItem(), 1, false);
			}
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lPotwierdz sprzedaz"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getSlot() == 13) {
				boolean b = e.getInventory().getItem(14).getItemMeta().getLore().get(0).equalsIgnoreCase(Colors.fix("&f&lWlaczone"));
				guiSell((Player)e.getWhoClicked(), e.getInventory().getItem(12), b);
				return;
			}
			if (e.getCurrentItem() != null && e.getSlot() == 10) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem() != null && e.getSlot() == 14) {
				boolean b = !e.getInventory().getItem(14).getItemMeta().getLore().get(0).equalsIgnoreCase(Colors.fix("&f&lWlaczone"));
				if (b) e.getInventory().setItem(14, new CItem(Material.MOJANG_BANNER_PATTERN).addEnchant(Enchantment.DURABILITY, 10).setName(Colors.fix("&4&lWyroznienie oferty")).addLore(Colors.fix("&f&lWlaczone")).toIs());
				else e.getInventory().setItem(14, new CItem(Material.MOJANG_BANNER_PATTERN).setName(Colors.fix("&4&lWyroznienie oferty")).addLore(Colors.fix("&f&lWylaczone")).toIs());
				return;
			}
			if (e.getCurrentItem() != null && e.getSlot() == 16) {
				guiConfirm((Player)e.getWhoClicked(), e.getInventory().getItem(12), Integer.parseInt(e.getInventory().getItem(13).getItemMeta().getLore().get(0).replaceAll(Colors.fix("&f&l: "), "")), e.getInventory().getItem(14).getItemMeta().getLore().get(0).equalsIgnoreCase(Colors.fix("&f&lWlaczone")));
				return;
			}
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lTwoje oferty"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lDodaj oferte"))) {
				guiChoose((Player)e.getWhoClicked());
				return;
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lWygasle oferty"))) {
				expiried((Player)e.getWhoClicked());
				return;
			}
			if (e.getCurrentItem() != null && !e.getCurrentItem().getItemMeta().hasLore()) return;
			int oid = -1;
			for (String s : e.getCurrentItem().getItemMeta().getLore()) {
				if (s.startsWith(Colors.fix("&9&lOID:"))) {
					oid = Integer.parseInt(s.replaceAll(Colors.fix("&9&lOID:"), ""));
				}
			}
			if (oid > -1) {
				Rynek b = Rynek.getRynek(e.getWhoClicked().getName());
				RynekItem bi = b.getByOId(oid);
				ItemStack is = bi.is.clone();
				b.remove(bi.getId()); 
				e.getWhoClicked().getInventory().addItem(is);
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage(Colors.fix("&p &7Anulowano oferte!"));
			}
		}
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lRynek"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lWygasle oferty"))) {
				expiried((Player)e.getWhoClicked()); 
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lDodaj oferte"))) {
				guiChoose((Player)e.getWhoClicked());
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lAktywne oferty"))) {
				myOffers((Player)e.getWhoClicked());
			}
			String buf = e.getView().getTitle().replaceAll(Colors.fix("&4&lRynek strona "), "");
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getSlot() < 52) {
				int oid = -1;
				String mid = null;
				for (String s : e.getCurrentItem().getItemMeta().getLore()) {
					if (s.startsWith(Colors.fix("&9&kMID:"))) {
						mid = s.replaceAll(Colors.fix("&9&kMID:"), "");
					}
					if (s.startsWith(Colors.fix("&9&lOID:"))) {
						oid = Integer.parseInt(s.replaceAll(Colors.fix("&9&lOID:"), ""));
					}
				}
				if (oid > -1 && mid != null) {
					if (mid.equalsIgnoreCase(e.getWhoClicked().getName()) || e.getWhoClicked().getName().equalsIgnoreCase(mid)) {
						e.getWhoClicked().sendMessage(Colors.fix("&p &7Nie mozesz kupic towaru od siebie!"));
						e.getWhoClicked().closeInventory();
						return;
					}
					Rynek b = Rynek.getRynek(mid);
					RynekItem bi = b.getByOId(oid);
					User u = UserMan.getUser(e.getWhoClicked().getName());
					if (bi != null) {
						//
						if (u.getMoney() < bi.price) {
							e.getWhoClicked().sendMessage(Colors.fix("&p &7Nie stac cie na ten przedmiot!"));
							return;
						}
						e.getWhoClicked().closeInventory();
						if (!b.items.contains(bi) || !b.remove(oid)) {
							e.getWhoClicked().sendMessage(Colors.fix("&p &7Ten towar zostal juz kupiony!"));	
							return;
						}
						u.setMoney(u.getMoney() - bi.price);
						User u2 = UserMan.getUser(mid);
						u2.setMoney(u.getMoney() + (int) (bi.price * 0.9));
						e.getWhoClicked().getInventory().addItem(bi.is);
						b.save();
						if (Bukkit.getPlayer(mid) != null) {
							Bukkit.getPlayer(mid).sendMessage(Colors.fix("&p &7Ktos zakupil twoj towar."));
						}
					}
				}
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lWyszukaj innego gracza"))) {
				byNick((Player)e.getWhoClicked());
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lWyszukaj po nicku"))) {
				byNick((Player)e.getWhoClicked());
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&lWyszukaj po nazwie"))) {
				search((Player)e.getWhoClicked());
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&l-->"))) {
				int pi = Integer.parseInt(buf);
				if (pi * 27 > total() - 27) return;
				int i = 0;
				Inventory inv = Bukkit.createInventory(e.getWhoClicked(), 54, Colors.fix("&4&lRynek strona ") + (pi + 1));
				List<RynekItem> iv = getExtraNode(9, (pi + 1) * 9);
				for (RynekItem it : iv) {
					ItemStack is = it.is.clone();
					if (!is.hasItemMeta()) {
						is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
					}
					List<String> ll = is.getItemMeta().getLore();
					if (ll == null) {
						ll = new ArrayList<String>();
					}
					ll.add(Colors.fix("&9&kMID:") + it.getName());
					ll.add(Colors.fix("&6&lCena: " + it.price));
					ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
					ll.add(Colors.fix("&9&lOID:") + it.getId());
					ItemMeta im = is.getItemMeta();
					im.setLore(ll);
					is.setItemMeta(im);
					inv.setItem(i, is);
					i++;
				}
				for (i = 9; i < 18; i++) {
					inv.setItem(i, new CItem(Material.MAGENTA_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
				}
				List<RynekItem> in = getNode(27, (pi + 1) * 27);
				for (RynekItem it : in) {
					ItemStack is = it.is.clone();
					if (!is.hasItemMeta()) {
						is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
					}
					List<String> ll = is.getItemMeta().getLore();
					if (ll == null) {
						ll = new ArrayList<String>();
					}
					ll.add(Colors.fix("&9&kMID:") + it.getName());
					ll.add(Colors.fix("&6&lCena: " + it.price));
					ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
					ll.add(Colors.fix("&9&lOID:") + it.getId());
					ll.add(Colors.fix("&c&lWygasa: &7") + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(it.time));
					ItemMeta im = is.getItemMeta();
					im.setLore(ll);
					is.setItemMeta(im);
					inv.setItem(i, is);
					i++;
				}
				inv.setItem(45, new CItem(Material.CHEST).setName(Colors.fix("&4&lAktywne oferty")).toIs());
				inv.setItem(48, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l<--")).toIs());
				inv.setItem(50, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l-->")).toIs());
				inv.setItem(49, new CItem(Material.NETHER_STAR).setName(Colors.fix("&4&lDodaj oferte")).toIs());
				inv.setItem(53, new CItem(Material.ENDER_CHEST).setName(Colors.fix("&4&lWygasle oferty")).toIs());
				
				inv.setItem(46, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nicku")).toIs());
				inv.setItem(52, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nazwie")).toIs());
				for (i = 0; i < 54; i++) {
					if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
						inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
					}
				}
				e.getWhoClicked().openInventory(inv);
			}
			if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.fix("&4&l<--"))) {
				int pi = Integer.parseInt(buf);
				if (pi < 1) return;
				int i = 0;
				Inventory inv = Bukkit.createInventory(e.getWhoClicked(), 54, Colors.fix("&4&lRynek strona ") + (pi - 1));
				List<RynekItem> iv = getExtraNode(9, (pi - 1) * 9);
				for (RynekItem it : iv) {
					ItemStack is = it.is.clone();
					if (!is.hasItemMeta()) {
						is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
					}
					List<String> ll = is.getItemMeta().getLore();
					if (ll == null) {
						ll = new ArrayList<String>();
					}
					ll.add(Colors.fix("&9&kMID:") + it.getName());
					ll.add(Colors.fix("&6&lCena: " + it.price));
					ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
					ll.add(Colors.fix("&9&lOID:") + it.getId());
					ItemMeta im = is.getItemMeta();
					im.setLore(ll);
					is.setItemMeta(im);
					inv.setItem(i, is);
					i++;
				}
				for (i = 9; i < 18; i++) {
					inv.setItem(i, new CItem(Material.MAGENTA_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
				}
				List<RynekItem> in = getNode(27, (pi - 1) * 27);
				for (RynekItem it : in) {
					ItemStack is = it.is.clone();
					if (!is.hasItemMeta()) {
						is.setItemMeta(Bukkit.getItemFactory().getItemMeta(is.getType()));
					}
					List<String> ll = is.getItemMeta().getLore();
					if (ll == null) {
						ll = new ArrayList<String>();
					}
					ll.add(Colors.fix("&9&kMID:") + it.getName());
					ll.add(Colors.fix("&6&lCena: " + it.price));
					ll.add(Colors.fix("&6&lHandlarz: " + it.getName()));
					ll.add(Colors.fix("&9&lOID:") + it.getId());
					ll.add(Colors.fix("&c&lWygasa: &7") + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(it.time));
					ItemMeta im = is.getItemMeta();
					im.setLore(ll);
					is.setItemMeta(im);
					inv.setItem(i, is);
					i++;
				}
				inv.setItem(45, new CItem(Material.CHEST).setName(Colors.fix("&4&lAktywne oferty")).toIs());
				inv.setItem(48, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l<--")).toIs());
				inv.setItem(50, new CItem(Material.YELLOW_WOOL).setName(Colors.fix("&4&l-->")).toIs());
				inv.setItem(49, new CItem(Material.NETHER_STAR).setName(Colors.fix("&4&lDodaj oferte")).toIs());
				inv.setItem(53, new CItem(Material.ENDER_CHEST).setName(Colors.fix("&4&lWygasle oferty")).toIs());
				
				inv.setItem(46, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nicku")).toIs());
				inv.setItem(52, new CItem(Material.SPYGLASS).setName(Colors.fix("&4&lWyszukaj po nazwie")).toIs());
				for (i = 0; i < 54; i++) {
					if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
						inv.setItem(i, new CItem(Material.BLACK_STAINED_GLASS_PANE).setName(Colors.fix("&8")).toIs());
					}
				}
				e.getWhoClicked().openInventory(inv);
			}
		}
	}
	
	public static List<RynekItem> getNode(int m, int offset) {
		List<RynekItem> lst = new ArrayList<RynekItem>();
		int o = 0;
		for (Rynek b : Rynek.Ryneks) {
			for (RynekItem i : b.items) {
				if (o < offset) {
					o++;
					continue;
				}
				if (i.time > System.currentTimeMillis())
				if (lst.size() < m) lst.add(i); else return lst;
			}
		}
		return lst;
	}
	
	public static List<RynekItem> getExtraNode(int m, int offset) {
		List<RynekItem> lst = new ArrayList<RynekItem>();
		int o = 0;
		for (Rynek b : Rynek.Ryneks) {
			for (RynekItem i : b.items) {
				if (o < offset) {
					o++;
					continue;
				}
				if (i.time > System.currentTimeMillis())
				if (lst.size() < m) {
					if (i.extra) {
						lst.add(i); 
					}
				} else return lst;
			}
		}
		return lst;
	}

	public static List<RynekItem> forName(String m) {
		List<RynekItem> lst = new ArrayList<RynekItem>();
		for (Rynek b : Rynek.Ryneks) {
			for (RynekItem i : b.items) { 
				if (i.time > System.currentTimeMillis())
				if (i.is.getType().toString().toLowerCase().contains(m.toLowerCase()) || i.is.getItemMeta().getDisplayName().toLowerCase().contains(m.toLowerCase()) || i.is.getItemMeta().getDisplayName().toLowerCase().contentEquals(m.toLowerCase())) {
					lst.add(i);
				}
			}
		}
		return lst;
	}
	
	public static List<RynekItem> forName(String m, int offset) {
		List<RynekItem> lst = new ArrayList<RynekItem>();
		int o = 0;
		for (Rynek b : Rynek.Ryneks) {
			for (RynekItem i : b.items) {
				if (i.time > System.currentTimeMillis())
				if (i.is.getType().toString().toLowerCase().contains(m.toLowerCase()) || i.is.getItemMeta().getDisplayName().toLowerCase().contains(m.toLowerCase()) || i.is.getItemMeta().getDisplayName().toLowerCase().contentEquals(m.toLowerCase())) {
					if (o < offset) {
						o++;
						continue;
					}
					lst.add(i);
				}
			}
		}
		return lst;
	}
	
	public static int total() {
		int f = 0;
		for (Rynek b : Rynek.Ryneks) {
			for (RynekItem i : b.items) {
				if (i.time > System.currentTimeMillis()) f++;
			}
		}
		return f;
	}
	
	@EventHandler
	public void fix1(InventoryCloseEvent e) {
		ItemStack left = new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs();
		if (e.getPlayer().getInventory().containsAtLeast(left, 1)) {
			e.getPlayer().getInventory().remove(left);
		}
	}
	
	@EventHandler
	public void fix2(InventoryOpenEvent e) {
		ItemStack left = new CItem(Material.PAPER).addLore(Colors.fix("&8&l")).setName(" ").toIs();
		if (e.getPlayer().getInventory().containsAtLeast(left, 1)) {
			e.getPlayer().getInventory().remove(left);
		}
	}
}
