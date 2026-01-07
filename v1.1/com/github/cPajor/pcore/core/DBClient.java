package com.github.cPajor.pcore.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class DBClient {
	private static Socket peer;
	private static DataInputStream ins;
	private static DataOutputStream ous;
	
	public static void connect2() throws Exception {
		/*MySqlClient.INSTANCE.connect(Config.getHost(), Config.getPort(), Config.getDatabase(), Config.getUser(), Config.getPass());
		MySqlClient.INSTANCE.update("CREATE TABLE IF NOT EXISTS `data` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, `ckey` TEXT NOT NULL, `cvalue` TEXT NOT NULL);");
		ResultSet rs = MySqlClient.INSTANCE.query("SELECT * FROM `data`");
		while (rs.next()) {
			cache.put(rs.getString("ckey"), rs.getString("cvalue"));
		}*/
		if (peer != null) {
			peer.close();
		}	
		peer = new Socket("localhost", 2222);
		ins = new DataInputStream(peer.getInputStream());
		ous = new DataOutputStream(peer.getOutputStream());
	}
	
	private static File DIR = new File("data");
	private static Map<String, String> dmap = new HashMap<String, String>();
	
	public static void connect() throws Exception { 
		if (!DIR.exists()) {
			DIR.mkdirs();
		}
	}
	
	/*private static void hset(String s, String w) {
		if (cache.containsKey(s)) {
			cache.replace(s, w);
			try {
				MySqlClient.INSTANCE.update("UPDATE `data` SET cvalue='" + w + "' WHERE ckey='" + s + "';");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			cache.put(s, w);
			try {
				MySqlClient.INSTANCE.update("INSERT INTO `data` (`id`, `ckey`, `cvalue`) VALUES (NULL, '" + s + "', '" + w +"');");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public static void disconnect() throws Exception {
		
	}
	
	public static String get(String s) {
		if (dmap.containsKey(s)) {
			return dmap.get(s);
		} 
		File v = new File(DIR, s);
		if (v.exists()) {
			try {
				dmap.put(s, Files.readAllLines(v.toPath()).get(0));
				return get(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static void set(String key, String value) {
		if (dmap.containsKey(key)) {
			dmap.replace(key, value);
		} else {
			dmap.put(key, value);
		}
		new Thread(new Runnable() {
			public void run() {
				File v = new File(DIR, key);
				try {
					if (!v.exists()) {
						v.createNewFile();
					} 
					FileWriter fw = new FileWriter(v);
					fw.write(value);
					fw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static void disconnect2() throws Exception {
		//MySqlClient.INSTANCE.close();
		if (peer != null) {
			peer.close(); 
			peer = null;
		}
	}
	
	public static String get2(String s) {
		if (peer == null || !peer.isConnected()) {
			try {
				connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ous.writeUTF("get");
			ous.writeUTF(s);
			ous.flush();
			return ins.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "null";
	}
	
	public static void set2(String key, String value) {
		if (peer == null || !peer.isConnected()) {
			try {
				connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ous.writeUTF("set");
			ous.writeUTF(key);
			ous.writeUTF(value);
			ous.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
