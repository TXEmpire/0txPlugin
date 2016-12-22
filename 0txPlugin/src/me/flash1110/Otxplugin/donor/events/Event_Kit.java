package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_Kit extends SpendEvent {

	Integer slot;
	
	String Kit;
	
	Long LastUsed;
	
	public Event_Kit(Integer slot, Integer cost, String kit) {
		super (slot, cost);
		Kit = kit;
		LastUsed = (long) 0;
	}
	
	@Override
	public void run(Player player) {
		
		for (Player p : Bukkit.getOnlinePlayers())
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit " + Kit + " " + p.getName());
		
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		
		LastUsed = System.currentTimeMillis();
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " gives everyone Kit " + ChatColor.LIGHT_PURPLE + Kit);
		
	}
	
	@Override
	public Boolean check(Player player) {

		Long difference = System.currentTimeMillis() - LastUsed;
		
		if (difference < 600000) {
			
			Integer secondsgone = 0;
			
			while (difference >= 1000) {
				secondsgone++;
				difference-= 1000;
			}
			
			Integer secondsleft = 60 - secondsgone;
			
			player.sendMessage(ChatColor.RED + "Everyone was recently given a kit " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
			player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
			
			return false;
		}
		
		return true;
		
	}
}
