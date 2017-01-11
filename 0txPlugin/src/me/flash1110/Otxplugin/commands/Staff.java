package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staff implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("empireplugin.staffchat")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		if (args.length == 0) {
			
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Please include a message");
				return true;
			}
			
			Player player = (Player) sender;
			
			CombatPlayer cplayer = OtxPlugin.getPlayer(player);
			
			if (cplayer.getStaffChat()) {
				player.sendMessage(ChatColor.GOLD + "Auto staff chat " + ChatColor.RED + "disabled");
				cplayer.setStaffChat(false);
			} else {
				player.sendMessage(ChatColor.GOLD + "Auto staff chat " + ChatColor.GREEN + "enabled");
				cplayer.setStaffChat(true);
			}
			
		} else {
			
			String message = "";
			
			for (int i=0; i< args.length; i++)
				message+= args[i] + " ";
			
			sendMessage(message, (sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName()));
			
		}
		
		return true;
		
	}
	
	public static void sendMessage(String message, String name) {
		
		message = OtxPlugin.convertColour(message);
		message = OtxPlugin.convertFormat(message);
		
		String format =	ChatColor.GOLD + "" + ChatColor.BOLD + "[" + 
				ChatColor.RED + ChatColor.BOLD + "Staff" + 
				ChatColor.GOLD + ChatColor.BOLD + "] " + 
				ChatColor.BLUE + name + ChatColor.WHITE + "> " + ChatColor.YELLOW + message; 
		
		Bukkit.getServer().broadcast(format, "empireplugin.staffchat")	
	}
}
