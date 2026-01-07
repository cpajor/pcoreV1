package com.github.cPajor.pcore.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.github.cPajor.pcore.data.settings.Config;

public interface MySqlClient {
	public static final MySqlClient INSTANCE = new MySqlClient() {
		private Connection connect;
		
		public ResultSet query(String sql) throws Exception {
			if (this.connect == null || this.connect.isClosed()) {
				connect(Config.getHost(), Config.getPort(), Config.getDatabase(), Config.getUser(), Config.getPass());
			}
			Statement stmt = connect.createStatement();  
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		
		public void update(String sql) throws Exception {
			if (this.connect == null || this.connect.isClosed()) {
				connect(Config.getHost(), Config.getPort(), Config.getDatabase(), Config.getUser(), Config.getPass());
			}
			Statement stmt = connect.createStatement(); 
			stmt.executeLargeUpdate(sql);
		}
		
		public void close() throws Exception {
			connect.close();
		}

		@Override
		public void connect(String host, int port, String dbname, String user, String pass) throws Exception {
			if (connect != null) connect.close(); 
			Class.forName("com.mysql.jdbc.Driver");
			Properties inf = new Properties();
			inf.put("user", user);
			inf.put("password", pass);
			inf.put("autoReconnect", true);
			//connect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname, user, pass + "?autoReconnect=true");
			connect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname, inf);
		}
	};
	
	ResultSet query(String sql) throws Exception;
	
	void update(String sql) throws Exception;
	
	void close() throws Exception;
	
	void connect(String host, int port, String dbname, String user, String pass) throws Exception;

}
