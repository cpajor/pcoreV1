package com.github.cPajor.pcore.dzialka;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class DzialkaListeners implements Listener {
	
	@EventHandler
	public void blockbreak(BlockBreakEvent e) {
		if (!e.getBlock().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			if (dl.getPermission("other", Dzialka.PANEL_BUILD) > 0 && !dl.isMember(e.getPlayer().getName())) {
				return;
			}
			if (dl.getPermission(e.getPlayer().getName(), Dzialka.PANEL_BUILD) <= 0) {
				e.setCancelled(true);
				e.getPlayer().spawnParticle(Particle.WAX_ON, e.getBlock().getLocation(), 1);
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 5, 1, true));
			}
 		}
	}
	
	@EventHandler
	public void blockplace(BlockPlaceEvent e) {
		if (!e.getBlock().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			if (dl.getPermission("other", Dzialka.PANEL_BUILD) > 0 && !dl.isMember(e.getPlayer().getName())) {
				return;
			}
			if (dl.getPermission(e.getPlayer().getName(), Dzialka.PANEL_BUILD) <= 0) {
				e.setCancelled(true);
				e.getPlayer().spawnParticle(Particle.WAX_ON, e.getBlock().getLocation(), 1);
			}
 		}
	}
	
	@EventHandler
	public void expl(BlockExplodeEvent e) {
		if (!e.getBlock().getWorld().getName().equalsIgnoreCase("world")) return;
		e.blockList().clear();
	}
	
	@EventHandler
	public void lfdsa(EntityExplodeEvent e) {
		if (!e.getLocation().getWorld().getName().equalsIgnoreCase("world")) return;
		e.blockList().clear();
	}
	
	@EventHandler
	public void in(PlayerInteractEvent e) {
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.PHYSICAL) && e.getClickedBlock() != null) {
			if (!e.getClickedBlock().getWorld().getName().equalsIgnoreCase("world")) return;
			Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getClickedBlock().getLocation());
			if (dl != null) {
				
				if (dl.getPermission("other", Dzialka.PANEL_USE) > 0 && !dl.isMember(e.getPlayer().getName())) {
					return;
				}
				if (dl.getPermission(e.getPlayer().getName(), Dzialka.PANEL_USE) <= 0) {
					e.setCancelled(true);
					e.getPlayer().spawnParticle(Particle.WAX_ON, e.getClickedBlock().getLocation(), 1);
				} 
	 		}
		}
	}

	@EventHandler
	public void ingfd(EntityInteractEvent e) {
		if (!e.getEntity().getLocation().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getEntity().getLocation());
		if (dl != null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void bgftrtn(BlockIgniteEvent e) {
		if (!e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void bgftrtn(BlockBurnEvent e) {
		if (!e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void cla1(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
				Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getEntity().getLocation());
				if (dl != null) {
					if (dl.passive) {
						e.setCancelled(true);
					}
					return;
				}
			}
			if (e.getDamager().getType() == EntityType.ARROW || e.getDamager().getType() == EntityType.SPECTRAL_ARROW) {
				Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getEntity().getLocation());
				if (dl != null) {
					Projectile pr = (Projectile) e.getDamager();
					if (!(pr.getShooter() instanceof Player)) {
						return;
					}
					if (dl.passive) {
						e.setCancelled(true);
					}
					return;
				}
			}
		}
		if (e.getDamager() instanceof Player) {
			Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getEntity().getLocation());
			if (dl != null) {
				Player p = (Player) e.getDamager();
				if (!dl.isMember(p.getName())) {
					if (e.getEntity() instanceof Animals) {
						if (dl.getPermission("other", Dzialka.PANEL_KILL_ANIMAL) <= 0) {
							e.setCancelled(true);
							return;
						}
					} else {
						if (dl.getPermission("other", Dzialka.PANEL_KILL_HOSTILE) <= 0) {
							e.setCancelled(true);
							return;
						}
					}
					return;
				}
				if (e.getEntity() instanceof Animals) {
					if (dl.getPermission(p.getName(), Dzialka.PANEL_KILL_ANIMAL) <= 0) {
						e.setCancelled(true);
					}
				} else {
					if (dl.getPermission(p.getName(), Dzialka.PANEL_KILL_HOSTILE) <= 0) {
						e.setCancelled(true);
					}
				}
	 		}
		}
		if (e.getDamager().getType() == EntityType.ARROW || e.getDamager().getType() == EntityType.SPECTRAL_ARROW) {
			Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getEntity().getLocation());
			if (dl != null) {
				Projectile pr = (Projectile) e.getDamager();
				if (!(pr.getShooter() instanceof Player)) {
					return;
				}
				Player p = (Player) pr.getShooter();
				if (!dl.isMember(p.getName())) {
					if (e.getEntity() instanceof Animals) {
						if (dl.getPermission("other", Dzialka.PANEL_KILL_ANIMAL) <= 0) {
							e.setCancelled(true);
							return;
						}
					} else {
						if (dl.getPermission("other", Dzialka.PANEL_KILL_HOSTILE) <= 0) {
							e.setCancelled(true);
							return;
						}
					}
					return;
				}
				if (e.getEntity() instanceof Animals) {
					if (dl.getPermission(p.getName(), Dzialka.PANEL_KILL_ANIMAL) <= 0) {
						e.setCancelled(true);
					}
				} else {
					if (dl.getPermission(p.getName(), Dzialka.PANEL_KILL_HOSTILE) <= 0) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void bvfdpgr(PlayerBucketFillEvent e) {
		if (!e.getBlock().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			if (dl.getPermission("other", Dzialka.PANEL_LIQUID) > 0 && !dl.isMember(e.getPlayer().getName())) {
				return;
			}
			if (dl.getPermission(e.getPlayer().getName(), Dzialka.PANEL_LIQUID) <= 0) {
				e.setCancelled(true);
			}
 		}
	}

	@EventHandler
	public void bvfdpggfdsr(PlayerBucketEmptyEvent e) {
		if (!e.getBlock().getWorld().getName().equalsIgnoreCase("world")) return;
		Dzialka dl = DzialkaSys.getDzialkaByLoc(e.getBlock().getLocation());
		if (dl != null) {
			if (dl.getPermission("other", Dzialka.PANEL_LIQUID) > 0 && !dl.isMember(e.getPlayer().getName())) {
				return;
			}
			if (dl.getPermission(e.getPlayer().getName(), Dzialka.PANEL_LIQUID) <= 0) {
				e.setCancelled(true);
			}
 		}
	}
	
	@EventHandler
	public void gfdjdhgskkk(InventoryClickEvent e) {
		if (e.getView().getTitle().startsWith(Colors.fix("&4&lDzialka "))) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			String[] a = e.getView().getTitle().split(" ");
			Dzialka d = DzialkaSys.getDzialkaByOwner(a[1]);
			if (a.length > 2) {
				boolean b = d.getPermission(a[3], e.getSlot() + 1) == 1;
				d.setPermission(a[3], e.getSlot() + 1, b ? 0 : 1);
				Inventory inv = Bukkit.createInventory(p, 9, e.getView().getTitle());
				inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(a[3], Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(a[3], Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(2, new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(a[3], Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(a[3], Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(a[3], Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
				p.openInventory(inv);
			} else {
				if (e.getSlot() < 20) {
					String entry = e.getCurrentItem().getTranslationKey().replaceFirst(Colors.fix("&4"), "");
					if (entry.equalsIgnoreCase("Wolne miejsce")) {
						return;
					}
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + d.getOwner() + " : " + entry));
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission(entry, Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission(entry, Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission(entry, Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission(entry, Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission(entry, Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
				}
				if (e.getSlot() == 34) {
					d.passive = !d.passive;
					d.save();
					Inventory inv = Bukkit.createInventory(p, 36, Colors.fix("&4&lDzialka " + d.getOwner()));
					for (int i = 0; i < 20; i++) {
						String v = d.members.size() > i ? d.members.get(i) : null;
						inv.setItem(i, new CItem(Material.PLAYER_HEAD).setName(Colors.fix("&4" + (v == null ? "Wolne miejsce" : v))).setSkullOwner(v != null ? v : "null").toIs());
					}
					inv.setItem(34, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Status dzialki: " + (d.passive ? Colors.fix("&cAgresywna") : Colors.fix("&aPasywna")))).toIs());
					inv.setItem(35, new CItem(Material.COAL_BLOCK).setName(Colors.fix("&4Uprawnienia dla gosci")).toIs());
					p.openInventory(inv);
				}
				if (e.getSlot() == 35) {
					Inventory inv = Bukkit.createInventory(p, 9, Colors.fix("&4&lDzialka " + a[1] + " : other"));
					inv.setItem(0, new CItem(Material.NETHERITE_PICKAXE).setName(Colors.fix("&4Budowanie")).addLore(d.getPermission("other", Dzialka.PANEL_BUILD) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(1, new CItem(Material.BARREL).setName(Colors.fix("&4Interakcja z blokami")).addLore(d.getPermission("other", Dzialka.PANEL_USE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(2,  new CItem(Material.BUCKET).setName(Colors.fix("&4Uzywanie wiaderek")).addLore(d.getPermission("other", Dzialka.PANEL_LIQUID) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(3, new CItem(Material.SHEARS).setName(Colors.fix("&4Zadawanie obrazen mobom")).addLore(d.getPermission("other", Dzialka.PANEL_KILL_ANIMAL) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					inv.setItem(4, new CItem(Material.NETHERITE_SWORD).setName(Colors.fix("&4Zadawanie obrazen potworom")).addLore(d.getPermission("other", Dzialka.PANEL_KILL_HOSTILE) == 1 ? Colors.fix("&a&lDozwolone") : Colors.fix("&c&lZakazane")).toIs());
					p.openInventory(inv);
				}
			}
		}
	}
}
