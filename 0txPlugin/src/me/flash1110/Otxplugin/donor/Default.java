package me.flash1110.Otxplugin.donor;

import java.util.ArrayList;
import java.util.List;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.donor.events.Event_BuyPoints;
import me.flash1110.Otxplugin.donor.events.Event_Feed;
import me.flash1110.Otxplugin.donor.events.Event_GiveMoney;
import me.flash1110.Otxplugin.donor.events.Event_GivePotionEffect;
import me.flash1110.Otxplugin.donor.events.Event_Heal;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseMobOreDrops;
import me.flash1110.Otxplugin.donor.events.Event_IncreaseXPDrops;
import me.flash1110.Otxplugin.donor.events.Event_Kit;
import me.flash1110.Otxplugin.donor.events.Event_RandomItem;
import me.flash1110.Otxplugin.donor.events.SpendEvent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Default {

	
	public static void loadDefaults() {
		DInventory Health;
		DInventory Potions;
		DInventory Default;
		DInventory Money;
		DInventory Kits;
		DInventory Points;
		
		{
			
			String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Health GUI";
			
			ItemStack[] Items = new ItemStack[8];
			
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "This feeds all online players");
			Lore.add("");
			
			Items[3] = _Convert.ConvertItem(ChatColor.GOLD + "Feed all players", new ItemStack(Material.COOKIE), Lore, 100, false);
			
			Lore.clear();
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "This heals all online players");
			Lore.add("");
			
			Items[5] = _Convert.ConvertItem(ChatColor.GOLD + "Heal all players", new ItemStack(Material.GOLDEN_APPLE), Lore, 200, false);
			
			SpendEvent[] Events = new SpendEvent[2];
			
			Events[0] = new Event_Feed(3, 100);
			Events[1] = new Event_Heal(5, 200);
			
			Health = new DInventory(Name, null, Items, new InventoryClickLink[0], Events);
			
			OtxPlugin.StoredInventories.put(Name, Health);
			
		}
		
		
		{
			String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Kits";
			
			ItemStack[] Items = new ItemStack[8];
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players Kit General");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Iron Helmet");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Iron Chestplate");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Iron Leggings");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Iron Boots");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Sword");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Axe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Pickaxe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Shovel");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Shield");
				Lore.add("");
				
				Items[1] = _Convert.ConvertItem(ChatColor.GOLD + "Kit General", new ItemStack(Material.CHEST, 1), Lore, 50, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players Kit King");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Iron Helmet");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Iron Chestplate");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Iron Leggings");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Iron Boots");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Sword");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Axe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Pickaxe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Shovel");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Shield");
				Lore.add("");
				
				Items[2] = _Convert.ConvertItem(ChatColor.GOLD + "Kit King", new ItemStack(Material.CHEST, 1), Lore, 75, false);
			}
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players Kit Emperor");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Helmet");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Chestplate");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Leggings");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Diamond Boots");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Sword");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Axe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Pickaxe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Shovel");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Shield");
				Lore.add("");
				
				Items[3] = _Convert.ConvertItem(ChatColor.GOLD + "Kit Emperor", new ItemStack(Material.CHEST, 1), Lore, 150, false);
			}
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players Kit End");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Helmet");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Chestplate");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Leggings");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 2 Diamond Boots");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 3 Diamond Sword");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 3 Diamond Axe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 3 Diamond Pickaxe");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 3 Diamond Shovel");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 3 Shield");
				Lore.add("");
				
				Items[4] = _Convert.ConvertItem(ChatColor.GOLD + "Kit End", new ItemStack(Material.CHEST, 1), Lore, 200, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players Kit Archer");
				Lore.add("");
				Lore.add(ChatColor.LIGHT_PURPLE + "x1 Unbreaking 1 Bow");
				Lore.add(ChatColor.LIGHT_PURPLE + "x64 Arrows");
				Lore.add("");
				
				Items[0] = _Convert.ConvertItem(ChatColor.GOLD + "Kit Archer", new ItemStack(Material.CHEST, 1), Lore, 25, false);
			}
			
			SpendEvent[] Events = new SpendEvent[5];
			
	//		Events[0] = new Event_Kit(1, 50, "General");
	//		
		//	Events[1] = new Event_Kit(2, 75, "King");
			
		//	Events[2] = new Event_Kit(3, 150, "Emperor");
			
	//		Events[3] = new Event_Kit(4, 200, "End");
			
		//	Events[4] = new Event_Kit(0, 25, "Archer");
			
		//	Kits = new DInventory(Name, null, Items, new InventoryClickLink[0], Events);
			
		//	OtxPlugin.StoredInventories.put(Name, Kits);
		}
		
		{
			String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Points";
			
			ItemStack[] Items = new ItemStack[8];
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.GREEN + "Click here to buy");
				Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "$5 " + ChatColor.DARK_GRAY + "(100pts/$)");
				Lore.add("");
				
				Items[2] = _Convert.ConvertItem(ChatColor.GOLD + "500 Boost Points", new ItemStack(Material.INK_SACK, 1, (short) 2), Lore);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.GREEN + "Click here to buy");
				Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "$10 " + ChatColor.DARK_GRAY + "(150pts/$)");
				Lore.add("");
				
				Items[3] = _Convert.ConvertItem(ChatColor.GOLD + "1500 Boost Points", new ItemStack(Material.INK_SACK, 1, (short) 6), Lore);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.GREEN + "Click here to buy");
				Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "$15 " + ChatColor.DARK_GRAY + "(200pts/$)");
				Lore.add("");
				
				Items[4] = _Convert.ConvertItem(ChatColor.GOLD + "3000 Boost Points" , new ItemStack(Material.INK_SACK, 1, (short) 11), Lore);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.GREEN + "Click here to buy");
				Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "$20 " + ChatColor.DARK_GRAY + "(250pts/$)");
				Lore.add("");
				
				Items[5] = _Convert.ConvertItem(ChatColor.GOLD + "5000 Boost Points", new ItemStack(Material.INK_SACK, 1, (short) 1), Lore);
			}
			
			SpendEvent[] Events = new SpendEvent[8];
			
			/*
			 * 500bp - http://donate.enderchest.org/buy/a7uu
1500bp - http://donate.enderchest.org/buy/a7ud
3000bp - http://donate.enderchest.org/buy/a5IG
5000bp - http://donate.enderchest.org/buy/a7uj
			 */
			
			Events[0] = new Event_BuyPoints(2, 0, "http://donate.enderchest.org/buy/a7uu");
			Events[1] = new Event_BuyPoints(3, 0, "http://donate.enderchest.org/buy/a7ud");
			Events[2] = new Event_BuyPoints(4, 0, "http://donate.enderchest.org/buy/a5IG");
			Events[3] = new Event_BuyPoints(5, 0, "http://donate.enderchest.org/buy/a7uj");
			
			Points = new DInventory(Name, null, Items, new InventoryClickLink[0], Events);
			
			OtxPlugin.StoredInventories.put(Name, Points);
		}
		
