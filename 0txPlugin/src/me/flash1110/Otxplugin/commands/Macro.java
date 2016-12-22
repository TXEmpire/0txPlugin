package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Macro implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Nope.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("empireplugin.macro")) {
			sender.sendMessage(ChatColor.RED + "You lack the mighty permission to do that!");
			return true;
		}
		CombatPlayer cp = OtxPlugin.getPlayer(player);
		
		if (cp == null) {
			player.kickPlayer("Fatal Error. Relog");
			return true;
		}
		
		if (args.length == 0) {
			cp.setMacro(!cp.isMacro());
			
			player.sendMessage(ChatColor.GOLD + "Macro toggle has been set to " + (cp.isMacro() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
			return true;
		} else {
			String first = args[0].toLowerCase();
			
			switch (first) {
			case "on":
				if (cp.isMacro()) {
					player.sendMessage(ChatColor.GOLD + "You already have Macro enabled");
					return true;
				}
				
				cp.setMacro(true);
				player.sendMessage(ChatColor.GOLD + "Macro toggle has been set to " + ChatColor.GREEN + "Enabled");
				
				break;
				
			case "off":
				if (!cp.isMacro()) {
					player.sendMessage(ChatColor.GOLD + "You already have Macro disabled");
					return true;
				}
				
				cp.setMacro(false);
				player.sendMessage(ChatColor.GOLD + "Macro toggle has been set to " + ChatColor.RED + "Disabled");
				
				break;
				
				default:
					player.sendMessage(ChatColor.GOLD + "/macro on|off");
					break;
			}
		}
		
		return true;
	}

}
