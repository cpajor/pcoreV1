package com.github.cPajor.pcore.data.bazary;

import org.bukkit.inventory.ItemStack;

public class RynekItem {
	public int price;
	public ItemStack is;
	private int id;
	private String name;
	public boolean extra;
	public long time;
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public RynekItem(int i, ItemStack s, int id, String nam, boolean extra, long ti) {
		this.price = i;
		this.is = s;
		this.id = id;
		this.name = nam;
		this.extra = extra;
		this.time = ti;
	}
}
