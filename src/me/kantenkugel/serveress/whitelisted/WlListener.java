package me.kantenkugel.serveress.whitelisted;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class WlListener implements Listener {
	private static Whitelisted plugin;
	public HashMap<String, Long> queuehash = new HashMap<String, Long>();
	
	public WlListener(Whitelisted instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void wllogin(PlayerLoginEvent event) {
		Player pl = event.getPlayer();
		plugin.refreshlist();
		if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Player " + pl.getName() + " wants to join...");
		if(!(pl.hasPermission("whitelisted.join"))) {
			if(!(plugin.whitelisted.contains(pl.getName().toLowerCase()))) {
				if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "...deny!");
				event.disallow(Result.KICK_WHITELIST, plugin.whitelistmsg);
			} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Is Whitelisted... allow!");
		} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Has Permission... allow!");
	}
	
	public void handlequeue() {
		long acttime = System.currentTimeMillis();
		for(String key: queuehash.keySet()) {
			if(acttime > (queuehash.get(key) + 10*1000)) {
				queuehash.remove(key);
			}
		}
	}
	

	
}
