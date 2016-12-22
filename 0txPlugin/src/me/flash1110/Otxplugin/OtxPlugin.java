package me.flash1110.Otxplugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;

import me.flash1110.Otxplugin.commands.Bonus;
import me.flash1110.Otxplugin.commands.ChatColorCommand;
import me.flash1110.Otxplugin.commands.DonateGoal;
import me.flash1110.Otxplugin.commands.Donation;
import me.flash1110.Otxplugin.commands.FlyRegion;
import me.flash1110.Otxplugin.commands.List;
import me.flash1110.Otxplugin.commands.Macro;
import me.flash1110.Otxplugin.commands.MenuCommand;
import me.flash1110.Otxplugin.commands.ModCast;
import me.flash1110.Otxplugin.commands.NameColorCommand;
import me.flash1110.Otxplugin.commands.Nick;
import me.flash1110.Otxplugin.commands.RandomColor;
import me.flash1110.Otxplugin.commands.Reload;
import me.flash1110.Otxplugin.commands.Say;
import me.flash1110.Otxplugin.commands.Staff;
import me.flash1110.Otxplugin.donor.DInventory;
import me.flash1110.Otxplugin.donor.Default;
import me.flash1110.Otxplugin.listeners.ChatColorListener;
import me.flash1110.Otxplugin.listeners.DamageListener;
import me.flash1110.Otxplugin.listeners.InventoryListener;
import me.flash1110.Otxplugin.listeners.JoinListener;
import me.flash1110.Otxplugin.listeners.PlayerDeath;
import me.flash1110.Otxplugin.listeners.XPCheck;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.file.Data;
import me.flash1110.Otxplugin.objects.file.IPCheck;
import me.flash1110.Otxplugin.objects.file.SavedFile;
import me.flash1110.Otxplugin.objects.macro.MacroChecker;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

public class OtxPlugin extends JavaPlugin {

	public static Plugin p;
	
	private static Boolean isVanishNoPacketEnabled, isWorldGuardEnabled, isVaultEnabled;
	public static HashMap<String, DInventory> StoredInventories = new HashMap<String, DInventory>();
	
	public static Boolean getVanishNoPacketEnabled() {
		return isVanishNoPacketEnabled;
	}
	
	public static Boolean getWorldGuardEnabled() {
		return isWorldGuardEnabled;
	}
	
	public void onEnable() {
		p = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		if (getConfig().getConfigurationSection("colors") == null) {
			getConfig().createSection("colors");
			saveConfig();
		}
		
		if (getConfig().getConfigurationSection("commands") == null) {
			getConfig().createSection("commands");
			saveConfig();
		}
		
		for (String id : getConfig().getConfigurationSection("colors").getKeys(false)) {
			ChatColorListener.list.put(UUID.fromString(id), ChatColor.valueOf(getConfig().getString("colors." + id)));
		}
		
		new XPCheck(this);
		new ChatColorListener(this);
		
		getCommand("chatcolor").setExecutor(new ChatColorCommand());
		getCommand("namecolor").setExecutor(new NameColorCommand());
		getCommand("menu").setExecutor(new MenuCommand());
		
		new SavedFile(this);
		new Data(this);
		
		getCommand("macro").setExecutor(new Macro());
		getCommand("modcast").setExecutor(new ModCast());
		getCommand("list").setExecutor(new List());
		getCommand("region").setExecutor(new FlyRegion());
		getCommand("say").setExecutor(new Say());
		getCommand("bonus").setExecutor(new Bonus());
		getCommand("donategoal").setExecutor(new DonateGoal());
		getCommand("donation").setExecutor(new Donation());
		getCommand("creload").setExecutor(new Reload());
		getCommand("staff").setExecutor(new Staff());
		getCommand("randomcolor").setExecutor(new RandomColor());
		getCommand("nick").setExecutor(new Nick());
	//	getCommand("killstreak").setExecutor(new KillStreak());
/*		getCommand("ban").setExecutor(new Ban());
		getCommand("mute").setExecutor(new Mute());
		getCommand("tempban").setExecutor(new TempBan());
		getCommand("unban").setExecutor(new Unban());
		getCommand("unmute").setExecutor(new UnMute());
		getCommand("report").setExecutor(new Report()); */
		new JoinListener(this);
		new DamageListener(this);
		new IPCheck(this);
		new InventoryListener(this);
		new MacroChecker(this);
		new PlayerDeath(this);
		
		
		Default.loadDefaults();
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			String HostName = player.getAddress().getHostName();
			String[] IPs = HostName.split("\0");
			
			String IP;
			
			if (IPs.length < 3) {
				IP = player.getAddress().getAddress().getHostAddress().replace("/", "").replace("\\", "");
			} else {
				IP = IPs[2];
			}
			
			CombatPlayer cplayer = null;
			
			if (SavedFile.doesPlayerExistUUID(player.getUniqueId())) {
				cplayer = SavedFile.loadPlayer(player.getUniqueId(), player.getName(), IP);
			} else {
				SavedFile.addPlayer(player, IP);
				cplayer = SavedFile.loadPlayer(player.getUniqueId(), player.getName(), IP);
			}
			
			OtxPlugin.addPlayer(cplayer);
			MacroChecker.addPlayer(player.getUniqueId());
		}
		
