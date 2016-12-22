package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_IncreaseMobOreDrops extends SpendEvent {

	Integer slot;

	static Boolean Active;
	static Integer Multiplier = 1;
	
	Integer Time;
	Integer LocalMultiplier;

	public Event_IncreaseMobOreDrops(Integer slot, Integer cost, Integer time, Integer multiplier) {
		super(slot, cost);
		Time = time;
		Active = false;
		LocalMultiplier = multiplier;
	}

	@Override
	public void run(Player player) {
		
		Active = true;
		Multiplier = LocalMultiplier;
		
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " gives everyone " + ChatColor.RED + (LocalMultiplier == 2 ? "double" : "triple") + ChatColor.YELLOW + " drops from mobs & ores for " + ChatColor.LIGHT_PURPLE + Time + ChatColor.YELLOW + " minutes");

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ChatColorListener.plugin, new Runnable() {
			int time = 6000;
			public void run() {
				time = time - 20;
				
				switch (time) {
				case 3000:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost &edouble drops &ahas &e2m30s &aleft!"));
					break;
				case 1200:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost &edouble drops &ahas &e1m &aleft!"));
					
					break;
				case 600:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost &edouble drops &ais about to expire!"));
					break;
				default:
					break;
				}	
			}
		}, 20, 20);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ChatColorListener.plugin, new Runnable() {
			public void run() {
				
				OtxPlugin.BroadcastMessage(ChatColor.RED + (LocalMultiplier == 2 ? "Double" : "Triple") + " mob & ore drops has expired");
				Active = false;

			}
		}, Time * 60 * 20);
		
	}

	@Override
	public Boolean check(Player player) {

		if (Active) {
			player.sendMessage(ChatColor.RED + "There is already an drop multiplier in effect");
			return false;
		}
		
		return true;

	}

	public static Boolean getActive() {
		return Active;
	}
	
	public static Integer getMultiplier() {
		return Multiplier;
	}
	
}
