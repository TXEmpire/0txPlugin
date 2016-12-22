package me.flash1110.Otxplugin.donor.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Event_BuyPoints extends SpendEvent {

	Integer slot;
	
	String Url;
	
	public Event_BuyPoints(Integer slot, Integer cost, String url) {
		super (slot, cost);
		Url = url;
	}
	
	@Override
	public void run(Player player) {
		
		player.sendMessage(ChatColor.GREEN + "Go to " + ChatColor.GOLD + Url + ChatColor.GREEN + " to buy boost points");
	}
	
	@Override
	public Boolean check(Player player) {
		return true;
		
	}
}
