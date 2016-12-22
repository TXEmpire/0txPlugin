package me.flash1110.Otxplugin.offline.types;

import me.flash1110.Otxplugin.offline.OfflineAccessType;


public class UnMuteOffline  /* extends OfflineAccessType */{
/*	
	
	public UnMuteOffline(UUID sender, String targetname) {
		super(sender, targetname);
	}
	
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
		
		if (!getCTarget().getMuted()) {
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getCTarget().getName() + ChatColor.RED + " is not muted");
			return;
		}
		
		getCTarget().setMuteTime(0L);
		sender.sendMessage(ChatColor.GOLD + "You have unmuted " + ChatColor.RED + getCTarget().getName());
		
		SavedFile.savePlayer(getCTarget(), false);
		
	} */

} 
