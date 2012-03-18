package me.kantenkugel.serveress.whitelisted;

import java.util.HashMap;

import org.bukkit.ChatColor;
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
		String pl = event.getPlayer().getName();
		if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Player " + pl + " wants to join...");
		if(!(event.getPlayer().hasPermission("whitelisted.join"))) {
			if(!(plugin.whitelist.isWhitelisted(pl))) {
				if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "...deny!");							//deny debug output
				if(plugin.notify && !plugin.whitelist.isDenied(pl)) {													//notify enabled and player not denied
					if(queuehash.containsKey(pl.toLowerCase())) {														//if player tried before
						if(queuehash.get(pl.toLowerCase()) < (System.currentTimeMillis() - 20*1000)) {					//if he tried at least 20secs before
							queuehash.remove(pl.toLowerCase());															//delete from list, treat like first time
						} else {																						//if recent try
							event.disallow(Result.KICK_WHITELIST, "no admin has approved you yet");						//not approved yet
							return;																						//block rest of function
						}
					}
					boolean found=false;
					for(Player online: plugin.getServer().getOnlinePlayers()) {											//search for admins and broadcast
						if(online.hasPermission("whitelisted.admin")) {
							online.sendMessage(ChatColor.YELLOW+plugin.chatprefix+"Player "+pl+" is trying to join... add/deny?");
							found=true;
						}
					}
					if(!found) {																											//no admin reached
						event.disallow(Result.KICK_WHITELIST, "Your not whitelisted and no admin online");
					} else {																												//admin reached
						queuehash.put(pl.toLowerCase(), System.currentTimeMillis());														//add to list
						event.disallow(Result.KICK_WHITELIST, "Your not whitelisted. an admin may approve you. try again in 20secs");
					}
				} else {																								//no notify or player denied
					event.disallow(Result.KICK_WHITELIST, plugin.whitelistmsg);											//show whitelistmsg
				}
			} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Is Whitelisted... allow!");		//allow by whitelist debug output
		} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Has Permission... allow!");			//allowed by permission debug output
	}
	

	
}
