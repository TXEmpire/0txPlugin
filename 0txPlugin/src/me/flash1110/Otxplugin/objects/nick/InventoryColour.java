package me.flash1110.Otxplugin.objects.nick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryColour {
	
	private static HashMap<String, TxPlayerNick> Nicks = new HashMap<String, TxPlayerNick>();
	
	public static Boolean doesPlayerExist(String playername) {
		if (Nicks.containsKey(playername))
			return true;
		return false;
	}
	
	public static TxPlayerNick getPlayer(String playername) {
		return Nicks.get(playername);
	}
	
	public static void addPlayer(String playername) {
		Nicks.put(playername, new TxPlayerNick(playername));
	}
	
	public static void removePlayer(String playername) {
		Nicks.remove(playername);
	}
	
	public static void CreateInventory(Player player) {
		
		Inventory inv = Bukkit.getServer().createInventory(player, 27, ChatColor.YELLOW + "Change Nick");
		
		{
			
			ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
			List<String> lore = new ArrayList<String>();
			lore.add(" ");
			lore.add(ChatColor.AQUA + "Click a color to set the next letter");
			lore.add(ChatColor.AQUA + " of your name to that color");
			lore.add(" ");
			lore.add(ChatColor.RED + "Close the inventory to go back");
			lore.add(ChatColor.RED + " a letter or to cancel");
			lore.add(" ");
			item = updateItem(item, ChatColor.YELLOW + "Information", lore);
			inv.setItem(0, item);
			
		}
		
		for (Colour colour : Colour.values()) {
			
			ChatColor color = colour.getColour();
			
			ItemStack item = new ItemStack(colour.getBlockType()); item.setDurability(colour.getBlockSubID());
			
			item = setName(item, ChatColor.YELLOW + "Add " + colour.getName() + 
					(color == ChatColor.GRAY ? ChatColor.DARK_GRAY : ChatColor.GRAY) + 
					"(" + color + "Example" + 
					(color == ChatColor.GRAY ? ChatColor.DARK_GRAY : ChatColor.GRAY) + ")");
			
			inv.setItem(colour.getSlot(), item);
			
		}
		
		player.openInventory(inv);
		
	}
	
	public static void CreateColourInventory(Player player) {
		
		Inventory inv = Bukkit.getServer().createInventory(player, 27, ChatColor.YELLOW + "Change Color");
		
		{
			
			ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
			List<String> lore = new ArrayList<String>();
			lore.add(" ");
			lore.add(ChatColor.AQUA + "Click a colour to set your name");
			lore.add(ChatColor.AQUA + " to that colour");
			lore.add(" ");
			item = updateItem(item, ChatColor.YELLOW + "Information", lore);
			inv.setItem(0, item);
			
		}
		
		for (Colour colour : Colour.values()) {
			
			ChatColor color = colour.getColour();
			
			ItemStack item = new ItemStack(colour.getBlockType()); item.setDurability(colour.getBlockSubID());
			
			item = setName(item, ChatColor.YELLOW + "Set to " + colour.getName() + 
					(color == ChatColor.GRAY ? ChatColor.DARK_GRAY : ChatColor.GRAY) + 
					"(" + color + player.getName() + 
					(color == ChatColor.GRAY ? ChatColor.DARK_GRAY : ChatColor.GRAY) + ")");
			
			inv.setItem(colour.getSlot(), item);
			
		}
		
		player.openInventory(inv);
		
	}
	
	private static ItemStack setName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack updateItem(ItemStack item, String name, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

}
