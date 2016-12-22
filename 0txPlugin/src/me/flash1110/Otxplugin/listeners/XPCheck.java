package me.flash1110.Otxplugin.listeners;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class XPCheck implements Listener {
	
	public static OtxPlugin plugin;
	
	public XPCheck(OtxPlugin instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		final Player player = event.getEntity();
	
		if (player.hasPermission("empireplugin.end")) {
			final Integer xp = getTotalExperience(player);
			
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 33);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "End " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "50% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, (xp / 100) * 50);
	    			return;
	    		}
	    	}, 60);
	    } else if (player.hasPermission("empireplugin.emperor")) {
	    	final Integer xp = getTotalExperience(player);
	    	
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 64);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "Emperor " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "25% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, (xp / 100) * 25);
	    			return;
	    		}
	    	}, 60);
	    } else if (player.hasPermission("empireplugin.king")) {
	    	final Integer xp = getTotalExperience(player);
	    	
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 72);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "King " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "20% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, (xp / 100) * 20);
	    			return;
	    		}
	    	}, 60);
	    } else if (player.hasPermission("empireplugin.general")) {
	    	final Integer xp = getTotalExperience(player);
	    	
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 77);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "General " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "15% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, xp / 100 * 15);
	    			return;
	    		}
	    	}, 60);
	    } else if (player.hasPermission("empireplugin.knight")) {
	    	final Integer xp = getTotalExperience(player);
	    	
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 82);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "Knight " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "10% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, xp / 100 * 10);
	    			return;
	    		}
	    	}, 60); 
	    } else if (player.hasPermission("empireplugin.soldier")) {
	    	final Integer xp = getTotalExperience(player);
	    	
	    	event.setDroppedExp(getTotalExperience(player) / 100 * 87);
	    	
	    	Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
	    		public void run() {
	    			if (player == null || !player.isOnline()) return;
	    			player.sendMessage(ChatColor.GOLD + "Since you donated to " + ChatColor.RED + "Soldier " + ChatColor.GOLD + "you have recieved " + ChatColor.LIGHT_PURPLE + "5% " + ChatColor.GOLD + "of your XP");
	    			setTotalExperience(player, xp / 100 * 5);
	    			return;
	    		}
	    	}, 60);
	    }  else {
	    	return;
	    }
		
	}
	
	public static void setTotalExperience(final Player player, final int exp)
	{
		if (exp < 0)
		{
			throw new IllegalArgumentException("Experience is negative!");
		}
		player.setExp(0);
		player.setLevel(0);
		player.setTotalExperience(0);

		//This following code is technically redundant now, as bukkit now calulcates levels more or less correctly
		//At larger numbers however... player.getExp(3000), only seems to give 2999, putting the below calculations off.
		int amount = exp;
		while (amount > 0)
		{
			final int expToLevel = getExpAtLevel(player);
			amount -= expToLevel;
			if (amount >= 0)
			{
				// give until next level
				player.giveExp(expToLevel);
			}
			else
			{
				// give the rest
				amount += expToLevel;
				player.giveExp(amount);
				amount = 0;
			}
		}
	}
	
	private static int getExpAtLevel(final Player player)
	{
		return getExpAtLevel(player.getLevel());
	}

	public static int getExpAtLevel(final int level)
	{
		if (level > 29)
		{
			return 62 + (level - 30) * 7;
		}
		if (level > 15)
		{
			return 17 + (level - 15) * 3;
		}
		return 17;
	}
	
	public static int getExpToLevel(final int level)
	{
		int currentLevel = 0;
		int exp = 0;

		while (currentLevel < level)
		{
			exp += getExpAtLevel(currentLevel);
			currentLevel++;
		}
		if (exp < 0)
		{
			exp = Integer.MAX_VALUE;
		}
		return exp;
	}

	//This method is required because the bukkit player.getTotalExperience() method, shows exp that has been 'spent'.
	//Without this people would be able to use exp and then still sell it.
	public static int getTotalExperience(final Player player)
	{
		int exp = (int)Math.round(getExpAtLevel(player) * player.getExp());
		int currentLevel = player.getLevel();

		while (currentLevel > 0)
		{
			currentLevel--;
			exp += getExpAtLevel(currentLevel);
		}
		if (exp < 0)
		{
			exp = Integer.MAX_VALUE;
		}
		return exp;
	}
	
	public static int getExpUntilNextLevel(final Player player)
	{
		int exp = (int)Math.round(getExpAtLevel(player) * player.getExp());		
		int nextLevel = player.getLevel();
		return getExpAtLevel(nextLevel) - exp;
	}
}
