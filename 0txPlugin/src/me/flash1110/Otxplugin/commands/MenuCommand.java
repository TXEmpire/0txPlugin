package me.flash1110.Otxplugin.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("chatcolor.use")) {
			sender.sendMessage(ChatColor.RED + "You lack permission to access the money");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player");
			return true;
		}
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Color Menu");
		
		ItemStack chat = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta chatm = chat.getItemMeta();
		chatm.setDisplayName(ChatColor.RED + "Chat Color");
		chatm.setLore(Arrays.asList(ChatColor.GOLD + "Change your ChatColor"));
		chat.setItemMeta(chatm);
		
		ItemStack name = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta namem = name.getItemMeta();
		namem.setDisplayName(ChatColor.RED + "Name Color");
		namem.setLore(Arrays.asList(ChatColor.GOLD + "Change your Name Color"));
		name.setItemMeta(namem);
		
		inv.setItem(3, chat);
		inv.setItem(5, name);
		
		((Player) sender).openInventory(inv);
		return true;
	}

}