		Plugin vanishnopacket = this.getServer().getPluginManager().getPlugin("VanishNoPacket");
		Plugin worldguard = this.getServer().getPluginManager().getPlugin("WorldGuard");
		Plugin vault = this.getServer().getPluginManager().getPlugin("Vault");
		
		if (vault != null) {
			setupEconomy(this);
		}
		
		
		if (vanishnopacket != null )
			isVanishNoPacketEnabled = true; else isVanishNoPacketEnabled = false;
		
		Bukkit.getLogger().log(Level.INFO, "Vanish No Packet support " + (isVanishNoPacketEnabled ? "Enabled with version " + vanishnopacket.getDescription().getVersion() : "Disabled"));
		
		if (worldguard != null )
			isWorldGuardEnabled = true; else isWorldGuardEnabled = false;
		
		Bukkit.getLogger().log(Level.INFO, "World Guard support " + (isWorldGuardEnabled ? "Enabled with version " + worldguard.getDescription().getVersion() : "Disabled"));
		
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(Data.getDonateMessage());
			}
		}, 20, 20 * Data.getSecondsRepeat());
	}
	
public static Economy economy = null;
	
	public static boolean setupEconomy(OtxPlugin plugin) {
		RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
	}
	
	public static Economy getEcon() {
		return economy;
	}
	
	public void onDisable() {
		p = null;
		for (Entry<UUID, ChatColor> entry : ChatColorListener.list.entrySet()) {
			getConfig().set("colors." + entry.getKey().toString(), entry.getValue().name());
			saveConfig();
		}
		
		saveAll();
	}
	
	public void saveAll() {
		for (CombatPlayer player : players.values())
			SavedFile.savePlayer(player, true);
		
		players.clear();
	}
	
	
	public static Plugin getPlugin() {
		return p;
	}
	
	static HashMap<UUID, CombatPlayer> players = new HashMap<UUID, CombatPlayer>();
	
	public static void addPlayer(CombatPlayer cplayer) {
		if (players.containsKey(cplayer.getID())) return;
		players.put(cplayer.getID(), cplayer);
	}
	
	public static CombatPlayer getPlayer(Player player) {
		if (containsCPlayer(player))
			return players.get(player.getUniqueId());
		player.kickPlayer(ChatColor.DARK_RED + "Fatal Error occured. Please relog");
		return null;
	}
	
	public static CombatPlayer getPlayer(UUID id) {
		if (containsCPlayer(id))
			return players.get(id);
		return null;
	}
	
	public static Collection<CombatPlayer> getCplayers() {
		return players.values();
	}
	
	public static Boolean containsCPlayer(Player player) {
		if (players.containsKey(player.getUniqueId()))
			return true;
		return false;
	}
	
	public static Boolean containsCPlayer(UUID id) {
		if (players.containsKey(id))
			return true;
		return false;
	}
	
	public static void removePlayer(Player player) {
		if (containsCPlayer(player))
			players.remove(player.getUniqueId());
	}
	
	
public static Boolean isInventory(String title) {
		
		if (StoredInventories.containsKey(title))
			return true;
		return false;
		
	}
	
	public static DInventory getInventory(String title) {
		
		if (StoredInventories.containsKey(title))
			return StoredInventories.get(title);
		return null;
		
	}
	
	public static void BroadcastMessage(String message) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a[&eBoost&a]") + " " + ChatColor.RESET + message);
	}
	
