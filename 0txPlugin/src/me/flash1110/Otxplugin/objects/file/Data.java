package me.flash1110.Otxplugin.objects.file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.MyLocation;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Data {

	public static OtxPlugin plugin;

	public Data(OtxPlugin instance) {
		plugin = instance;
		try {
			loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static java.util.List<MyLocation> CombatBlocks = new ArrayList<MyLocation>();
	
	private static List<String> AllowedRegions;
	private static Integer donateGoal;
	private static Double donatedMoney;
	private static String donateMessage;
	private static Integer SecondsRepeat;
	private static Integer ColorCooldown;
	
	public static void loadData() throws IOException {
		
		File DataFile = new File(plugin.getDataFolder(), "Data.txt");
		
		if (!DataFile.exists()) {
			DataFile.getParentFile().mkdirs();
			DataFile.createNewFile();
		}
		
		FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);

		DataConfig.addDefault("AllowedRegions", new ArrayList<String>());
		
		DataConfig.addDefault("CombatBlocks", new ArrayList<String>());
		
		DataConfig.addDefault("DonateGoal", 100.00);
		
		DataConfig.addDefault("DonatedMoney", 0.00);
		
		DataConfig.addDefault("DonateMessage", "&d%money$ &6out of &d%goal% &6this month");
		DataConfig.addDefault("SecondsRepeat", 5);
		DataConfig.addDefault("ColorCooldown", 60);
		
		DataConfig.options().copyDefaults(true);
		DataConfig.save(DataFile);
		
		AllowedRegions = DataConfig.getStringList("AllowedRegions");
		
		for (String loc : DataConfig.getStringList("CombatBlocks")) {
			CombatBlocks.add(new MyLocation(loc));
		}
		
		donateGoal = DataConfig.getInt("DonateGoal");
		donatedMoney = DataConfig.getDouble("DonatedMoney");
		donateMessage = DataConfig.getString("DonateMessage");
		SecondsRepeat = DataConfig.getInt("SecondsRepeat");
		ColorCooldown = DataConfig.getInt("ColorCooldown");
	}
	
	public static Integer getSecondsRepeat() {
		return SecondsRepeat;
	}
	
	public static String getDonateMessage() {
		String donate = donateMessage;
		donate = ChatColor.translateAlternateColorCodes('&', donate);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		donate = donate.replace("%money%", df.format(getMoney()));
		donate = donate.replace("%goal%", getGoal() + "");
		donate = donate.replace("%n%", "\n");
		
		return donate;
	}
	
	public static Integer getColorCooldown() {
		return ColorCooldown;
	}
	
	public static Integer getGoal() {
		File DataFile = new File(plugin.getDataFolder(), "Data.txt");
		
		if (!DataFile.exists())
			try {
				DataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
		
		return DataConfig.getInt("DonateGoal");
	}
	
	public static Double getMoney() {
		File DataFile = new File(plugin.getDataFolder(), "Data.txt");
		
		if (!DataFile.exists())
			try {
				DataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
		
		return DataConfig.getDouble("DonatedMoney");
	}
	
	public static void addMoney(double money) {
		double toAdd = getMoney() + money;
		
		File DataFile = new File(plugin.getDataFolder(), "Data.txt");
		
		if (!DataFile.exists())
			try {
				DataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
		
		DataConfig.set("DonatedMoney", toAdd);
		
		try {
			DataConfig.save(DataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setGoal(int goal) {
		donateGoal = goal;
		
		File DataFile = new File(plugin.getDataFolder(), "Data.txt");
		
		if (!DataFile.exists())
			try {
				DataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
		
		DataConfig.set("DonateGoal", donateGoal);
		
		try {
			DataConfig.save(DataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public static void addCombatBlock(org.bukkit.Location location) {
	
	MyLocation blocklocation = new MyLocation(location);
	CombatBlocks.add(blocklocation);
	
	File DataFile = new File(plugin.getDataFolder(), "Data.txt");
	
	if (!DataFile.exists())
		try {
			DataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
	
	List<String> CurrentBlocks = DataConfig.getStringList("CombatBlocks");
	CurrentBlocks.add(blocklocation.getLocation());
	
	DataConfig.set("CombatBlocks", CurrentBlocks);
	
	try {
		DataConfig.save(DataFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static boolean removeCombatBlock(org.bukkit.Location location) {
	
	MyLocation blocklocation = new MyLocation(location);
	if (!CombatBlocks.contains(blocklocation)) return false;
	CombatBlocks.remove(blocklocation);
	
	File DataFile = new File(plugin.getDataFolder(), "Data.txt");
	
	if (!DataFile.exists())
		try {
			DataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
	
	List<String> CurrentBlocks = DataConfig.getStringList("CombatBlocks");
	CurrentBlocks.remove(blocklocation.getLocation());
	
	DataConfig.set("CombatBlocks", CurrentBlocks);
	
	try {
		DataConfig.save(DataFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
}


public static void addRegion(String name) {
	
	AllowedRegions.add(name.toLowerCase());
	
	File DataFile = new File(plugin.getDataFolder(), "Data.txt");
	
	if (!DataFile.exists())
		try {
			DataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
	
	List<String> regions = DataConfig.getStringList("AllowedRegions");
	regions.add(name.toLowerCase());
	
	DataConfig.set("AllowedRegions", regions);
	
	try {
		DataConfig.save(DataFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static List<String> getRegions() {
	return AllowedRegions;
}

public static Boolean doesRegionExist(String name) {
	
	if (AllowedRegions.contains(name.toLowerCase()))
		return true;
	return false;
	
}

	public static void deleteRegion(String name) {
	
	AllowedRegions.remove(name.toLowerCase());
	
	File DataFile = new File(plugin.getDataFolder(), "Data.txt");
	
	if (!DataFile.exists())
		try {
			DataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	FileConfiguration DataConfig = YamlConfiguration.loadConfiguration(DataFile);
	
	List<String> regions = DataConfig.getStringList("AllowedRegions");
	regions.remove(name.toLowerCase());
	
	DataConfig.set("AllowedRegions", regions);
	
	try {
		DataConfig.save(DataFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
