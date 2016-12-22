package me.flash1110.Otxplugin.donor.events;

import java.util.Random;

import me.flash1110.Otxplugin.OtxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Event_RandomItem extends SpendEvent {

	Integer slot;
	
	Long LastUsed;
	
	public Event_RandomItem(Integer slot, Integer cost) {
		super(slot, cost);
		LastUsed = (long) 0;
	}
	
	@Override
	public void run(Player player) {
		LastUsed = System.currentTimeMillis();
		for (Player P : Bukkit.getOnlinePlayers()) {
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		}
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " gives everyone a Random Item");
		for (Player P : Bukkit.getOnlinePlayers()) {
			GivePresent(P);
		}
	}
	
	@Override
	public Boolean check(Player player) {

		Long difference = System.currentTimeMillis() - LastUsed;
		
		if (difference < 60000) {
			
			Integer secondsgone = 0;
			
			while (difference >= 1000) {
				secondsgone++;
				difference-= 1000;
			}
			
			Integer secondsleft = 60 - secondsgone;
			
			player.sendMessage(ChatColor.RED + "Everyone was recently given an item " + ChatColor.YELLOW + secondsgone + ChatColor.RED + " second ago");
			player.sendMessage(ChatColor.RED + "Please wait " + ChatColor.YELLOW + secondsleft + ChatColor.RED + " seconds before trying again");
			
			return false;
		}
		
		return true;
		
	}
	
	public static void GivePresent(Player player)
	  {
	    int STARTpick = 1;
	    int ENDpick = 146;
	    Random randompick = new Random();
	    long rangepick = ENDpick - STARTpick + 1L;
	    
	    long fractionpick = (long) (rangepick * randompick.nextDouble());
	    int randomNumberpick = (int)(fractionpick + STARTpick);
	    
	    int itemid = 1;
	    int subid = 0;
	    String enchant1 = "";
	    String enchant2 = "";
	    String enchant3 = "";
	    int enchant1level = 0;
	    int enchant2level = 0;
	    int enchant3level = 0;
	    
	    String itemname = "";
	    
	    int amount = 0;
	    
	    int STARTpick1 = 1;
	    int ENDpick1 = 1;
	    if (randomNumberpick == 1)
	    {
	      itemid = 30;
	      ENDpick1 = 64;
	      itemname = "Web";
	    }
	    if (randomNumberpick == 2)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 0;
	      itemname = "White Wool";
	    }
	    if (randomNumberpick == 3)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 1;
	      itemname = "Orange Wool";
	    }
	    if (randomNumberpick == 4)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 2;
	      itemname = "Magenta Wool";
	    }
	    if (randomNumberpick == 5)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 3;
	      itemname = "Light Blue Wool";
	    }
	    if (randomNumberpick == 6)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 4;
	      itemname = "Yellow Wool";
	    }
	    if (randomNumberpick == 7)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 5;
	      itemname = "Lime Wool";
	    }
	    if (randomNumberpick == 8)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 6;
	      itemname = "Pink Wool";
	    }
	    if (randomNumberpick == 9)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 7;
	      itemname = "Dark Gray Wool";
	    }
	    if (randomNumberpick == 10)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 8;
	      itemname = "Gray Wool";
	    }
	    if (randomNumberpick == 11)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 9;
	      itemname = "Cyan Wool";
	    }
	    if (randomNumberpick == 12)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 10;
	      itemname = "Purple Wool";
	    }
	    if (randomNumberpick == 13)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 11;
	      itemname = "Dark Blue Wool";
	    }
	    if (randomNumberpick == 14)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 12;
	      itemname = "Brown Wool";
	    }
	    if (randomNumberpick == 15)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 13;
	      itemname = "Dark Green Wool";
	    }
	    if (randomNumberpick == 16)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 14;
	      itemname = "Red Wool";
	    }
	    if (randomNumberpick == 17)
	    {
	      itemid = 35;
	      ENDpick1 = 64;
	      subid = 15;
	      itemname = "Black Wool";
	    }
	    if (randomNumberpick == 18)
	    {
	      itemid = 41;
	      ENDpick1 = 3;
	      itemname = "Gold Block";
	    }
	    if (randomNumberpick == 19)
	    {
	      itemid = 42;
	      ENDpick1 = 6;
	      itemname = "Iron Block";
	    }
	    if (randomNumberpick == 20)
	    {
	      itemid = 46;
	      ENDpick1 = 32;
	      itemname = "TNT";
	    }
	    if (randomNumberpick == 21)
	    {
	      itemid = 47;
	      ENDpick1 = 18;
	      itemname = "Bookshelf";
	    }
	    if (randomNumberpick == 22)
	    {
	      itemid = 48;
	      ENDpick1 = 64;
	      itemname = "Mossy Cobble";
	    }
	    if (randomNumberpick == 23)
	    {
	      itemid = 49;
	      ENDpick1 = 48;
	      itemname = "Obsidian";
	    }
	    if (randomNumberpick == 24)
	    {
	      itemid = 57;
	      ENDpick1 = 1;
	      itemname = "Diamond Block";
	    }
	    if (randomNumberpick == 25)
	    {
	      itemid = 79;
	      ENDpick1 = 64;
	      itemname = "Ice";
	    }
	    if (randomNumberpick == 26)
	    {
	      itemid = 80;
	      ENDpick1 = 64;
	      itemname = "Snow Block";
	    }
	    if (randomNumberpick == 27)
	    {
	      itemid = 86;
	      ENDpick1 = 64;
	      itemname = "Pumpkin";
	    }
	    if (randomNumberpick == 28)
	    {
	      itemid = 89;
	      ENDpick1 = 64;
	      itemname = "Lightstone";
	    }
	    if (randomNumberpick == 29)
	    {
	      itemid = 91;
	      ENDpick1 = 64;
	      itemname = "Jack O Lanturn";
	    }
	    if (randomNumberpick == 30)
	    {
	      itemid = 103;
	      ENDpick1 = 64;
	      itemname = "Melon Block";
	    }
	    if (randomNumberpick == 31)
	    {
	      itemid = 116;
	      ENDpick1 = 2;
	      itemname = "Enchantment Table";
	    }
	    if (randomNumberpick == 32)
	    {
	      itemid = 133;
	      ENDpick1 = 1;
	      itemname = "Emerald Block";
	    }
	    if (randomNumberpick == 33)
	    {
	      itemid = 138;
	      ENDpick1 = 1;
	      itemname = "Beacon";
	    }
	    if (randomNumberpick == 34)
	    {
	      itemid = 256;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 2;
	      enchant2 = "fortune";
	      enchant2level = 1;
	      itemname = "Iron Spade &4(&aDura&7-&dII&6|&aFortune&7-&dI&4)";
	    }
	    if (randomNumberpick == 35)
	    {
	      itemid = 256;
	      ENDpick1 = 1;
	      enchant1 = "fortune";
	      enchant1level = 2;
	      itemname = "Iron Spade &4(&aFortune&7-&dI&4)";
	    }
	    if (randomNumberpick == 36)
	    {
	      itemid = 256;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      itemname = "Iron Spade &4(&aDura&7-&dIII&4)";
	    }
	    if (randomNumberpick == 37)
	    {
	      itemid = 257;
	      ENDpick1 = 1;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      enchant2 = "silktouch";
	      enchant2level = 1;
	      itemname = "Iron Pick &4(&aDura&7-&eIII&6|&aSilkTouch&7-&dIII&4)";
	    }
	    if (randomNumberpick == 38)
	    {
	      itemid = 257;
	      ENDpick1 = 1;
	      enchant1 = "fortune";
	      enchant1level = 2;
	      itemname = "Iron Pick &4(&aFortune&7-&dII&4)";
	    }
	    if (randomNumberpick == 39)
	    {
	      itemid = 258;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      itemname = "Iron Axe &4(&aDura&7-&dIII&4)";
	    }
	    if (randomNumberpick == 40)
	    {
	      itemid = 258;
	      ENDpick1 = 1;
	      enchant1 = "fortune";
	      enchant1level = 2;
	      itemname = "Iron Axe &4(&aFortune&7-&dII&4)";
	    }
	    if (randomNumberpick == 41)
	    {
	      itemid = 261;
	      ENDpick1 = 1;
	      enchant1 = "infinity";
	      enchant1level = 1;
	      enchant2 = "flame";
	      enchant2level = 1;
	      itemname = "Bow &4(&aInfinity&7-&dI&6|&aFlame&7-&dI&4)";
	    }
	    if (randomNumberpick == 42)
	    {
	      itemid = 261;
	      ENDpick1 = 1;
	      enchant1 = "power";
	      enchant1level = 1;
	      enchant2 = "flame";
	      enchant2level = 1;
	      itemname = "Bow &4(&aPOwer&7-&dI&6|&aFlame&7-&dI&4)";
	    }
	    if (randomNumberpick == 43)
	    {
	      itemid = 261;
	      ENDpick1 = 1;
	      enchant1 = "power";
	      enchant1level = 1;
	      itemname = "Bow &4(&aInfinity&7-&dI&6|&aFlame&7-&dI&4)";
	    }
	    if (randomNumberpick == 44)
	    {
	      itemid = 261;
	      ENDpick1 = 1;
	      enchant1 = "punch";
	      enchant1level = 1;
	      enchant2 = "flame";
	      enchant2level = 1;
	      itemname = "Bow &4(&aPunch&7-&dI&6|&aFlame&7-&dII&4)";
	    }
	    if (randomNumberpick == 45)
	    {
	      itemid = 264;
	      ENDpick1 = 10;
	      itemname = "Diamond";
	    }
	    if (randomNumberpick == 46)
	    {
	      itemid = 265;
	      ENDpick1 = 16;
	      itemname = "Iron Ingot";
	    }
	    if (randomNumberpick == 47)
	    {
	      itemid = 266;
	      ENDpick1 = 24;
	      itemname = "Gold Ingot";
	    }
	    if (randomNumberpick == 48)
	    {
	      itemid = 267;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 2;
	      enchant2 = "fire";
	      enchant2level = 1;
	      itemname = "Iron Sword &4(&aSharp&7-&dII&6|&aFire&7-&dII&4)";
	    }
	    if (randomNumberpick == 49)
	    {
	      itemid = 267;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 2;
	      enchant2 = "fire";
	      enchant2level = 2;
	      enchant3 = "knockback";
	      enchant3level = 2;
	      itemname = "Iron Sword &4(&aSharp&7-&dII&6|&aFire&7-&dII&6|&aKB&7-&dII&4)";
	    }
	    if (randomNumberpick == 50)
	    {
	      itemid = 276;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 1;
	      itemname = "Diamond Sword &4(&aSharp&7-&dI&4)";
	    }
	    if (randomNumberpick == 51)
	    {
	      itemid = 276;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 1;
	      enchant2 = "fire";
	      enchant2level = 2;
	      enchant2 = "knockback";
	      enchant2level = 2;
	      itemname = "Diamond Sword &4(&aSharp&7-&dI&6|&aFire&7-&dII&6|&aKB&7-&dII&4)";
	    }
	    if (randomNumberpick == 52)
	    {
	      itemid = 276;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 1;
	      enchant2 = "fire";
	      enchant2level = 2;
	      enchant2 = "knockback";
	      enchant2level = 2;
	      itemname = "Diamond Sword &4(&aSharp&7-&dI&6|&aFire&7-&dII&6|&aKB&7-&dII&4)";
	    }
	    if (randomNumberpick == 53)
	    {
	      itemid = 277;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      itemname = "Diamond Shovel &4(&Dura&7-&dIII&4)";
	    }
	    if (randomNumberpick == 54)
	    {
	      itemid = 278;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 2;
	      enchant2 = "fortune";
	      enchant2level = 2;
	      itemname = "Diamond Pick &4(&aDura&7-&dII&6|&aFortune&7-&dII&4)";
	    }
	    if (randomNumberpick == 55)
	    {
	      itemid = 278;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      enchant2 = "silktouch";
	      enchant2level = 1;
	      itemname = "Diamond Pick &4(&aDura&7-&dIII&6|&aSilkTouch&7-&dI&4)";
	    }
	    if (randomNumberpick == 56)
	    {
	      itemid = 278;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      enchant2 = "fortune";
	      enchant2level = 2;
	      itemname = "Diamond Pick &4(&aDura&7-&dIII&6|&aFortune&7-&dII&4)";
	    }
	    if (randomNumberpick == 57)
	    {
	      itemid = 279;
	      ENDpick1 = 1;
	      enchant1 = "dura";
	      enchant1level = 3;
	      itemname = "Diamond Axe &4(&aDura&7-&dIII&4)";
	    }
	    if (randomNumberpick == 58)
	    {
	      itemid = 283;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 3;
	      enchant2 = "fire";
	      enchant2level = 2;
	      itemname = "Gold Sword &4(&aSharp&7-&dIII&6|&aFire&7-&dII&4)";
	    }
	    if (randomNumberpick == 59)
	    {
	      itemid = 283;
	      ENDpick1 = 1;
	      enchant1 = "sharp";
	      enchant1level = 3;
	      enchant2 = "knockback";
	      enchant2level = 2;
	      itemname = "Gold Sword &4(&aSharp&7-&dIII&6|&aKB&7-&dII&4)";
	    }
	    if (randomNumberpick == 60)
	    {
	      itemid = 289;
	      ENDpick1 = 64;
	      itemname = "Sulphur";
	    }
	    if (randomNumberpick == 61)
	    {
	      itemid = 293;
	      ENDpick1 = 1;
	      itemname = "Diamond Hoe";
	    }
	    if (randomNumberpick == 62)
	    {
	      itemid = 295;
	      ENDpick1 = 64;
	      itemname = "Seeds";
	    }
	    if (randomNumberpick == 63)
	    {
	      itemid = 302;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Chainmail Helmet &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 64)
	    {
	      itemid = 303;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Chainmail Chestplate &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 65)
	    {
	      itemid = 304;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Chainmail Leggings &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 66)
	    {
	      itemid = 305;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Chainmail Boots &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 67)
	    {
	      itemid = 306;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Iron Helmet &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 68)
	    {
	      itemid = 307;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Iron Chestplate &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 69)
	    {
	      itemid = 308;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Iron Leggings &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 70)
	    {
	      itemid = 309;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 2;
	      itemname = "Iron Boots &4(&aProt&7-&dII&4)";
	    }
	    if (randomNumberpick == 71)
	    {
	      itemid = 310;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 1;
	      itemname = "Diamond Helmet &4(&aProt&7-&dI&4)";
	    }
	    if (randomNumberpick == 72)
	    {
	      itemid = 311;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 1;
	      itemname = "Diamond Chestplate &4(&aProt&7-&dI&4)";
	    }
	    if (randomNumberpick == 73)
	    {
	      itemid = 312;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 1;
	      itemname = "Diamond Leggings &4(&aProt&7-&dI&4)";
	    }
	    if (randomNumberpick == 74)
	    {
	      itemid = 313;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 1;
	      itemname = "Diamond Boots &4(&aProt&7-&dI&4)";
	    }
	    if (randomNumberpick == 75)
	    {
	      itemid = 314;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 3;
	      enchant2 = "fireprot";
	      enchant2level = 4;
	      itemname = "Gold Helmet &4(&aProt&7-&dIII&6|&aFireProt&7-&dIV&4)";
	    }
	    if (randomNumberpick == 76)
	    {
	      itemid = 315;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 4;
	      enchant2 = "fireprot";
	      enchant2level = 4;
	      itemname = "Gold Chestplate &4(&aProt&7-&dIV&6|&aFireProt&7-&dIV&4)";
	    }
	    if (randomNumberpick == 77)
	    {
	      itemid = 316;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 4;
	      enchant2 = "fireprot";
	      enchant2level = 4;
	      itemname = "Gold Leggings &4(&aProt&7-&dIV&6|&aFireProt&7-&dIV&4)";
	    }
	    if (randomNumberpick == 78)
	    {
	      itemid = 317;
	      ENDpick1 = 1;
	      enchant1 = "prot";
	      enchant1level = 4;
	      enchant2 = "fireprot";
	      enchant2level = 4;
	      itemname = "Gold Boots &4(&aProt&7-&dIV&6|&aFireProt&7-&dIV&4)";
	    }
	    if (randomNumberpick == 79)
	    {
	      itemid = 321;
	      ENDpick1 = 64;
	      itemname = "Painting";
	    }
	    if (randomNumberpick == 80)
	    {
	      itemid = 322;
	      ENDpick1 = 1;
	      itemname = "Golden Apple";
	    }
	    if (randomNumberpick == 81)
	    {
	      itemid = 116;
	      ENDpick1 = 3;
	      itemname = "Enchantment Table";
	    }
	    if (randomNumberpick == 82)
	    {
	      itemid = 326;
	      ENDpick1 = 32;
	      itemname = "Water Bucket";
	    }
	    if (randomNumberpick == 83)
	    {
	      itemid = 327;
	      ENDpick1 = 32;
	      itemname = "Lava Bucket";
	    }
	    if (randomNumberpick == 84)
	    {
	      itemid = 331;
	      ENDpick1 = 64;
	      itemname = "Redstone";
	    }
	    if (randomNumberpick == 85)
	    {
	      itemid = 332;
	      ENDpick1 = 64;
	      itemname = "Snowball";
	    }
	    if (randomNumberpick == 86)
	    {
	      itemid = 334;
	      ENDpick1 = 64;
	      itemname = "Leather";
	    }
	    if (randomNumberpick == 87)
	    {
	      itemid = 335;
	      ENDpick1 = 32;
	      itemname = "Milk Bucket";
	    }
	    if (randomNumberpick == 88)
	    {
	      itemid = 341;
	      ENDpick1 = 64;
	      itemname = "Slime Ball";
	    }
	    if (randomNumberpick == 89)
	    {
	      itemid = 357;
	      ENDpick1 = 64;
	      itemname = "Cookie";
	    }
	    if (randomNumberpick == 90)
	    {
	      itemid = 361;
	      ENDpick1 = 64;
	      itemname = "Pumpkin Seeds";
	    }
	    if (randomNumberpick == 91)
	    {
	      itemid = 362;
	      ENDpick1 = 64;
	      itemname = "Melon Seeds";
	    }
	    if (randomNumberpick == 92)
	    {
	      itemid = 368;
	      ENDpick1 = 10;
	      itemname = "Enderpearl";
	    }
	    if (randomNumberpick == 93)
	    {
	      itemid = 369;
	      ENDpick1 = 28;
	      itemname = "Blaze Rod";
	    }
	    if (randomNumberpick == 94)
	    {
	      itemid = 370;
	      ENDpick1 = 3;
	      itemname = "Ghast Tear";
	    }
	    if (randomNumberpick == 95)
	    {
	      itemid = 372;
	      ENDpick1 = 35;
	      itemname = "Nether Wart";
	    }
	    if (randomNumberpick == 96)
	    {
	      itemid = 378;
	      ENDpick1 = 10;
	      itemname = "Magma Cream";
	    }
	    if (randomNumberpick == 97)
	    {
	      itemid = 382;
	      ENDpick1 = 30;
	      itemname = "Glistening Melon";
	    }
	    if (randomNumberpick == 98)
	    {
	      itemid = 383;
	      subid = 50;
	      ENDpick1 = 2;
	      itemname = "Creeper Spawn Egg";
	    }
	    if (randomNumberpick == 99)
	    {
	      itemid = 383;
	      subid = 51;
	      ENDpick1 = 5;
	      itemname = "Skeleton Spawn Egg";
	    }
	    if (randomNumberpick == 100)
	    {
	      itemid = 383;
	      subid = 52;
	      ENDpick1 = 5;
	      itemname = "Spider Spawn Egg";
	    }
	    if (randomNumberpick == 101)
	    {
	      itemid = 383;
	      subid = 54;
	      ENDpick1 = 5;
	      itemname = "Zombie Spawn Egg";
	    }
	    if (randomNumberpick == 102)
	    {
	      itemid = 383;
	      subid = 55;
	      ENDpick1 = 5;
	      itemname = "Slime Spawn Egg";
	    }
	    if (randomNumberpick == 103)
	    {
	      itemid = 383;
	      subid = 57;
	      ENDpick1 = 5;
	      itemname = "Pigman Spawn Egg";
	    }
	    if (randomNumberpick == 104)
	    {
	      itemid = 383;
	      subid = 59;
	      ENDpick1 = 5;
	      itemname = "Cave Spider Spawn Egg";
	    }
	    if (randomNumberpick == 105)
	    {
	      itemid = 383;
	      subid = 65;
	      ENDpick1 = 1;
	      itemname = "Bat Spawn Egg";
	    }
	    if (randomNumberpick == 106)
	    {
	      itemid = 383;
	      subid = 66;
	      ENDpick1 = 2;
	      itemname = "Witch Spawn Egg";
	    }
	    if (randomNumberpick == 107)
	    {
	      itemid = 383;
	      subid = 90;
	      ENDpick1 = 5;
	      itemname = "Pig Spawn Egg";
	    }
	    if (randomNumberpick == 108)
	    {
	      itemid = 383;
	      subid = 91;
	      ENDpick1 = 5;
	      itemname = "Sheep Spawn Egg";
	    }
	    if (randomNumberpick == 109)
	    {
	      itemid = 383;
	      subid = 92;
	      ENDpick1 = 5;
	      itemname = "Cow Spawn Egg";
	    }
	    if (randomNumberpick == 110)
	    {
	      itemid = 383;
	      subid = 93;
	      ENDpick1 = 6;
	      itemname = "Chicken Spawn Egg";
	    }
	    if (randomNumberpick == 111)
	    {
	      itemid = 383;
	      subid = 94;
	      ENDpick1 = 3;
	      itemname = "Squid Spawn Egg";
	    }
	    if (randomNumberpick == 112)
	    {
	      itemid = 383;
	      subid = 95;
	      ENDpick1 = 3;
	      itemname = "Wolf Spawn Egg";
	    }
	    if (randomNumberpick == 113)
	    {
	      itemid = 383;
	      subid = 96;
	      ENDpick1 = 2;
	      itemname = "Mooshroom Spawn Egg";
	    }
	    if (randomNumberpick == 114)
	    {
	      itemid = 383;
	      subid = 98;
	      ENDpick1 = 1;
	      itemname = "Ocelot Spawn Egg";
	    }
	    if (randomNumberpick == 115)
	    {
	      itemid = 383;
	      subid = 120;
	      ENDpick1 = 1;
	      itemname = "Villager Spawn Egg";
	    }
	    if (randomNumberpick == 116)
	    {
	      itemid = 384;
	      ENDpick1 = 28;
	      itemname = "Exp Bottle";
	    }
	    if (randomNumberpick == 117)
	    {
	      itemid = 388;
	      ENDpick1 = 2;
	      itemname = "Emerald";
	    }
	    if (randomNumberpick == 118)
	    {
	      itemid = 389;
	      ENDpick1 = 3;
	      itemname = "Item Frame";
	    }
	    if (randomNumberpick == 119)
	    {
	      itemid = 391;
	      ENDpick1 = 32;
	      itemname = "Carrot";
	    }
	    if (randomNumberpick == 120)
	    {
	      itemid = 392;
	      ENDpick1 = 64;
	      itemname = "Potato";
	    }
	    if (randomNumberpick == 121)
	    {
	      itemid = 396;
	      ENDpick1 = 8;
	      itemname = "Golden Carrot";
	    }
	    if (randomNumberpick == 122)
	    {
	      itemid = 399;
	      ENDpick1 = 1;
	      itemname = "Nether Star";
	    }
	    if (randomNumberpick == 123)
	    {
	      itemid = 2256;
	      ENDpick1 = 4;
	      itemname = "Golden Disk";
	    }
	    if (randomNumberpick == 124)
	    {
	      itemid = 2257;
	      ENDpick1 = 4;
	      itemname = "Green Disk";
	    }
	    if (randomNumberpick == 125)
	    {
	      itemid = 2258;
	      ENDpick1 = 4;
	      itemname = "Orange Disk";
	    }
	    if (randomNumberpick == 126)
	    {
	      itemid = 2259;
	      ENDpick1 = 4;
	      itemname = "Red Disk";
	    }
	    if (randomNumberpick == 127)
	    {
	      itemid = 2260;
	      ENDpick1 = 4;
	      itemname = "Green/White Disk";
	    }
	    if (randomNumberpick == 128)
	    {
	      itemid = 2261;
	      ENDpick1 = 4;
	      itemname = "Purple Disk";
	    }
	    if (randomNumberpick == 129)
	    {
	      itemid = 2262;
	      ENDpick1 = 4;
	      itemname = "Pink/White Disk";
	    }
	    if (randomNumberpick == 130)
	    {
	      itemid = 2263;
	      ENDpick1 = 4;
	      itemname = "Black Disk";
	    }
	    if (randomNumberpick == 131)
	    {
	      itemid = 2264;
	      ENDpick1 = 4;
	      itemname = "White Disk";
	    }
	    if (randomNumberpick == 132)
	    {
	      itemid = 2265;
	      ENDpick1 = 4;
	      itemname = "Dark Green/Lime Disk";
	    }
	    if (randomNumberpick == 133)
	    {
	      itemid = 2266;
	      ENDpick1 = 4;
	      itemname = "Black Broken Disk";
	    }
	    if (randomNumberpick == 134)
	    {
	      itemid = 2267;
	      ENDpick1 = 4;
	      itemname = "Teal Disk";
	    }
	    if (randomNumberpick == 135)
	    {
	      itemid = 419;
	      ENDpick1 = 1;
	      itemname = "Diamond Horse Armor";
	    }
	    if (randomNumberpick == 136)
	    {
	      itemid = 418;
	      ENDpick1 = 1;
	      itemname = "Gold Horse Armor";
	    }
	    if (randomNumberpick == 137)
	    {
	      itemid = 416;
	      ENDpick1 = 1;
	      itemname = "Iron Horse Armor";
	    }
	    if (randomNumberpick == 138)
	    {
	      itemid = 81;
	      ENDpick1 = 43;
	      itemname = "Cactus";
	    }
	    if (randomNumberpick == 139)
	    {
	      itemid = 201;
	      ENDpick1 = 8;
	      itemname = "Purpur Block";
	    }
	    if (randomNumberpick == 140)
	    {
	      itemid = 46;
	      ENDpick1 = 48;
	      itemname = "TNT";
	    }
	    if (randomNumberpick == 141)
	    {
	      itemid = 383;
	      subid = 100;
	      ENDpick1 = 1;
	      itemname = "Horse Spawn Egg";
	    }  
	    if (randomNumberpick == 142) {
	    	itemid = 260;
	    	ENDpick = 1;
	    	itemname = "&eFlash's Apple";
	    }
	    if (randomNumberpick == 143) {
	    	itemid = 145;
	    	ENDpick = 5;
	    	itemname = "Anvil";
	    }
	    if (randomNumberpick == 144) {
	    	itemid = 130;
	    	ENDpick = 1;
	    	itemname = "&6&lEnderchest of Legend";
	    }
	    if (randomNumberpick == 145) {
	    	itemid = 47;
	    	subid = 0;
	    	ENDpick = 12;
	    	itemname = "Bookshelves";
	    }
	    if (randomNumberpick == 146) {
	    	itemid = 145;
	    	subid = 2;
	    	ENDpick = 10;
	    	itemname = "&eAnvil of Damage";
	    }
	    
	    Random randompick1 = new Random();
	    long rangepick1 = ENDpick1 - STARTpick1 + 1L;
	    
	    long fractionpick1 = (long) (rangepick1 * randompick1.nextDouble());
	    int randomNumberpick1 = (int)(fractionpick1 + STARTpick1);
	    
	    amount = randomNumberpick1;
	    
	    ItemStack itemtogive = new ItemStack(itemid, amount);
	    if (subid > 0) {
	      itemtogive.setDurability((short)subid);
	    }
	    if (enchant1 == "fireprot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_FIRE, 
	        enchant1level);
	    }
	    if (enchant1 == "prot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 
	        enchant1level);
	    }
	    if (enchant1 == "power") {
	      itemtogive.addEnchantment(Enchantment.ARROW_DAMAGE, enchant1level);
	    }
	    if (enchant1 == "knockback") {
	      itemtogive.addEnchantment(Enchantment.KNOCKBACK, enchant1level);
	    }
	    if (enchant1 == "fire") {
	      itemtogive.addEnchantment(Enchantment.FIRE_ASPECT, enchant1level);
	    }
	    if (enchant1 == "dura") {
	      itemtogive.addEnchantment(Enchantment.DURABILITY, enchant1level);
	    }
	    if (enchant1 == "fortune") {
	      itemtogive.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 
	        enchant1level);
	    }
	    if (enchant1 == "silktouch") {
	      itemtogive.addEnchantment(Enchantment.SILK_TOUCH, enchant1level);
	    }
	    if (enchant1 == "sharp") {
	      itemtogive.addEnchantment(Enchantment.DAMAGE_ALL, enchant1level);
	    }
	    if (enchant1 == "flame") {
	      itemtogive.addEnchantment(Enchantment.ARROW_FIRE, enchant1level);
	    }
	    if (enchant1 == "punch") {
	      itemtogive.addEnchantment(Enchantment.ARROW_KNOCKBACK, 
	        enchant1level);
	    }
	    if (enchant1 == "infinity") {
	      itemtogive.addEnchantment(Enchantment.ARROW_INFINITE, enchant1level);
	    }
	    if (enchant2 == "fireprot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_FIRE, 
	        enchant2level);
	    }
	    if (enchant2 == "prot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 
	        enchant2level);
	    }
	    if (enchant2 == "power") {
	      itemtogive.addEnchantment(Enchantment.ARROW_DAMAGE, enchant2level);
	    }
	    if (enchant2 == "knockback") {
	      itemtogive.addEnchantment(Enchantment.KNOCKBACK, enchant2level);
	    }
	    if (enchant2 == "fire") {
	      itemtogive.addEnchantment(Enchantment.FIRE_ASPECT, enchant2level);
	    }
	    if (enchant2 == "dura") {
	      itemtogive.addEnchantment(Enchantment.DURABILITY, enchant2level);
	    }
	    if (enchant2 == "fortune") {
	      itemtogive.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 
	        enchant2level);
	    }
	    if (enchant2 == "silktouch") {
	      itemtogive.addEnchantment(Enchantment.SILK_TOUCH, enchant2level);
	    }
	    if (enchant2 == "sharp") {
	      itemtogive.addEnchantment(Enchantment.DAMAGE_ALL, enchant2level);
	    }
	    if (enchant2 == "flame") {
	      itemtogive.addEnchantment(Enchantment.ARROW_FIRE, enchant2level);
	    }
	    if (enchant2 == "punch") {
	      itemtogive.addEnchantment(Enchantment.ARROW_KNOCKBACK, 
	        enchant2level);
	    }
	    if (enchant2 == "infinity") {
	      itemtogive.addEnchantment(Enchantment.ARROW_INFINITE, enchant2level);
	    }
	    if (enchant3 == "fireprot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_FIRE, 
	        enchant3level);
	    }
	    if (enchant3 == "prot") {
	      itemtogive.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 
	        enchant3level);
	    }
	    if (enchant3 == "power") {
	      itemtogive.addEnchantment(Enchantment.ARROW_DAMAGE, enchant3level);
	    }
	    if (enchant3 == "knockback") {
	      itemtogive.addEnchantment(Enchantment.KNOCKBACK, enchant3level);
	    }
	    if (enchant3 == "fire") {
	      itemtogive.addEnchantment(Enchantment.FIRE_ASPECT, enchant3level);
	    }
	    if (enchant3 == "dura") {
	      itemtogive.addEnchantment(Enchantment.DURABILITY, enchant3level);
	    }
	    if (enchant3 == "fortune") {
	      itemtogive.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 
	        enchant3level);
	    }
	    if (enchant3 == "silktouch") {
	      itemtogive.addEnchantment(Enchantment.SILK_TOUCH, enchant3level);
	    }
	    if (enchant3 == "sharp") {
	      itemtogive.addEnchantment(Enchantment.DAMAGE_ALL, enchant3level);
	    }
	    if (enchant3 == "flame") {
	      itemtogive.addEnchantment(Enchantment.ARROW_FIRE, enchant3level);
	    }
	    if (enchant3 == "punch") {
	      itemtogive.addEnchantment(Enchantment.ARROW_KNOCKBACK, 
	        enchant3level);
	    }
	    if (enchant3 == "infinity") {
	      itemtogive.addEnchantment(Enchantment.ARROW_INFINITE, enchant3level);
	    }
	    player.getInventory().addItem(new ItemStack[] { itemtogive });
	    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lYOU GOT:") + " " + ChatColor.GREEN + amount + " " + ChatColor.translateAlternateColorCodes('&', itemname));
	  }
}
