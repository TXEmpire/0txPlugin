package me.flash1110.Otxplugin.objects.macro;

import java.util.HashMap;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.WorldGuardRegionCheck;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MacroChecker implements Listener {

	public static OtxPlugin plugin;
	
	private static HashMap<UUID, ClickLog> PlayerClickLogs = new HashMap<UUID, ClickLog>();
	
	public MacroChecker(OtxPlugin instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
			
		if (!(event.getAction() == Action.LEFT_CLICK_AIR)) return;
		
		UUID id = event.getPlayer().getUniqueId();
			
			Click click = new Click(System.currentTimeMillis());
			ClickLog log = getPlayer(id);
			log.addClick(click);
			
			checkPlayer(id);
	}
	
	public static void addPlayer(UUID id) {
		PlayerClickLogs.put(id, new ClickLog());
	}
	
	public static void removePlayer(UUID id) {
		PlayerClickLogs.remove(id);
	}
	
	public static ClickLog getPlayer(UUID id) {
		return PlayerClickLogs.get(id);
	}
	
	public static void checkPlayer(UUID id) {
		
		ClickLog log = getPlayer(id);
				
		log.cleanseClicks();
		
		if (log.getClickCount() >= 15) {
			
			if (!log.checkAgain())
				return;
			
			logClickWarning(id, log.getClickCount());
			log.updateLastChecked();
			
		}
		
	}
	
	private static String MacroPrefix = ChatColor.RED + "[" + ChatColor.DARK_AQUA + "Macro" + ChatColor.RED + "]" + ChatColor.WHITE + " ";
	
	public static void logClickWarning(UUID id, int count) {
		
		Player target = Bukkit.getPlayer(id);
		
		if (!WorldGuardRegionCheck.getWithinAllowedRegion(target.getLocation())) {
			return;
		}
		
		for (Player P : Bukkit.getOnlinePlayers()) {
			CombatPlayer cp = OtxPlugin.getPlayer(P);
			
			if (P.hasPermission("empireplugin.macro") && cp.isMacro())
				P.sendMessage(MacroPrefix + ChatColor.BLUE + target.getDisplayName() + ChatColor.GOLD + " clicked " + ChatColor.LIGHT_PURPLE + count + ChatColor.GOLD + "");
		}	
	}
}