public static String removeColourFormat(String s) {
		
		s = s.replace("" + ChatColor.BLACK, "")
				.replace("" + ChatColor.DARK_BLUE, "")
				.replace("" + ChatColor.DARK_GREEN, "")
				.replace("" + ChatColor.DARK_AQUA, "")
				.replace("" + ChatColor.DARK_RED, "")
				.replace("" + ChatColor.DARK_PURPLE, "")
				.replace("" + ChatColor.GOLD, "")
				.replace("" + ChatColor.GRAY, "")
				.replace("" + ChatColor.DARK_GRAY, "")
				.replace("" + ChatColor.BLUE, "")
				.replace("" + ChatColor.GREEN, "")
				.replace("" + ChatColor.AQUA, "")
				.replace("" + ChatColor.RED, "")
				.replace("" + ChatColor.LIGHT_PURPLE, "")
				.replace("" + ChatColor.YELLOW, "")
				.replace("" + ChatColor.WHITE, "")
				.replace("" + ChatColor.MAGIC, "")
				.replace("" + ChatColor.BOLD, "")
				.replace("" + ChatColor.STRIKETHROUGH, "")
				.replace("" + ChatColor.UNDERLINE, "")
				.replace("" + ChatColor.ITALIC, "")
				.replace("" + ChatColor.RESET, "");
		
		return s;
		
	}
	
	public static String convertColourFormatBack(String s) {
		
		s = s.replace("" + ChatColor.BLACK, "&0")
				.replace("" + ChatColor.DARK_BLUE, "&1")
				.replace("" + ChatColor.DARK_GREEN, "&2")
				.replace("" + ChatColor.DARK_AQUA, "&3")
				.replace("" + ChatColor.DARK_RED, "&4")
				.replace("" + ChatColor.DARK_PURPLE, "&5")
				.replace("" + ChatColor.GOLD, "&6")
				.replace("" + ChatColor.GRAY, "&7")
				.replace("" + ChatColor.DARK_GRAY, "&8")
				.replace("" + ChatColor.BLUE, "&9")
				.replace("" + ChatColor.GREEN, "&a")
				.replace("" + ChatColor.AQUA, "&b")
				.replace("" + ChatColor.RED, "&c")
				.replace("" + ChatColor.LIGHT_PURPLE, "&d")
				.replace("" + ChatColor.YELLOW, "&e")
				.replace("" + ChatColor.WHITE, "&f")
				.replace("" + ChatColor.MAGIC, "&k")
				.replace("" + ChatColor.BOLD, "&l")
				.replace("" + ChatColor.STRIKETHROUGH, "&m")
				.replace("" + ChatColor.UNDERLINE, "&n")
				.replace("" + ChatColor.ITALIC, "&o")
				.replace("" + ChatColor.RESET, "&r");
		
		return s;
		
	}
	
	public static String convertColour(String s) {
		
		s = s.replace("&0", "" + ChatColor.BLACK)
				.replace("&1", "" + ChatColor.DARK_BLUE)
				.replace("&2", "" + ChatColor.DARK_GREEN)
				.replace("&3", "" + ChatColor.DARK_AQUA)
				.replace("&4", "" + ChatColor.DARK_RED)
				.replace("&5", "" + ChatColor.DARK_PURPLE)
				.replace("&6", "" + ChatColor.GOLD)
				.replace("&7", "" + ChatColor.GRAY)
				.replace("&8", "" + ChatColor.DARK_GRAY)
				.replace("&9", "" + ChatColor.BLUE)
				.replace("&a", "" + ChatColor.GREEN)
				.replace("&b", "" + ChatColor.AQUA)
				.replace("&c", "" + ChatColor.RED)
				.replace("&d", "" + ChatColor.LIGHT_PURPLE)
				.replace("&e", "" + ChatColor.YELLOW)
				.replace("&f", "" + ChatColor.WHITE);
		
		return s;
		
	}
		
	public static String convertFormat(String s) {
		
		s = s.replace("&k", "" + ChatColor.MAGIC)
				.replace("&l", "" + ChatColor.BOLD)
				.replace("&m", "" + ChatColor.STRIKETHROUGH)
				.replace("&n", "" + ChatColor.UNDERLINE)
				.replace("&o", "" + ChatColor.ITALIC)
				.replace("&r", "" + ChatColor.RESET);
		
		return s;
		
	}
	
