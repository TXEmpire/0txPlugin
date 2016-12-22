package me.flash1110.Otxplugin.listeners;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.commands.Staff;
import me.flash1110.Otxplugin.objects.CombatPlayer;
import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatColorListener implements Listener {

	public static OtxPlugin plugin;
	
	public ChatColorListener(OtxPlugin instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static HashMap<UUID, ChatColor> list = new HashMap<UUID, ChatColor>();
	public static HashMap<UUID, ChatColor> namecolor = new HashMap<UUID, ChatColor>();
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}
		
		if (event.getCurrentItem() == null) 
			return;
		
		if (!event.getCurrentItem().hasItemMeta()) 
			return;
		
		Player player = (Player) event.getWhoClicked();
		if (ChatColor.stripColor(event.getInventory().getName()).equals("Pick your ChatColor")) {
			event.setResult(Result.DENY);
			
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
				case "No Permission":
					player.sendMessage(ChatColor.RED + "You lack permission");
					player.closeInventory();
					break;
				case "Light Green":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GREEN + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.GREEN);
					break;
				case "Light Blue":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.AQUA + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.AQUA);
					break;
				case "Light Red":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.RED + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.RED);
					break;
				case "Light Purple":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.LIGHT_PURPLE + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.LIGHT_PURPLE);
					break;
				case "Yellow":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.YELLOW + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.YELLOW);
					break;
				case "White":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.WHITE + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.WHITE);
					break;
				case "Black":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.BLACK + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.BLACK);
					break;
				case "Dark Blue":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_BLUE + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_BLUE);
					break;
				case "Dark Green":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_GREEN + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_GREEN);
					break;
				case "Dark Aqua (Cyan)":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_AQUA + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_AQUA);
					break;
				case "Dark Red":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_RED + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_RED);
					break;
				case "Purple":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_PURPLE + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_PURPLE);
					break;
				case "Gold (Orange)":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GOLD + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.GOLD);
					break;
				case "Gray":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GRAY + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.GRAY);
					break;
				case "Dark Gray":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_GRAY + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.DARK_GRAY);
					break;
				case "Blue":
					player.sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.BLUE + "this");
					player.closeInventory();
					list.put(player.getUniqueId(), ChatColor.BLUE);
					break;
			}
		} else if (ChatColor.stripColor(event.getInventory().getName()).equals("Pick your Name Color")) {
			event.setResult(Result.DENY);
			String pre;
			if (player.hasPermission("namecolor.owner")) {
			    pre = "&4Owner";
			   } else if (player.hasPermission("namecolor.admin")) {
			    pre = "&4Admin";
			   } else if (player.hasPermission("namecolor.dev")) {
			    pre = "&4Dev";
			   } else if (player.hasPermission("namecolor.builder")) {
			    pre = "&bBuilder";
			   } else if (player.hasPermission("namecolor.modplus")) {
				   pre = "&bStaff";
			   } else if (player.hasPermission("namecolor.mod")) {
			    pre = "&6Staff";
			   } else if (player.hasPermission("namecolor.trial")) {
			    pre = "&dTrial";
			   } else {
			    pre = "";
			   }

			
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case "No Permission":
				player.sendMessage(ChatColor.RED + "You lack permission");
				player.closeInventory();
				break;
			case "Light Green":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GREEN + "this");
				player.closeInventory();
				String lgreen = (pre.equals("") ? pre + "&a" : pre + "&a ");
				pre = "pex user " + player.getName() + " prefix \"" + lgreen + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Blue":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.AQUA + "this");
				player.closeInventory();
				String lblue = (pre.equals("") ? pre + "&b" : pre + "&b ");
				pre = "pex user " + player.getName() + " prefix \"" + lblue + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Red":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.RED + "this");
				player.closeInventory();
				String lred = (pre.equals("") ? pre + "&c" : pre + "&c ");
				pre = "pex user " + player.getName() + " prefix \"" + lred + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Purple":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.LIGHT_PURPLE + "this");
				player.closeInventory();
				String lpurp = (pre.equals("") ? pre + "&d" : pre + "&d ");
				pre = "pex user " + player.getName() + " prefix \"" + lpurp + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Yellow":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.YELLOW + "this");
				player.closeInventory();
				String lyel = (pre.equals("") ? pre + "&e" : pre + "&e ");
				pre = "pex user " + player.getName() + " prefix \"" + lyel + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "White":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.WHITE + "this");
				player.closeInventory();
				String white = (pre.equals("") ? pre + "&f" : pre + "&f ");
				pre = "pex user " + player.getName() + " prefix \"" + white + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Black":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.BLACK + "this");
				player.closeInventory();
				String bl = (pre.equals("") ? pre + "&0" : pre + "&0 ");
				pre = "pex user " + player.getName() + " prefix \"" + bl + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Blue":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_BLUE + "this");
				player.closeInventory();
				String blue = (pre.equals("") ? pre + "&1" : pre + "&1 ");
				pre = "pex user " + player.getName() + " prefix \"" + blue + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Green":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_GREEN + "this");
				player.closeInventory();
				String gre = (pre.equals("") ? pre + "&2" : pre + "&2 ");
				pre = "pex user " + player.getName() + " prefix \"" + gre + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Aqua (Cyan)":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_AQUA + "this");
				player.closeInventory();
				String cyan = (pre.equals("") ? pre + "&3" : pre + "&3 ");
				pre = "pex user " + player.getName() + " prefix \"" + cyan + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Red":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_RED + "this");
				player.closeInventory();
				String red = (pre.equals("") ? pre + "&4" : pre + "&4 ");
				pre = "pex user " + player.getName() + " prefix \"" + red + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Purple":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_PURPLE + "this");
				player.closeInventory();
				String pur = (pre.equals("") ? pre + "&5" : pre + "&5 ");
				pre = "pex user " + player.getName() + " prefix \"" + pur + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Gold (Orange)":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GOLD + "this");
				player.closeInventory();
				String gold = (pre.equals("") ? pre + "&6" : pre + "&6 ");
				pre = "pex user " + player.getName() + " prefix \"" + gold + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Gray":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GRAY + "this");
				player.closeInventory();
				String gra = (pre.equals("") ? pre + "&7" : pre + "&7 ");
				pre = "pex user " + player.getName() + " prefix \"" + gra + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Gray":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_GRAY + "this");
				player.closeInventory();
				String dg = (pre.equals("") ? pre + "&8" : pre + "&8 ");
				pre = "pex user " + player.getName() + " prefix \"" + dg + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Blue":
				player.sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.BLUE + "this");
				player.closeInventory();
				String blu = (pre.equals("") ? pre + "&9" : pre + "&9 ");
				pre = "pex user " + player.getName() + " prefix \"" + blu + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			
			}
		} else if (ChatColor.stripColor(event.getInventory().getName()).equals("Color Menu")) {
			event.setResult(Result.DENY);
				
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case "Chat Color":
				player.closeInventory();
				Bukkit.dispatchCommand((Player) player, "cc");
			    break;
			case "Name Color":
				player.closeInventory();
				Bukkit.dispatchCommand((Player) player, "nc");
		    	break;
			default:
				player.closeInventory();
		    	break;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		CombatPlayer cplayer = OtxPlugin.getPlayer(event.getPlayer());
		String prefix = OtxPlugin.convertFormat(OtxPlugin.convertColour(PermissionsEx.getUser(player).getPrefix()));
		
		if (cplayer.getStaffChat() && player.hasPermission("0txplugin.staffchat")) {
			Staff.sendMessage(event.getMessage(), player.getDisplayName());;
			event.setCancelled(true);
			return;
		}
		
		
		if (list.containsKey(player.getUniqueId())) {
	//		event.setFormat(event.getFormat().replace("{factions_name|rp}",  "{factions_name|rp}" +  prefix + player.getDisplayName() + ":" + " " + list.get(player.getUniqueId()) + event.getMessage()));
			event.setFormat(prefix + player.getDisplayName() + ChatColor.RESET + ":" + " " + list.get(player.getUniqueId()) + event.getMessage());
		} else { 
			event.setFormat(prefix + player.getDisplayName() + ChatColor.RESET + ":" + " " + OtxPlugin.convertColourFormatPermissionSign(event.getMessage(), player));
	
		}
	}
	
	public static ChatColor getRandomColor(){
		  ChatColor[] colors = { ChatColor.WHITE, ChatColor.RED, ChatColor.GREEN, ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.DARK_PURPLE };
		  int randomColor = new Random().nextInt(colors.length);
		  return colors[randomColor];
	}
}
