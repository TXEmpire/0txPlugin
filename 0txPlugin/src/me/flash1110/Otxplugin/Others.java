package me.flash1110.Otxplugin;

import org.bukkit.ChatColor;

public class Others {
	
	public static String stripColour(String msg) {
		
		String msg2 = new String(msg);
		
		for (ChatColor cc : ChatColor.values())
			msg2 = msg2.replace("" + cc, "");
		
		return msg2;
	}

}
