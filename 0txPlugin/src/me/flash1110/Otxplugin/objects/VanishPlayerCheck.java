package me.flash1110.Otxplugin.objects;

import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;
import org.kitteh.vanish.staticaccess.VanishNotLoadedException;

@SuppressWarnings("deprecation")
public class VanishPlayerCheck {
		
		public static Boolean getVanished(Player player) {
			
			try {
				if (VanishNoPacket.isVanished(player.getName()))
					return true;
			} catch (VanishNotLoadedException e) { }
			
			return false;
			
		}
		
		public static void ToggleVanish(Player player) {
			try {
				VanishNoPacket.toggleVanishSilent(player);
			} catch (VanishNotLoadedException e) { }
		}

}
