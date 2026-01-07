package com.github.cPajor.pcore.data.user;

import java.util.ArrayList;
import java.util.List;

public class Combat {
	public static List<Combat> users = new ArrayList<Combat>();
	private String name;
	private int combat;
	private boolean incognito;
	
	public Combat( String name) {
		this.name = name;
		this.combat = 0;
		this.incognito = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTime() {
		return this.combat;
	}
	
	public void setTime( int i) {
		this.combat = i;
	}
	
	public boolean isIncognito() {
		return this.incognito;
	}
	
	public void setIncognito( boolean bool) {
		this.incognito = bool;
	}
	
	public static Combat get( String name) {
		for (Combat u : users) {
			if (name.equals(u.getName())) {
				return u;
			}
		}
		users.add(new Combat(name));
		return get(name);
	}
}
