package me.flash1110.Otxplugin.commands;

import me.flash1110.Otxplugin.listeners.ChatColorListener;

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

public class ChatColorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("chatcolor.use")) {
			sender.sendMessage(ChatColor.RED + "You lack permission to change your chat color");
			return true;
		}
		
		if (args.length <= 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player");
				return true;
			}
			Inventory inv = Bukkit.createInventory(null, 18, ChatColor.AQUA + "Pick your ChatColor");
			
			ItemStack noperm = new ItemStack(Material.BEDROCK);
			ItemMeta metaperm = noperm.getItemMeta();
			metaperm.setDisplayName(ChatColor.RED + "No permission");
			noperm.setItemMeta(metaperm);
			
			if (sender.hasPermission("chatcolor.lightgreen")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "Light Green");
			    lg.setItemMeta(meta);
			    inv.setItem(0, lg);
			} else {
				inv.setItem(0, noperm);
			}
			
			if (sender.hasPermission("chatcolor.lightblue")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.AQUA + "Light Blue");
			    lg.setItemMeta(meta);
			    inv.setItem(1, lg);
			} else {
				inv.setItem(1, noperm);
			}
			
			if (sender.hasPermission("chatcolor.lightred")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 6);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.RED + "Light Red");
			    lg.setItemMeta(meta);
			    inv.setItem(2, lg);
			} else {
				inv.setItem(2, noperm);
			}
			
			if (sender.hasPermission("chatcolor.lightpurple")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Light Purple");
			    lg.setItemMeta(meta);
			    inv.setItem(3, lg);
			} else {
				inv.setItem(3, noperm);
			}
			
			if (sender.hasPermission("chatcolor.yellow")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.YELLOW + "Yellow");
			    lg.setItemMeta(meta);
			    inv.setItem(4, lg);
			} else {
				inv.setItem(4, noperm);
			}
			
			if (sender.hasPermission("chatcolor.white")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE + "White");
			    lg.setItemMeta(meta);
			    inv.setItem(5, lg);
			} else {
				inv.setItem(5, noperm);
			}
			
			if (sender.hasPermission("chatcolor.black")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.BLACK + "Black");
			    lg.setItemMeta(meta);
			    inv.setItem(6, lg);
			} else {
				inv.setItem(6, noperm);
			}
			
			if (sender.hasPermission("chatcolor.darkblue")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_BLUE + "Dark Blue");
			    lg.setItemMeta(meta);
			    inv.setItem(7, lg);
			} else {
				inv.setItem(7, noperm);
			}
			
			if (sender.hasPermission("chatcolor.darkgreen")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green");
			    lg.setItemMeta(meta);
			    inv.setItem(8, lg);
			} else {
				inv.setItem(8, noperm);
			}
			
			if (sender.hasPermission("chatcolor.darkaqua")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 9);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_AQUA + "Dark Aqua (Cyan)");
			    lg.setItemMeta(meta);
			    inv.setItem(9, lg);
			} else {
				inv.setItem(9, noperm);
			}
			
			if (sender.hasPermission("chatcolor.darkred")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_RED + "Dark Red");
			    lg.setItemMeta(meta);
			    inv.setItem(10, lg);
			} else {
				inv.setItem(10, noperm);
			}
			
			if (sender.hasPermission("chatcolor.purple")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Purple");
			    lg.setItemMeta(meta);
			    inv.setItem(11, lg);
			} else {
				inv.setItem(11, noperm);
			}
			
			if (sender.hasPermission("chatcolor.gold")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "Gold (Orange)");
			    lg.setItemMeta(meta);
			    inv.setItem(12, lg);
			} else {
				inv.setItem(12, noperm);
			}
			
			if (sender.hasPermission("chatcolor.gray")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.GRAY + "Gray");
			    lg.setItemMeta(meta);
			    inv.setItem(13, lg);
			} else {
				inv.setItem(13, noperm);
			}
			
			if (sender.hasPermission("chatcolor.darkgray")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_GRAY + "Dark Gray");
			    lg.setItemMeta(meta);
			    inv.setItem(14, lg);
			} else {
				inv.setItem(14, noperm);
			}
			
			if (sender.hasPermission("chatcolor.blue")) {
				ItemStack lg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
				ItemMeta meta = lg.getItemMeta();
				meta.setDisplayName(ChatColor.BLUE + "Blue");
			    lg.setItemMeta(meta);
			    inv.setItem(15, lg);
			} else {
				inv.setItem(15, noperm);
			}
			
			((Player) sender).openInventory(inv);
			return true;
		} else {
			if (sender.hasPermission("chatcolor.change")) {
			String name = args[0];
			Player player = Bukkit.getPlayer(name);
			if (player != null) {
				ChatColorListener.list.remove(player.getUniqueId());
				ChatColorListener.list.put(player.getUniqueId(), ChatColor.getByChar(args[1]));
				sender.sendMessage(ChatColor.GREEN + "Set " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "'s ChatColor to " + ChatColor.getByChar(args[1]) + "this");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Could not find Player " + ChatColor.GOLD + args[0]);
				return true;
			}
	      }
		}
		return true;
	}

}
