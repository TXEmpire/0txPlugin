package me.flash1110.Otxplugin.commands;

import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.offline.OfflineAccess;
import me.flash1110.Otxplugin.offline.types.DonorOffline;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bonus implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		

		if (args.length > 0 && sender.hasPermission("empireplugin.boost.admin")) {
			String first = args[0].toLowerCase();
			
			switch (first) {
			
			case "view" :
				break;
				
			case "add" :
				break;
				
			case "remove" :
				break;
			
			case "set" :
				break;
				
			default :
				sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/boost <view|add|remove|set> <player> [#]");
				return true;
			}
			
			int amount = 0;
			
			if (args.length == 1) {
				
				if (first.equals("view"))
					sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/boost " + args[0].toLowerCase() + " <player>");
				else
					sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/boost " + args[0].toLowerCase() + " <player> <amount>");
				
				return true;
			
			} else if (args.length == 2 && !first.equals("view")) {
				sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/boost " + args[0].toLowerCase() + " <player> <amount>");
				return true;
			} else if (!first.equals("view")) {
				
				try {
					amount = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage(ChatColor.RED + "Amount should be a valid number");
					return true;
				}
				
			}
			
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[1]);			
			if (target == null || !target.isOnline()) {
				
				UUID uuid = null;
				
				if (sender instanceof Player) {
					uuid = ((Player) sender).getUniqueId();		
					
				}
				
				OfflineAccess OfflinePlayer = new OfflineAccess(ChatColorListener.plugin, new DonorOffline(uuid, args[1], args[0], amount));
				Thread OfflineThread = new Thread(OfflinePlayer);
				OfflineThread.start();
				
			} else {
				
				CombatPlayer ctarget = OtxPlugin.getPlayer(target);
				
				switch (first) {
				
				case "view" :
					sender.sendMessage(ChatColor.GRAY + target.getDisplayName() + ChatColor.GOLD + " has " + ChatColor.LIGHT_PURPLE + ctarget.getDonnorPointBalance() + ChatColor.GOLD + " boost points");
					break;
					
				case "add" :
					ctarget.addDonnorPointBalance(amount);
					sender.sendMessage(ChatColor.GOLD + "Added " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " boost points to " + ChatColor.GRAY + target.getDisplayName());
					target.sendMessage(ChatColor.GOLD + "You were credited with " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " boost points");
					break;
					
				case "remove" :
					ctarget.removeDonnorPointBalance(amount);
					sender.sendMessage(ChatColor.GOLD + "Removed " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " boost points from " + ChatColor.GRAY  + target.getDisplayName());
					target.sendMessage(ChatColor.RED + "You had " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " boost points removed");
					break;
				
				case "set" :
					ctarget.setDonnorPointBalance(amount);
					sender.sendMessage(ChatColor.GOLD + "Set " + ChatColor.GRAY + target.getDisplayName() + ChatColor.GOLD + " boost points to " + ChatColor.LIGHT_PURPLE + amount);
					target.sendMessage(ChatColor.GOLD + "Your boost points were set to " + ChatColor.LIGHT_PURPLE + amount);
					break;
					
				}
				
			}
			
		} else {
			
			
			if (!(sender instanceof Player)) {
				sender.sendMessage("You can't do this!");
				return true;
			}
			Player player = (Player) sender;
			
				if (OtxPlugin.StoredInventories == null || OtxPlugin.StoredInventories.size() < 1) {
					return true;
				}
				
				if (OtxPlugin.StoredInventories.get(ChatColor.BOLD + "" + ChatColor.BLACK + "Server Boost Menu") == null) {
					sender.sendMessage(ChatColor.RED + "Cannot find the Boost menu");
					return true;
				}
				OtxPlugin.StoredInventories.get(ChatColor.BOLD + "" + ChatColor.BLACK + "Server Boost Menu").open(player);
			}

		
		return true;
	}

	
}
