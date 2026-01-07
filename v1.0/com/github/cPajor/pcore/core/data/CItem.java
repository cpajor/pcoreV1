package com.github.cPajor.pcore.core.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CItem {
    private ItemStack is;
    
    public CItem( Material m) {
        this(m, 1);
    }
    
    public CItem( Material m,  int ilosc) {
        this.is = new ItemStack(m, ilosc);
        if (!this.is.hasItemMeta()) {
        	this.is.setItemMeta(Bukkit.getItemFactory().getItemMeta(m));
        }
    }
    
    public CItem setAmount( int i) {
        this.is.setAmount(i);
        return this;
    }
    
    public CItem setDurability( short durability) {
        this.is.setDurability(durability);
        return this;
    }
    
    public CItem setName( String name) {
         ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(name);
        this.is.setItemMeta(im);
        return this;
    }
    
    public CItem setSkullOwner( String owner) {
        try {
        	SkullMeta im = (SkullMeta)this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public CItem( ItemStack is) {
        this.is = is;
    }
    
    public CItem clone() {
        return new CItem(this.is);
    }
    
    public CItem setLore( String lore) {
         ItemMeta im = this.is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        return this;
    }
    
    public CItem addLore( String lore) {
         ItemMeta im = this.is.getItemMeta();
        List<String> line = new ArrayList<String>();
        if (im.hasLore()) {
            line = new ArrayList<String>(im.getLore());
        }
        line.add(lore);
        im.setLore(line);
        this.is.setItemMeta(im);
        return this;
    }
    
    public CItem addEnchant( Enchantment ench,  int lvl) {
        this.is.addUnsafeEnchantment(ench, lvl);
        return this;
    }
    
    public CItem removeEnchant( Enchantment ench) {
        if (this.is.containsEnchantment(ench)) {
            this.is.removeEnchantment(ench);
        }
        return this;
    }
    
    public CItem removeLore( String lore) {
         ItemMeta im = this.is.getItemMeta();
        List<String> line = new ArrayList<String>();
        if (im.hasLore()) {
            line = new ArrayList<String>(im.getLore());
        }
        if (!line.contains(lore)) {
            return this;
        }
        line.remove(lore);
        im.setLore(line);
        this.is.setItemMeta(im);
        return this;
    }
    
    public CItem setCustomModelData(int lvl) {
        ItemMeta im = this.is.getItemMeta();
        im.setCustomModelData(lvl);
        this.is.setItemMeta(im);
        return this;
    }
    
    public CItem attrib(Attribute a, AttributeModifier b) {
    	ItemMeta im = this.is.getItemMeta();
    	im.removeAttributeModifier(a);
    	im.addAttributeModifier(a, b);
    	this.is.setItemMeta(im);
    	return this;
    }
    
    public ItemStack toIs() {
        return this.is;
    }
}
