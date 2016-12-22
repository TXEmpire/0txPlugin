package me.flash1110.Otxplugin.objects.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class IPCheck {

public static OtxPlugin plugin;
	
	public IPCheck(OtxPlugin instance) {
		plugin = instance;
	}
	
	public static Boolean checkIP(UUID ID, String IP, String playername) {
		
		String[] IPs = IP.split("\\.");
		String ip = "";
		
		for (int i=0; i<IPs.length-1; i++)
			ip+= IPs[i] + "/";
		
		File IPFile = new File(plugin.getDataFolder(), "IPs/" + ip +  IP + ".txt");
		
		IPFile.getParentFile().mkdirs();
		
		if (!IPFile.exists()) {
			try {
				IPFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	FileConfiguration ipconfig = YamlConfiguration.loadConfiguration(IPFile);
	    	ipconfig.set(ID.toString(), playername);
	    	try {
	    		ipconfig.save(IPFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	return true;
		}
				
		FileConfiguration ipconfig = YamlConfiguration.loadConfiguration(IPFile);
		
		Integer size = ipconfig.getKeys(false).size();
		
		if (ipconfig.contains(ID.toString())) {
			
			if (!ipconfig.getString(ID.toString()).equals(playername)) {
				
				ipconfig.set(ID.toString(), playername);
				
		    	try {
		    		ipconfig.save(IPFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
			}
			
			return true;
			
		} else {
			
			if (size >= 5)
				return false;
						
			ipconfig.set(ID.toString(), playername);
			
	    	try {
	    		ipconfig.save(IPFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	return true;
			
		}
		
	}
}
