package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_NoWaterDamage extends SpendEvent {

	Integer slot;

	static boolean Active;

	Integer Time;

	public Event_NoWaterDamage(Integer slot, Integer cost, Integer time) 
	{
		super(slot, cost);
		Time = time;
		Active = false;
	}

	@Override
	public void run(Player player) {
		Active = true;

		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a server boost!");
		
		OtxPlugin.BroadcastMessage(ChatColor.GRAY  + player.getDisplayName() + ChatColor.YELLOW + " gives everyone no water damage for " + ChatColor.LIGHT_PURPLE + Time + ChatColor.YELLOW + " minutes");

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ChatColorListener.plugin, new Runnable() {
			public void run() {
				
				OtxPlugin.BroadcastMessage(ChatColor.RED + "No water damage has expired");
				Active = false;

			}
		}, Time * 60 * 20);
		
	}

	@Override
	public Boolean check(Player player) {

		if (Active) {
			player.sendMessage(ChatColor.RED + "All players are already taking no water damage");
			return false;
		}

		return true;

	}

	public static Boolean getActive() {
		return Active;
	}
}
