package me.flash1110.Otxplugin.listeners;

import java.util.ArrayList;
import java.util.Set;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseMobOreDrops;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseXPDrops;
import me.flash1110.Otxplugin.donor.events.Event_NoFallDamage;
import me.flash1110.Otxplugin.donor.events.Event_NoWaterDamage;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.WorldGuardRegionCheck;
import me.flash1110.Otxplugin.objects.file.Data;
import me.flash1110.Otxplugin.objects.file.SavedFile;
import me.flash1110.Otxplugin.objects.macro.MacroChecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class DamageListener implements Listener {

	public OtxPlugin plugin;
	
	public DamageListener(OtxPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onEntitySpawn(CreatureSpawnEvent event) {
		
		if (event.getLocation().getWorld().getEnvironment() == Environment.THE_END) {
			if (event.getSpawnReason() == SpawnReason.NATURAL) {
				event.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity DamagerE = event.getDamager();
		
		if (event.isCancelled())
			return;
		
		if (event.getDamage() <= 0.0)
			return;
		
		if (DamagerE instanceof Arrow) {
			
			Arrow arrow = (Arrow) DamagerE;
			
			if (arrow.getShooter() instanceof Player) {
				
				Entity DamagedE = event.getEntity();
				
				if (DamagedE instanceof Player) {
					
					Player Damager = (Player) arrow.getShooter();
					
					if (Damager.getGameMode() == GameMode.CREATIVE)
						return;
										
					Player Damaged = (Player) DamagedE;
					
					if (Damaged.getUniqueId().equals(Damager.getUniqueId())) {
						if (WorldGuardRegionCheck.getWithinAllowedRegion(Damaged.getLocation())) {
							Damaged.sendMessage(ChatColor.RED + "Can't hit yourself with a bow in warzone");
							event.setCancelled(true);
							return;
						} else {
							return;
						}
					}
					
					if (Damaged.getGameMode() == GameMode.CREATIVE)
						return;
				}
			}
			
		}
		
		if (DamagerE instanceof Player) {
			
			Player Damager = (Player) DamagerE;
			Entity DamagedE = event.getEntity();
			
			if (DamagedE instanceof Player) {
				
				Player Damaged = (Player) DamagedE;
				
				
				if (Damager.isFlying())
					Damager.setFlying(false);
				
				Damager.setAllowFlight(false);
				
				Damager.sendMessage(ChatColor.RED + "Flymode disabled in combat");
				
				
				
				if (Damaged.isFlying())
					Damaged.setFlying(false);
			
					Damaged.setAllowFlight(false);
			
					Damaged.sendMessage(ChatColor.RED + "Flymode disabled in combat");
				
			}
			
			if (event.getCause().equals(DamageCause.FALL))
				if (Event_NoFallDamage.getActive())
					event.setCancelled(true);
			
			if (event.getCause().equals(DamageCause.DROWNING))
				if (Event_NoWaterDamage.getActive())
					event.setCancelled(true);
			
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		MacroChecker.removePlayer(player.getUniqueId());
		
		while (InventoryListener.IgnoreNextSpecialClose.contains(player.getUniqueId()))
			InventoryListener.IgnoreNextSpecialClose.remove(player.getUniqueId());
		
		InventoryListener.closeOnlineViewer(player.getName());
		
		CombatPlayer cp = OtxPlugin.getPlayer(player);
		
		if (cp == null) {
			return;
		}
		
	/*	if (NametagAPI.isNicked(player)) {
			NametagAPI.removeNametag(player);
		} */
		
		if (cp.getCombatTime() > 0) {
			player.setHealth(0.0);
			Bukkit.broadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.RED + " logged out in combat");
			cp.setKill(true);
		}
		
		if (cp.getWatcherMode()) {
			
			cp.setWatcherMode(false);
		//	WatcherCommand.setNotInWatcherMode(player);
			
		}
		
		SavedFile.savePlayer(cp, true);
		
		OtxPlugin.removePlayer(player);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();	
		
		if (event.getTo() == null)
			return;
		
		CombatPlayer cp = OtxPlugin.getPlayer(player);
		if (cp == null) return;
		
	/*	
		if (cp.getCombatTime() > 0) {
			
			ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionContainer().get(event.getTo().getWorld()).getApplicableRegions(event.getTo());
			
			Set<ProtectedRegion> regions = set.getRegions();
			
			for (ProtectedRegion region : regions) {
				if (region.getId().equals("newspawn") || region.getId().equals("spawn") || region.getId().equals("nogo")) {
					player.sendMessage(ChatColor.RED + "Cannot walk into spawn in combat");
					event.setCancelled(true);
				}
			}
		} */
		
		if (player.getVehicle() != null) {
			if (!player.hasPermission("empireplugin.vehiclebypass")) {
				if (WorldGuardRegionCheck.getWithinAllowedRegion(event.getTo())) {
					player.leaveVehicle();
					player.sendMessage(ChatColor.RED + "Cannot use vehicles in warzone");
				}
				if (player.isFlying()) {
					player.leaveVehicle();
					player.sendMessage(ChatColor.RED + "Cannot use vehicles in air");
				}
			}
		}
		
		
		if (event.getPlayer().getLocation().getWorld().getName().endsWith("_nether"))
        {
			if (event.getTo().getBlockY() >= 126) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "Teleporting to above the nether is disabled");
				return;
			}
        }
		
		if (cp.getWatcherMode()) {
			
			if (!WorldGuardRegionCheck.getWithinAllowedRegion(event.getTo())) {
				cp.setWatcherMode(false);
			//	WatcherCommand.setNotInWatcherMode(player);
			}
			
			return;
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getFrom().getBlockX() == event.getTo().getBlockX())
			if (event.getFrom().getBlockZ() == event.getTo().getBlockZ())
				if (event.getFrom().getBlockY() == event.getTo().getBlockY())
					return;
		
		Player player = event.getPlayer();
	/*	
		if (cp.getCombatTime() > 0) {
			
			ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionContainer().get(event.getTo().getWorld()).getApplicableRegions(event.getTo());
			
			Set<ProtectedRegion> regions = set.getRegions();
			
			for (ProtectedRegion region : regions) {
				if (region.getId().equals("newspawn") || region.getId().equals("spawn") || region.getId().equals("nogo")) {
					player.sendMessage(ChatColor.RED + "Cannot walk into spawn in combat");
					event.setCancelled(true);
				}
			}
		} */
		
		if (event.getPlayer().getLocation().getWorld().getName().endsWith("_nether"))
        {
			if (event.getTo().getBlockY() >= 126) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "Teleporting to above the nether is disabled");
				return;
			}
        }
		
		if (player.getAllowFlight()) {
			
			if (player.hasPermission("0txplugin.fly.bypass"))
				return;
			
			if (event.getTo().getWorld().getEnvironment() == Environment.THE_END) {
				if (player.isFlying())
					player.setFlying(false);
				
				player.setAllowFlight(false);
				
				player.sendMessage(ChatColor.RED + "Flying is disabled in the End");
				return;
			}
			
			if (player.hasPermission("0txplugin.fly.warzone")) {
				ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionContainer().get(event.getTo().getWorld()).getApplicableRegions(event.getTo());
				
				Set<ProtectedRegion> regions = set.getRegions();
				
				for (ProtectedRegion region : regions) {
					
					if (region.getId().equalsIgnoreCase("warzone"))
						return;
					
				}
			}
			
			if (WorldGuardRegionCheck.getWithinAllowedRegion(event.getTo()))
				return;
			
			if (OtxPlugin.getFactionCanFly(event.getTo(), player))
				return;
			
			if (player.isFlying())
				player.setFlying(false);
			
			player.setAllowFlight(false);
			
			player.sendMessage(ChatColor.RED + "Flymode disabled");
			
		}
		
	}
