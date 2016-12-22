package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

public class Event_ChangeWeather_Clear extends SpendEvent {
	
	Integer slot;
	
	Long LastUsed;
	
	public Event_ChangeWeather_Clear(Integer slot, Integer cost) {
		super (slot, cost);
		LastUsed = (long) 0;
	}
	
	@Override
	public void run(Player player) {
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a server boost!");
		
		player.getWorld().setStorm(true);
		player.getWorld().setWeatherDuration(1);
		OtxPlugin.BroadcastMessage(ChatColor.YELLOW + "Weather is set to" + ChatColor.LIGHT_PURPLE + " Clear " + ChatColor.YELLOW + "by " + ChatColor.GRAY + player.getDisplayName());
		
		LastUsed = System.currentTimeMillis();
		
	}
	
	@Override
	public Boolean check(Player player) {
		
		if (player.getWorld().getEnvironment() == Environment.NORMAL) {
			
			if (player.getWorld().hasStorm()) {
				
				Long difference = System.currentTimeMillis() - LastUsed;
				
				if (difference < 30000) {
					
					Integer secondsgone = 0;
					
					while (difference >= 1000) {
						secondsgone++;
						difference-= 1000;
					}
					
					Integer secondsleft = 30 - secondsgone;
					
					player.sendMessage(ChatColor.RED + "Weather was recently set to clear " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
					player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
					
					return false;
				}
				
				return true;
				
			} else {
				player.sendMessage(ChatColor.RED + "It is already clear in your world");
			}
			
		} else {
			player.sendMessage(ChatColor.RED + "You are not within a world that has weather");
		}
		
		return false;
		
	}

}
