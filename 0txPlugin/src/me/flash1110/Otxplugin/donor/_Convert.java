package me.flash1110.Otxplugin.donor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class _Convert {
	
	public static ItemStack ConvertItem(String name, ItemStack item, List<String> lore, int cost, Boolean added) {
		
		if (lore == null)
			lore = new ArrayList<String>();
		
		List<String> lore2 = new ArrayList<String>(lore);
		
		lore2.add("");
		lore2.add(ChatColor.YELLOW + "Cost" + ChatColor.WHITE + ": " + ChatColor.LIGHT_PURPLE + (added == true ? "%cost%" : cost));
		lore2.add(ChatColor.YELLOW + "Your Balance" + ChatColor.WHITE + ": " + ChatColor.LIGHT_PURPLE + "%dp%");
		lore2.add(ChatColor.YELLOW + "Left" + ChatColor.WHITE + ": " + ChatColor.LIGHT_PURPLE + "%left%");
		lore2.add("cost=" + cost);
		lore2.add("");
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore2);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack ConvertItem(String name, ItemStack item, List<String> lore) {
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		
		return item;
	}

}
