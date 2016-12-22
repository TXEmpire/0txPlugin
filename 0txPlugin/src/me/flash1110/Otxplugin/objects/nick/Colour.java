package me.flash1110.Otxplugin.objects.nick;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Colour {
	
	BLACK(1, Material.WOOL, 15),
	DARK_BLUE(2, Material.WOOL, 11),
	DARK_GREEN(3, Material.WOOL, 13),
	DARK_AQUA(4, Material.WOOL, 9),
	DARK_RED(5, Material.REDSTONE_BLOCK, 0),
	DARK_PURPLE(6, Material.WOOL, 10),
	GOLD(7, Material.GOLD_BLOCK, 0),
	GRAY(10, Material.WOOL, 8),
	DARK_GRAY(11, Material.WOOL, 7),
	BLUE(12, Material.WOOL, 3),
	GREEN(13, Material.WOOL, 5),
	AQUA(14, Material.DIAMOND_BLOCK, 0),
	RED(15, Material.STAINED_CLAY, 14),
	LIGHT_PURPLE(16, Material.WOOL, 2),
	YELLOW(21, Material.WOOL, 4),
	WHITE(23, Material.WOOL, 0);
	
	private Integer Slot, BlockSub;
	private Material Type;
	
	Colour(Integer slot, Material type, Integer subid) {
		
		Slot = slot;
		Type = type;
		BlockSub = subid;
		
	}
	
	public String getName() {
		return WordUtils.capitalizeFully(name().toLowerCase().replace("_", " "));
	}
	
	public Integer getSlot() {
		return Slot;
	}
	
	public ChatColor getColour() {
		return ChatColor.valueOf(name());
	}
	
	public Material getBlockType() {
		return Type;
	}
	
	public Short getBlockSubID() {
		return BlockSub.shortValue();
	}
	
	public static Colour getFromSlot(Integer slot) {
		
		if (slot == null)
			return null;
		
		for (Colour colour : values())
			if (colour.getSlot() == slot)
				return colour;
		
		return null;
		
	}

}
