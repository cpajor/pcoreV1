package com.github.cPajor.pcore.data.man;

import java.util.ArrayList;
import java.util.List;

import com.github.cPajor.pcore.data.user.TUser;

public class TUserMan {
	public static List<TUser> tusers = new ArrayList<TUser>();

    public static TUser getUser( String name) {
        for (TUser u : TUserMan.tusers) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        tusers.add(new TUser(name));
        return getUser(name);
    }
}
