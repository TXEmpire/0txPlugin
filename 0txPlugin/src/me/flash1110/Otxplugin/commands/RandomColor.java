package me.flash1110.Otxplugin.commands;

import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.file.Data;
import me.flash1110.Otxplugin.offline.OfflineAccess;
import me.flash1110.Otxplugin.offline.types.ColorOffline;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomColor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("Nope");
				return true;
			} else if (args.length == 1) {
			
			
			String name = args[0];
			Player play = Bukkit.getPlayer(name);
			if (play == null || !play.isOnline()) {
				sender.sendMessage(ChatColor.RED + "Correct Usage if offline: /randomcolor <player> <on/off>");
				return true;
			}
			CombatPlayer player = OtxPlugin.getPlayer(play);
			player.setRandomColor(!player.isRandomColor());
			sender.sendMessage(ChatColor.GOLD + "Set to " + player.isRandomColor());
			return true;
			} else if (args.length >= 2) {
				String name = args[0];
				Boolean which = (args[1].equalsIgnoreCase("off") ? false : true);
				Player play = Bukkit.getPlayer(name);
				if (play == null || !play.isOnline()) {
					UUID senduuid = null;
					
					if (sender instanceof Player) {
						senduuid = ((Player) sender).getUniqueId();
					}
					
					OfflineAccess OfflinePlayer = new OfflineAccess(ChatColorListener.plugin, new ColorOffline(senduuid, args[0], which));
					Thread OfflineThread = new Thread(OfflinePlayer);
					OfflineThread.start();
					return true;
				}
				CombatPlayer player = OtxPlugin.getPlayer(play);
				player.setRandomColor(which);
				sender.sendMessage(ChatColor.GOLD + "Set to " + player.isRandomColor());
				return true;
			}
		}
		
		if (!sender.hasPermission("empireplugin.randomcolor")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}

		Player player = (Player) sender;
		CombatPlayer cplayer = OtxPlugin.getPlayer(player);
		if (args.length == 0) {
			cplayer.setRandomColor(!cplayer.isRandomColor());
			sender.sendMessage(ChatColor.GOLD + "Random Color has been " + (cplayer.isRandomColor() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));
			return true;
		} else {
	/*		
			User us = OtxPlugin.getEss().getUser(player);
			if (us.isMuted()) {
			sender.sendMessage(ChatColor.RED + "Cannot use Random Color while muted.");
			return true; */
			}
			
			int cooldown = Data.getColorCooldown();
			Long dif = (System.currentTimeMillis() - cplayer.getColor())/1000;
			
			if (dif < cooldown && !player.hasPermission("empireplugin.randomcolor.bypass")) {
				player.sendMessage(ChatColor.GOLD + "Please wait " + ChatColor.LIGHT_PURPLE + (60 - dif.intValue()) + ChatColor.GOLD + " before sending another Random Color message - to turn it off do /random");
				return true;
			}
			
			String[] parts = args;
			StringBuffer buffer = new StringBuffer();
			for (String string : parts) {
				ChatColor color = ChatColorListener.getRandomColor();
				buffer.append(color + string + " ");
			}
			
			Bukkit.broadcastMessage(player.getDisplayName() + ": " + buffer.toString());
			cplayer.setColor(System.currentTimeMillis());
			return true;
		}
}
