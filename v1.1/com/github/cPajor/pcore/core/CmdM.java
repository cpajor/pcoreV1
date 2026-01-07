package com.github.cPajor.pcore.core;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.github.cPajor.pcore.core.cmd.ACmd;

public class CmdM {
    public static  HashMap<String, ACmd> commands;
    private static  Reflection.FieldAccessor<SimpleCommandMap> f;
    private static CommandMap cmdMap;
    
    static {
        commands = new HashMap<String, ACmd>();
        f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
        CmdM.cmdMap = (CommandMap)CmdM.f.get(Bukkit.getServer().getPluginManager());
    }
    
    public static void register( ACmd cmd) {
        if (CmdM.cmdMap == null) {
            CmdM.cmdMap = (CommandMap)CmdM.f.get(Bukkit.getServer().getPluginManager());
        }
        CmdM.cmdMap.register(cmd.getName(), (org.bukkit.command.Command)cmd);
        CmdM.commands.put(cmd.getName(), cmd);
    }
}
