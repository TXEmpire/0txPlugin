package me.flash1110.Otxplugin.donor;

import java.util.ArrayList;
import java.util.List;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.donor.events.SpendEvent;
import me.flash1110.Otxplugin.listeners.ChatColorListener;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

public class DInventory {
	
	private	String						InventoryName;
	
	private	DInventory					ReturnToOnClose;
	
	private	ItemStack[]					Items;
	private	Integer						Size;
	
	private	InventoryClickLink[]		Clicks;
	private	SpendEvent[]				Clicks2;
	
	public DInventory(String name, DInventory returnto, ItemStack[] items, InventoryClickLink[] clicks, SpendEvent[] clicks2) {
		
		InventoryName = name;
		ReturnToOnClose = returnto;
		Items = items;
		Clicks = clicks;
		Clicks2 = clicks2;
		
		Integer size = Items.length;
		Integer size2 = Items.length;
		
		while (size2 > 0)
			size2-= 9;
		
		Integer toadd = 0 - (size2);
		
		size+= toadd;
		Size = size;
	}
	
	public void setReturnTo(DInventory returnto) {
		ReturnToOnClose = returnto;
	}
	
	public void open(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, Size, InventoryName);
		
		Integer itemon = -1;
		
		CombatPlayer cplayer = OtxPlugin.getPlayer(player);
		
		for (ItemStack item : Items) {
			
			itemon++;
			
			if (item == null)
				continue;
			
			ItemStack newitem = new ItemStack(item);
			
			if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
				
				final List<String> Lore = new ArrayList<String>(item.getItemMeta().getLore());
				
				Integer cost = null;
				
				for (String c : Lore)
					if (c.startsWith("cost="))
						cost = Integer.parseInt(c.replace("cost=", ""));
				
				List<String> Lore2 = new ArrayList<String>();
				
				Boolean addcantafford = false;
				
				for (String s : Lore) {
					
					s = s.replace("%dp%", cplayer == null ? "0" : ("" + cplayer.getDonnorPointBalance()));
					
					if (cost != null) {
						
						s = s.replace("%left%", cplayer == null ? "0" : ("" + (cplayer.getDonnorPointBalance() - cost)));
						
						if (cost > cplayer.getDonnorPointBalance())
							addcantafford = true;
						
					}
					
					if (!s.startsWith("cost="))
						Lore2.add(s);
					
				}
				
				ItemMeta meta = newitem.getItemMeta();
				
				if (addcantafford) {
					
					Lore2.add(ChatColor.RED + "You can't afford this");
					Lore2.add("");
					
					String ItemName = meta.getDisplayName();
					
				
					
					meta.setDisplayName(ItemName);
					
				}
				
				meta.setLore(Lore2);
				newitem.setItemMeta(meta);
				
			}
			
			inv.setItem(itemon, newitem);
			
		}
		
		player.openInventory(inv);
		
	}
	
	public void open(Player player, Integer cost1) {
		
		Inventory inv = Bukkit.createInventory(null, Size, InventoryName);
		
		Integer itemon = -1;
		
		CombatPlayer cplayer = OtxPlugin.getPlayer(player);
		
		for (ItemStack item : Items) {
			
			itemon++;
			
			if (item == null)
				continue;
			
			ItemStack newitem = new ItemStack(item);
			
			if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
				
				final List<String> Lore = new ArrayList<String>(item.getItemMeta().getLore());
				
				Integer cost = null;
				
				for (String c : Lore)
					if (c.startsWith("cost="))
						cost = Integer.parseInt(c.replace("cost=", ""));
				
				cost = cost + cost1;
				
				List<String> Lore2 = new ArrayList<String>();
				
				Boolean addcantafford = false;
				
				for (String s : Lore) {
					
					s = s.replace("%dp%", cplayer == null ? "0" : ("" + cplayer.getDonnorPointBalance()));
					
					s = s.replace("%cost%", cost == null ? "0" : "" + cost);
					
					if (cost != null) {
						
						s = s.replace("%left%", cplayer == null ? "0" : ("" + (cplayer.getDonnorPointBalance() - cost)));
						
						if (cost > cplayer.getDonnorPointBalance())
							addcantafford = true;
						
					}
					
					if (!s.startsWith("cost="))
						Lore2.add(s);
					
				}
				
				ItemMeta meta = newitem.getItemMeta();
				
				if (addcantafford) {
					
					Lore2.add(ChatColor.RED + "You can't afford this");
					Lore2.add("");
					
					String ItemName = meta.getDisplayName();
					
					ItemName = ItemName.replace("" + ChatColor.AQUA, ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.BLACK, ChatColor.BLACK + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.BLUE, ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_AQUA, ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_BLUE, ChatColor.DARK_BLUE + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_GRAY, ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_GREEN, ChatColor.DARK_GREEN + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_PURPLE, ChatColor.DARK_PURPLE + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.DARK_RED, ChatColor.DARK_RED + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.GOLD, ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.GRAY, ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.GREEN, ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.RED, ChatColor.RED + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.WHITE, ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH)
							.replace("" + ChatColor.YELLOW, ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH);
					
					meta.setDisplayName(ItemName);
					
				}
				
				meta.setLore(Lore2);
				newitem.setItemMeta(meta);
				
			}
			
			inv.setItem(itemon, newitem);
			
		}
		
		player.openInventory(inv);
		
	}
	
	public void handelClick(Player player, Integer Clicked) {
		
		if (Clicks != null) {
			
			for (InventoryClickLink icl : Clicks) {
				
				if (icl == null)
					continue;
				
				if (icl.getSlot() == Clicked) {
					forceclose(player);
					if (icl.getExtra() == 0) {
						icl.getInventory().open(player);
					} else {
						System.out.println("Handle Click Extra");
						icl.getInventory().open(player, icl.getExtra());
					}
					return;
				}
				
			}
			
		}
		
		if (Clicks2 != null) {
			
			for (SpendEvent se : Clicks2) {
				
				if (se.getSlot() == Clicked) {
					
					if (!se.checkcost(player)) {
						player.sendMessage(ChatColor.RED + "You can't afford this");
						return;
					}
					
					if (se.check(player)) {
						CombatPlayer cp = OtxPlugin.getPlayer(player);
						cp.removeDonnorPointBalance(se.getCost());
						
						if (se.getCost() != 0) {
							player.sendMessage(ChatColor.GOLD + "Your new balance is " + ChatColor.LIGHT_PURPLE + cp.getDonnorPointBalance());
						}
						
						forceclose(player);
						se.run(player);
					}
					
					return;
				}
				
			}
			
		}
		
	}
	
	public void handelClose(Player player) {
		
		if (player.getOpenInventory().getTopInventory().getItem(0) != null)
			if (player.getOpenInventory().getTopInventory().getItem(0).getType() == Material.ACTIVATOR_RAIL)
				return;
		
		if (ReturnToOnClose != null) {
			
			final Player player2 = player;
			
	        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncDelayedTask(ChatColorListener.plugin, new Runnable() {
	            @Override
	            public void run() {
	            	
	            	if (player2 != null && player2.isOnline())
	            		ReturnToOnClose.open(player2);
	            	
	            }
	        }, 1L);
			

			
		}
		
	}
	
	public void forceclose(Player player) {
		player.getOpenInventory().getTopInventory().setItem(0, new ItemStack(Material.ACTIVATOR_RAIL));
		player.closeInventory();
	}

}
