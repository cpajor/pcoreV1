package com.github.cPajor.pcore.data.user;

import com.github.cPajor.pcore.core.DBClient;

public class Depozyt {
	private String name;
	private int kox;
	private int ref;
	private int perla;
	private int arrow;
	
	public Depozyt( String name) {
		this.name = name;
		this.kox = 0;
		this.ref = 0;
		this.perla = 0;
		this.arrow = 0;
	}
	
	public Depozyt(String name, int var1, int var2, int var3, int var4) {
		this.name = name;
		this.kox = var1;
		this.ref = var2;
		this.perla = var3;
		this.arrow = var4;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getKox() {
		return this.kox;
	}
	
	public void setKox( int i) {
		this.kox = i;
	}
	
	public void addKox( int i) {
		this.kox = this.kox + i;
	}
	
	public int getRef() {
		return this.ref;
	}
	
	public void setRef( int i) {
		this.ref = i;
	}
	
	public void addRef( int i) {
		this.ref = this.ref + i;
	}
	
	public int getPerla() {
		return this.perla;
	}
	
	public void setPerla( int i) {
		this.perla = i;
	}
	
	public void addPerla( int i) {
		this.perla = this.perla + i;
	}
	
	public int getArrow() {
		return this.arrow;
	}
	
	public void setArrow( int i) {
		this.arrow = i;
	}
	
	public void addArrow( int i) {
		this.arrow = this.arrow + i;
	}
	
	public void save() {
		DBClient.set("depo." + name + ".var1", "" + this.kox);
		DBClient.set("depo." + name + ".var2", "" + this.ref);
		DBClient.set("depo." + name + ".var3", "" + this.perla);
		DBClient.set("depo." + name + ".var4", "" + this.arrow);
	}
}
