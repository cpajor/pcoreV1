package com.github.cPajor.pcore.klan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.github.cPajor.pcore.core.DBClient;

public class Klan {
	private String owner;
	public List<String> members = new ArrayList<String>();
	private Map<String, Map<Integer, Integer>> permCache;
	
	public Map<String, Integer> zaproszenia = new HashMap<String,Integer>();
	
	public static final int PANEL_ADDUSER = 1;
	public static final int PANEL_REMOVEUSER = 2;
	public static final int PANEL_USEPANEL = 3;
	public static final int PANEL_PANELOTHER = 4;
	
	public Klan(String nme) {
		this.owner = nme.toLowerCase();
		permCache = new HashMap<String, Map<Integer, Integer>>();
	}
	
	public Klan(Player p) {
		this.owner = p.getName().toLowerCase();
		permCache = new HashMap<String, Map<Integer, Integer>>();
		for (int i = 0; i < 5; i++) {
			this.setPermission(p.getName(), i, 1);
		}
	}

	public String getOwner() {
		return owner;
	}
	
	public boolean isMember(String s) {
		return members.contains(s.toLowerCase()) || (s.equalsIgnoreCase(owner) && owner.equalsIgnoreCase(s));
	}

	public int getPermission(String member, int perm) {
		if (permCache.containsKey(member) && permCache.get(member).containsKey(perm)) {
			return permCache.get(member).get(perm);
		}
		String r = DBClient.get("klan." + this.owner + ".perm." + member + ".p" + perm);
		if (r != null) {
			if (!r.equalsIgnoreCase("null")) {
				int v = Integer.parseInt(r);
				if (!permCache.containsKey(member)) {
					permCache.put(member, new HashMap<Integer, Integer>());
				}
				if (permCache.get(member).containsKey(perm)) {
					permCache.get(member).replace(perm, v);
				} else {
					permCache.get(member).put(perm, v);
				}
				return v;
			}
		}
		return 0;
	}
	
	public void setPermission(String member, int perm, int newPerm) {
		if (!permCache.containsKey(member)) {
			permCache.put(member, new HashMap<Integer, Integer>());
		}
		if (permCache.get(member).containsKey(perm)) {
			permCache.get(member).replace(perm, newPerm);
		} else {
			permCache.get(member).put(perm, newPerm);
		}
		DBClient.set("klan." + this.owner + ".perm." + member + ".p" + perm, "" + newPerm);
	}
	
	public void delete() {
		for (int i = 0; i < 20; i++) {
			DBClient.set("klan." + this.owner + ".m" + i, "null");
		}
		for (String key : permCache.keySet()) {
			Map<Integer, Integer> mp = permCache.get(key);
			for (Integer i : mp.keySet()) {
				DBClient.set("klan." + this.owner + ".perm." + key + ".p" + i, "null");
			}
		}
	}
 
	public void save() {
		for (int i = 0; i < members.size(); i++) {
			DBClient.set("klan." + this.owner + ".m" + i, members.get(i) + "");
		}
		DBClient.set("klan." + this.owner + ".m" + members.size(), "null");
	}
}
