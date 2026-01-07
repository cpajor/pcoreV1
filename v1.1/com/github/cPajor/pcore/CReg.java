package com.github.cPajor.pcore;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.cPajor.pcore.core.DBClient;
import com.github.cPajor.pcore.core.Man;
import com.github.cPajor.pcore.data.bazary.Rynek;
import com.github.cPajor.pcore.data.man.DepoMan;
import com.github.cPajor.pcore.data.settings.Config;
import com.github.cPajor.pcore.data.user.Depozyt;
import com.github.cPajor.pcore.data.util.BarUtil;
import com.github.cPajor.pcore.dzialka.DzialkaSys;

public class CReg extends JavaPlugin {
	
	private static CReg plugin;
	
	public static CReg getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		plugin = this;
		this.saveDefaultConfig();
		Config.load();
		try {
			DBClient.connect();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Man.init();
		Man.initTab();
		Rynek.loadAll();
		DzialkaSys.load(); 
		//PQuakeReg.init(this);
	}
	
	public void onDisable() {	
		for (Depozyt d : DepoMan.depo.values()) {
			d.save();
		}
		for (Rynek r : Rynek.Ryneks) {
			r.save(); 
		}
		Rynek.saveAll();
		DzialkaSys.save(); 
		try {
			DBClient.disconnect();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		/*if (Bukkit.getPluginManager().isPluginEnabled("Citizens")) {
			CitizensHook.Hunhook();
		}*/
		BarUtil.removeAll();
		try { 
			Man.unTab();
		} catch (Exception e) {}
	}
	
	public static void is(String s) { 
//        try {
//        	File file = new File(Main.getPlugin().getDataFolder(), "is.log");
//        	if (!file.exists()) {
//        		file.createNewFile();
//        	}
//            FileWriter fw = new FileWriter(file, true);
//            PrintWriter pw = new PrintWriter(fw);
//            pw.println("[" + new Date().toString() + "]: " + s);
//            pw.flush();
//            pw.close();
//        } catch (IOException e) {}
    }

}
