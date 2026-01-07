package com.github.cPajor.pcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;

public class ItemCommand extends ACmd implements Listener {

	public ItemCommand() {
		super("item", true);
	}
	
	@EventHandler
	public void clck(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(Colors.fix("&4Itemy"))) {
			e.setCancelled(true);
			switch (e.getSlot()) {
			case 0:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.DIAMOND_PICKAXE, 1).setName(Colors.fix("&4Kilof programisty")).addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).toIs());
				break;
			case 1:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.IRON_HORSE_ARMOR, 1).setName(Colors.fix("&4Anty-Nogi")).toIs());
				break;
			case 2:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4Poseidon")).addEnchant(Enchantment.RIPTIDE, 4).toIs());
				break;
			case 3:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.FEATHER, 1).setName(Colors.fix("&4&lTotem wiatru")).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			case 4:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.COBWEB, 1).setName(Colors.fix("&4&lRzucana pajeczyna")).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			case 5:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.BLAZE_ROD, 1).setName(Colors.fix("&4&lUsuwacz pajeczyn")).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			}
		}
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(p, 54, Colors.fix("&4Itemy"));
		inv.setItem(0, new CItem(Material.DIAMOND_PICKAXE, 1).setName(Colors.fix("&4Kilof programisty")).addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).toIs());
		inv.setItem(1, new CItem(Material.IRON_HORSE_ARMOR, 1).setName(Colors.fix("&4Anty-Nogi")).toIs());
		inv.setItem(2, new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4Poseidon")).addEnchant(Enchantment.RIPTIDE, 4).toIs());
		inv.setItem(3, new CItem(Material.FEATHER, 1).setName(Colors.fix("&4&lTotem wiatru")).addEnchant(Enchantment.DURABILITY, 10).toIs());
		inv.setItem(4, new CItem(Material.COBWEB, 1).setName(Colors.fix("&4&lRzucana pajeczyna")).addEnchant(Enchantment.DURABILITY, 10).toIs());
		inv.setItem(5, new CItem(Material.BLAZE_ROD, 1).setName(Colors.fix("&4&lUsuwacz pajeczyn")).addEnchant(Enchantment.DURABILITY, 10).toIs());
		p.openInventory(inv);
		return true;
	}
}
