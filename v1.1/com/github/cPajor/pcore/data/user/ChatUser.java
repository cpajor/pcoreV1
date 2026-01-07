package com.github.cPajor.pcore.data.user;

import java.util.concurrent.ConcurrentHashMap;

public class ChatUser {
	
	private static  ConcurrentHashMap<String, ChatUser> users;
    
    static {
        users = new ConcurrentHashMap<String, ChatUser>();
    }
    
    private String name;
    private boolean pandora;
    private boolean gracze;
    private boolean is;
    private boolean automsg;
    private boolean priv;
    
    public ChatUser( String name) {
    	this.name = name;
    	this.pandora = true;
    	this.gracze = true;
    	this.automsg = true;
    	this.is = true;
    	this.priv = true;
    	ChatUser.users.put(name, this);
    }

    public static ChatUser getUser( String name) {
        for ( ChatUser u : ChatUser.users.values()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return new ChatUser(name);
    }
    
    public String getName() {
    	return this.name;
    }
    
    public boolean get( String s) {
    	switch (s) {
			case "pandora":
				return this.pandora;
			case "gracze":
				return this.gracze;
			case "is":
				return this.is;
			case "automsg":
				return this.automsg;
			case "priv":
				return this.priv;
			default:
				return true;
    	}
    }
    
    public void set( String s,  boolean bool) {
    	switch (s) {
    		case "pandora":
    			this.pandora = bool;
    			break;
    		case "gracze":
    			this.gracze = bool;
    			break;
    		case "is":
    			this.is = bool;
    			break;
    		case "automsg":
    			this.automsg = bool;
    			break;
    		case "priv":
    			this.priv = bool;
    			break;
    	}
    }
    
}