/*		{
			String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Money";
			
			ItemStack[] Items = new ItemStack[8];
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $1000");
				Lore.add("");
				
				Items[0] = _Convert.ConvertItem(ChatColor.GOLD + "$1000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 250, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $2000");
				Lore.add("");
				
				Items[1] = _Convert.ConvertItem(ChatColor.GOLD + "$2000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 275, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $3000");
				Lore.add("");
				
				Items[2] = _Convert.ConvertItem(ChatColor.GOLD + "$3000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 300, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $4000");
				Lore.add("");
				
				Items[3] = _Convert.ConvertItem(ChatColor.GOLD + "$4000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 325, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $5000");
				Lore.add("");
				
				Items[4] = _Convert.ConvertItem(ChatColor.GOLD + "$5000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 350, false);
			}
			
			SpendEvent[] Events = new SpendEvent[5];
			
			Events[0] = new Event_GiveMoney(0, 250, 1000);
			
			Events[1] = new Event_GiveMoney(1, 275, 2000);
			
			Events[2] = new Event_GiveMoney(2, 300, 3000);
			
			Events[3] = new Event_GiveMoney(3, 325, 4000);
			
			Events[4] = new Event_GiveMoney(4, 350, 5000);
			
			Money = new DInventory(Name, null, Items, new InventoryClickLink[0], Events);
			
			OtxPlugin.StoredInventories.put(Name, Money);
		} */
		
		{
			
		String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Potion Effects";
		
		ItemStack[] Items = new ItemStack[8];
		
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Speed II");
			Lore.add(ChatColor.YELLOW + "for 5 min");
			Lore.add("");
			
			Items[0] = _Convert.ConvertItem(ChatColor.GOLD + "Speed II (5:00)", getPotion(PotionEffectType.SPEED, 300, 2), Lore, 125, false);
		}
		
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Fire Resistance I");
			Lore.add(ChatColor.YELLOW + "for 5 min");
			Lore.add("");
			
			Items[1] = _Convert.ConvertItem(ChatColor.GOLD + "Fire Resistance I (5:00)", getPotion(PotionEffectType.FIRE_RESISTANCE, 300, 1), Lore, 300, false);
		}
		
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Regeneration I");
			Lore.add(ChatColor.YELLOW + "for 5 min");
			Lore.add("");
			
			Items[2] = _Convert.ConvertItem(ChatColor.GOLD + "Regeneration I (5:00)", getPotion(PotionEffectType.REGENERATION, 300, 1), Lore, 300, false);
		}
		
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Strength");
			Lore.add(ChatColor.YELLOW + "for 5 minutes");
			Lore.add("");
			
			Items[3] = _Convert.ConvertItem(ChatColor.GOLD + "Strength (5:00)", getPotion(PotionEffectType.INCREASE_DAMAGE, 300, 1), Lore, 450, false);
		}
		
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Night Vision");
			Lore.add(ChatColor.YELLOW + "for 5 minutes");
			Lore.add("");
			
			Items[4] = _Convert.ConvertItem(ChatColor.GOLD + "Night Vision (5:00)", getPotion(PotionEffectType.NIGHT_VISION, 300, 1), Lore, 50, false);
		}
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Haste");
			Lore.add(ChatColor.YELLOW + "for 5 min");
			Lore.add("");
			
			Items[5] = _Convert.ConvertItem(ChatColor.GOLD + "Haste I (5:00)", getPotion(PotionEffectType.FAST_DIGGING, 300, 1), Lore, 200, false);
		}
		
		{
			List<String> Lore = new ArrayList<String>();
			
			Lore.add("");
			Lore.add(ChatColor.YELLOW + "Give every online player Leaping");
			Lore.add(ChatColor.YELLOW + "for 5 min");
			Lore.add("");
			
			Items[6] = _Convert.ConvertItem(ChatColor.GOLD + "Leaping I (5:00)", getPotion(PotionEffectType.JUMP, 300, 1), Lore, 250, false);
		}
		
		
		SpendEvent[] Events = new SpendEvent[21];
		
		Events[0] = new Event_GivePotionEffect(0, 125, PotionEffectType.SPEED, 300, 2);

		Events[1] = new Event_GivePotionEffect(1, 300, PotionEffectType.FIRE_RESISTANCE, 300, 1);
		
		Events[2] = new Event_GivePotionEffect(2, 300, PotionEffectType.REGENERATION, 300, 1);
		
		
		Events[3] = new Event_GivePotionEffect(3, 450, PotionEffectType.INCREASE_DAMAGE, 300, 1);
		
		Events[4] = new Event_GivePotionEffect(4, 50, PotionEffectType.NIGHT_VISION, 300, 1);
		
		Events[5] = new Event_GivePotionEffect(5, 200, PotionEffectType.FAST_DIGGING, 300, 1);
		
		Events[6] = new Event_GivePotionEffect(6, 250, PotionEffectType.JUMP, 300, 1);
		
		Potions = new DInventory(Name, null, Items, new InventoryClickLink[0], Events);
		
		OtxPlugin.StoredInventories.put(Name, Potions);
	
		}
		
		{
			String Name = ChatColor.BOLD + "" + ChatColor.BLACK + "Server Boost Menu";
			
			ItemStack[] Items = new ItemStack[9];
			
		/*	{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players money");
				Lore.add("");
				Items[0] = _Convert.ConvertItem(ChatColor.GOLD + "Money", new ItemStack(Material.GOLD_INGOT, 1), Lore);
			} */
			
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all online players $1000");
				Lore.add("");
				Items[0] = _Convert.ConvertItem(ChatColor.GOLD + "$1000", new ItemStack(Material.GOLD_INGOT, 1), Lore, 400, false);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all players double "); 
				Lore.add(ChatColor.YELLOW + "xp drops for 5 min");
				Lore.add("");
				
				Items[1] = _Convert.ConvertItem(ChatColor.GOLD + "Double XP for 5 min", new ItemStack(Material.EXP_BOTTLE, 1), Lore, 200, false);
				
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Feed and Heal all players online");
				Lore.add("");
				Items[3] = _Convert.ConvertItem(ChatColor.GREEN + "Feed/Heal Players", new ItemStack(Material.MELON), Lore);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "You currently have" + ChatColor.LIGHT_PURPLE + " %dp%" + ChatColor.YELLOW + " Boost points");
				Lore.add("");
				Lore.add(ChatColor.GRAY + "Boost points are given via donations");
				Lore.add(ChatColor.GRAY + "Click here for the packages");
				Lore.add("");
				Items[8] = _Convert.ConvertItem(ChatColor.GREEN + "Buy Boost Points", new ItemStack(Material.BUCKET), Lore);
			}
			
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give all players double "); 
				Lore.add(ChatColor.YELLOW + "mob and ore drops for 5 min");
				Lore.add("");
				Items[2] = _Convert.ConvertItem(ChatColor.GOLD + "Double drops for 5 min", new ItemStack(Material.IRON_PICKAXE, 1), Lore, 200, false);
			}
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give every online player a variety");
				Lore.add(ChatColor.YELLOW + "of kits");
				Lore.add("");
				Items[5] = _Convert.ConvertItem(ChatColor.GREEN + "Kits", new ItemStack(Material.CHEST), Lore);
			}
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give every online player a variety");
				Lore.add(ChatColor.YELLOW + "of potion effects");
				Lore.add("");
				Items[4] = _Convert.ConvertItem(ChatColor.GREEN + "Potion Effects", new ItemStack(Material.GLASS_BOTTLE), Lore);
			}
			{
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add(ChatColor.YELLOW + "Give every online player a random item");
				Lore.add("");
				Items[6] = _Convert.ConvertItem(ChatColor.GREEN + "Random Item", new ItemStack(Material.RED_MUSHROOM), Lore, 500, false);
			}
			
			InventoryClickLink[] Clicks = new InventoryClickLink[8];
			
	//		Clicks[0] = new InventoryClickLink(0, Money);
			Clicks[0] = new InventoryClickLink(3, Health);
			Clicks[1] = new InventoryClickLink(4, Potions);
	//		Clicks[2] = new InventoryClickLink(5, Kits);
			Clicks[3] = new InventoryClickLink(8, Points);
			
			SpendEvent[] Events = new SpendEvent[4];
			
			Events[0] = new Event_GiveMoney(0, 400, 1000);
			Events[1] = new Event_IncreaseXPDrops(1, 200, 5, 2);
			Events[2] = new Event_IncreaseMobOreDrops(2, 200, 5, 2);
			Events[3] = new Event_RandomItem(6, 500);
	
			Default = new DInventory(Name, null, Items, Clicks, Events);
			
			OtxPlugin.StoredInventories.put(Name, Default);
		
		}
		
		Health.setReturnTo(Default);
		Potions.setReturnTo(Default);
