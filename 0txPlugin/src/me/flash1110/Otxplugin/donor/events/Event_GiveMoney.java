package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_GiveMoney extends SpendEvent {

	Integer slot;

	Integer Money;
	
	Long LastUsed;
	
	public Event_GiveMoney(Integer slot, Integer cost, Integer money) {
		super(slot, cost);
		Money = money;
		LastUsed = (long) 0;
	}

	@Override
	public void run(Player player) {
		
		for (Player P : Bukkit.getOnlinePlayers()) {
			OtxPlugin.getEcon().depositPlayer(P.getName(), Money);
		}
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		
		LastUsed = System.currentTimeMillis();
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " gives everyone " + ChatColor.LIGHT_PURPLE + "$" + Money);
	}

	@Override
	public Boolean check(Player player) {

		Long difference = System.currentTimeMillis() - LastUsed;
		
		if (difference < 60000) {
			
			Integer secondsgone = 0;
			
			while (difference >= 1000) {
				secondsgone++;
				difference-= 1000;
			}
			
			Integer secondsleft = 60 - secondsgone;
			
			player.sendMessage(ChatColor.RED + "Everyone was recently given money " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
			player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
			
			return false;
		}
		
		return true;

	}
}
