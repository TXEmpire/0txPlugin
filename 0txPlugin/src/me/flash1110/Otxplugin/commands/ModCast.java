package me.flash1110.Otxplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("empireplugin.modcast")) {
			sender.sendMessage(ChatColor.RED + "You lack permission to use modcast");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "You must supply a message");
			return true;
		}
		
		String msg = "";
		
		int msgon = 0;
		
		while (msgon < args.length) {
			msg = msg + args[msgon] + " ";
			msgon++;
		}
		
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		
		if (!(sender instanceof Player)) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + sender.getName() + ": " + ChatColor.DARK_RED + "ModCast" + ChatColor.GOLD + "] " + ChatColor.RESET + msg);
			return true;
		}
		
		Player player = (Player) sender;
		
		Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + player.getDisplayName() + ": " + ChatColor.DARK_RED + "ModCast" + ChatColor.GOLD + "] " + ChatColor.RESET + msg);
		return true;
	}
}
