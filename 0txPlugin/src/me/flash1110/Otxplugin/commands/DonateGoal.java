package me.flash1110.Otxplugin.commands;


import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DonateGoal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("empireplugin.donategoal")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + Data.getMoney() + ChatColor.GOLD + " has been donated this month.");
			sender.sendMessage(ChatColor.GOLD + "The goal is " + ChatColor.LIGHT_PURPLE + Data.getGoal());
			return true;
		}
		
		if (args.length == 1) {
			sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/donategoal set <goal>");
			return true;
		}
		
		String first = args[0].toLowerCase();
		
		if (first != "set") {
			sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/donategoal set <goal>");
			return true;
		}
		
		int amount;
		try {
		  amount = Integer.parseInt(args[1]);	
		} catch (NumberFormatException ex) {
			sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/donategoal set <goal>");
			return true;
		}
		
		Data.setGoal(amount);
		sender.sendMessage(ChatColor.GOLD + "Set " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " as the goal");
		return true;
	}

}
