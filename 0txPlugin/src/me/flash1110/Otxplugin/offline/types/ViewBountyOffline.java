package me.flash1110.Otxplugin.offline.types;

import java.util.UUID;

import me.flash1110.Otxplugin.offline.OfflineAccessType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ViewBountyOffline extends OfflineAccessType {

	public ViewBountyOffline(UUID sender, String targetname) {
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
		
		if (sender != null)
			sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + getCTarget().getName() + ChatColor.GOLD + " has a bounty of " + ChatColor.LIGHT_PURPLE + getCTarget().getBounty());
				
	}
}
