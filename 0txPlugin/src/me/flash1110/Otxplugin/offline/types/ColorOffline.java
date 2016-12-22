package me.flash1110.Otxplugin.offline.types;

import java.util.UUID;

import me.flash1110.Otxplugin.objects.file.SavedFile;
import me.flash1110.Otxplugin.offline.OfflineAccessType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ColorOffline extends OfflineAccessType {

	private Boolean Which;
	
	public ColorOffline(UUID sender, String targetname, boolean which) {
		super(sender, targetname);
		Which = which;
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
		
		if (Which) {
			getCTarget().setRandomColor(true);
			sender.sendMessage(ChatColor.GOLD + "Random Color has been " + ChatColor.GREEN + "enabled");
		} else {
			getCTarget().setRandomColor(false);
			sender.sendMessage(ChatColor.GOLD + "Random Color has been " + ChatColor.GREEN + "enabled");
		}
		
		SavedFile.savePlayer(getCTarget(), false);
		
	}
}
