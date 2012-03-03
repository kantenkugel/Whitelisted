package me.kantenkugel.serveress.whitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class WlListener implements Listener {
	private static Whitelist plugin;
	
	public WlListener(Whitelist instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void wllogin(PlayerLoginEvent event) {
		Player pl = event.getPlayer();
		plugin.refreshlist();
		if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Player " + pl.getName() + " wants to join...");
		if(!(pl.hasPermission("whitelist.join"))) {
			if(!(plugin.whitelisted.contains(pl.getName().toLowerCase()))) {
				if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "...deny!");
				event.disallow(Result.KICK_WHITELIST, "Sorry but you're not whitelisted");
			} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Is Whitelisted... allow!");
		} else if(plugin.showconsolelog) plugin.logger.info(plugin.chatprefix + "Has Permission... allow!");
	}
}
