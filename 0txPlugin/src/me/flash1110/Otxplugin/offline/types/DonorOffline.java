package me.flash1110.Otxplugin.offline.types;

import java.util.UUID;

import me.flash1110.Otxplugin.objects.file.SavedFile;
import me.flash1110.Otxplugin.offline.OfflineAccessType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DonorOffline extends OfflineAccessType {
	
	private String Type;
	private Integer Amount;
	
	public DonorOffline(UUID sender, String targetname, String type, int amount) {
		super(sender, targetname);
		Type = type;
		Amount = amount;
	}
	
	@Override
	public void call() {
		
		CommandSender sender;
		
		if (getSenderID() != null) {
			sender = Bukkit.getPlayer(getSenderID());
		} else
			sender = Bukkit.getConsoleSender();
		
		if (getCTarget() == null) {
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getTargetName() + ChatColor.RED + " has never joined");	
			return;
		}
		
		switch (Type.toLowerCase()) {
		
		case "view" :
			if (sender != null)
				sender.sendMessage(ChatColor.GRAY + getTargetName() + ChatColor.GOLD + " has " + ChatColor.LIGHT_PURPLE + getCTarget().getDonnorPointBalance() + ChatColor.GOLD + " boost points");
			break;
			
		case "add" :
			getCTarget().addDonnorPointBalance(Amount);
			if (sender != null)
				sender.sendMessage(ChatColor.GOLD + "Added " + ChatColor.LIGHT_PURPLE + Amount + ChatColor.GOLD + " boost points to " + ChatColor.GRAY +  getTargetName());
			break;
			
		case "remove" :
			getCTarget().removeDonnorPointBalance(Amount);
			if (sender != null)
				sender.sendMessage(ChatColor.GOLD + "Removed " + ChatColor.LIGHT_PURPLE + Amount + ChatColor.GOLD + " boost points from " + ChatColor.GRAY + getTargetName());
			break;
		
		case "set" :
			getCTarget().setDonnorPointBalance(Amount);
			if (sender != null)
				sender.sendMessage(ChatColor.GOLD + "Set " + ChatColor.GRAY + getTargetName() + ChatColor.GOLD + " boost points to " + ChatColor.LIGHT_PURPLE + Amount);
			break;
			
		}
		
		SavedFile.savePlayer(getCTarget(), false);
		
	}

}

