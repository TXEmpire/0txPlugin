package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Donation implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("empireplugin.donation")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/donation <amount>");
			return true;
		}
		
		Double amount;
		try {
			amount = Double.parseDouble(args[0]);
		} catch (NumberFormatException ex) {
			sender.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.GOLD + "/donation <amount>");
			return true;
		}
		
		Data.addMoney(amount);
		sender.sendMessage(ChatColor.GOLD + "Added " + ChatColor.LIGHT_PURPLE + amount + ChatColor.GOLD + " to the total amount");
		return true;
	}

}