//		Money.setReturnTo(Default);
	//	Kits.setReturnTo(Default);
		Points.setReturnTo(Default);

	}
	
	private static ItemStack getPotion(PotionEffectType pet, Integer Duration, Integer Level) {
		
		ItemStack Potion = new ItemStack(Material.POTION);
		Potion.setDurability(getDura(pet));
		
		PotionMeta meta = (PotionMeta) Potion.getItemMeta(); 
		meta.setMainEffect(pet);
		PotionEffect effect = new PotionEffect(pet, (Duration * 20), (Level-1));
		meta.clearCustomEffects();
		meta.addCustomEffect(effect, true); 
		Potion.setItemMeta(meta);
		
		return Potion;
	}
	
	private static short getDura(PotionEffectType pet) {
		
		if (pet.getName().equals("ABSORPTION"))
			return 0;
		if (pet.getName().equals("BLINDNESS"))
			return 0;
		if (pet.getName().equals("CONFUSION"))
			return 0;
		if (pet.getName().equals("DAMAGE_RESISTANCE"))
			return 0; 
		if (pet.getName().equals("FAST_DIGGING"))
			return 0;
		if (pet.getName().equals("FIRE_RESISTANCE"))
			return 8195;
		if (pet.getName().equals("HEALTH_BOOST"))
			return 8197;
		if (pet.getName().equals("HUNGER"))
			return 0;
		if (pet.getName().equals("INCREASE_DAMAGE"))
			return 8201;
		if (pet.getName().equals("INVISIBILITY"))
			return 8206;
		if (pet.getName().equals("JUMP"))
			return 8235;
		if (pet.getName().equals("NIGHT_VISION"))
			return 8198;
		if (pet.getName().equals("POISON"))
			return 8196;
		if (pet.getName().equals("REGENERATION"))
			return 8193;
		if (pet.getName().equals("SATURATION"))
			return 0;
		if (pet.getName().equals("SLOW"))
			return 8202;
		if (pet.getName().equals("SLOW_DIGGING"))
			return 0;
		if (pet.getName().equals("SPEED"))
			return 8194;
		if (pet.getName().equals("WATER_BREATHING"))
			return 8205;
		if (pet.getName().equals("WEAKNESS"))
			return 8200;
		if (pet.getName().equals("WITHER"))
			return 0;
		
		return 0;

	} 
}
