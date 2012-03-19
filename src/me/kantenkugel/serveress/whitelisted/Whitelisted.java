package me.kantenkugel.serveress.whitelisted;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Whitelisted extends JavaPlugin {
	//public boolean SqlOn, SqlEn;												//SqlOn -> No errors; SqlEn -> Config
	//public boolean Customtable, SqlMode;										//SqlMode: 0->Own, 1->External
	//public String Sqlname;
	public Whitelist whitelist;
	public String chatprefix;
	public String whitelistmsg, notifymsg, noadminmsg, trylatermsg;
	public boolean showconsolelog, notify;
	public PluginDescriptionFile pdf;
	public final Logger logger = Logger.getLogger("Minecraft");
	//public List<String> whitelisted, denylist;
	
	public void onEnable() {
		whitelist = new WlFileA(this);
		pdf = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new WlListener(this), this);
		chatprefix = "["+pdf.getName()+"] ";
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		reloadc();
		logger.info(chatprefix + "v" + pdf.getVersion() + " is now enabled!");
	}
	
	public void onDisable() {
		logger.info(chatprefix + "is now disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player && !(sender.hasPermission("whitelisted.admin"))) {
			sender.sendMessage(ChatColor.RED + chatprefix + "You dont have the Permission to do that!");
			return true;
		} else if(args.length > 0) {
			switch(args[0].toLowerCase()) {
			case "list":
				String list = whitelist.getList();
				if(list == "") report(sender, "No Players are whitelisted!", ChatColor.RED);
				else {
					report(sender, "Players in list: ", ChatColor.YELLOW);
					report(sender, list, ChatColor.GREEN);
				}
				break;
			case "add":
				if(args.length != 2) {
					report(sender, "You have to specify a player.", ChatColor.RED);
					report(sender, "e.g. /whitelist add Steve", ChatColor.RED);
				} else {
					if(whitelist.wlAdd(args[1])) {
						report(sender, "Player "+args[1]+" added to whitelist", ChatColor.GREEN);
					} else {
						report(sender, "That Player is already whitelisted", ChatColor.GOLD);
					}
				}
				break;
			case "deny":
				if(args.length != 2) {
					report(sender, "You have to specify a player.", ChatColor.RED);
					report(sender, "e.g. /whitelist deny Steve", ChatColor.RED);
				} else {
					if(whitelist.dlAdd(args[1])) {
						report(sender, "Player "+args[1]+" has been moved to the deny-list", ChatColor.GREEN);
					} else {
						report(sender, "That Player is already denied", ChatColor.GOLD);
					}
				}
				break;
			case "rem":
			case "remove":
			case "rm":
				if(args.length != 2) {
					report(sender, "You have to specify a player.", ChatColor.RED);
					report(sender, "e.g. /whitelist rem Steve", ChatColor.RED);
				} else {
					if(whitelist.wlRemove(args[1])) {
						report(sender, "Player "+args[1]+" removed from whitelist", ChatColor.GREEN);
					} else {
						report(sender, "That player is not whitelisted!", ChatColor.GOLD);
					}
					
				}
				break;
			case "reload":
				reloadc();
				report(sender, "Config reloaded!", ChatColor.BLUE);
				break;
			default:
				return false;
			}
			return true;
		}
		return false;
	}
	
	private void report(CommandSender sender, String msg, ChatColor color) {
		if(sender == getServer().getConsoleSender()) {
			logger.info(chatprefix + msg);
		} else if(sender instanceof Player) {
			sender.sendMessage(color + chatprefix + msg);
		}	
	}
	
	private void reloadc() {
		this.reloadConfig();
		notify = this.getConfig().getBoolean("Config.Notify", true);
		showconsolelog = this.getConfig().getBoolean("Config.ShowLog", false);
		whitelistmsg = this.getConfig().getString("Config.Msg.NotWhitelisted", "You are not Whitelisted!");
		notifymsg = this.getConfig().getString("Confog.Msg.Notified", "Your not whitelisted. An admin may add you. Try again in 20secs");
		noadminmsg = this.getConfig().getString("Config.Msg.NoAdminOnline", "Your not whitelisted and there is no admin online");
		trylatermsg = this.getConfig().getString("Config.Msg.TryLater", "You just tried to join... try again in 20secs");
	}

}
