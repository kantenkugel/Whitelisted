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
	
	private void refreshlist() {
		plugin.reloadConfig();
		whitelist = plugin.getConfig().getStringList("Whitelist");
		denylist = plugin.getConfig().getStringList("Denylist");
	}
}
