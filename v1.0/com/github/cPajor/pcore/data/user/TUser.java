package com.github.cPajor.pcore.data.user;

import com.github.cPajor.pcore.core.DBClient;

public class TUser {
	private String name;
	private long time;
	public static final long KVA = 900000; // 15 min
    private int po1Cache = 0;
    
    public TUser(String name) {
        this.name = name;
        this.time = System.currentTimeMillis() + KVA;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPoints() {
        int p = 0; 
        try {
        	p = Integer.parseInt(DBClient.get("tuser." + this.name + ".points"));
        } catch (Exception e) {
        	
        }
        po1Cache = p;
        return p;
    }
    
    public int getCachedPoints() {
    	return po1Cache; 
    }
    
    public void setPoints(int rank) {
    	po1Cache = rank;
        DBClient.set("tuser." + this.name + ".points", "" + rank);
    }
    
	public long getTime() {
		return time;
	}
	
	public long getTimeLeft() {
		long l = time - System.currentTimeMillis();
		return l >= 0 ? l : 0;
	}
	
	public void setTime(long rank) {
        this.time = rank;
    }

}
