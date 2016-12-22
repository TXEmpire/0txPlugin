package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.objects.nick.InventoryColour;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("0txplugin.nick.view") && !sender.hasPermission("0txplugin.nick")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You can't have a nick!");
			return true;
		}
		
		InventoryColour.CreateInventory((Player) sender);
		
		return true;		
		
	}
	
	
}
