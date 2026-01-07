package com.github.cPajor.pcore.data.settings;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.cPajor.pcore.CReg;

public class Config {
	
    private static String host;
    private static int port;
    private static String user;
    private static String pass;
    private static String database;
    
    public static void load() {
        FileConfiguration c = CReg.getPlugin().getConfig();
        Config.host = c.getString("mysql.host");
        Config.port = c.getInt("mysql.port");
        Config.user = c.getString("mysql.user");
        Config.pass = c.getString("mysql.pass");
        Config.database = c.getString("mysql.database");
    }
    
    public static String getHost() {
        return Config.host;
    }
    
    public static int getPort() {
        return Config.port;
    }
    
    public static String getUser() {
        return Config.user;
    }
    
    public static String getPass() {
        return Config.pass;
    }
    
    public static String getDatabase() {
        return Config.database;
    }
}