public static Boolean getFactionProtection(Location location, Player player) {
		
		Faction faction = Board.getInstance().getFactionAt(new FLocation(location));
		
		if (faction == null)
			return false;
		
		String id = faction.getId();
		
		if (id.equals("warzone") || id.equals("safezone"))
			return true;
		
		if (id.equals("none"))
			return false;
		
		String playerfaction = FPlayers.getInstance().getById(player.getUniqueId().toString()).getFactionId();
		
		if (id.equals(playerfaction))
			return false;
		
		return true;
		
	}
	
	public static Boolean getInSafeZone(Location location) {
		
		Faction faction = Board.getInstance().getFactionAt(new FLocation(location));
		
		if (faction == null)
			return false;
		
		
		String id = faction.getId();
		
		if (id.equals("safezone"))
			return true;
		
		return false;
		
	}
	
	public static Boolean getFactionCanFly(Location location, Player player) {
		
		Faction faction = Board.getInstance().getFactionAt(new FLocation(location));
		
		if (faction == null)
			return false;
		String id = faction.getId();
		
		if (id.equals("safezone"))
			return true;
		
		if (id.equals("warzone"))
			return false;
		
		if (id.equals("wilderness") || id.equals("none") || faction.isWilderness())
			return false;
		
		String playerfaction = FPlayers.getInstance().getById(player.getUniqueId().toString()).getFactionId();
		
		if (id.equals(playerfaction))
			return true;
		
		return false;
		
	}
	
	public static Boolean getFactionCanFlyWarzone(Location location, Player player) {
		
		Faction faction = Board.getInstance().getFactionAt(new FLocation(location));
		
		if (faction == null)
			return false;
		String id = faction.getId();
		
		if (id.equals("safezone"))
			return true;
		
		if (id.equals("warzone"))
			return true;
		
		if (id.equals("none"))
			return false;
		
		String playerfaction = FPlayers.getInstance().getById(player.getUniqueId().toString()).getFactionId();
		
		if (id.equals(playerfaction))
			return true;
		
		return false;
		
	}
	
	public static String convertColourFormatPermissionSign(String s, Player player) {
		
		if (player.hasPermission("0txplugin.sign.colour.black"))
			s = s.replace("&0", "" + ChatColor.BLACK);
		
		if (player.hasPermission("0txplugin.sign.colour.darkblue"))
			s = s.replace("&1", "" + ChatColor.DARK_BLUE);
		
		if (player.hasPermission("0txplugin.sign.colour.darkgreen"))
			s = s.replace("&2", "" + ChatColor.DARK_GREEN);
		
		if (player.hasPermission("0txplugin.sign.colour.darkaqua"))
			s = s.replace("&3", "" + ChatColor.DARK_AQUA);
		
		if (player.hasPermission("0txplugin.sign.colour.darkred"))
			s = s.replace("&4", "" + ChatColor.DARK_RED);
		
		if (player.hasPermission("0txplugin.sign.colour.darkpurple"))
			s = s.replace("&5", "" + ChatColor.DARK_PURPLE);
		
		if (player.hasPermission("0txplugin.sign.colour.gold"))
			s = s.replace("&6", "" + ChatColor.GOLD);
		
		if (player.hasPermission("0txplugin.sign.colour.gray"))
			s = s.replace("&7", "" + ChatColor.GRAY);
		
		if (player.hasPermission("0txplugin.sign.colour.darkgray"))
			s = s.replace("&8", "" + ChatColor.DARK_GRAY);
		
		if (player.hasPermission("0txplugin.sign.colour.blue"))
			s = s.replace("&9", "" + ChatColor.BLUE);
		
		if (player.hasPermission("0txplugin.sign.colour.green"))
			s = s.replace("&a", "" + ChatColor.GREEN);
		
		if (player.hasPermission("0txplugin.sign.colour.aqua"))
			s = s.replace("&b", "" + ChatColor.AQUA);
		
		if (player.hasPermission("0txplugin.sign.colour.red"))
			s = s.replace("&c", "" + ChatColor.RED);
		
		if (player.hasPermission("0txplugin.sign.colour.lightpurple"))
			s = s.replace("&d", "" + ChatColor.LIGHT_PURPLE);
		
		if (player.hasPermission("0txplugin.sign.colour.yellow"))
			s = s.replace("&e", "" + ChatColor.YELLOW);
		
		if (player.hasPermission("0txplugin.sign.colour.white"))
			s = s.replace("&f", "" + ChatColor.WHITE);
		
		if (player.hasPermission("0txplugin.sign.format.magic"))
			s = s.replace("&k", "" + ChatColor.MAGIC);
		
		if (player.hasPermission("0txplugin.sign.format.bold"))
			s = s.replace("&l", "" + ChatColor.BOLD);
		
		if (player.hasPermission("0txplugin.sign.format.strikethrough"))
			s = s.replace("&m", "" + ChatColor.STRIKETHROUGH);
		
		if (player.hasPermission("0txplugin.sign.format.underline"))
			s = s.replace("&n", "" + ChatColor.UNDERLINE);
		
		if (player.hasPermission("0txplugin.sign.format.italic"))
			s = s.replace("&o", "" + ChatColor.ITALIC);
		
		if (player.hasPermission("0txplugin.sign.format.reset"))
			s = s.replace("&r", "" + ChatColor.RESET);
		
		return s;
		
	}
	
}
