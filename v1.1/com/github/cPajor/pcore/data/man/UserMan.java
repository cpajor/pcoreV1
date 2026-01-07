package com.github.cPajor.pcore.data.man;

import java.util.ArrayList;
import java.util.List;

import com.github.cPajor.pcore.data.user.User;

public class UserMan {
	
	public static List<User> users = new ArrayList<User>();
   
    public static User getUser( String name) {
        for ( User u : UserMan.users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        users.add(new User(name));
        return getUser(name);
    }

}
