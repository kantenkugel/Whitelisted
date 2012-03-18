package me.kantenkugel.serveress.whitelisted;

import java.util.List;

public class WlFileA {
	private static Whitelisted plugin;
	private static List<String> whitelist, denylist;
	
	
	public WlFileA(Whitelisted instance) {
		plugin = instance;
	}
	
	public boolean isWhitelisted(String player) {
		refreshlist();
		return whitelist.contains(player.toLowerCase());
	}
	
	public boolean isDenied(String player) {
		refreshlist();
		return denylist.contains(player.toLowerCase());
	}
	
	public boolean whitelistAdd(String player) {
		refreshlist();
		if(whitelist.contains(player.toLowerCase())) return false;
		else {
			if(denylist.contains(player.toLowerCase())) {
				denylist.remove(player.toLowerCase());
				plugin.getConfig().set("Denylist", denylist);
			}
			whitelist.add(player.toLowerCase());
			plugin.getConfig().set("Whitelist", whitelist);
			plugin.saveConfig();
			return true;
		}
	}
	
	public boolean denylistAdd(String player) {
		refreshlist();
		if(denylist.contains(player.toLowerCase())) return false;
		else {
			if(whitelist.contains(player.toLowerCase())) {
				whitelist.remove(player.toLowerCase());
				plugin.getConfig().set("Whitelist", whitelist);
			}
			denylist.add(player.toLowerCase());
			plugin.getConfig().set("Denylist", denylist);
			plugin.saveConfig();
			return true;
		}
	}
	
	public void close() {
		return;
	}
	
	private void refreshlist() {
		plugin.reloadConfig();
		whitelist = plugin.getConfig().getStringList("Whitelist");
		denylist = plugin.getConfig().getStringList("Denylist");
	}
}
