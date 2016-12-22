package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_Heal extends SpendEvent {
	
	Integer slot;
	
	Long LastUsed;
	
	public Event_Heal(Integer slot, Integer cost) {
		super (slot, cost);
		LastUsed = (long) 0;
	}
	
	@Override
	public void run(Player player) {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setSaturation(0);
			p.setFoodLevel(20);
			p.setHealth(20);
		}
		
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		
		LastUsed = System.currentTimeMillis();
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " healed everyone!");
		
	}
	
	@Override
	public Boolean check(Player player) {

		Long difference = System.currentTimeMillis() - LastUsed;
		
		if (difference < 120000) {
			
			Integer secondsgone = 0;
			
			while (difference >= 1000) {
				secondsgone++;
				difference-= 1000;
			}
			
			Integer secondsleft = 120 - secondsgone;
			
			player.sendMessage(ChatColor.RED + "Everyone was recently healed " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
			player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
			
			return false;
		}
		
		return true;
		
	}

}
