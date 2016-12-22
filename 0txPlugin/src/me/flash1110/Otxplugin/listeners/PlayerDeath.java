package me.flash1110.Otxplugin.listeners;

import java.util.Random;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerDeath implements Listener {

	public static OtxPlugin plugin;
	
	public PlayerDeath(OtxPlugin instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (player.getKiller() != null) {
			if (player.getKiller() instanceof Player) {
				Player killer = player.getKiller();
				
				if (player.hasPermission("0tx.collectheads.bypass")) {
					return;
				}
				
				if (killer.hasPermission("flash.rank.end")) {
					
					ItemStack head = new ItemStack(Material.SKULL_ITEM);
					head.setDurability((short) 3);
					
					SkullMeta meta = (SkullMeta) head.getItemMeta();
					meta.setOwner(player.getName());
						
					head.setItemMeta(meta);
						
					player.getWorld().dropItemNaturally(player.getLocation(), head);
				} else if (killer.hasPermission("flash.rank.emperor")) {
				

					
					if (getRandPercent(75)) {
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						head.setDurability((short) 3);
						
						SkullMeta meta = (SkullMeta) head.getItemMeta();
						meta.setOwner(player.getName());
						
						head.setItemMeta(meta);
						
						player.getWorld().dropItemNaturally(player.getLocation(), head);
						
					}
				} else if (killer.hasPermission("flash.rank.king")) {
				
					
					if (getRandPercent(50)) {
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						head.setDurability((short) 3);
						
						SkullMeta meta = (SkullMeta) head.getItemMeta();
						meta.setOwner(player.getName());
						
						head.setItemMeta(meta);
						
						player.getWorld().dropItemNaturally(player.getLocation(), head);
						
					}
				} else if (killer.hasPermission("flash.rank.general")) {
					
					
					if (getRandPercent(30)) {
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						head.setDurability((short) 3);
						
						SkullMeta meta = (SkullMeta) head.getItemMeta();
						meta.setOwner(player.getName());
						
						head.setItemMeta(meta);
						
						player.getWorld().dropItemNaturally(player.getLocation(), head);
						
					}
				} else if (killer.hasPermission("flash.rank.knight")) {
					
					
					if (getRandPercent(20)) {
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						head.setDurability((short) 3);
						
						SkullMeta meta = (SkullMeta) head.getItemMeta();
						meta.setOwner(player.getName());
						
						head.setItemMeta(meta);
						
						player.getWorld().dropItemNaturally(player.getLocation(), head);
						
					}
				} else if (killer.hasPermission("flash.rank.soldier")) {
				
					
					if (getRandPercent(10)) {
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM);
						head.setDurability((short) 3);
						
						SkullMeta meta = (SkullMeta) head.getItemMeta();
						meta.setOwner(player.getName());
						
						head.setItemMeta(meta);
						
						player.getWorld().dropItemNaturally(player.getLocation(), head);
						
					}
				}
			}
		}
	}
	
	public static boolean getRandPercent(int percent) {
	    Random rand = new Random();
	    return rand.nextInt(100) <= percent;
	}
	
	@EventHandler
	public void onEXP(PlayerExpChangeEvent event) {
		
		Player killer = event.getPlayer();
	    if (killer.hasPermission("empireplugin.looting"))
	    {
	      ItemStack hand = killer.getItemInHand();
	      if ((hand == null) || (hand.getType() == Material.AIR)) {
	        return;
	      }
	      if (killer.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS))
	      {
	        double bm = 2.0D;
	        int level = hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
	        if (level == 1) {
	          bm = 1.5D;
	        } else if (level == 2) {
	          bm = 2.5D;
	        } else if (level == 3) {
	        	bm = 3.5D;
	        }
	        event.setAmount((int)(event.getAmount() * bm));
	      }
	    }
		
		
	}
}
