package me.flash1110.Otxplugin.listeners;

import java.util.ArrayList;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseMobOreDrops;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseXPDrops;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.file.SavedFile;
import me.flash1110.Otxplugin.objects.macro.MacroChecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {
	
	public static OtxPlugin plugin;
	
	public JoinListener(OtxPlugin empire) {
		plugin = empire;
		plugin.getServer().getPluginManager().registerEvents(this, empire);
	}
	
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event) {
		if (event.getEntity().getWorld().getEnvironment() == Environment.NETHER) {
			if (event.getEntity().getType() == EntityType.SKELETON) {
				if (event.getSpawnReason() == SpawnReason.SPAWNER) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		
		if (OtxPlugin.getPlayer(player) != null) {
			event.setResult(Result.KICK_OTHER);
			event.setKickMessage(ChatColor.RED + "You logged in twice");
			System.out.println("Player " + player.getName() + " was already logged in! Try to again.");
			player.kickPlayer(null);
			OtxPlugin.removePlayer(player);
			return;
		}
		
		String HostName = event.getHostname();
		String[] IPs = HostName.split("\0");
		
		String IP;
		
		if (IPs.length < 3) {
			IP = event.getAddress().getHostAddress().replace("/", "").replace("\\", "");
		} else {
			IP = IPs[2];
		}
		
		System.out.println("Player " + player.getName() + " is logging in through IP " + IP);
		
		CombatPlayer cplayer = null;
		
		if (SavedFile.doesPlayerExistUUID(player.getUniqueId())) {
			cplayer = SavedFile.loadPlayer(player.getUniqueId(), player.getName(), IP);
		} else {
			SavedFile.addPlayer(player, IP);
			cplayer = SavedFile.loadPlayer(player.getUniqueId(), player.getName(), IP);
		}
		
	/*	if (cplayer.getBanned()) {
			
			Long bantime = cplayer.getBanTime();
			
			if (bantime == -1) {
				event.disallow(Result.KICK_BANNED, ChatColor.DARK_RED + "You are Banned: " + cplayer.getLastBanMessage() + "\n " + ChatColor.RED + "Expires: " + ChatColor.YELLOW + "Forever");
			} else {
				
				String time = Ban.caculateBanLength(cplayer.getBanTime());
				event.disallow(Result.KICK_BANNED, ChatColor.DARK_RED + "You are Banned: " + cplayer.getLastBanMessage() + "\n " + ChatColor.RED + "Expires: " + ChatColor.YELLOW + time);
				
			}
			
			return;
			
		} else if (player.isBanned()) {
			player.setBanned(false);
			event.allow();
		} */
		
		OtxPlugin.addPlayer(cplayer);
		
		if (event.getResult() != Result.ALLOWED)
			return;
		
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	/*	final Player player = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				sendTitle(player, 10, 60, 10, "&6&lWelcome to Enderchest", "&bdonate.enderchest.org");
			}
		}, 30); */
		
	/*	Score name = EmpirePlugin.o.getScore(ChatColor.GOLD + "Players Online:");
		name.setScore(Bukkit.getOnlinePlayers().size());
		
		event.getPlayer().setScoreboard(EmpirePlugin.board); */
		Player player = event.getPlayer();
		MacroChecker.addPlayer(player.getUniqueId());
		
		

		
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Empire Plugin" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Sucessfully loaded your information");
		
		
		
		
		CombatPlayer cp = OtxPlugin.getPlayer(player);
		
		if (cp != null)
			cp.refreshDisplayName();
		
//		NametagAPI.setNametag(player, cplayer.getRank().getColor() + player.getName(), false, true);
	//	NametagAPI.setNametag(player, cplayer.getRank().getColor() + WordUtils.capitalizeFully(cplayer.getRank().name()) + " " + player.getName(), true, false);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
			
			Player player = event.getPlayer();
			
			MacroChecker.removePlayer(player.getUniqueId());
			
			player.setDisplayName(null);
			
			while (InventoryListener.IgnoreNextSpecialClose.contains(player.getUniqueId()))
				InventoryListener.IgnoreNextSpecialClose.remove(player.getUniqueId());
			
			
			InventoryListener.closeOnlineViewer(player.getName());
					
			if (OtxPlugin.containsCPlayer(player)) {
				
				CombatPlayer cplayer = OtxPlugin.getPlayer(player);
				
				
				
				SavedFile.savePlayer(cplayer, true);
				OtxPlugin.removePlayer(player);
				
			}
			
		}
	
	@EventHandler
		public void onBlockPlace(BlockPlaceEvent event) {
			if (event.getBlock().getType() == Material.BEDROCK) {
				if (!event.getPlayer().isOp()) {
					event.setCancelled(true);
				}
			}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (Event_IncreaseXPDrops.getActive()) {
			int xp = event.getExpToDrop();
			event.setExpToDrop(xp * Event_IncreaseXPDrops.getMultiplier());
		}
		
		if (Event_IncreaseMobOreDrops.getActive()) {
			
			Block b = event.getBlock();
			
			if (!b.getType().name().toUpperCase().endsWith("_ORE"))
				return;
			
			if (event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) 
				return;
			
			if (b.getType() == Material.IRON_ORE || b.getType() == Material.GOLD_ORE) 
				return;
			
			if (b.getType() == Material.DIAMOND_ORE)
				event.getBlock().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.DIAMOND, Event_IncreaseMobOreDrops.getMultiplier() - 1));
			else {
				
				ArrayList<ItemStack> drops = new ArrayList<ItemStack>(b.getDrops());
				
				for (int i=1; i< Event_IncreaseMobOreDrops.getMultiplier(); i++)
					for (ItemStack is : drops)
						event.getBlock().getWorld().dropItemNaturally(b.getLocation(), is);
				
			}
			
		} 
	}
}