/*	@EventHandler
	public void onRegen(EntityRegainHealthEvent e) {
		 
		if ((e.getEntityType() == EntityType.PLAYER) && (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED)) {
			
			if (!WorldGuardRegionCheck.getWithinAllowedRegion(e.getEntity().getLocation())) return;
			
			e.setAmount(e.getAmount() / 6.5D);
		}
	} */
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player)) {
		if (Event_IncreaseXPDrops.getActive()) {
				
				int xp = event.getDroppedExp();
				event.setDroppedExp(xp * Event_IncreaseXPDrops.getMultiplier());
				
			}
			
			if (event.getEntity() instanceof LivingEntity) {
				
				if (Event_IncreaseMobOreDrops.getActive()) {

					ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
					
					for (ItemStack is : event.getDrops())
						drops.add(is);
						
					event.getDrops().clear();
					
					for (ItemStack is : drops)
						if (checkItem(is)) {
							event.getDrops().add(new ItemStack(is.getType(), is.getAmount() * Event_IncreaseMobOreDrops.getMultiplier()));
						} else {
							event.getDrops().add(new ItemStack(is.getType(), is.getAmount()));
						}
					
				}
				
			}
		} 
	}
	
/*	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player dead = event.getEntity();
		Player killer = event.getEntity().getKiller();
		
		if (killer == null) return;
		
		ItemStack item = dead.getInventory().getItemInMainHand();
		ItemStack item2 = killer.getInventory().getItemInMainHand();
		
		if (item != null) {
			if (!item.hasItemMeta()) return;
			ItemMeta meta = item.getItemMeta();
			if (meta == null) return;
			
			List<String> lore = new ArrayList<String>(meta.getLore());
			
				for (String string : lore) {
					if (string.startsWith(ChatColor.stripColor("Killstreak: "))) {
						int killstreak = Integer.parseInt(string.replace("Killstreak: ", ""));
						lore.clear();
						lore.add(ChatColor.GOLD + "Killstreak: " + ChatColor.LIGHT_PURPLE + 0);
						player.sendMessage(ChatColor.RED + "You lost your killstreak of " + killstreak);
					}
				}
		}
	} */
	
private Boolean checkItem(ItemStack item) {
		
		switch (item.getType()) {
		
		case FEATHER :
			return true;
			
		case RAW_CHICKEN :
			return true;
			
		case COOKED_CHICKEN :
			return true;
			
		case LEATHER :
			return true;
			
		case RAW_BEEF :
			return true;
			
		case COOKED_BEEF :
			return true;
			
		case PORK :
			return true;
			
		case GRILLED_PORK :
			return true;
			
		case WOOL :
			return true;
			
		case BONE :
			return true;
			
		case INK_SACK :
			return true;
			
		case ROTTEN_FLESH :
			return true;
			
		case IRON_INGOT :
			return true;
			
		case BLAZE_ROD :
			return true;
			
		case GLOWSTONE_DUST :
			return true;
			
		case STRING :
			return true;
			
		case SPIDER_EYE :
			return true;
			
		case ENDER_PEARL :
			return true;
			
		case GHAST_TEAR :
			return true;
			
		case SULPHUR :
			return true;
			
		case MAGMA_CREAM :
			return true;
			
		case ARROW :
			return true;
			
		case SLIME_BALL :
			return true;
			
		case SKULL_ITEM :
			return true;
			
		case GOLD_NUGGET :
			return true;
			
		case CARROT :
			return true;
			
		case POTATO :
			return true;
			
		default:
			return false;
			
		}
		
	}
}
