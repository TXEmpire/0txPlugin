package me.flash1110.Otxplugin.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.flash1110.Otxplugin.Others;
import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.donor.DInventory;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.nick.Colour;
import me.flash1110.Otxplugin.objects.nick.InventoryColour;
import me.flash1110.Otxplugin.objects.nick.TxPlayerNick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitScheduler;

public class InventoryListener implements Listener {

	public static OtxPlugin plugin;
	
	public InventoryListener(OtxPlugin instance) {
		
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static void closeOnlineViewer(String playername) {
		
		for (Player P : Bukkit.getOnlinePlayers()) {
			
			if (P.getOpenInventory().getTopInventory().getType() == InventoryType.PLAYER) {
				
				InventoryHolder holder = P.getOpenInventory().getTopInventory().getHolder();
				String HolderName = holder.toString().split("=")[1].replace("}", "");
				
				if (HolderName.equalsIgnoreCase(playername)) {
					P.closeInventory();
					P.sendMessage(ChatColor.GOLD + playername + ChatColor.RED + " logged out, invsee closed");
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if (event.getInventory() == null)
			return;
		
		if (event.getWhoClicked() instanceof Player) {
			
			Player player = (Player) event.getWhoClicked();
			
			if (OtxPlugin.isInventory(event.getInventory().getTitle())) {
				
				event.setCancelled(true);
			
				DInventory dinv = OtxPlugin.getInventory(event.getInventory().getTitle());
				dinv.handelClick((Player) event.getWhoClicked(), event.getRawSlot());	
			}
			
			if (event.getInventory().getName().equals(ChatColor.YELLOW + "Change Nick")) {
				
				event.setCancelled(true);
				event.setResult(Result.DENY);
				
				
				if (!player.hasPermission("0txplugin.nick.view")) {
					player.sendMessage(ChatColor.RED + "You lack permission");
					player.closeInventory();
					return;
				}
				
				if (!player.hasPermission("0txplugin.nick")) {
					player.sendMessage(ChatColor.RED + "You lack permission");
					return;
				}
				
				String playername = player.getName();
				
				if (!InventoryColour.doesPlayerExist(playername))
					InventoryColour.addPlayer(playername);
				
				TxPlayerNick txplayernick = InventoryColour.getPlayer(playername);
				int rawslot = event.getRawSlot();
				
				Colour foundcolour = Colour.getFromSlot(rawslot);
				
				if (foundcolour == null)
					return;
				
				if (!player.hasPermission("0txplugin.nick." + foundcolour.getName().replace(" ", "").toLowerCase())) {
					player.sendMessage(ChatColor.RED + "You lack permission to use that colour");
					return;
				}
				
				if (txplayernick.addColour("" + foundcolour.getColour()))
					player.sendMessage(ChatColor.YELLOW + "Nick �f= " + txplayernick.getNick());
				else {
					
					player.sendMessage(ChatColor.YELLOW + "Nick �f= " + txplayernick.getNick());
					
					String convertedmessage = Others.stripColour(txplayernick.getNick());
					
					if (!convertedmessage.equals(playername)) {
						InventoryColour.removePlayer(playername);
						player.sendMessage("�cAn error occured, please try again");
						player.closeInventory();
						return;
					}
					
					if (txplayernick.getNick().length() > 48) {
						InventoryColour.removePlayer(playername);
						player.sendMessage(ChatColor.RED + "That nickname is too long");
						player.sendMessage(ChatColor.RED + "Every colour change = 3 characters");
						player.sendMessage(ChatColor.RED + "The max character length is 40");
						player.closeInventory();
						return;
					}
					
					CombatPlayer cplayer = OtxPlugin.getPlayer(player);
					cplayer.setDisplayName(txplayernick.getNick());
					
					InventoryColour.removePlayer(playername);
					
					if (!IgnoreNextSpecialClose.contains(player.getUniqueId()))
						IgnoreNextSpecialClose.add(player.getUniqueId());
					
					player.closeInventory();
					
				}
				
			}
			
			if (event.getInventory().getName().equals(ChatColor.YELLOW + "Change Arrow Trail")) {
				
				event.setCancelled(true);
				event.setResult(Result.DENY);
				
				if (!player.hasPermission("empireplugin.effect.arrow")) {
					player.sendMessage(ChatColor.RED + "You lack permission");
					return;
				}
				
				int rawslot = event.getRawSlot();
				
				CombatPlayer cplayer = OtxPlugin.getPlayer(player);
				
				if (!IgnoreNextSpecialClose.contains(player.getUniqueId()))
					IgnoreNextSpecialClose.add(player.getUniqueId());
				
				player.closeInventory();
				
			}
		}
	}			
	
	private static List<String> AllowedOpen = new ArrayList<String>();
	
	public static List<UUID> IgnoreNextSpecialClose = new ArrayList<UUID>();
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		
		if (event.getInventory() == null)
			return;
		
		if (event.getPlayer() instanceof Player) {
			
			String title = event.getInventory().getTitle();
			
		
			if (OtxPlugin.isInventory(title)) {
				
				DInventory dinv = OtxPlugin.getInventory(event.getInventory().getTitle());
				dinv.handelClose((Player) event.getPlayer());
				
			} else {
				
				final Player player = (Player) event.getPlayer();
				
				if (IgnoreNextSpecialClose.contains(player.getUniqueId()))
					IgnoreNextSpecialClose.remove(player.getUniqueId());
				else {
					
					if (title.equals(ChatColor.YELLOW + "Select VChest") || 
							title.equals(ChatColor.YELLOW + "Change Arrow Trail") || 
							title.equals(ChatColor.YELLOW + "Change Color")) {
						
						openDefault(player);
						
					}
					
				}
								
			}
			
			if (event.getInventory().getName().equals(ChatColor.YELLOW + "Change Nick")) {
				
				final Player player = (Player) event.getPlayer();
				
				String playername = player.getName();
				
				if (!InventoryColour.doesPlayerExist(playername)) {
					openDefault(player);
					return;
				}
				
				TxPlayerNick txplayer = InventoryColour.getPlayer(playername);
				
				if (txplayer.removeColour()) {
					player.sendMessage(ChatColor.YELLOW + "Nick" + ChatColor.WHITE + " = " + txplayer.getNick());
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run(){
							if (player != null && player.isOnline()) {
								AllowedOpen.add(player.getName());
								InventoryColour.CreateInventory(player);
							}
						}
					}, 5L);
				} else {
					player.sendMessage(ChatColor.RED + "Nick change canceled");
				}
				
			}
		}
	}	
	
	private static void openDefault(final Player player) {
		
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(InventoryListener.plugin, new Runnable() {
            @Override
            public void run() {
            	
            	if (player != null && player.isOnline())
            		OtxPlugin.StoredInventories.get(ChatColor.BOLD + "" + ChatColor.BLACK + "Server Boost Menu").open(player);
            	
            }
        }, 1L);
		
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		
		if (event.getInventory() == null)
			return;
		
		if (event.isCancelled())
			return;
		
		/*if ((type == InventoryType.CHEST || type == InventoryType.ENDER_CHEST) && EmpirePlugin.getServerType() == ServerType.TX_9) {
			
			ItemStack[] contents = event.getInventory().getContents().clone();
			
			for (ItemStack item : contents) {
				
				if (item == null)
					continue;
				
				if (item.getEnchantments() == null)
					continue;
				
				if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
					
					ItemMeta meta = item.getItemMeta();
					
					if (meta.getLore().contains(ChatColor.GOLD + "Enchanted"))
						continue;
					
				}
				
				for (Enchantment e : new HashMap<Enchantment, Integer>(item.getEnchantments()).keySet()) {
					
					if (e.getName().equals("DAMAGE_ALL") && item.getEnchantmentLevel(e) > 2) {
						item.removeEnchantment(e);
						item.addEnchantment(e, 2);
					}
					
					if (e.getName().equals("ARROW_DAMAGE") && item.getEnchantmentLevel(e) > 2) {
						item.removeEnchantment(e);
						item.addEnchantment(e, 2);
					}
					
					if (e.getName().startsWith("PROTECTION_") && item.getEnchantmentLevel(e) > 2) {
						item.removeEnchantment(e);
						item.addEnchantment(e, 2);
					}
					
				}
				
			}
			
			if (contents != null && contents.length > 0)
				event.getInventory().setContents(contents);
			
		}*/
		
		if (event.getInventory().getName().equals(ChatColor.YELLOW + "Change Nick")) {
			
			if (event.getPlayer() instanceof Player) {
				
				Player player = (Player) event.getPlayer();
				
				String playername = player.getName();
				
				if (AllowedOpen.contains(playername)) {
					AllowedOpen.remove(playername);
					return;
				}
				
				if (InventoryColour.doesPlayerExist(playername))
					InventoryColour.removePlayer(playername);
				
			}
			
			
		}
		
	}
	
}
