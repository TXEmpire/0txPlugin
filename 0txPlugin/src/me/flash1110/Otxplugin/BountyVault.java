package me.flash1110.Otxplugin;

import me.flash1110.Otxplugin.objects.CombatPlayer;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class BountyVault {

	public static Economy economy = OtxPlugin.getEcon();
	
	public static Boolean increaseBounty(Player sender, Player target, int amount) {
		
		
		EconomyResponse r = economy.withdrawPlayer(sender.getName(), amount);
		if (!r.transactionSuccess()) {
			sender.sendMessage(ChatColor.RED + "You lack the balance to do this");
			return false;
		}
		
		sender.sendMessage(ChatColor.GOLD + "Bounty of " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " placed on " + ChatColor.GRAY + target.getDisplayName());
		
		CombatPlayer cplayer = OtxPlugin.getPlayer(target);
		cplayer.addToBounty(amount);
//		Titles.sendTitle(target, 10, 20, 10, "", ChatColor.RED + "Your bounty has been raised to " + ChatColor.LIGHT_PURPLE + cplayer.getBounty());
		
		broadcastBounty(target, cplayer.getBounty());
		
		return true;
	}
	
	public static void claimBounty(Player player, int amount, Player killed) {
		
		economy.depositPlayer(player.getName(), amount);
		player.sendMessage(ChatColor.GOLD + "You claimed a bounty of " + ChatColor.LIGHT_PURPLE + amount);
		Bukkit.broadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GOLD + " claims a bounty of " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " from " + ChatColor.GRAY + killed.getDisplayName());
		
	}
	
	public static void broadcastBounty(Player target, int newbounty) {
		
		Bukkit.broadcastMessage(ChatColor.GRAY + target.getDisplayName() + ChatColor.GOLD + " has a bounty of " + ChatColor.LIGHT_PURPLE + newbounty + ChatColor.GOLD + " on their head");
		
	} 
} 
