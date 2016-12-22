package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

public class Event_ChangeTime_Day extends SpendEvent {
	
	Integer slot;
	
	Long LastUsed;
	
	public Event_ChangeTime_Day(Integer slot, Integer cost) {
		super (slot, cost);
		LastUsed = (long) 0;
	}
	
	@Override
	public void run(Player player) {

		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a server boost!");
		
		player.getWorld().setTime(2000);
		OtxPlugin.BroadcastMessage(ChatColor.YELLOW + "Time is set to" + ChatColor.LIGHT_PURPLE + " Day " + ChatColor.YELLOW + "by " + ChatColor.GRAY + player.getDisplayName());
		
		LastUsed = System.currentTimeMillis();
		
	}
	
	@Override
	public Boolean check(Player player) {
		
		if (player.getWorld().getEnvironment() == Environment.NORMAL) {
			
			if (player.getWorld().getTime() >= 12000) {
				
				Long difference = System.currentTimeMillis() - LastUsed;
				
				if (difference < 30000) {
					
					Integer secondsgone = 0;
					
					while (difference >= 1000) {
						secondsgone++;
						difference-= 1000;
					}
					
					Integer secondsleft = 30 - secondsgone;
					
					player.sendMessage(ChatColor.RED + "Time was recently set to day " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
					player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
					
					return false;
				}
				
				return true;
				
			} else {
				player.sendMessage(ChatColor.RED + "It is already day within your world");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "You are not within a world that has a day/night cycle");
		}
		
		return false;
		
	}

}
