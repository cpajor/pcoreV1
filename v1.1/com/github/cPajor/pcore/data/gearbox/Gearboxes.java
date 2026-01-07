package com.github.cPajor.pcore.data.gearbox;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class Gearboxes {
	public static final ItemStack E36TURBO = new CItem(Material.DIAMOND_HORSE_ARMOR).setName(Colors.fix("&4E36&fTurbo")).addLore(Colors.fix("&fMoc: 150 kW")).addLore(Colors.fix("&fTurbo: 1.2 bar")).addLore(Colors.fix("&fNitro: 0 kWs")).addLore(Colors.fix("&fZawieszenie: 0.7 SPORT")).addEnchant(Enchantment.DURABILITY, 10).toIs();
	public static final ItemStack Z350 = new CItem(Material.IRON_HORSE_ARMOR).setName(Colors.fix("&4Z350")).addLore(Colors.fix("&fMoc: 250 kW")).addLore(Colors.fix("&fTurbo: 0.6 bar")).addLore(Colors.fix("&fNitro: 0 kWs")).addLore(Colors.fix("&fZawieszenie: 1.0 GWINT")).addEnchant(Enchantment.DURABILITY, 10).toIs();
	public static final ItemStack POLONEZ = new CItem(Material.IRON_HORSE_ARMOR).setName(Colors.fix("&4Polonez")).addLore(Colors.fix("&fMoc: 140 kW")).addLore(Colors.fix("&fTurbo: 0.75 bar")).addLore(Colors.fix("&fNitro: 20 kWs")).addLore(Colors.fix("&fZawieszenie: 1.35 FSO")).addEnchant(Enchantment.DURABILITY, 10).toIs();
	public static final ItemStack MUSTANG = new CItem(Material.GOLDEN_HORSE_ARMOR).setName(Colors.fix("&4Mustang")).addLore(Colors.fix("&fMoc: 220 kW")).addLore(Colors.fix("&fTurbo: 1.3 bar")).addLore(Colors.fix("&fNitro: 0 kWs")).addLore(Colors.fix("&fZawieszenie: 1.0 GWINT")).addEnchant(Enchantment.DURABILITY, 10).toIs();
	
	public static int getHorse(ItemStack is) {
		if (is.isSimilar(E36TURBO)) {
			return 1;
		}
		if (is.isSimilar(Z350)) {
			return 4;
		}
		if (is.isSimilar(POLONEZ)) {
			return 3;
		}
		if (is.isSimilar(MUSTANG)) {
			return 2;
		}
		return -1;
	}
}
