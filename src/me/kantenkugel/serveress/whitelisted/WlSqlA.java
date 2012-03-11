package me.kantenkugel.serveress.whitelisted;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
*/

public class WlSqlA {
	/*private static Connection con;
	private static Statement stmt;
	private static String table;
	private static Whitelisted plugin;

	public WlSqlA (String host, String port, String username, String password, String database, String ntable, Whitelisted instance) {
		table = ntable;
		plugin = instance;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port, username, password);
			stmt = con.createStatement();
		} catch(SQLException e) {
			plugin.logger.severe(plugin.chatprefix+"Couldn't connect to MySQL! Check the config and type \"/wl relsql\"");
			plugin.logger.severe(plugin.chatprefix+"Error: "+e.getMessage());
			con = null;
			plugin.SqlOn = false;
		}
		
		if(con != null) {
			try {
				stmt.execute("USE '"+database+"';");
				stmt.execute("CREATE TABLE IF NOT EXISTS '"+table+"' (name text);");
			} catch(Exception e) {
				plugin.logger.severe(plugin.chatprefix+"Couldn't create table! Check the database and user rights in the config and type \"/wl relsql\"");
				plugin.logger.severe(plugin.chatprefix+"Error: "+e.getMessage());
				closecon();
				plugin.SqlOn = false;
			}
		}
	}
	
	public void closecon() {
		try {
			con.close();
		} catch(SQLException e) {
			//nothing to do
		}
	}
	
	public boolean iswhitelisted(Player pl) {
		if(con == null) return false;
		ResultSet result;
		try {
			result = stmt.executeQuery("SELECT * FROM '"+table+"' WHERE 'name' = '"+pl.getName().toLowerCase()+"';");
			return result.absolute(1);
		} catch(Exception e) {
			return false;
		}
	}
	public boolean iswhitelisted(String name) {
		if(con == null) return false;
		ResultSet result;
		try {
			result = stmt.executeQuery("SELECT * FROM '"+table+"' WHERE 'name' = '"+name.toLowerCase()+"';");
			return result.absolute(1);
		} catch(Exception e) {
			return false;
		}
	}
	public boolean addplayer(Player pl) {
		if(con == null) return false;
		if(iswhitelisted(pl)) return false;
		try {
			stmt.execute("INSERT INTO '"+table+"' VALUES ('"+pl.getName().toLowerCase()+"');");
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean addplayer(String name) {
		if(con == null) return false;
		if(iswhitelisted(name)) return false;
		try {
			stmt.execute("INSERT INTO '"+table+"' VALUES ('"+name.toLowerCase()+"');");
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean remplayer(Player pl) {
		if(con == null) return false;
		if(!iswhitelisted(pl)) return false;
		try {
			stmt.execute("DELETE FROM '"+table+"' WHERE 'name' = '"+pl.getName().toLowerCase()+"';");
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean remplayer(String name) {
		if(con == null) return false;
		if(!iswhitelisted(name)) return false;
		try {
			stmt.execute("DELETE FROM '"+table+"' WHERE 'name' = '"+name.toLowerCase()+"';");
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	public String getWlList() {
		String list = "";
		ResultSet result;
		if(con == null) return list;
		try {
			result = stmt.executeQuery("SELECT 'name' FROM '"+table+"';");
			while(result.next()) {
				if(list == "") list = result.getString("name");
				else list = list + ", " + result.getString("name");
			}
		} catch(Exception e) {
			return list;
		}
		return list;

	}
	*/
	
}
