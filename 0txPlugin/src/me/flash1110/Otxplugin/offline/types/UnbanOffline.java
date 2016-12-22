package me.flash1110.Otxplugin.offline.types;

import me.flash1110.Otxplugin.offline.OfflineAccessType;


public class UnbanOffline /*extends OfflineAccessType */{
/*	
	public UnbanOffline(UUID sender, String targetname) {
		super(sender, targetname);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void call() {
		
		CommandSender sender;
		
		if (getSenderID() != null)
			sender = Bukkit.getPlayer(getSenderID());
		else
			sender = Bukkit.getConsoleSender();
		
		if (getCTarget() == null) {
			if (sender != null)
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getTargetName() + ChatColor.RED + " has never joined");	
				return;
		}
		
		if (getCTarget() == null) {
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getTargetName() + ChatColor.RED + " has never joined");	
			return;
		}
		
		if (!getCTarget().getBanned()) {
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getCTarget().getName() + ChatColor.RED + " is not banned");	
			return;
		}
		
		OfflinePlayer offlinetarget = Bukkit.getOfflinePlayer(getCTarget().getName());
		
		Boolean player;
		
		if (sender instanceof Player)
			player = true;
		else
			player = false;
		
		if (player) {
			sender.sendMessage(ChatColor.GOLD + "You unbanned " + ChatColor.RED + getCTarget().getName());
		} else
			sender.sendMessage(ChatColor.GOLD + "You unbanned " + ChatColor.RED + getCTarget().getName());
		
		for (Player P : Bukkit.getOnlinePlayers())
			if (P.hasPermission("craftibasics.unban.notify") && !P.getName().equals(sender.getName()))
				if (player)
					P.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + ((Player) sender).getDisplayName() + ChatColor.GOLD + " unbanned " + ChatColor.RED + getCTarget().getName());
				else
					P.sendMessage(ChatColor.RED + "Console " + ChatColor.GOLD + "unbanned " + ChatColor.RED + getCTarget().getName());
					
		getCTarget().setBanTime(0L);
		if (offlinetarget != null)
			offlinetarget.setBanned(false);
		
		SavedFile.savePlayer(getCTarget(), false);
		
	}
*/
} 
