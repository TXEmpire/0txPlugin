package me.flash1110.Otxplugin.objects.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SavedFile {

	public static OtxPlugin plugin;
	
	public SavedFile(OtxPlugin instance) {
		plugin = instance;
	}
	
	public static void addPlayer(Player player, String IP) {
File playerFileDirect = new File(plugin.getDataFolder(), "Players");
		
		if (!playerFileDirect.exists()) {
			playerFileDirect.mkdir();
		}
		
		UUID ID = player.getUniqueId();
		
		String first2id = ID.toString().substring(0, 2);
		
		File PlayerFile = new File(plugin.getDataFolder(), "Players/" + first2id + "/" +  ID + ".txt");
				
		PlayerFile.getParentFile().mkdirs();
		
		if (!PlayerFile.exists()) {
			
			String firstletter = player.getName().toLowerCase().substring(0, 1);
			String secondletter = player.getName().toLowerCase().substring(1, 2);
			
			File OfflineFile = new File(plugin.getDataFolder(), "OfflineAccess/" + firstletter + "/" + firstletter + secondletter + ".txt");
			OfflineFile.getParentFile().mkdirs();
			
			if (!OfflineFile.exists()) {
				try {
					OfflineFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
	    	ofconfig.set(player.getName().toLowerCase(), ID.toString());
	    	
	    	try {
				ofconfig.save(OfflineFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				PlayerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			FileConfiguration pConfig = YamlConfiguration.loadConfiguration(PlayerFile);
			
			for (Defaults d : Defaults.values()) 
				pConfig.set(d.name().replace("_", "."), d.getDefault());
			
			pConfig.set("PLAYERNAME", player.getName());
			
			List<String> UsedIP = new ArrayList<String>();
			UsedIP.add(IP);
			
			pConfig.set("IPADDRESS", IP);
			pConfig.set("USEDIP", UsedIP);
			
			try {
				pConfig.save(PlayerFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static Boolean doesPlayerExistOffline(String name) {
		
		if (name.length() < 3)
			return false;
		
		String firstletter = name.toLowerCase().substring(0, 1);
		String secondletter = name.toLowerCase().substring(1, 2);
		
		File OfflineFile = new File(plugin.getDataFolder(), "OfflineAccess/" + firstletter + "/" +  firstletter + secondletter + ".txt");
		
		if (!OfflineFile.exists())
			return false;
		
		FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
		
		if (ofconfig.contains(name.toLowerCase()))
			return true;
		
		return false;
		
	}
	
public static Boolean doesPlayerExistUUID(UUID ID) {
		
		String first2id = ID.toString().substring(0, 2);
		
		File PlayerFile = new File(plugin.getDataFolder(), "Players/" + first2id + "/" +  ID + ".txt");
				
		PlayerFile.getParentFile().mkdirs();
		
		if (PlayerFile.exists())
			return true;
		return false;
		
	}
	
	public static UUID getUUID(String name) {
		
		String firstletter = name.toLowerCase().substring(0, 1);
		String secondletter = name.toLowerCase().substring(1, 2);
		
		File OfflineFile = new File(plugin.getDataFolder(), "OfflineAccess/" + firstletter + "/" +  firstletter + secondletter + ".txt");
		
		if (!OfflineFile.exists())
			return null;
		
		FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
		
		if (ofconfig.contains(name.toLowerCase()))
			return UUID.fromString(ofconfig.getString(name.toLowerCase()));
		
		return null;
		
	}
	
	public static CombatPlayer loadPlayer(UUID ID, String name, String IP) {
		
		String first2id = ID.toString().substring(0, 2);
		
		File PlayerFile = new File(plugin.getDataFolder(), "Players/" + first2id + "/" +  ID + ".txt");
				
		PlayerFile.getParentFile().mkdirs();
		
		if (!PlayerFile.exists()) {
			return null;
		}
		
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(PlayerFile);
		
		String PlayerName = pConfig.getString("PLAYERNAME");
		
		if (name != null && !name.equals("")) {
			if (!PlayerName.equals(name)) {
				pConfig.set("PLAYERNAME", name);
				pConfig.set("DISPLAYNAME", null);
				try {
					pConfig.save(PlayerFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String firstletter = name.toLowerCase().substring(0, 1);
				String secondletter = name.toLowerCase().substring(1, 2);
				
				String firstletterold = PlayerName.toLowerCase().substring(0, 1);
				String secondletterold = PlayerName.toLowerCase().substring(1, 2);
				
				File OfflineFile = new File(plugin.getDataFolder(), "OfflineAccess/" + firstletter + "/" +  firstletter + secondletter + ".txt");
				OfflineFile.getParentFile().mkdirs();
				
				if (OfflineFile.exists()) {
					
		    		FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
		    		ofconfig.set(name.toLowerCase(), ID.toString());
		    		
		    		try {
						ofconfig.save(OfflineFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					
					try {
						OfflineFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
		    		FileConfiguration ofconfig = YamlConfiguration.loadConfiguration(OfflineFile);
		    		ofconfig.set(name.toLowerCase(), ID.toString());
		    		
		    		try {
						ofconfig.save(OfflineFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				File OfflineFileOld = new File(plugin.getDataFolder(), "OfflineAccess/" + firstletterold + "/" +  firstletterold + secondletterold + ".txt");
				FileConfiguration ofconfigold = YamlConfiguration.loadConfiguration(OfflineFileOld);
				
				ofconfigold.set(PlayerName.toLowerCase(), null);
				
				try {
					ofconfigold.save(OfflineFileOld);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		boolean save = false;
		
		for (Defaults d : Defaults.values()) {
			String dname = d.name().replace("_", ".");
			if (!pConfig.contains(dname)) {
				pConfig.set(dname, d.getDefault());
				save = true;
			}
		}
		
		if (save) {
			try {
				pConfig.save(PlayerFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String PLAYERNAME = pConfig.getString("PLAYERNAME");
		Integer DONNOR_POINTS = pConfig.getInt("DONNOR.POINTS");
		String DISPLAYNAME = pConfig.contains("DISPLAYNAME") ? pConfig.getString("DISPLAYNAME").replace("&", "�") : null;
		Boolean RANDOM = pConfig.getBoolean("RANDOM.COLOR");
		
		Integer BOUNTY = pConfig.getInt("BOUNTY");
		
		List<String> USEDIPS = new ArrayList<String>(pConfig.getStringList("USEDIP"));
		
		if (IP != null)
			if (!USEDIPS.contains(IP))
				USEDIPS.add(IP);
		
		CombatPlayer combatPlayer = new CombatPlayer(DISPLAYNAME, ID, IP, USEDIPS, PLAYERNAME, BOUNTY, RANDOM, DONNOR_POINTS);
		return combatPlayer;
	}
	
	public static void savePlayer(CombatPlayer player, Boolean online) {
		UUID ID = player.getID();
		
		String first2id = ID.toString().substring(0, 2);
		
		File PlayerFile = new File(plugin.getDataFolder(), "Players/" + first2id + "/" +  ID + ".txt");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(PlayerFile);
		
		if (player.getDisplayName() == null) 
			pConfig.set("DISPLAYNAME", null);
		else
			pConfig.set("DISPLAYNAME", player.getDisplayName().equals("") ? null : player.getDisplayName().replace("�", "&"));
		
		pConfig.set("DONNOR.POINTS", player.getDonnorPointBalance());
		pConfig.set("RANDOM.COLOR", player.isRandomColor());
		pConfig.set("BOUNTY", player.getBounty());
		if (player.getIP() != null && online) {
			pConfig.set("IPADDRESS", player.getIP());
			pConfig.set("USEDIP", player.getUsedIPs());
		}
		
		
		
		try {
			pConfig.save(PlayerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
