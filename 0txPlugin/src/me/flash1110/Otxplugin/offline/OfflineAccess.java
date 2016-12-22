package me.flash1110.Otxplugin.offline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class OfflineAccess implements Runnable {
	
	private		OfflineAccessType		Offlineaccesstype;
	
	private		OtxPlugin			Plugin;
	
	public OfflineAccess(OtxPlugin p, OfflineAccessType oct) { Plugin = p; Offlineaccesstype = oct; };
	
	@Override
	public void run() {
		
		UUID Order = Offlineaccesstype.getSenderID();
		String NameTarget = Offlineaccesstype.getTargetName();		
		
		if (NameTarget.length() <= 2) {
			
			CommandSender sender;
			
			if (Order != null)
				sender = Bukkit.getPlayer(Order);
			else
				sender = Bukkit.getConsoleSender();
			
			if (sender == null)
				return;
			
			if (sender instanceof Player) {
				
				if (!((Player) sender).isOnline())
					return;
				
			}
			
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + NameTarget + ChatColor.RED + " has never joined");
			return;
			
		}
		
		String firstletter = new String(NameTarget).toLowerCase().substring(0, 1);
		String secondletter = new String(NameTarget).toLowerCase().substring(1, 2);
		
		File OfflineFile = new File(Plugin.getDataFolder(), "OfflineAccess/" + firstletter + "/" +  firstletter + secondletter + ".txt");
		
		if (!OfflineFile.exists()) {
			
			CommandSender sender;
			
			if (Order != null)
				sender = Bukkit.getPlayer(Order);
			else
				sender = Bukkit.getConsoleSender();
			
			if (sender == null)
				return;
			
			if (sender instanceof Player) {
				
				if (!((Player) sender).isOnline())
					return;
				
			}
			
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + NameTarget + ChatColor.RED + " has never joined");
			return;
			
		}
		
		FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
		
		if (!ofconfig.contains(new String(NameTarget).toLowerCase())) {
			
			CommandSender sender;
			
			if (Order != null)
				sender = Bukkit.getPlayer(Order);
			else
				sender = Bukkit.getConsoleSender();
			
			if (sender == null)
				return;
			
			if (sender instanceof Player) {
				
				if (!((Player) sender).isOnline())
					return;
				
				
			}
			
			sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + NameTarget + ChatColor.RED + " has never joined");
			return;
			
		}
		
		UUID targetID = UUID.fromString(ofconfig.getString(new String(NameTarget).toLowerCase()));
		
		String first2id = targetID.toString().substring(0, 2);
		
		File PlayerFile = new File(Plugin.getDataFolder(), "Players/" + first2id + "/" +  targetID + ".txt");
		
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(PlayerFile);
		
		String PLAYERNAME = pConfig.getString("PLAYERNAME");
		String DISPLAYNAME = pConfig.contains("DISPLAYNAME") ? pConfig.getString("DISPLAYNAME").replace("&", "�") : null;
		String LASTBANMESSAGE = pConfig.contains("LASTBANMESSAGE") ? OtxPlugin.convertFormat(OtxPlugin.convertColour(pConfig.getString("LASTBANMESSAGE"))) : null;
		Boolean kill = pConfig.getBoolean("KILL");
		List<String> TheBans = new ArrayList<String>(pConfig.getStringList("BANS"));
		List<String> Bans = new ArrayList<String>();
		
		for (String ban : TheBans)
			Bans.add(OtxPlugin.convertFormat(OtxPlugin.convertColour(ban)));
		
		Long BanTime = pConfig.contains("TIME.BAN") ? pConfig.getLong("TIME.BAN") : 0L;
		Long MuteTime = pConfig.contains("TIME.MUTE") ? pConfig.getLong("TIME.MUTE") : 0L;
		
		Integer DONNOR_POINTS = pConfig.getInt("DONNOR.POINTS");
		String IP = pConfig.getString("IPADDRESS");
		List<String> USEDIPS = new ArrayList<String>(pConfig.getStringList("USEDIP"));
		Integer BOUNTY = pConfig.contains("BOUNTY") ? pConfig.getInt("BOUNTY") : 0;
		Boolean random = pConfig.getBoolean("RANDOM.COLOR");
		
		CombatPlayer cplayer = new CombatPlayer(PLAYERNAME, targetID, IP, USEDIPS, PLAYERNAME, BOUNTY, random, DONNOR_POINTS);	
		
		Offlineaccesstype.setCTarget(cplayer);
		Offlineaccesstype.call();
		
		if (Order != null) {
			CombatPlayer csender = OtxPlugin.getPlayer(Order);
		}
		
	}

}
