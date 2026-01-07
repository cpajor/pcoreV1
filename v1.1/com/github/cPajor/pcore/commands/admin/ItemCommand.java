package com.github.cPajor.pcore.commands.admin;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

import com.github.cPajor.pcore.core.cmd.ACmd;
import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.listeners.player.MagicWeaponListeners;

public class ItemCommand extends ACmd implements Listener {

	public ItemCommand() {
		super("item", true);
	}
	
	@EventHandler
	public void clck(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(Colors.fix("&4Itemy"))) {
			e.setCancelled(true);
			switch (e.getSlot() + 1) {
			case 1:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.DIAMOND_PICKAXE, 1).setName(Colors.fix("&4Kilof programisty")).addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).toIs());
				break;
			case 2:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CREEPER_SPAWN_EGG, 1).setDurability((short)0).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			case 3:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.IRON_HORSE_ARMOR, 1).setName(Colors.fix("&4Anty-Nogi")).toIs());
				break;
			case 4:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4Poseidon")).addEnchant(Enchantment.RIPTIDE, 4).toIs());
				break;
			case 5:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.BOW, 1).setName(Colors.fix("&4Scorn")).addEnchant(Enchantment.SWIFT_SNEAK, 4).toIs());
				break;
			case 6:
				e.getWhoClicked().getInventory().addItem(MagicWeaponListeners.FLOPPY_X.clone());
				break;
			case 7:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.RECOVERY_COMPASS, 1).setName(Colors.fix("&4&lTotem ognia")).addEnchant(Enchantment.ARROW_FIRE, 10).toIs());
				break;
			case 8:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.FEATHER, 1).setName(Colors.fix("&4&lTotem wiatru")).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			case 9:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lZatruta czapka")).setCustomModelData(1).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).toIs());
				break;
			case 10:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lWizard")).setCustomModelData(2).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toIs());
				break;
			case 11:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lCzapka Pajora")).setCustomModelData(3).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toIs());
				break;
			case 12:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.DIAMOND_SWORD, 1).setName(Colors.fix("&4&lDiamentowy mlot")).setCustomModelData(1).toIs());
				break;
			case 13:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.NETHERITE_SWORD, 1).setName(Colors.fix("&4&lNetherytowy mlot")).setCustomModelData(1).toIs());
				break;
			case 14:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.NETHERITE_SWORD, 1).setName(Colors.fix("&4&lBanHammer™")).setCustomModelData(2).addEnchant(Enchantment.DAMAGE_ALL, 7).addEnchant(Enchantment.DURABILITY, 1).addEnchant(Enchantment.FIRE_ASPECT, 4).toIs());
				break;
			case 15:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lMuszkiet")).setCustomModelData(1).addEnchant(Enchantment.QUICK_CHARGE, 1).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.MULTISHOT, 1).addEnchant(Enchantment.PIERCING, 10).addEnchant(Enchantment.MENDING, 1).toIs());
				break;
			case 16:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lRewolwer")).setCustomModelData(3).addEnchant(Enchantment.QUICK_CHARGE, 3).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.PIERCING, 5).addEnchant(Enchantment.MENDING, 1).toIs());
				break;
			case 17:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lMagiczny rewolwer")).setCustomModelData(3).addEnchant(Enchantment.QUICK_CHARGE, 5).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.PIERCING, 2).addEnchant(Enchantment.MENDING, 1).toIs());
				break;
			case 18:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.NETHER_STAR, 1).setName(Colors.fix("&4&lWstorm™")).addEnchant(Enchantment.DURABILITY, 10).toIs());
				break;
			case 19:
				e.getWhoClicked().getInventory().addItem(new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lJESTER")).setCustomModelData(4).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6).toIs());
			}
		}
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(p, 54, Colors.fix("&4Itemy"));
		inv.setItem(0, new CItem(Material.DIAMOND_PICKAXE, 1).setName(Colors.fix("&4Kilof programisty")).addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).toIs());
		inv.setItem(1, new CItem(Material.CREEPER_SPAWN_EGG, 1).setDurability((short)0).addEnchant(Enchantment.DURABILITY, 10).toIs());
		inv.setItem(2, new CItem(Material.IRON_HORSE_ARMOR, 1).setName(Colors.fix("&4Anty-Nogi")).toIs());
		inv.setItem(3, new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4Poseidon")).addEnchant(Enchantment.RIPTIDE, 4).toIs());
		inv.setItem(4, new CItem(Material.BOW, 1).setName(Colors.fix("&4Scorn")).addEnchant(Enchantment.SWIFT_SNEAK, 4).toIs());
		inv.setItem(5, MagicWeaponListeners.FLOPPY_X);
		inv.setItem(6, new CItem(Material.RECOVERY_COMPASS, 1).setName(Colors.fix("&4&lTotem ognia")).addEnchant(Enchantment.ARROW_FIRE, 10).toIs());
		inv.setItem(7, new CItem(Material.FEATHER, 1).setName(Colors.fix("&4&lTotem wiatru")).addEnchant(Enchantment.DURABILITY, 10).toIs());
		inv.setItem(8, new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lZatruta czapka")).setCustomModelData(1).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toIs());
		inv.setItem(9, new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lWizard")).setCustomModelData(2).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toIs());
		inv.setItem(10, new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lCzapka Pajora")).setCustomModelData(3).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toIs());
		inv.setItem(11, new CItem(Material.DIAMOND_SWORD, 1).setName(Colors.fix("&4&lDiamentowy mlot")).setCustomModelData(1).toIs());	
		inv.setItem(12, new CItem(Material.NETHERITE_SWORD, 1).setName(Colors.fix("&4&lNetherytowy mlot")).setCustomModelData(1).toIs());
		inv.setItem(13, new CItem(Material.NETHERITE_SWORD, 1).setName(Colors.fix("&4&lBanHammer™")).setCustomModelData(2).addEnchant(Enchantment.DAMAGE_ALL, 7).addEnchant(Enchantment.DURABILITY, 1).addEnchant(Enchantment.FIRE_ASPECT, 4).toIs());	
		inv.setItem(14, new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lMuszkiet")).setCustomModelData(1).addEnchant(Enchantment.QUICK_CHARGE, 1).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.MULTISHOT, 1).addEnchant(Enchantment.PIERCING, 10).addEnchant(Enchantment.MENDING, 1).toIs());	
		inv.setItem(15, new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lRewolwer")).setCustomModelData(3).addEnchant(Enchantment.QUICK_CHARGE, 3).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.PIERCING, 5).addEnchant(Enchantment.MENDING, 1).toIs());
		inv.setItem(16, new CItem(Material.CROSSBOW, 1).setName(Colors.fix("&4&lMagiczny rewolwer")).setCustomModelData(3).addEnchant(Enchantment.QUICK_CHARGE, 5).addEnchant(Enchantment.DURABILITY, 10).addEnchant(Enchantment.PIERCING, 5).addEnchant(Enchantment.MENDING, 1).toIs());
		inv.setItem(17, new CItem(Material.NETHER_STAR, 1).setName(Colors.fix("&4&lWstorm™")).addEnchant(Enchantment.DURABILITY, 10).toIs());
		inv.setItem(18, new CItem(Material.CARVED_PUMPKIN, 1).setName(Colors.fix("&4&lJESTER")).setCustomModelData(4).attrib(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "GENERIC_ARMOR", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).attrib(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6).toIs());
		p.openInventory(inv);
		return true;
	}
}
