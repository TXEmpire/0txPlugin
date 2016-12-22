package me.flash1110.Otxplugin;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {

	
	public static void givePresent(Player p) {
		 int STARTpick = 1;
		    int ENDpick = 13;
		    Random randompick = new Random();
		    long rangepick = ENDpick + STARTpick + 1L;
		    
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
		    
		    /*
		     * Stick: Noob Beating stick
			   Rose: For a very special someone
			   Torch: Safety Torch
			   Painting: Decorate your house!		   
			   Seeds: Go plant a garden!
			   Ladder: Climb to new heights!
			   Jackolantern: Angry pumpkin 
			   Mushroom: Eat this and be like Mario!
			   Boat: Row row row your boat!
			   Flint/steel: HEHE FIRE!!!!
			   String: If you find yourself in a labyrinth…
			   Brick: Build a wall
			   Potato: Chad_Bonogees
		     */
		    
		    if (randomNumberpick == 0) {
		    	itemid = 54;
		    	ENDpick1 = 5;
		    	itemname = "Store your loot!";
		    }
		    
		    if (randomNumberpick == 1) {
		    	itemid = 280;
		    	ENDpick1 = 1;
		    	itemname = "Noob Beating Stick";
		    }
		    if (randomNumberpick == 2) {
		    	itemid = 38;
		    	ENDpick1 = 1;
		    	itemname = "For a very special someone";
		    }
		    if (randomNumberpick == 3) {
		    	itemid = 50;
		    	ENDpick1 = 1;
		    	itemname = "Safety Torch";
		    }
		    if (randomNumberpick == 4) {
		    	itemid = 321;
		    	ENDpick1 = 1;
		    	itemname = "Decorate your house!";
		    }
		    if (randomNumberpick == 5) {
		    	itemid = 295;
		    	ENDpick1 = 1;
		    	itemname = "Go plant a garden!";
		    }
		    if (randomNumberpick == 6) {
		    	itemid = 65;
		    	ENDpick1 = 1;
		    	itemname = "Climb to new heights!";
		    }
		    if (randomNumberpick == 7) {
		    	itemid = 91;
		    	ENDpick1 = 1;
		    	itemname = "Angry pumpkin";
		    }
		    if (randomNumberpick == 8) {
		    	itemid = 40;
		    	ENDpick1 = 1;
		    	itemname = "Eat this and be like Mario!";
		    }
		    if (randomNumberpick == 9) {
		    	itemid = 333;
		    	ENDpick1 = 1;
		    	itemname = "Row, row, row your boat";
		    }
		    if (randomNumberpick == 10) {
		    	itemid = 259;
		    	ENDpick1 = 1;
		    	itemname = "HEHE FIRE!!!!!!";
		    }
		    if (randomNumberpick == 11) {
		    	itemid = 287;
		    	ENDpick1 = 1;
		    	itemname = "If you find yourself in a labyrinth...";
		    }
		    if (randomNumberpick == 12) {
		    	itemid = 45;
		    	ENDpick1 = 1;
		    	itemname = "Build a wall";
		    }
		    if (randomNumberpick == 13) {
		    	itemid = 392;
		    	ENDpick1 = 1;
		    	itemname = "Chad_Bonogees";
		    }
		    if (randomNumberpick == 14) {
		    	itemid = 58;
		    	ENDpick1 = 4;
		    	itemname = "Craft some items for me!";
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
		    p.getInventory().addItem(new ItemStack[] { itemtogive });
		    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&lYOU GOT:") + " " + ChatColor.GREEN + amount + " " + ChatColor.translateAlternateColorCodes('&', itemname));
	}
	
	public static String getDurationBreakdown(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("You can use it now!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }
}
