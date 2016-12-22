package me.flash1110.Otxplugin.commands;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;
import org.kitteh.vanish.staticaccess.VanishNotLoadedException;

import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class List implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("0txplugin.list")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		HashMap<String, TreeSet<String>> UserGroups = new HashMap<String, TreeSet<String>>();
		
		Boolean canseevanisheed = sender.hasPermission("0tx.list.hidden");
		
		for (Player P : Bukkit.getOnlinePlayers()) {
			
			String[] groups = PermissionsEx.getUser(P).getGroupNames();
			
			String group = "default";
			
			if (groups.length > 0)
				group = groups[0];
			
			String prefix = OtxPlugin.convertColour(OtxPlugin.convertFormat(PermissionsEx.getUser(P).getPrefix()));
			
			boolean isvanished = false;
			
				try {
					isvanished = VanishNoPacket.isVanished(P.getName());
				} catch (VanishNotLoadedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if (isvanished && !canseevanisheed)
				continue;
			
			if (UserGroups.containsKey(group))
				UserGroups.get(group).add(ChatColor.GRAY + prefix + P.getDisplayName() + (isvanished == true ? ChatColor.DARK_GRAY + "[HIDDEN]" : ""));
			else {
				UserGroups.put(group, new TreeSet<String>());
				UserGroups.get(group).add(ChatColor.GRAY + prefix + P.getDisplayName() + (isvanished == true ? ChatColor.DARK_GRAY + "[HIDDEN]" : ""));
			}
			
		}
		
		SortedSet<String> keys = new TreeSet<String>(UserGroups.keySet());
		
		sender.sendMessage(ChatColor.GOLD + "There are " + 
				ChatColor.RED + Bukkit.getOnlinePlayers().length + 
				ChatColor.GOLD + " out of a maximum of " + 
				ChatColor.RED + Bukkit.getServer().getMaxPlayers() + 
				ChatColor.GOLD + " players online.");
		
		for (String S : keys) {
			
			String online = "";
			
			for (String P : UserGroups.get(S)) {
				
				if (online.equals("")) {
					online = ChatColor.GOLD + S + ": " + ChatColor.GRAY + P;
				} else {
					online = online + ChatColor.WHITE + ", " + ChatColor.GRAY + P;
				}
				
			}
			
			sender.sendMessage(online);
			
		}
		
		return true;		
		
	}
	
}

