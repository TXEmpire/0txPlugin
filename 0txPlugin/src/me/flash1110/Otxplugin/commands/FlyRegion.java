package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyRegion implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Cannot use this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (!p.hasPermission("0txplugin.region")) {
			p.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		if (args.length <= 1) {
			p.sendMessage(ChatColor.GOLD + "Add a region: " + ChatColor.RED + "/regen add [region]");
			p.sendMessage(ChatColor.GOLD + "Delete a region: " + ChatColor.RED + "/regen delete [region]");
			return true;
		}
		
		String first = args[0].toLowerCase();
		String region = args[1].toLowerCase();
		
		switch (first) {
		case "add":
			if (Data.doesRegionExist(region)) {
				sender.sendMessage(ChatColor.RED + "That region is already added");
			} else {
				Data.addRegion(region);
				sender.sendMessage(ChatColor.GREEN + "Region added");
			}
			return true;
		case "delete":
			if (!Data.doesRegionExist(region)) {
				sender.sendMessage(ChatColor.RED + "That region is not added");
			} else {
				Data.deleteRegion(region);
				sender.sendMessage(ChatColor.GREEN + "Region removed");
			}
			return true;
		default:
			p.sendMessage(ChatColor.GOLD + "Add a region: " + ChatColor.RED + "/regen add [region]");
			p.sendMessage(ChatColor.GOLD + "Delete a region: " + ChatColor.RED + "/regen delete [region]");
			return true;
		}
	}
}
